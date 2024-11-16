package Modelo;

public class Ciudad {
    private int id; 
    private String nombre; 
    private String codigoPais;    
    private int poblacion; 
    
    public Ciudad() {
    }
    
    public Ciudad(int id, String nombre, String codigoPais, int poblacion) {
        this.id = id;
        this.nombre = nombre;
        this.codigoPais = codigoPais;
        this.poblacion = poblacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoPais() {
        return codigoPais;
    }

    public void setCodigoPais(String codigoPais) {
        this.codigoPais = codigoPais;
    }

    public int getPoblacion() {
        return poblacion;
    }

    public void setPoblacion(int poblacion) {
        this.poblacion = poblacion;
    }
}
            