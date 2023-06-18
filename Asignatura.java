import java.util.ArrayList;

public class Asignatura {
    private  String nombre;
    private String sigla;
    private float promFinal;
    private float promParcial;
    private int anio;

    private String curso;
    private ArrayList<Evaluacion> evaluaciones;
    public Asignatura(String n, String s, int a, String c){
        nombre = n;
        sigla = s;
        anio = a;
        promParcial = 0;
        promFinal = 0;
        curso = c;
        evaluaciones = new ArrayList<Evaluacion>();
    }

    public String getSigla() {
        return sigla;
    }
    public String getNombre(){
        return nombre;
    }
    public int getAnio() {
        return anio;
    }

    public String getCurso(){
        return curso;
    }
    public float getPromFinal() {
        return promFinal;
    }
    public float getPromParcial() {
        return promParcial;
    }
    public void addEvaluacion(Evaluacion evaluacion){
        evaluaciones.add(evaluacion);
    }
    public ArrayList<Evaluacion> getEvaluaciones(){
        return evaluaciones;
    }
}
