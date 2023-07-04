import java.util.ArrayList;
/**
 * @throws Clase que modela a una persona con las características de un profesor, en el que se asignan las asignaturas que dicta y correo electrónico.
 * @author Sofía Martínez
 */
public class Profesor extends Persona{
    /**
     *   Lista conformada por Objeto Asignatura que correspodiente a las asignaturas que el profesor dicta.
     */
    private ArrayList<Asignatura> asignaturas;
    /**
     *   String que corresponde al correo electrónico del profesor.
     */
    private String correo;
    /**
     * Constructor de Profesor, inicializa un nuevo profesor.
     * @param n1 String del primer nombre del profesor.
     * @param n2 String del segundo nombre del profesor.
     * @param ap String del apellido paterno del profesor.
     * @param am String del apellido materno del profesor.
     * @param r String RUT del profesor.
     * @param c String que correponde al correo del profesor.
     */
    public Profesor(String n1, String n2, String ap, String am, String r, String c) {
        super(n1, n2, ap, am, r);
        asignaturas = new ArrayList<Asignatura>();
        correo = c;
    }
    /**
     * Añade un Objeto Asignatura a la lista de asignaturas que el profesor dicta.
     */
    public void addAsignatura (Asignatura asignatura){
        asignaturas.add(asignatura);
    }
    /**
     * Recupera las lista de asignaturas del profesor.
     * @return las <tt>asignaturas</tt> del profesor.
     */
    public ArrayList<Asignatura> getAsignaturas(){
        return asignaturas;
    }
    /**
     * Recupera el correo electrónico del profesor.
     * @return el <tt>correo</tt> del profesor.
     */
    public String getCorreo(){
        return correo;
    }
}
