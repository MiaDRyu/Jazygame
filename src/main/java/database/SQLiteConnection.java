package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLiteConnection {
    //Crear archivo sqlite con extensión .db en la raíz del proyecto
    private static final String URL = "jdbc:sqlite:algebra.db";

    //Método para la conexión con sqlite
    public static Connection connect(){
        try {
            //Driver manager que regresa la URL con la conexión
            return DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.err.println("No se pudo conectar a la base de datos");
            return null;
        }
    }
}
