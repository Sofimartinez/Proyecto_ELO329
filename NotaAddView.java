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
/**
 * @throws Clase que modela la vista con el formulario para poder añadir notas a las alumnos.
 * @author Sofía Martínez
 */
public class NotaAddView extends Group {
    /**
     *   Lista de Objetos Alumno corresponde a los alumnos a los que se les añadirá la nota.
     */
    private ArrayList<Alumno> alumnos;
    /**
     *   Objeto Evaluacion corresponde a la evaluación a la que se añadiran notas.
     */
    private Evaluacion evaluacion;
    /**
     *   Objeto Asignatura corresponde a la información de la asignatura que mostrará la vista.
     */
    private Asignatura asignatura;
    /**
     *   Objeto Profesor corresponde al profesor que dicta la asignatura.
     */
    private Profesor profesor;
    /**
     *  Objeto TableView de Evaluacion que permite visualizar la tabla de alumnos y añadir la nota.
     */
    private TableView<Evaluacion> tableEvalView;
    /**
     * Constructor de NotaAddView, inicializa una nueva vista para añadir notas.
     * @param a Lista de Objetos Alumnos que corresponde a los alumnos que rendienron la evalaución.
     * @param e Objeto Evaluacion corresponde a la asignatura a la cual se añadiran notas.
     * @param p Lista de Objetos Profesor que es el profesor que dicta la asignatura.
     * @param asig Objeto Asignatura corresponde a la información de la asignatura que mostrará la vista.
     * @param table Objeto TableView de Evaluacion que permite visualizar la tabla de alumnos y añadir la nota.
     */
    public NotaAddView(ArrayList<Alumno> a, Evaluacion e, Profesor p, Asignatura asig, TableView<Evaluacion> table){
        alumnos = a;
        evaluacion = e;
        profesor = p;
        asignatura = asig;
        tableEvalView = table;
        makeNotaAddView();
    }
    /**
     * Genera los elementos gráficos de la vista de formulario para ingresar notas a una evaluación.
     */
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

            //Verificación formato notas
            ArrayList<String> listError= new ArrayList<String>();
            boolean error = false;
            for(int i =0; i<alumnos.size();i++) {
                try {
                    if(!alumnos.get(i).getNotaField().getText().equals("")){
                        evaluacion.addNota(Float.valueOf(alumnos.get(i).getNotaField().getText()), alumnos.get(i).getNumLista() - 1);
                    }else{
                        evaluacion.addNota(Float.valueOf(0),alumnos.get(i).getNumLista()-1);
                    }
                }catch (NumberFormatException nfe){
                    error = true;
                    alumnos.get(i).getNotaField().clear();
                }
            }
            tableEvalView.refresh();

            if(error) {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Algunos datos son incorrectos. Se enviará un correo a los apoderados donde el dato ingresado fue correcto");
                alert.showAndWait();
            }else{
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText(null);
                alert.setTitle("Información");
                alert.setContentText("Se enviará un correo a los apoderados para informar de los resultados de la evaluación");
                alert.showAndWait();
            }
            //Envio de correos
            java.awt.EventQueue.invokeLater(new Runnable() {
                public void run() {
                    ArrayList<String> listError= new ArrayList<String>();
                    for(int i =0; i<alumnos.size();i++) {
                        if(!alumnos.get(i).getNotaField().getText().equals("")){
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
                        }
                    }
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
