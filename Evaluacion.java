import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.time.LocalDate;
import java.util.ArrayList;
/**
 * @throws Clase que modela una evaluación con toda la información necesaria y su asociación a una asignatura.
 * @author Sofía Martínez
 */
public class Evaluacion {
    /**
     * Objeto LocalDate que indica la fecha en la que realizará la evaluación.
     */
    private LocalDate fecha;
    /**
     * String con la descripción de los contenidos de la evaluación.
     */
    private String descripcion;
    /**
     * String del título de la evaluación.
     */
    private String titulo;
    /**
     * Número decimal que corresponde al promedio de notas de los alumnos en la evaluación.
     */
    private float promedio;
    /**
     * Ponderación como un entero.
     */
    private int ponderacion;
    /**
     * Objeto Button que permite añadir las notas de los alumnos.
     */
    private Button addNota;
    /**
     * Objeto Profesor es el profesor acargo de la evaluación.
     */
    private Profesor profesor;
    /**
     * Objeto Asigntura correspondiente a la asignatura a la cual se le asigna una evaluación.
     */
    private Asignatura asignatura;
    /**
     * Lista de Objetos Alumno corresponde a la lista de los alumnos a los que se aplicará la evaluación.
     */
    private ArrayList<Alumno> alumnos;
    /**
     * Lista de Objetos Float corresponde a la lista de las notas de los alumnos en la evaluación.
     */
    private ArrayList<Float> notas;
    /**
     *  Objeto TableView de Evaluacion que permite visualizar la tabla de evaluaciones.
     */
    private TableView<Evaluacion> tableEvalView;
    /**
     * Constructor de Evaluacion, inicializa una nueva evaluación.
     * @param f Objeto LocalDate que indica la fecha en la que realizará la evaluación.
     * @param d String con la descripción de los contenidos de la evaluación.
     * @param t String del título de la evaluación.
     * @param p ponderación como un entero.
     * @param alumn Lista de Objetos Alumno corresponde a la lista de los alumnos a los que se aplicará la evaluación.
     * @param prof Objeto Profesor es el profesor acargo de la evaluación.
     * @param asig Objeto Asigntura correspondiente a la asignatura a la cual se le asigna una evaluación.
     */
    public Evaluacion(LocalDate f, String d, String t, int p, ArrayList<Alumno> alumn, Profesor prof, Asignatura asig){
        fecha = f;
        descripcion = d;
        titulo = t;
        ponderacion = p;
        alumnos = alumn;
        profesor = prof;
        asignatura = asig;
        notas = new ArrayList<Float>();
        for(int i=0; i < alumnos.size(); i++){
            notas.add(Float.valueOf(0));
        }
        promedio = 0;
        addNota = new Button("Ingresar Notas");

        addNota.setOnAction(e -> {
            Stage stageAdd = new Stage();
            NotaAddView view = new NotaAddView(alumnos,this, profesor, asignatura, tableEvalView);

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(view);
            stageAdd.setScene(new Scene(hBox, 800, 650));
            stageAdd.initModality(Modality.WINDOW_MODAL);
            stageAdd.showAndWait();
        });
    }
    /**
     * Añade una nueva nota para un alumno en particular.
     * @param nota número correspondiente a la nota de la evaluación como un número flotante
     * @param alumno número de lista del alumno.
     */
    public void addNota(Float nota, int alumno){
        notas.set(alumno,nota);
    }
    /**
     * Elimina todas la notas que se encuentren ingresadas para la evaluación.
     */
    public void deleteNotas(){
        notas.clear();
    }
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Recupera la descripción de los contenidos de la evaluación.
     * @return la <tt>descripción</tt> de la evaluación.
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Recupera el título correspondiente a la evaluación.
     * @return el <tt>título</tt> de la evaluación.
     */
    public String getTitulo() {
        return titulo;
    }
    /**
     * Recupera el valor perteniente a la ponderación de la evaluación.
     * @return la <tt>ponderación</tt> de la evaluación.
     */
    public int getPonderacion() {
        return ponderacion;
    }
    public Button getAddNota(){
        return addNota;
    }
    /**
     * Recupera el promedio de la nota de todos los alumnos.
     * @return el <tt>promedio</tt> de la evaluación.
     */
    public float getPromedio() {
        if(notas.size()>0){
            float sum = 0;
            for(int i=0; i<notas.size();i++){
                sum += Float.parseFloat(notas.get(i).toString());
            }
            promedio = (float) (Math.round((sum/ notas.size())*100.0)/100.0);
        }
        return promedio;
    }
    /**
     * Añade los Objetos Alumnos a la lista de alumnos que rinden la evaluación.
     */
    public void addAlumnos(ArrayList<Alumno> alumnos){
        this.alumnos = alumnos;
    }
    /**
     * Asigna el valor a para el Objeto TableView de evalauciones.
     */
    public void setTableEvalView(TableView<Evaluacion> table){
        tableEvalView = table;
    }
    /**
     * Recupera la nota de la evaluación para un alumno en específico.
     * @param alumno número de lista del alumno.
     * @return la <tt>nota</tt> de un alumno en particular.
     */
    public Float getNota(int alumno){
        return notas.get(alumno);
    }
    /**
     * Recupera las lista de notas de la evaluación.
     * @return las <tt>notas</tt> de la evaluación.
     */
    public ArrayList<Float> getNotas(){
        return notas;
    }

}
