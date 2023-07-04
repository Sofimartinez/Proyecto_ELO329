import java.util.ArrayList;
/**
 * @throws Clase que modela a una persona con las características de un apoderado, en el que se asignan los pupilos y correo.
 * @author Sofía Martínez
 */
public class Apoderado extends Persona{
    /**
     *   Lista conformada por Objeto Alumno que correspodiente a los pupilos del apoderado.
     */
    private ArrayList<Alumno> pupilos;
    /**
     *   String que corresponde al correo electrónico del apoderado.
     */
    private String correo;
    /**
     * Constructor de Apoderado, inicializa un nuevo apoderado.
     * @param n1 String del primer nombre del apoderado.
     * @param n2 String del segundo nombre del apoderado.
     * @param ap String del apellido paterno del apoderado.
     * @param am String del apellido materno del apoderado.
     * @param r String RUT del apoderado.
     * @param c String que correponde al correo del apoderado.
     */
    public Apoderado(String n1, String n2, String ap, String am, String r, String c) {
        super(n1, n2, ap, am, r);
        pupilos = new ArrayList<Alumno>();
        correo = c;
    }
    /**
     * Recupera el correo electrónico del apoderado.
     * @return el <tt>correo</tt>del alumno.
     */
    public String getCorreo(){
        return correo;
    }
    /**
     * Añade un Objeto Alumno a la lista de pupilos del apoderado.
     */
    public void addPupilo(Alumno pupilo){
        pupilos.add(pupilo);
    }
}
