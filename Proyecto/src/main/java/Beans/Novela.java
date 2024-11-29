/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Beans;

/**
 *
 * @author USURIO
 */
public class Novela {
    private String nombre;
    private String imagen;
    private String sinopsis;

    public Novela(String nombre, String imagen, String sinopsis) {
        this.nombre = nombre;
        this.imagen = imagen;
        this.sinopsis = sinopsis;
    }

    // Getters y setters
    public String getNombre() {
        return nombre;
    }

    public String getImagen() {
        return imagen;
    }

    public String getSinopsis() {
        return sinopsis;
    }
}
