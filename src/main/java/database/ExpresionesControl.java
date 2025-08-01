package database;

import java.sql.*;

public class ExpresionesControl {

    public static void insertarExpresionesIniciales() {

        String checkSql = "SELECT COUNT(*) FROM expresiones";
        String insertSql = "INSERT INTO expresiones (id_expresion, expresion, tipo, tipo_solucion, solucion) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = SQLiteConnection.connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(checkSql)) {

            if (rs.next() && rs.getInt(1) > 0) return;

        try (PreparedStatement pstmt = conn.prepareStatement(insertSql)) {

            // 1–8: Diferencia de cuadrados
            pstmt.setString(2, "x^2 - 36");
            pstmt.setString(3, "Diferencia de cuadrados");
            pstmt.setString(4, "Binomio conjugado");
            pstmt.setString(5, "(x + 6)(x - 6)");
            pstmt.addBatch();

            pstmt.setString(2, "16x^2 - 1");
            pstmt.setString(3, "Diferencia de cuadrados");
            pstmt.setString(4, "Binomio conjugado");
            pstmt.setString(5, "(4x + 1)(4x - 1)");
            pstmt.addBatch();

            pstmt.setString(2, "a^2 - 49");
            pstmt.setString(3, "Diferencia de cuadrados");
            pstmt.setString(4, "Binomio conjugado");
            pstmt.setString(5, "(a + 7)(a - 7)");
            pstmt.addBatch();

            pstmt.setString(2, "25x^2 - 4y^2");
            pstmt.setString(3, "Diferencia de cuadrados");
            pstmt.setString(4, "Binomio conjugado");
            pstmt.setString(5, "(5x + 2y)(5x - 2y)");
            pstmt.addBatch();

            pstmt.setString(2, "49a^2 - 64");
            pstmt.setString(3, "Diferencia de cuadrados");
            pstmt.setString(4, "Binomio conjugado");
            pstmt.setString(5, "(7a + 8)(7a - 8)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 - 100");
            pstmt.setString(3, "Diferencia de cuadrados");
            pstmt.setString(4, "Binomio conjugado");
            pstmt.setString(5, "(x + 10)(x - 10)");
            pstmt.addBatch();

            pstmt.setString(2, "9x^2 - y^2");
            pstmt.setString(3, "Diferencia de cuadrados");
            pstmt.setString(4, "Binomio conjugado");
            pstmt.setString(5, "(3x + y)(3x - y)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 - 121");
            pstmt.setString(3, "Diferencia de cuadrados");
            pstmt.setString(4, "Binomio conjugado");
            pstmt.setString(5, "(x + 11)(x - 11)");
            pstmt.addBatch();

            // 9–16: Trinomio cuadrado perfecto
            pstmt.setString(2, "x^2 + 4x + 4");
            pstmt.setString(3, "Trinomio cuadrado perfecto");
            pstmt.setString(4, "Binomio al cuadrado");
            pstmt.setString(5, "(x + 2)^2");
            pstmt.addBatch();

            pstmt.setString(2, "a^2 - 6a + 9");
            pstmt.setString(3, "Trinomio cuadrado perfecto");
            pstmt.setString(4, "Binomio al cuadrado");
            pstmt.setString(5, "(a - 3)^2");
            pstmt.addBatch();

            pstmt.setString(2, "4x^2 + 12x + 9");
            pstmt.setString(3, "Trinomio cuadrado perfecto");
            pstmt.setString(4, "Binomio al cuadrado");
            pstmt.setString(5, "(2x + 3)^2");
            pstmt.addBatch();

            pstmt.setString(2, "9y^2 - 30y + 25");
            pstmt.setString(3, "Trinomio cuadrado perfecto");
            pstmt.setString(4, "Binomio al cuadrado");
            pstmt.setString(5, "(3y - 5)^2");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 - 8x + 16");
            pstmt.setString(3, "Trinomio cuadrado perfecto");
            pstmt.setString(4, "Binomio al cuadrado");
            pstmt.setString(5, "(x - 4)^2");
            pstmt.addBatch();

            pstmt.setString(2, "a^2 + 10a + 25");
            pstmt.setString(3, "Trinomio cuadrado perfecto");
            pstmt.setString(4, "Binomio al cuadrado");
            pstmt.setString(5, "(a + 5)^2");
            pstmt.addBatch();

            pstmt.setString(2, "16x^2 - 8x + 1");
            pstmt.setString(3, "Trinomio cuadrado perfecto");
            pstmt.setString(4, "Binomio al cuadrado");
            pstmt.setString(5, "(4x - 1)^2");
            pstmt.addBatch();

            pstmt.setString(2, "y^2 + 14y + 49");
            pstmt.setString(3, "Trinomio cuadrado perfecto");
            pstmt.setString(4, "Binomio al cuadrado");
            pstmt.setString(5, "(y + 7)^2");
            pstmt.addBatch();

            // 17–24: Trinomio x² + bx + c
            pstmt.setString(2, "x^2 + 5x + 6");
            pstmt.setString(3, "Trinomio x^2 + bx + c");
            pstmt.setString(4, "Binomios simples");
            pstmt.setString(5, "(x + 2)(x + 3)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 + 7x + 10");
            pstmt.setString(3, "Trinomio x^2 + bx + c");
            pstmt.setString(4, "Binomios simples");
            pstmt.setString(5, "(x + 2)(x + 5)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 + 11x + 30");
            pstmt.setString(3, "Trinomio x^2 + bx + c");
            pstmt.setString(4, "Binomios simples");
            pstmt.setString(5, "(x + 5)(x + 6)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 - 3x - 10");
            pstmt.setString(3, "Trinomio x^2 + bx + c");
            pstmt.setString(4, "Binomios simples");
            pstmt.setString(5, "(x - 5)(x + 2)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 - x - 6");
            pstmt.setString(3, "Trinomio x^2 + bx + c");
            pstmt.setString(4, "Binomios simples");
            pstmt.setString(5, "(x - 3)(x + 2)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 + 3x - 18");
            pstmt.setString(3, "Trinomio x^2 + bx + c");
            pstmt.setString(4, "Binomios simples");
            pstmt.setString(5, "(x + 6)(x - 3)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 - 4x - 21");
            pstmt.setString(3, "Trinomio x^2 + bx + c");
            pstmt.setString(4, "Binomios simples");
            pstmt.setString(5, "(x - 7)(x + 3)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 + 2x - 24");
            pstmt.setString(3, "Trinomio x^2 + bx + c");
            pstmt.setString(4, "Binomios simples");
            pstmt.setString(5, "(x + 6)(x - 4)");
            pstmt.addBatch();

            // 25–32: Trinomio ax^2 + bx + c
            pstmt.setString(2, "2x^2 + 7x + 3");
            pstmt.setString(3, "Trinomio ax^2 + bx + c");
            pstmt.setString(4, "Binomios por agrupación");
            pstmt.setString(5, "(2x + 1)(x + 3)");
            pstmt.addBatch();

            pstmt.setString(2, "3x^2 - 10x + 8");
            pstmt.setString(3, "Trinomio ax^2 + bx + c");
            pstmt.setString(4, "Binomios por agrupación");
            pstmt.setString(5, "(3x - 4)(x - 2)");
            pstmt.addBatch();

            pstmt.setString(2, "6x^2 + 11x + 3");
            pstmt.setString(3, "Trinomio ax^2 + bx + c");
            pstmt.setString(4, "Binomios por agrupación");
            pstmt.setString(5, "(3x + 1)(2x + 3)");
            pstmt.addBatch();

            pstmt.setString(2, "4x^2 - 4x - 3");
            pstmt.setString(3, "Trinomio ax^2 + bx + c");
            pstmt.setString(4, "Binomios por agrupación");
            pstmt.setString(5, "(2x + 1)(2x - 3)");
            pstmt.addBatch();

            pstmt.setString(2, "5x^2 + 13x + 6");
            pstmt.setString(3, "Trinomio ax^2 + bx + c");
            pstmt.setString(4, "Binomios por agrupación");
            pstmt.setString(5, "(5x + 3)(x + 2)");
            pstmt.addBatch();

            pstmt.setString(2, "2x^2 - x - 6");
            pstmt.setString(3, "Trinomio ax^2 + bx + c");
            pstmt.setString(4, "Binomios por agrupación");
            pstmt.setString(5, "(2x + 3)(x - 2)");
            pstmt.addBatch();

            pstmt.setString(2, "3x^2 + 2x - 8");
            pstmt.setString(3, "Trinomio ax^2 + bx + c");
            pstmt.setString(4, "Binomios por agrupación");
            pstmt.setString(5, "(3x - 4)(x + 2)");
            pstmt.addBatch();

            pstmt.setString(2, "6x^2 - 5x - 6");
            pstmt.setString(3, "Trinomio ax^2 + bx + c");
            pstmt.setString(4, "Binomios por agrupación");
            pstmt.setString(5, "(3x + 2)(2x - 3)");
            pstmt.addBatch();

            // 33–40: Suma o diferencia de cubos
            pstmt.setString(2, "x^3 + 27");
            pstmt.setString(3, "Suma de cubos");
            pstmt.setString(4, "Binomio y trinomio especial");
            pstmt.setString(5, "(x + 3)(x^2 - 3x + 9)");
            pstmt.addBatch();

            pstmt.setString(2, "8x^3 - 125");
            pstmt.setString(3, "Diferencia de cubos");
            pstmt.setString(4, "Binomio y trinomio especial");
            pstmt.setString(5, "(2x - 5)(4x^2 + 10x + 25)");
            pstmt.addBatch();

            pstmt.setString(2, "27a^3 + 8");
            pstmt.setString(3, "Suma de cubos");
            pstmt.setString(4, "Binomio y trinomio especial");
            pstmt.setString(5, "(3a + 2)(9a^2 - 6a + 4)");
            pstmt.addBatch();

            pstmt.setString(2, "x^3 - 64");
            pstmt.setString(3, "Diferencia de cubos");
            pstmt.setString(4, "Binomio y trinomio especial");
            pstmt.setString(5, "(x - 4)(x^2 + 4x + 16)");
            pstmt.addBatch();

            pstmt.setString(2, "a^3 + b^3");
            pstmt.setString(3, "Suma de cubos");
            pstmt.setString(4, "Binomio y trinomio especial");
            pstmt.setString(5, "(a + b)(a^2 - ab + b^2)");
            pstmt.addBatch();

            pstmt.setString(2, "x^3 - y^3");
            pstmt.setString(3, "Diferencia de cubos");
            pstmt.setString(4, "Binomio y trinomio especial");
            pstmt.setString(5, "(x - y)(x^2 + xy + y^2)");
            pstmt.addBatch();

            pstmt.setString(2, "125 - 27x^3");
            pstmt.setString(3, "Diferencia de cubos");
            pstmt.setString(4, "Binomio y trinomio especial");
            pstmt.setString(5, "(5 - 3x)(25 + 15x + 9x^2)");
            pstmt.addBatch();

            pstmt.setString(2, "64a^3 + 1");
            pstmt.setString(3, "Suma de cubos");
            pstmt.setString(4, "Binomio y trinomio especial");
            pstmt.setString(5, "(4a + 1)(16a^2 - 4a + 1)");
            pstmt.addBatch();

            // 41–50: Factor común
            pstmt.setString(2, "5x^2 + 10x");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "5x(x + 2)");
            pstmt.addBatch();

            pstmt.setString(2, "3a^2b - 6ab");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "3ab(a - 2)");
            pstmt.addBatch();

            pstmt.setString(2, "4x^3 - 8x^2");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "4x^2(x - 2)");
            pstmt.addBatch();

            pstmt.setString(2, "6y^2 + 9y");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "3y(2y + 3)");
            pstmt.addBatch();

            pstmt.setString(2, "2ab + 4a");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "2a(b + 2)");
            pstmt.addBatch();

            pstmt.setString(2, "x^2 - 3x");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "x(x - 3)");
            pstmt.addBatch();

            pstmt.setString(2, "7m^2 - 14mn");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "7m(m - 2n)");
            pstmt.addBatch();

            pstmt.setString(2, "12x^2y + 18xy^2");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "6xy(2x + 3y)");
            pstmt.addBatch();

            pstmt.setString(2, "10x^3 + 5x^2");
            pstmt.setString(3, "Factor común");
            pstmt.setString(4, "Factor común");
            pstmt.setString(5, "5x^2(2x + 1)");
            pstmt.addBatch();

            // Ejecutar el lote
            pstmt.executeBatch();
            System.out.println("Las 50 expresiones se insertaron correctamente.");
          }
        } catch (SQLException e) {
            System.out.println("Error al insertar expresiones: " + e.getMessage());
        }
    }
}