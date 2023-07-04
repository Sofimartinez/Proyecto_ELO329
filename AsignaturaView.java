import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * @throws Clase que modela la vista con la información de la asignatura, controla el añadir nuevas evaluaciones y añadir notas.
 * @author Sofía Martínez
 */
public class AsignaturaView extends Group {
    /**
     *   Objeto Asignatura corresponde a la información de la asignatura que mostrará la vista.
     */
    private Asignatura asignatura;
    /**
     *   Objeto curso que representa la información relacionada a los alumnos.
     */
    private Curso curso;
    /**
     *   Lista conformada por Objeto Apoderado Lista de Objeto Apoderado son todos los apoderados del colegio.
     */
    private ArrayList<Apoderado> apoderados;
    /**
     *   Objeto Profesor corresponde al profesor que dicta la asignatura.
     */
    private Profesor profesor;
    /**
     *   Lista conformada por Objeto Curso que correspodiente a los cursos del profesor.
     */
    private ArrayList<Curso> cursos;
    /**
     * Constructor de AsignaturaView, inicializa un nuevo vista de asignatura.
     * @param asignatura Objeto Asignatura corresponde a la información de la asignatura que mostrará la vista.
     * @param curso Objeto curso que representa la información relacionada a los alumnos.
     * @param apoderados Lista de Objeto Apoderado son todos los apoderados del colegio.
     * @param profesor Objeto Profesor corresponde al profesor que dicta la asignatura.
     * @param cursos Lista de Objetos Curso que corresponde a los cursos del profesor.
     */
    public AsignaturaView(Asignatura asignatura, Curso curso, ArrayList<Apoderado> apoderados, Profesor profesor, ArrayList<Curso> cursos){
        this.asignatura = asignatura;
        this.curso = curso;
        this.apoderados = apoderados;
        this.profesor = profesor;
        this.cursos = cursos;
        makeAsignaturaView();
    }
    /**
     * Genera los elementos gráficos de la vista de asignaturas y controla las acciones de añadir evalaución y notas.
     */
    public void makeAsignaturaView(){
        VBox vBox = new VBox();
        vBox.setSpacing(5);
        vBox.setAlignment(Pos.CENTER);
        vBox.setMinWidth(650);

        ScrollPane scroll = new ScrollPane();
        scroll.setContent(vBox);
        scroll.setPrefSize(650, 650);

        MenuBar menuBar = new MenuBar();
        Menu menuSalir = new Menu("Salir");
        Menu menuVolver = new Menu("Volver");
        menuBar.getMenus().addAll(menuSalir, menuVolver);
        MenuItem menuLogOut = new MenuItem("Cerrar sesión");
        MenuItem menuAsig = new MenuItem("Asignaturas");
        menuSalir.getItems().addAll(menuLogOut);
        menuVolver.getItems().addAll(menuAsig);

        vBox.getChildren().addAll(menuBar);

        menuLogOut.setOnAction(e ->{
            Stage window = (Stage) menuBar.getScene().getWindow();
            window.close();
        });
        menuAsig.setOnAction(e ->{
            Stage window = (Stage) menuBar.getScene().getWindow();
            ProfesorView profesorView = new ProfesorView(profesor, cursos, apoderados);
            window.setScene(new Scene(profesorView, 650, 650));
        });

        Label asigLabel = new Label("(" + asignatura.getSigla() + ") " + asignatura.getNombre() + " " + asignatura.getAnio());
        asigLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        vBox.getChildren().addAll(asigLabel);

        Label alumnoLabel = new Label("Lista de Alumnos");
        alumnoLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));
        vBox.getChildren().addAll(alumnoLabel);

        //Tabla alumnos
        TableView<Alumno> tableAlumView = new TableView<Alumno>();

        TableColumn colNombre1 = new TableColumn("Primer Nombre");
        TableColumn colNombre2 = new TableColumn("Segundo Nombre");
        TableColumn colApellidoP = new TableColumn("Apellido Paterno");
        TableColumn colApellidoM = new TableColumn("Apellido Materno");
        colNombre1.setMinWidth(162);
        colNombre2.setMinWidth(162);
        colApellidoP.setMinWidth(162);
        colApellidoM.setMinWidth(162);

        tableAlumView.getColumns().addAll(colNombre1,colNombre2,colApellidoP,colApellidoM);

        colNombre1.setCellValueFactory(new PropertyValueFactory<Alumno,LocalDate>("nombre1"));
        colNombre2.setCellValueFactory(new PropertyValueFactory<Alumno,String>("nombre2"));
        colApellidoP.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("apellidoPaterno"));
        colApellidoM.setCellValueFactory(new PropertyValueFactory<Alumno, Integer>("apellidoMaterno"));

        ObservableList<Alumno> alumnos = FXCollections.observableArrayList(new Alumno[]{});
        alumnos.addAll(curso.getAlumnos());
        tableAlumView.setItems(alumnos);

        vBox.getChildren().addAll(tableAlumView);
        HBox hBox = new HBox();
        hBox.setSpacing(360);

        Label evalLabel = new Label("Evaluaciones programadas");
        evalLabel.setPadding(new Insets(0,0,0,10));
        evalLabel.setFont(Font.font("Arial", FontWeight.BOLD, 15));

        Button addEval = new Button(" Agregar");

        hBox.getChildren().addAll(evalLabel, addEval);
        vBox.getChildren().addAll(hBox);

        //Tabla evaluaciones
        TableView<Evaluacion> tableEvalView = new TableView<Evaluacion>();
        tableEvalView.setEditable(true);

        TableColumn colFecha = new TableColumn("Fecha");
        TableColumn colTitulo = new TableColumn("Titulo");
        TableColumn colPonderacion = new TableColumn("Ponderación");
        TableColumn colPromedio = new TableColumn("Promedio");
        TableColumn colAction = new TableColumn("Acción");
        colFecha.setMinWidth(130);
        colTitulo.setMinWidth(130);
        colPonderacion.setMinWidth(130);
        colPromedio.setMinWidth(130);
        colAction.setMinWidth(130);

        tableEvalView.getColumns().addAll(colFecha,colTitulo,colPonderacion, colPromedio, colAction);
        colFecha.setCellValueFactory(new PropertyValueFactory<Evaluacion,LocalDate>("fecha"));
        colTitulo.setCellValueFactory(new PropertyValueFactory<Evaluacion,String>("titulo"));
        colPonderacion.setCellValueFactory(new PropertyValueFactory<Evaluacion, Integer>("ponderacion"));
        colPromedio.setCellValueFactory(new PropertyValueFactory<Evaluacion, Float>("promedio"));
        colAction.setCellValueFactory(new PropertyValueFactory<Evaluacion, String>("addNota"));

//        ObservableList<Evaluacion> evals = FXCollections.observableArrayList(new Evaluacion[]{
//                new Evaluacion(LocalDate.now(),"descripcion1", "titulo1",20, curso.getAlumnos(), profesor,asignatura),
//                new Evaluacion(LocalDate.now(),"descripcion2", "titulo2",25, curso.getAlumnos(),profesor,asignatura),
//                 new Evaluacion(LocalDate.now(),"descripcion3", "titulo3",15, curso.getAlumnos(),profesor,asignatura),
//                new Evaluacion(LocalDate.now(),"descripcion1", "titulo1",20, curso.getAlumnos(),profesor,asignatura),
//                new Evaluacion(LocalDate.now(),"descripcion1", "titulo1",20, curso.getAlumnos(),profesor,asignatura),
//                new Evaluacion(LocalDate.now(),"descripcion1", "titulo1",20, curso.getAlumnos(),profesor,asignatura),
//
//        });
        ObservableList<Evaluacion> evals = FXCollections.observableArrayList(new Evaluacion[]{});
        evals.addAll(asignatura.getEvaluaciones());
        for(int i=0; i<evals.size(); i++){
            evals.get(i).setTableEvalView(tableEvalView);
        }
        tableEvalView.setItems(evals);

        addEval.setOnAction(e -> {
            Stage stageAdd = new Stage();
            EvalAddView evalAddView = new EvalAddView(asignatura.getEvaluaciones(), apoderados, asignatura, curso, profesor);

            HBox hBo = new HBox();
            hBo.setAlignment(Pos.CENTER);
            hBo.getChildren().addAll(evalAddView);
            stageAdd.setScene(new Scene(hBo, 350, 500));
            stageAdd.initModality(Modality.WINDOW_MODAL);
            stageAdd.showAndWait();

            Evaluacion eval = evalAddView.getEval();
            if (eval != null) {
                asignatura.addEvaluacion(eval);
                evals.add(eval);
                eval.setTableEvalView(tableEvalView);
                tableEvalView.refresh();
            }
        });

        vBox.getChildren().addAll(tableEvalView);
        getChildren().addAll(vBox,scroll);
    }
}


