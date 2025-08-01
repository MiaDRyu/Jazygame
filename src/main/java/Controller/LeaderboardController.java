package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Perfil;
import mx.edu.utch.proyectouiv.App;
import database.SQLiteConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class LeaderboardController {

    @FXML
    private TableView<Perfil> tblLeaderboard;

    @FXML
    private TableColumn<Perfil, String> colNombre;

    @FXML
    private TableColumn<Perfil, Integer> colHighscore;

    @FXML
    public void initialize() {
        colNombre.setCellValueFactory(cellData -> new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNombre()));
        colHighscore.setCellValueFactory(cellData -> new javafx.beans.property.SimpleIntegerProperty(cellData.getValue().getHighscore()).asObject());

        cargarLeaderboard();
    }

    private void cargarLeaderboard() {
        ObservableList<Perfil> lista = FXCollections.observableArrayList();

        String sql = "SELECT nombre, highscore FROM perfiles ORDER BY highscore DESC";

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Perfil p = new Perfil();
                p.setNombre(rs.getString("nombre"));
                p.setHighscore(rs.getInt("highscore"));
                lista.add(p);
            }

            tblLeaderboard.setItems(lista);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    void clickVolver() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) tblLeaderboard.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
