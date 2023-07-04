import javafx.scene.control.TextField;
/**
 * @throws Clase que modela a una persona con las características de un alumno, en el que se asigna el apoderado, su número de lista y el curso al que pertenece .
 * @author Sofía Martínez
 */
public class Alumno extends Persona{
    /**
     *   Objeto Curso correspodiente al curso en el que estudia el alumno.
     */
    private Curso curso;
    /**
     * Entero que representa el número de la lista.
     */
    private int numLista;
    /**
     * Objeto Apoderado que representa al apoderado.
     */
    private Apoderado apoderado;
    /**
     * Objeto TextField correspondiente a un campo de texto para ingresar las notas.
     */
    private TextField notaField;
    /**
     * Constructor de Alumno, inicializa un nuevo alumno.
     * @param n1 String del primer nombre del alumno.
     * @param n2 String del segundo nombre del alumno.
     * @param ap String del apellido paterno del alumno.
     * @param am String del apellido materno del alumno.
     * @param r String RUT del alumno.
     * @param c Objeto Curso que correponde al curso en el que esta estudiando el alumno.
     */
    public Alumno(String n1, String n2, String ap, String am, String r, Curso c) {
        super(n1, n2, ap, am, r);
        curso = c;
        notaField = new TextField();
    }
    /**
     * Establece un valor para el atributo del número de lista del alumno.
     */
    public void setNumLista(int numLista) {
        this.numLista = numLista;
    }
    /**
     * Recupera el número de lista.
     * @return el <tt>número de lista</tt> del alumno.
     */
    public int getNumLista(){
        return numLista;
    }
    /**
     * Establece un valor del Objeto TextField notaField con la nota ingresada.
     */
    public void setNotaField(TextField nota){
        notaField = nota;
    }
    /**
     * Recupera el valor almacenado en el Objeto TextField notaField.
     * @return el contenido del <tt>campo de texto</tt>del alumno.
     */
    public TextField getNotaField(){
        return notaField;
    }
    /**
     * Recupera el valor almacenado en el Objeto Apoderado.
     * @return el <tt>apoderado</tt>del alumno.
     */
    public Apoderado getApoderado(){
        return apoderado;
    }
    /**
     * Establece un valor para el atributo nota proveniente desde el Objeto TextField.
     */
    public void setNota(Float nota){
        if(nota != 0){
            notaField.setText(String.valueOf(nota));
        }
    }
    /**
     * Establece un valor para el Objeto atributo apoderado.
     */
    public void setApoderado(Apoderado apoderado){
        this.apoderado = apoderado;
    }
}
