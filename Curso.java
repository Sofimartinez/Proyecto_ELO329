import java.util.ArrayList;

public class Curso {
    private String grado;
    private int numAlumnos;
    private ArrayList<Asignatura> asignaturas;
    private ArrayList<Alumno> alumnos;
    public Curso(String g, int numAlumnos){
        grado = g;
        this.numAlumnos = numAlumnos;
        asignaturas = new ArrayList<Asignatura>();
        alumnos = new ArrayList<Alumno>();
    }
    public String getGrado(){
        return grado;
    }
    public ArrayList<Alumno> getAlumnos(){
        return alumnos;
    }
    public void addAsignatura(Asignatura asignatura){
        asignaturas.add(asignatura);
    }
    public void addAlumno(Alumno alumno){
        alumnos.add(alumno);
    }
}
