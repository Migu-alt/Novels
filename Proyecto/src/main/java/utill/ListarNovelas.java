/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package utill;

import Beans.Novela;
import com.google.gson.Gson;
import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author USURIO
 */
public class ListarNovelas extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Configuración para mostrar el contenido como HTML
        response.setContentType("text/html; charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Aquí conectarías a la base de datos y obtendrías las novelas
        List<Novela> novelas = obtenerNovelasDeLaBD();

        // Inicia el contenido HTML
        out.println("<!DOCTYPE html>");
        out.println("<html lang=\"es\">");
        out.println("<head>");
        out.println("<meta charset=\"UTF-8\">");
        out.println("<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        out.println("<title>Lista de Novelas</title>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("</head>");
        out.println("<body>");
        out.println("<div class=\"container my-5\">");
        out.println("<div class=\"row\">");

        // Aquí creas las "ventanitas" para cada novela
        for (Novela novela : novelas) {
            out.println("<div class=\"col-md-4\">");
            out.println("<div class=\"card\">");
            out.println("<img src=\"" + novela.getImagen() + "\" class=\"card-img-top\" alt=\"" + novela.getNombre() + "\">");
            out.println("<div class=\"card-body\">");
            out.println("<h5 class=\"card-title\">" + novela.getNombre() + "</h5>");
            out.println("<p class=\"card-text\">" + novela.getSinopsis() + "</p>");
            out.println("</div>");
            out.println("</div>");
            out.println("</div>");
        }

        // Cierra el contenedor y el HTML
        out.println("</div>");
        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
    }

    private List<Novela> obtenerNovelasDeLaBD() {
        
        List<Novela> novelas = new ArrayList<>();
        ConexionBD conexionBD = new ConexionBD();
        
        try (Connection con = conexionBD.getConnection()) {
            String sql = "SELECT nombre, rutaImagen, sinopsis FROM novela";
            try (PreparedStatement stmt = con.prepareStatement(sql);
                 ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String nombre = rs.getString("nombre");
                    String imagen = rs.getString("rutaImagen");
                    String sinopsis = rs.getString("sinopsis");

                    // Imprime los resultados en la consola
                    System.out.println("Nombre: " + nombre);
                    System.out.println("Imagen: " + imagen);
                    System.out.println("Sinopsis: " + sinopsis);

                    // Agrega la novela a la lista
                    novelas.add(new Novela(nombre, imagen, sinopsis));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
            return novelas;
        }
        return novelas;
    }
    
}