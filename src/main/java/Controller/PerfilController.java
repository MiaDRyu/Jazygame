package Controller;

import database.SQLiteConnection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Perfil;
import model.PerfilSesion;
import mx.edu.utch.proyectouiv.App;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PerfilController {

    @FXML
    private ListView<Perfil> listPerfiles;

    @FXML
    private TextField txtNuevoPerfil;

    private ObservableList<Perfil> perfiles = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        cargarPerfiles();
    }

    private void cargarPerfiles() {
        perfiles.clear();
        try (Connection conn = SQLiteConnection.connect()) {
            String query = "SELECT * FROM perfiles";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Perfil perfil = new Perfil(
                        rs.getInt("id_perfil"),
                        rs.getString("nombre"),
                        rs.getInt("puntaje"),
                        rs.getInt("highscore")
                );
                perfiles.add(perfil);
            }
            listPerfiles.setItems(perfiles);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void crearPerfil() {
        String nombre = txtNuevoPerfil.getText().trim();
        if (nombre.isEmpty()) return;

        try (Connection conn = SQLiteConnection.connect()) {
            String insert = "INSERT INTO perfiles (nombre, puntaje, highscore) VALUES (?, 0, 0)";
            PreparedStatement ps = conn.prepareStatement(insert);
            ps.setString(1, nombre);
            ps.executeUpdate();

            txtNuevoPerfil.clear();
            cargarPerfiles();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void seleccionarPerfil() {
        Perfil perfil = listPerfiles.getSelectionModel().getSelectedItem();
        if (perfil == null) return;

        PerfilSesion.setPerfilActual(perfil);

        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) listPerfiles.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Menú Principal");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
