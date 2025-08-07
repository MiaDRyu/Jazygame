package mx.edu.utch.proyectouiv;

import database.DatabaseSetup;
import database.ExpresionesControl;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;

public class App extends Application {
    @Override
    public void start(Stage stage) throws IOException {

        DatabaseSetup.initDatabase();
        ExpresionesControl.insertarExpresionesIniciales();

        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("seleccionPerfil.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 295, 400);
        stage.setTitle("Inicio");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}