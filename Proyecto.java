import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.Scene;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Proyecto extends Application{
    private Profesor profesor;

    @Override
    public void start(Stage primaryStage) {
        List<String> args = getParameters().getRaw();
        if (args.size() != 1) {
            System.out.println("Usage: java Proyecto <configurationFile.txt>");
            System.exit(-1);
        }
        Scanner in = null;
        try {
            in = new Scanner(new File(args.get(0)));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(-1);
        }

        ArrayList<Asignatura> auxAsignaturas = new ArrayList<Asignatura>();
        ArrayList<Alumno> auxAlumnos = new ArrayList<Alumno>();
        ArrayList<Profesor> auxProfesores = new ArrayList<Profesor>();
        ArrayList<Apoderado> auxApoderados = new ArrayList<Apoderado>();
        ArrayList<Curso> auxCursos = new ArrayList<Curso>();

        Colegio colegio = new Colegio(in, auxAsignaturas, auxAlumnos, auxProfesores, auxApoderados, auxCursos);
        in.close();

        VBox vBox = new VBox();
        HBox hBox = new HBox();
        hBox.setAlignment(Pos.CENTER);
        vBox.setAlignment(Pos.CENTER);
        hBox.getChildren().addAll(vBox);
        vBox.getChildren().addAll(colegio);
        vBox.setPadding(new Insets(10, 10,10,10));

        Scene scene = new Scene(hBox,650, 650);
        primaryStage.setTitle("Proyecto");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
