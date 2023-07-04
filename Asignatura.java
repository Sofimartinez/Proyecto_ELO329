import java.util.ArrayList;
/**
 * @throws Clase que modela a una asignatura que consta con los métodos para añadir una nueva evaluación y calcular el promedio de notas.
 * @author Sofía Martínez
 */
public class Asignatura {
    /**
     *   String que corresponde al nombre natural de la asignatura.
     */
    private  String nombre;
    /**
     *   String correspondiente a la sigla la asignatura.
     */
    private String sigla;
    /**
     *   Entero que representa el año en el que dicta actualmente la asignatura.
     */
    private int anio;
    /**
     *   String que corresponde al curso al cual pertenece la asignatura.
     */
    private String curso;
    /**
     *   Lista conformada por Objeto Evaluacion que correspodiente a las evaluaciones de la asignatura.
     */
    private ArrayList<Evaluacion> evaluaciones;
    /**
     * Constructor de Asignatura, inicializa una nueva asignatura.
     * @param n String con el nombre natural de la asignatura.
     * @param s String que indica la abreviación con siglas del nombre de la asignatura.
     * @param a Entero del año en el cual se dicta la asignatura.
     * @param c String que correponde al nombre del curso al cual pertenece la aaignatura.
     */
    public Asignatura(String n, String s, int a, String c){
        nombre = n;
        sigla = s;
        anio = a;
        curso = c;
        evaluaciones = new ArrayList<Evaluacion>();
    }
    /**
     * Recupera la sigla de la asignatura.
     * @return la <tt>sigla</tt> de la asignatura.
     */
    public String getSigla() {
        return sigla;
    }
    /**
     * Recupera el nombre de la asignatura.
     * @return el <tt>nombre</tt> de la asignatura.
     */
    public String getNombre(){
        return nombre;
    }
    /**
     * Recupera el año en el que se dicta la asignatura.
     * @return el <tt>año</tt> de la asignatura.
     */
    public int getAnio() {
        return anio;
    }
    /**
     * Recupera el curso en el que se dicta la asignatura.
     * @return el <tt>curso</tt> de la asignatura.
     */
    public String getCurso(){
        return curso;
    }
    /**
     * Añade un Objeto Evaluacion a la lista de evaluaciones del curso.
     */
    public void addEvaluacion(Evaluacion evaluacion){
        evaluaciones.add(evaluacion);
    }
    /**
     * Recupera la lista de evaluaciones de la asignatura.
     * @return las <tt>evaluaciones</tt> de la asignatura.
     */
    public ArrayList<Evaluacion> getEvaluaciones(){
        return evaluaciones;
    }
}
