import javafx.scene.control.TextField;
public class Alumno extends Persona{
    private Curso curso;
    private int numLista;
    private Apoderado apoderado;
    private TextField notaField;

    public Alumno(String n1, String n2, String ap, String am, String r, Curso c) {
        super(n1, n2, ap, am, r);
        curso = c;
        notaField = new TextField();
    }
    public void setNumLista(int numLista) {
        this.numLista = numLista;
    }
    public int getNumLista(){
        return numLista;
    }
    public void setNotaField(TextField nota){
        notaField = nota;
    }
    public TextField getNotaField(){
        return notaField;
    }
    public Apoderado getApoderado(){
        return apoderado;
    }
    public void setNota(Float nota){
        if(nota != 0){
            notaField.setText(String.valueOf(nota));
        }
    }
    public void setApoderado(Apoderado apoderado){
        this.apoderado = apoderado;
    }
}
