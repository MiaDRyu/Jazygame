package Controller;

import database.SQLiteConnection;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import model.Expresion;
import model.Partida;
import model.PartidaSesion;
import model.Perfil;

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
                    System.out.println("Partida guardada por primera vez con ID: " + idGenerado);
                }

            } else {
                String updateSql = "UPDATE partidas SET etapa = ?, puntaje = ? WHERE id_partida = ?";
                PreparedStatement updatePs = conn.prepareStatement(updateSql);
                updatePs.setInt(1, partida.getEtapa());
                updatePs.setInt(2, partida.getPuntaje());
                updatePs.setInt(3, partida.getId());
                updatePs.executeUpdate();
                System.out.println("Partida actualizada.");
            }

            Perfil perfil = partida.getPerfil();
            int puntajeActual = partida.getPuntaje();
            if (puntajeActual > perfil.getHighscore()) {
                String updateHighscoreSql = "UPDATE perfiles SET highscore = ? WHERE id_perfil = ?";
                PreparedStatement highscorePs = conn.prepareStatement(updateHighscoreSql);
                highscorePs.setInt(1, puntajeActual);
                highscorePs.setInt(2, perfil.getId());
                highscorePs.executeUpdate();

                perfil.setHighscore(puntajeActual);
                System.out.println("Nuevo highscore actualizado para " + perfil.getNombre() + ": " + puntajeActual);
            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Guardar partida");
            alert.setHeaderText(null);
            alert.setContentText("Tu progreso ha sido guardado.");
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

            if (expresionActual == null) return; // Protección por si no hay datos

            lblExpresion.setText(expresionActual.getExpresion());
            List<String> opciones = new ArrayList<>();

            String query;
            PreparedStatement ps;

            switch (etapa) {
                case 0: // Preguntar tipo de expresión
                    opciones.add(expresionActual.getTipoExpresion());
                    respuestaCorrecta = expresionActual.getTipoExpresion();

                    query = "SELECT DISTINCT tipo FROM expresiones WHERE tipo != ? ORDER BY RANDOM() LIMIT 3";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, expresionActual.getTipoExpresion());
                    break;

                case 1: // Preguntar tipo de solución
                    opciones.add(expresionActual.getTipoSolucion());
                    respuestaCorrecta = expresionActual.getTipoSolucion();

                    query = "SELECT DISTINCT tipo_solucion FROM expresiones WHERE tipo_solucion != ? ORDER BY RANDOM() LIMIT 3";
                    ps = conn.prepareStatement(query);
                    ps.setString(1, expresionActual.getTipoSolucion());
                    break;

                case 2: // Preguntar solución
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
            System.out.println("¡Correcto!");
            etapa++;
            cargarPregunta();
        } else {
            System.out.println("Incorrecto");
        }
    }

    @FXML
    public void initialize() {
        cargarPregunta();
    }
}
