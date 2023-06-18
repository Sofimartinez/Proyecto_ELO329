import java.util.ArrayList;

public class Profesor extends Persona{
    private ArrayList<Asignatura> asignaturas;
    private String correo;

    public Profesor(String n1, String n2, String ap, String am, String r, String c) {
        super(n1, n2, ap, am, r);
        asignaturas = new ArrayList<Asignatura>();
        correo = c;
    }
    public void addAsignatura (Asignatura asignatura){
        asignaturas.add(asignatura);
    }

    public ArrayList<Asignatura> getAsignaturas(){
        return asignaturas;
    }
    public String getCorreo(){
        return correo;
    }
}
