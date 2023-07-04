import java.util.ArrayList;
/**
 * @throws Clase curso que modelo que conforma un curso como las asignaturas, alumnos y el grado.
 * @author Sofía Martínez
 */
public class Curso {
    // Campos privados
    /**
     *  Nombre del grado con texto.
     */
    private String grado;
    /**
     *  Cantidad de alumnos.
     */
    private int numAlumnos;
    /**
     *  Lista de asignaturas que conforman el curso.
     */
    private ArrayList<Asignatura> asignaturas;
    /**
     * Lista de Alumnos que forman parte del curso.
     */
    private ArrayList<Alumno> alumnos;
    /**
     * Constructor de Curso, inicializa un nuevo curso.
     * @param g nombre del curso como un String.
     * @param numAlumnos cantidad de alumnos en el curso.
     */
    public Curso(String g, int numAlumnos){
        grado = g;
        this.numAlumnos = numAlumnos;
        asignaturas = new ArrayList<Asignatura>();
        alumnos = new ArrayList<Alumno>();
    }
    /**
     * Recupera el grado del curso.
     * @return  el <tt>grado</tt>
     */
    public String getGrado(){
        return grado;
    }
    /**
     * Recupera la lista de alumnos.
     * @return  los <tt>alumnos</tt>
     */
    public ArrayList<Alumno> getAlumnos(){
        return alumnos;
    }
    /**
     * Añade un Objeto Asignatura a la lista de asignaturas del curso.
     */
    public void addAsignatura(Asignatura asignatura){
        asignaturas.add(asignatura);
    }
    /**
     * Añade un Objeto Alumnos a la lista de alumnos del curso.
     */
    public void addAlumno(Alumno alumno){
        alumnos.add(alumno);
    }
}
