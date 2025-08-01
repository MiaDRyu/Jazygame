package Controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Partida;
import model.PartidaSesion;
import model.Perfil;
import model.PerfilSesion;
import mx.edu.utch.proyectouiv.App;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class InicioController {

    @FXML
    private Button btnCp;

    @FXML
    private Button btnInst;

    @FXML
    private Button btnLdb;

    @FXML
    private Button btnNG;

    @FXML
    private Button btnLoad;

    @FXML
    private Label lblProf;

    @FXML
    void clickCp(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("seleccionPerfil.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnNG.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Seleccionar un Perfil");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickInst(ActionEvent event) {
        instrucciones();
    }

    @FXML
    void clickLdb(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("leaderboard.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnLdb.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Leaderboard");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickNG(ActionEvent event) {
        nuevoJuego();
    }

    @FXML
    void clickLoad(ActionEvent event) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("cargarPartida.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnLoad.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Cargar Partida");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void initialize() {
        Perfil perfil = PerfilSesion.getPerfilActual();
        if (perfil != null) {
            lblProf.setText("Perfil: " + perfil.getNombre());
        } else {
            lblProf.setText("Perfil: No seleccionado");
        }
    }

    public void nuevoJuego(){
        try {

            Partida nuevaPartida = new Partida();

            nuevaPartida.setId(0);
            nuevaPartida.setEtapa(0);
            nuevaPartida.setPuntaje(0);
            nuevaPartida.setPerfil(PerfilSesion.getPerfilActual());

            PartidaSesion.setPartidaActual(nuevaPartida);

            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("partida.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnNG.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Nueva Partida");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void instrucciones(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("instrucciones.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnNG.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Nueva Partida");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void leaderboard(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("partida.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnInst.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Instrucciones");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cambiarPerfil(){
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("partida.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) btnNG.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Nueva Partida");
            stage.show();
            stage.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
