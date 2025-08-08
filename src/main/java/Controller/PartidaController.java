package Controller;

import database.SQLiteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import model.Expresion;
import model.Partida;
import model.PartidaSesion;
import model.Perfil;
import mx.edu.utch.proyectouiv.App;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PartidaController {

    @FXML
    private Button btnA;

    @FXML
    private Button btnB;

    @FXML
    private Button btnC;

    @FXML
    private Button btnD;

    @FXML
    private Button btnSave;

    @FXML
    private Label lblExpresion;

    @FXML
    private Label lblPregunta;

    private Expresion expresionActual;
    private int etapa = 0;
    private int puntaje;
    private String respuestaCorrecta;

    @FXML
    void clickA(ActionEvent event) {
        comprobarRespuesta(btnA.getText());
    }

    @FXML
    void clickB(ActionEvent event) {
        comprobarRespuesta(btnB.getText());
    }

    @FXML
    void clickC(ActionEvent event) {
        comprobarRespuesta(btnC.getText());
    }

    @FXML
    void clickD(ActionEvent event) {
        comprobarRespuesta(btnD.getText());
    }

    @FXML
    void clickSave(ActionEvent event) {
        Partida partida = PartidaSesion.getPartidaActual();
        if (partida == null) {
            System.out.println("No hay partida activa.");
            return;
        }

        partida.setEtapa(etapa);
        partida.setPuntaje(puntaje);

        try (Connection conn = SQLiteConnection.connect()) {
            if (partida.getId() == 0) {
                String insertSql = "INSERT INTO partidas (Fk_Perfil, etapa, puntaje) VALUES (?, ?, ?)";
                PreparedStatement insertPs = conn.prepareStatement(insertSql, PreparedStatement.RETURN_GENERATED_KEYS);
                insertPs.setInt(1, partida.getPerfil().getId());
                insertPs.setInt(2, partida.getEtapa());
                insertPs.setInt(3, partida.getPuntaje());
                insertPs.executeUpdate();

                ResultSet generatedKeys = insertPs.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int idGenerado = generatedKeys.getInt(1);
                    partida.setId(idGenerado);
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Partida Guardada");
                    alert.setHeaderText(null);
                    alert.setContentText("Partida guardada por primera vez con ID: " + idGenerado);
                    alert.showAndWait();
                }

            } else {
                String updateSql = "UPDATE partidas SET etapa = ?, puntaje = ? WHERE id_partida = ?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, partida.getEtapa());
                updatePs.setInt(2, partida.getPuntaje());
                updatePs.setInt(3, partida.getId());
                updatePs.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Guardado exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Se ha guardado la partida");
                alert.showAndWait();
            }

            Perfil perfil = partida.getPerfil();
            int puntajeActual = partida.getPuntaje();
            boolean hayHighscore = false;
            if (puntajeActual > perfil.getHighscore()) {
                String updateHighscoreSql = "UPDATE perfiles SET highscore = ? WHERE id_perfil = ?";
                PreparedStatement highscorePs = conn.prepareStatement(updateHighscoreSql);
                highscorePs.setInt(1, puntajeActual);
                highscorePs.setInt(2, perfil.getId());
                highscorePs.executeUpdate();

                perfil.setHighscore(puntajeActual);
                hayHighscore = true;
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardar partida");
            alert.setHeaderText(null);
            if (hayHighscore){
                alert.setContentText("Tu progreso ha sido guardado. Tu puntaje más alto ahora es: "+perfil.getHighscore());
            } else {
                alert.setContentText("Tu progreso ha sido guardado.");
            }
            alert.showAndWait();

        } catch (SQLException e) {
            System.err.println("Error al guardar la partida:");
            e.printStackTrace();
        }
    }

    public void cargarPregunta() {
        try (Connection conn = SQLiteConnection.connect()) {

            if (etapa == 3){
                expresionActual = null;
                etapa = 0;
                puntaje++;
            }

            if (expresionActual == null) {
                // Solo se elige una nueva expresión si aún no hay una activa
                String queryExpr = "SELECT * FROM expresiones ORDER BY RANDOM() LIMIT 1";
                PreparedStatement ps = conn.prepareStatement(queryExpr);
                ResultSet rs = ps.executeQuery();

                if (rs.next()) {
                    expresionActual = new Expresion(
                            rs.getInt("id_expresion"),
                            rs.getString("expresion"),
                            rs.getString("tipo"),
                            rs.getString("tipo_solucion"),
                            rs.getString("solucion")
                    );
                }
            }

            if (expresionActual == null) return;

            lblExpresion.setText(expresionActual.getExpresion());
            List<String> opciones = new ArrayList<>();

            String query;
            PreparedStatement ps;

            switch (etapa) {
                case 0: // Preguntar tipo de expresión
                    lblPregunta.setText("¿Qué tipo de expresión es esta?");
                    opciones.add(expresionActual.getTipoExpresion());
                    respuestaCorrecta = expresionActual.getTipoExpresion();

                    query = "SELECT DISTINCT tipo FROM expresiones WHERE tipo != ? ORDER BY RANDOM() LIMIT 3";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, expresionActual.getTipoExpresion());
                    break;

                case 1: // Preguntar tipo de solución
                    lblPregunta.setText("¿Qué producto obtenemos al factorizarla?");
                    opciones.add(expresionActual.getTipoSolucion());
                    respuestaCorrecta = expresionActual.getTipoSolucion();

                    query = "SELECT DISTINCT tipo_solucion FROM expresiones WHERE tipo_solucion != ? ORDER BY RANDOM() LIMIT 3";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, expresionActual.getTipoSolucion());
                    break;

                case 2: // Preguntar solución
                    lblPregunta.setText("Cuál es la respuesta?");
                    opciones.add(expresionActual.getSolucion());
                    respuestaCorrecta = expresionActual.getSolucion();

                    query = "SELECT solucion FROM expresiones WHERE id_expresion != ? ORDER BY RANDOM() LIMIT 3";
                    ps = conn.prepareStatement(query);
                    ps.setInt(1, expresionActual.getId());
                    break;

                default:
                    return;
            }

            ResultSet rsIncorrectas = ps.executeQuery();
            while (rsIncorrectas.next()) {
                opciones.add(rsIncorrectas.getString(1));
            }

            Collections.shuffle(opciones);

            btnA.setText(opciones.get(0));
            btnB.setText(opciones.get(1));
            btnC.setText(opciones.get(2));
            btnD.setText(opciones.get(3));

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void comprobarRespuesta(String respuestaSeleccionada){
        if (respuestaSeleccionada.equals(respuestaCorrecta)) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Correcta!");
            alert.setHeaderText(null);
            alert.setContentText("Respuesta correcta!");
            alert.showAndWait();
            etapa++;
            cargarPregunta();
        } else {
            System.out.println("Incorrecto");

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Incorrecta");
            alert.setHeaderText(null);
            alert.setContentText("Respuesta Incorrecta, volviendo al menú principal");
            alert.showAndWait();

            try {
                FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource("inicio.fxml"));
                Scene scene = new Scene(fxmlLoader.load());

                Stage stage = (Stage) lblExpresion.getScene().getWindow();
                stage.setScene(scene);
                stage.setTitle("Menú Principal");
                stage.show();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @FXML
    public void initialize() {
        cargarPregunta();
    }
}
