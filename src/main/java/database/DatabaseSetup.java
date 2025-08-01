package database;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseSetup {
    public static void initDatabase() {
        String sqlPerfiles = """
            CREATE TABLE IF NOT EXISTS perfiles (
            id_perfil INTEGER PRIMARY KEY AUTOINCREMENT,
            nombre TEXT NOT NULL,
            puntaje INTEGER,
            highscore INTEGER);""";

        String sqlExpresiones = """
            CREATE TABLE IF NOT EXISTS expresiones (
            id_expresion INTEGER PRIMARY KEY AUTOINCREMENT,
            expresion TEXT NOT NULL,
            tipo TEXT NOT NULL,
            tipo_solucion TEXT NOT NULL,
            solucion TEXT NOT NULL);""";

        String sqlPartidas = """
            CREATE TABLE IF NOT EXISTS partidas (
            id_partida INTEGER PRIMARY KEY AUTOINCREMENT,
            fk_perfil INTEGER NOT NULL,
            etapa INTEGER NOT NULL,
            puntaje TEXT NOT NULL);""";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement()) {

            stmt.execute(sqlPerfiles);
            stmt.execute(sqlExpresiones);
            stmt.execute(sqlPartidas);
            System.out.println("Se inicializó la base de datos correctamente.");

        } catch (SQLException e) {
            System.out.println("No se pudo crear la tabla :c " + e.getMessage());
        }
    }

}
