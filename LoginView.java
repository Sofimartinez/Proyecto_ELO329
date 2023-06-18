import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.util.ArrayList;

public class LoginView extends Group {
    private ArrayList<Profesor> profesores;
    private ArrayList<Curso> cursos;
    private ArrayList<Apoderado> apoderados;
    public LoginView(ArrayList<Profesor> profesores, ArrayList<Curso> cursos, ArrayList<Apoderado> apoderados){
        this.profesores = profesores;
        this.cursos = cursos;
        this.apoderados = apoderados;
        makeLoginView();
    }
    private void makeLoginView(){
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label headerLabel = new Label("Bienvenido");
        headerLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        grid.add(headerLabel, 0,0,2,1);
        GridPane.setHalignment(headerLabel, HPos.CENTER);
        GridPane.setMargin(headerLabel, new Insets(20, 0,20,0));

        // Add Name Label
        VBox vBox = new VBox();
        Label rutLabel = new Label("Rut profesor");
        vBox.getChildren().addAll(rutLabel);

        // Add Name Text Field
        TextField rutField = new TextField();
        vBox.getChildren().addAll(rutField);
        grid.add(vBox,1,1);
        Button submitButton = new Button("Ingresar");
        submitButton.setAlignment(Pos.CENTER);
        grid.add(submitButton, 0, 4, 2, 1);
        GridPane.setHalignment(submitButton, HPos.CENTER);
        GridPane.setMargin(submitButton, new Insets(20, 0,20,0));

        submitButton.setOnAction(e -> {
            String rut = rutField.getText();
            boolean encontrado = false;
            for(int i=0; i < profesores.size(); i++){
                if(profesores.get(i).getRut().equals(rut)){
                    encontrado = true;
                    Stage window = (Stage) submitButton.getScene().getWindow();
                    HBox hBox = new HBox();
                    hBox.setAlignment(Pos.CENTER);
                    ProfesorView profesorView = new ProfesorView(profesores.get(i), cursos, apoderados);
                    hBox.getChildren().addAll(profesorView);
                    window.setScene(new Scene(hBox, 650, 650));
                }
            }
            if(!encontrado){
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Error");
                alert.setContentText("Rut no encontrado");
                alert.showAndWait();
            }
        });
        getChildren().addAll(grid);
    }
}
