import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.time.LocalDate;
import java.util.ArrayList;

public class NotaAddView extends Group {
    private ArrayList<Alumno> alumnos;
    private Evaluacion evaluacion;
    private Asignatura asignatura;
    private Profesor profesor;
    private TableView<Evaluacion> tableEvalView;
    public NotaAddView(ArrayList<Alumno> a, Evaluacion e, Profesor p, Asignatura asig, TableView<Evaluacion> table){
        alumnos = a;
        evaluacion = e;
        profesor = p;
        asignatura = asig;
        tableEvalView = table;
        makeNotaAddView();
    }

    private void makeNotaAddView(){
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setPadding(new Insets(10, 0, 0, 10));

        Label headerLabel = new Label("Ingresar Notas evaluacion: " + evaluacion.getTitulo());
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        vBox.getChildren().addAll(headerLabel);

        Label titleTable = new Label("Lista de alumnos");
        titleTable.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        vBox.getChildren().addAll(titleTable);

        //Tabla alumnos
        TableView<Alumno> tableAlumView = new TableView<Alumno>();

        TableColumn colNombre1 = new TableColumn("Primer Nombre");
        TableColumn colNombre2 = new TableColumn("Segundo Nombre");
        TableColumn colApellidoP = new TableColumn("Apellido Paterno");
        TableColumn colApellidoM = new TableColumn("Apellido Materno");
        TableColumn colNota = new TableColumn("Nota");
        colNombre1.setMinWidth(150);
        colNombre2.setMinWidth(150);
        colApellidoP.setMinWidth(150);
        colApellidoM.setMinWidth(150);
        colNota.setMinWidth(150);

        tableAlumView.getColumns().addAll(colNombre1,colNombre2,colApellidoP,colApellidoM,colNota);

        colNombre1.setCellValueFactory(new PropertyValueFactory<Alumno, LocalDate>("nombre1"));
        colNombre2.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre2"));
        colApellidoP.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("apellidoPaterno"));
        colApellidoM.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("apellidoMaterno"));
        colNota.setCellValueFactory(new PropertyValueFactory<Alumno, String>("notaField"));

        ObservableList<Alumno> alums = FXCollections.observableArrayList(new Alumno[]{});
        alums.addAll(alumnos);
        if(evaluacion.getNotas().size() > 0) {
            for (int i = 0; i < alumnos.size(); i++) {
                alumnos.get(i).setNota(evaluacion.getNota((alumnos.get(i).getNumLista()-1)));
            }
        }
        tableAlumView.setItems(alums);
        tableAlumView.refresh();

        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        hBox.setSpacing(5);
        Button submitButton = new Button("Guardar");
        Button cancelButton = new Button("Salir");
        hBox.getChildren().addAll(submitButton,cancelButton);

        submitButton.setOnAction(e -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Información");
            alert.setContentText("Se han añadido correctamente las notas. Se enviará un correo a los apoderados para informar de los resultados de la evaluación");
            alert.showAndWait();

            //Envio de correos
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    for(int i =0; i<alumnos.size();i++) {
                        if(!alumnos.get(i).getNotaField().getText().equals("")){
                            try{
                                evaluacion.addNota(Float.valueOf(alumnos.get(i).getNotaField().getText()), alumnos.get(i).getNumLista()-1);
                                String newline = System.getProperty("line.separator");
                                String emailFrom = profesor.getCorreo();
                                String subject = "Nueva nota ingresa  asignatura " + asignatura.getSigla() + " " + asignatura.getNombre();
                                String content = "Estimado señor/a "+ alumnos.get(i).getApoderado().getApellidoPaterno() +", " + newline + newline +
                                        "Su pupilo/a " + alumnos.get(i).getNombre1() + " " + alumnos.get(i).getApellidoPaterno() +
                                        " a sido calificado en la evaluación " + evaluacion.getTitulo() + " de la asignatura " + asignatura.getNombre() + " con un " + alumnos.get(i).getNotaField().getText()+"."+
                                        newline + newline + "Saludos," + newline + profesor.getNombre1() + " " + profesor.getApellidoPaterno() + " " + profesor.getApellidoMaterno() + ".";
                                EnvioCorreoNota correo = new EnvioCorreoNota();
                                correo.createEmail(emailFrom, alumnos.get(i).getApoderado().getCorreo(), content, subject);
                                correo.sendEmail();
                            }catch (NumberFormatException nfe){
                                System.out.println("Dato ingresado en formato no válido");
                            }
                        }else{
                            evaluacion.addNota(Float.valueOf(0),alumnos.get(i).getNumLista()-1);
                        }
                    }
                    tableEvalView.refresh();
                }
            });
        });

        cancelButton.setOnAction(e -> {
            Stage stage = (Stage) cancelButton.getScene().getWindow();
            for(int i =0; i<alumnos.size();i++){
                alumnos.get(i).getNotaField().clear();
            }
            stage.close();
            tableEvalView.refresh();
        });

        vBox.getChildren().addAll(tableAlumView, hBox);
        getChildren().addAll(vBox);
    }
}
