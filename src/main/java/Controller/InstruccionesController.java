package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import mx.edu.utch.proyectouiv.App;

public class InstruccionesController {

    @FXML
    private Button btnBack;

    @FXML
    void clickBack(ActionEvent event) {
        volver();
    }

    private void volver() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnBack.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
