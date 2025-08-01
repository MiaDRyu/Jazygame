package Controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import model.Partida;
import model.PartidaSesion;
import model.PerfilSesion;
import database.SQLiteConnection;
import mx.edu.utch.proyectouiv.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class cargarPartidaController {

    @FXML private TableView<Partida> tablaPartidas;
    @FXML private TableColumn<Partida, Integer> colId;
    @FXML private TableColumn<Partida, Integer> colEtapa;
    @FXML private TableColumn<Partida, Integer> colPuntaje;
    @FXML private Button btnSeleccionar;

    private ObservableList<Partida> listaPartidas;

    @FXML
    public void initialize() {
        colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        colEtapa.setCellValueFactory(new PropertyValueFactory<>("etapa"));
        colPuntaje.setCellValueFactory(new PropertyValueFactory<>("puntaje"));

        cargarPartidas();
    }

    private void cargarPartidas() {
        listaPartidas = FXCollections.observableArrayList();
        int perfilId = PerfilSesion.getPerfilActual().getId();

        String sql = "SELECT id_partida, etapa, puntaje FROM partidas WHERE Fk_Perfil = ?";

        try (Connection conn = SQLiteConnection.connect();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setInt(1, perfilId);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Partida partida = new Partida();
                partida.setId(rs.getInt("id_partida"));
                partida.setEtapa(rs.getInt("etapa"));
                partida.setPuntaje(rs.getInt("puntaje"));
                partida.setPerfil(PerfilSesion.getPerfilActual());

                listaPartidas.add(partida);
            }

            tablaPartidas.setItems(listaPartidas);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickSeleccionar() {
        Partida seleccionada = tablaPartidas.getSelectionModel().getSelectedItem();

        if (seleccionada != null) {
            PartidaSesion.setPartidaActual(seleccionada);

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("partida.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = (Stage) tablaPartidas.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Partida Cargada");
                stage.show();

            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Sin selección");
            alert.setHeaderText(null);
            alert.setContentText("Selecciona una partida para cargar.");
            alert.showAndWait();
        }
    }

    @FXML
    private void clickVolver() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("inicio.fxml"));
            Scene scene = new Scene(fxmlLoader.load());

            Stage stage = (Stage) tablaPartidas.getScene().getWindow();
            stage.setScene(scene);
            stage.setTitle("Inicio");
            stage.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
