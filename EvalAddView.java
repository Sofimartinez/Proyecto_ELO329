import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;


public class EvalAddView extends Group {
    private ArrayList<Evaluacion> evaluaciones;
    private ArrayList<Alumno> alumnos;
    private ArrayList<Apoderado> apoderados;
    private Asignatura asignatura;
    private Evaluacion eval;
    private Profesor profesor;
    private Curso curso;

    public EvalAddView(ArrayList<Evaluacion> evaluaciones, ArrayList<Apoderado> apoderados, Asignatura asignatura, Curso curso, Profesor profesor){
        this.evaluaciones = evaluaciones;
        this.alumnos = curso.getAlumnos();
        this.apoderados = apoderados;
        this.asignatura = asignatura;
        this.profesor = profesor;
        this.curso = curso;
        makeAEvalAddView();
    }

    private void makeAEvalAddView(){
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 0, 10));

        Label headerLabel = new Label("Agregar evaluación");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        vBox.getChildren().addAll(headerLabel);

        Label fechaLabel = new Label("Fecha");
        DatePicker fechaPicker = new DatePicker();
        Label tituloLabel = new Label("Tema");
        TextField tituloField = new TextField();
        Label descLabel = new Label("Descripción");
        TextField descField = new TextField();
        Label pondLabel = new Label("Ponderación %");
        TextField pondField = new TextField();

        vBox.getChildren().addAll(fechaLabel,fechaPicker,tituloLabel,tituloField,descLabel,descField,pondLabel,pondField);

        Button submitButton = new Button("Ingresar");
        Button cancelButton = new Button("Salir");
        submitButton.setOnAction(e -> {
                LocalDate fecha = fechaPicker.getValue();
                String titulo = tituloField.getText();
                String descripcion = descField.getText();
                int ponderacion = Integer.parseInt(pondField.getText());

                Evaluacion auxEval = new Evaluacion(fecha, descripcion, titulo, ponderacion, curso.getAlumnos(), profesor, asignatura);
                if(!evaluaciones.contains(auxEval)) {
                    eval = auxEval;
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Información");
                    alert.setContentText("Se ha añadido correctamente. Se enviará un correo a los apoderados para informar de la nueva evaluación");
                    alert.showAndWait();

                    //Envio de correo
                    java.awt.EventQueue.invokeLater(new Runnable() {
                        public void run() {
                            String newline = System.getProperty("line.separator");
                            String emailFrom = profesor.getCorreo();
                            String subject = "Nueva evaluación agendada en " + asignatura.getSigla() + " " + asignatura.getNombre();
                            String content = "Señores apoderados," + newline + newline + "Los alumnos de " + curso.getGrado() + " tienen una nueva evaluación para la asignatura de "
                                    + asignatura.getNombre() +" agendada para el día " + fecha + " correspondiente a "+ titulo + ", detallada a continuación:" + newline
                                    + descripcion + newline + newline + "Saludos," + newline + profesor.getNombre1() + " " + profesor.getApellidoPaterno() + " " + profesor.getApellidoMaterno() + ".";
                            String correos = "";
                            for(int i=0; i< alumnos.size(); i++){
                                String rutAlumno = apoderados.get(i).getRut();
                                for(int j=0; j< apoderados.size(); j++){
                                    String rutApoderado = apoderados.get(j).getRut();
                                    if(rutApoderado.equals(rutAlumno)){
                                        if(correos.equals("")){
                                            correos = apoderados.get(j).getCorreo();
                                        }else{
                                            correos = correos + "," + apoderados.get(j).getCorreo();
                                        }
                                        break;
                                    }
                                }
                            }

                            if(!correos.equals("")) {
                                EnvioCorreos correo = new EnvioCorreos();
                                correo.createEmail(emailFrom,correos,content,subject);
                                correo.sendEmail();
                            }
                        }
                    });

                    Stage stage = (Stage) submitButton.getScene().getWindow();
                    stage.close();
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Error");
                    alert.setContentText("La evaluación ya esta registrada");
                    alert.showAndWait();
                }
        });

        cancelButton.setOnAction(e -> {
            eval = null;
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            stage.close();
        });
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(submitButton,cancelButton);
        vBox.getChildren().addAll(hBox);

        getChildren().addAll(vBox);
    }

    public Evaluacion getEval(){
        return eval;
    }

}
