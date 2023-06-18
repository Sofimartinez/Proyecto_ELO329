import java.util.ArrayList;

public class Apoderado extends Persona{
    private ArrayList<Alumno> pupilos;
    private String correo;
    public Apoderado(String n1, String n2, String ap, String am, String r, String c) {
        super(n1, n2, ap, am, r);
        pupilos = new ArrayList<Alumno>();
        correo = c;
    }
    public String getCorreo(){
        return correo;
    }
    public void addPupilo(Alumno pupilo){
        pupilos.add(pupilo);
    }
}
