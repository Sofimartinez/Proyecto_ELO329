import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;
/**
 * @throws Clase que modela la vista con la información de las asignturas del profesor, el cambio de vista a una asignatura particular.
 * @author Sofía Martínez
 */
public class ProfesorView extends Group{
    /**
     *   Objeto Profesor.
     */
    private Profesor profesor;
    /**
     *   Lista de cursos que se dictan el colegio
     */
    private ArrayList<Curso> cursos;
    /**
     *   Lista de apoderados del colegio
     */
    private ArrayList<Apoderado> apoderados;
    /**
     * Constructor de ProfesorView, inicializa un nueva vista de profesor.
     * @param profesor Objeto Profesor profesor que inicia sesión.
     * @param curso Lista de Objeto Curso que representa la información relacionada a los alumnos.
     * @param apoderados Lista de Objeto Apoderado son todos los apoderados del colegio.
     */
    public ProfesorView(Profesor profesor, ArrayList<Curso> curso, ArrayList<Apoderado> apoderados){
        this.profesor = profesor;
        this.cursos = curso;
        this.apoderados = apoderados;
        makeProfesorView();
    }
    /**
     * Genera los elementos gráficos de la vista de todas las asignatura del profesor y controla las acciones de seleccionar una asignaturada.
     */
    private void makeProfesorView(){
        BorderPane borderPane = new BorderPane();
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(650);
        vBox.setMinHeight(600);
        vBox.setPadding(new Insets(10, 0, 10, 0));

        MenuBar menuBar = new MenuBar();
        Menu menuSalir = new Menu("Salir");
        menuBar.getMenus().addAll(menuSalir);
        MenuItem menuLogOut = new MenuItem("Cerrar sesión");
        menuSalir.getItems().addAll(menuLogOut);

        BorderPane.setAlignment(menuBar, Pos.TOP_LEFT);
        borderPane.setTop(menuBar);

        menuLogOut.setOnAction(e ->{
            Stage window = (Stage) menuBar.getScene().getWindow();
            window.close();
        });

        Label headerLabel = new Label("Asignaturas");
        headerLabel.setPadding(new Insets(12,12,12,12));
        vBox.getChildren().addAll(headerLabel);
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));

        ArrayList<Asignatura> asig = profesor.getAsignaturas();

        for(int i=0; i<asig.size(); i++){
            Button asigButton = new Button(asig.get(i).getSigla() + " - " + asig.get(i).getNombre());
            int finalIndex = i;
            asigButton.setOnAction(e -> {
                for(int j=0; j<cursos.size();j++){
                    if(asig.get(finalIndex).getCurso().equals(cursos.get(j).getGrado()));
                    Curso curso = cursos.get(j);
                    Stage window = (Stage) asigButton.getScene().getWindow();

                    ScrollPane scrollPane = new ScrollPane();
                    AsignaturaView asigView = new AsignaturaView(asig.get(finalIndex),curso, apoderados, profesor,cursos);
                    scrollPane.setContent(asigView);
                    scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
                    window.setScene(new Scene(asigView, 650, 650));
                    break;
                }

            });
            vBox.getChildren().addAll(asigButton);

        }
        BorderPane.setAlignment(vBox, Pos.CENTER);
        BorderPane.setMargin(vBox, new Insets(12,12,12,12));
        borderPane.setCenter(vBox);
        getChildren().addAll(borderPane);
    }
}
