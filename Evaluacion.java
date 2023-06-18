import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.layout.HBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

import javax.sound.midi.SysexMessage;
import java.time.LocalDate;
import java.util.ArrayList;

public class Evaluacion {
    private LocalDate fecha;
    private String descripcion;
    private String titulo;
    private float promedio;
    private int ponderacion;
    private Button addNota;
    private Profesor profesor;
    private Asignatura asignatura;
    private ArrayList<Alumno> alumnos;
    private ArrayList<Float> notas;
    private TableView<Evaluacion> tableEvalView;

    public Evaluacion(LocalDate f, String d, String t, int p, ArrayList<Alumno> alumn, Profesor prof, Asignatura asig){
        fecha = f;
        descripcion = d;
        titulo = t;
        ponderacion = p;
        alumnos = alumn;
        profesor = prof;
        asignatura = asig;
        notas = new ArrayList<Float>();
        for(int i=0; i < alumnos.size(); i++){
            notas.add(Float.valueOf(0));
        }
        promedio = 0;
        addNota = new Button("Ingresar Notas");

        addNota.setOnAction(e -> {
            Stage stageAdd = new Stage();
            NotaAddView view = new NotaAddView(alumnos,this, profesor, asignatura, tableEvalView);

            HBox hBox = new HBox();
            hBox.setAlignment(Pos.CENTER);
            hBox.getChildren().addAll(view);
            stageAdd.setScene(new Scene(hBox, 800, 650));
            stageAdd.initModality(Modality.WINDOW_MODAL);
            stageAdd.showAndWait();
        });
    }

    public void addNota(Float nota, int alumno){
        notas.set(alumno,nota);
    }
    public void deleteNotas(){
        notas.clear();
    }
    public LocalDate getFecha() {
        return fecha;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public String getTitulo() {
        return titulo;
    }
    public int getPonderacion() {
        return ponderacion;
    }
    public Button getAddNota(){
        return addNota;
    }
    public float getPromedio() {
        if(notas.size()>0){
            float sum = 0;
            for(int i=0; i<notas.size();i++){
                sum += Float.parseFloat(notas.get(i).toString());
            }
            promedio = (float) (Math.round((sum/ notas.size())*100.0)/100.0);
        }
        return promedio;
    }
    public void addAlumnos(ArrayList<Alumno> alumnos){
        this.alumnos = alumnos;
    }
    public void setTableEvalView(TableView<Evaluacion> table){
        tableEvalView = table;
    }
    public Float getNota(int alumno){
        return notas.get(alumno);
    }
    public ArrayList<Float> getNotas(){
        return notas;
    }

}
