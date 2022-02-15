package classes;

public class Aeronave {

    private String categoria;
    private String modelo;

    public Aeronave() {

    }

    public Aeronave(String categoria, String modelo) {
        this.categoria = categoria;
        this.modelo = modelo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

}
