/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utill;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionBD {
    private static final String URL = "jdbc:mariadb://localhost:3316/proyecto";
    private static final String USER = "root";
    private static final String PASSWORD = "root";
        public static Connection getConnection() throws SQLException {
        try {
            // Asegúrate de que el driver JDBC está cargado
            Class.forName("org.mariadb.jdbc.Driver");  // Si usas mariadb, usa este driver
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            // Si no se puede establecer la conexión, imprime el error
            e.printStackTrace();
            throw new SQLException("Error al obtener la conexión a la base de datos", e);
        }
    }
}