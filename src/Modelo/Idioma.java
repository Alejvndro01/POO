package Modelo;

public class Idioma {
    private int codigo;
    private String nombre;
    private String pais;
    private boolean oficial;

    public Idioma() {
    }

    public Idioma(int codigo, String nombre, String pais, boolean oficial) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.pais = pais;
        this.oficial = oficial;
    }
}
