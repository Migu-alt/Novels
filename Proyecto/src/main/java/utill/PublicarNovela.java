/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package utill;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;

public class PublicarNovela extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Recoge los parámetros del formulario
        String nombreNovela = request.getParameter("nombreNovela");
        String imagenRuta = request.getParameter("rutaImagen");
        String sinopsis = request.getParameter("sinopsis");

        // Establece la conexión a la base de datos
        try (Connection con = ConexionBD.getConnection()) {
            // Crea la consulta SQL
            String sql = "INSERT INTO novela (nombre, rutaImagen, sinopsis) VALUES (?, ?, ?)";
            try (PreparedStatement stmt = con.prepareStatement(sql)) {
                // Establece los valores para la consulta
                stmt.setString(1, nombreNovela);
                stmt.setString(2, imagenRuta);
                stmt.setString(3, sinopsis);

                // Ejecuta la actualización en la base de datos
                int filasAfectadas = stmt.executeUpdate();
                if (filasAfectadas > 0) {
                    response.getWriter().println("Novela publicada correctamente.");
                } else {
                    response.getWriter().println("Error al publicar la novela.");
                }
            }
        } catch (SQLException e) {
            // Maneja excepciones relacionadas con la base de datos
            e.printStackTrace();
            response.getWriter().println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}