/**
 * @throws Clase que modela a una persona contiene los datos comunes de una persona como sus nombre, apellidos y RUT.
 * @author Sofía Martínez
 */
public class Persona {
    /**
     * Primer nombre.
     */
    private String nombre1;
    /**
     * Segundo nombre.
     */
    private String nombre2;
    /**
     * Apellido paterno.
     */
    private String apellidoPaterno;
    /**
     * Apellido materno.
     */
    private String apellidoMaterno;
    /**
     * Rut de la persona como un String.
     */
    private String rut;
    /**
     * Constructor de Persona, inicializa una nueva persona.
     * @param n1 String del primer nombre.
     * @param n2 String del segundo nombre
     * @param ap String del apellido paterno.
     * @param am String del apellido materno.
     * @param r String RUT.
     */
    public Persona(String n1, String n2, String ap, String am, String r){
        nombre1 = n1;
        nombre2 = n2;
        apellidoPaterno = ap;
        apellidoMaterno = am;
        rut = r;
    }
    /**
     * Recupera el primer nombre de la person.
     * @return el <tt>primer nombre</tt> de la persona.
     */
    public String getNombre1() {
        return nombre1;
    }
    /**
     * Recupera el segundo nombre de la person.
     * @return el <tt>segundo nombre</tt> de la persona.
     */
    public String getNombre2() {
        return nombre2;
    }
    /**
     * Recupera el apellido paterno de la person.
     * @return el <tt>apellido paterno</tt> de la persona.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }
    /**
     * Recupera el apellido materno de la person.
     * @return el <tt>apellido materno</tt> de la persona.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }
    /**
     * Recupera el RUT de la person.
     * @return el <tt>RUT</tt> de la persona.
     */
    public String getRut() {
        return rut;
    }
}
