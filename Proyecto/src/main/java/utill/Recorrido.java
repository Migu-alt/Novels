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

public class Recorrido extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        

        List<Novela> novelas = new ArrayList<>();
        
        // Establece la conexión a la base de datos
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
            return;
        }

        // Convierte la lista de novelas a JSON
        Gson gson = new Gson();
        String jsonResponse = gson.toJson(novelas);

        // Imprime la respuesta JSON
        System.out.println("Respuesta JSON: " + jsonResponse);
        
        String relativePath = "src/main/webapp/novelas.json"; // Ruta relativa a `src`
        try (FileWriter file = new FileWriter(relativePath)) {
            file.write(jsonResponse);  // Escribe el JSON en el archivo
            file.flush();  // Asegúrate de que los datos se escriban
            System.out.println("Archivo JSON creado con éxito en: " + relativePath);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error al escribir el archivo JSON: " + e.getMessage());
        }
    }   
}
