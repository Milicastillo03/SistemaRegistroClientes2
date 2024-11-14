package Modelo;

public class Maquinas {
    
    private int id_Maquinas;
    private String Numero_de_serie;
    private String marca;
    private String modelo;
    private String tipo;;
    private String Clientes;

    public Maquinas() {
    }

    public Maquinas(int id_Maquinas, String Numero_de_serie, String marca, String modelo, String tipo, String Clientes) {
        this.id_Maquinas = id_Maquinas;
        this.Numero_de_serie = Numero_de_serie;
        this.marca = marca;
        this.modelo = modelo;
        this.tipo = tipo;
        this.Clientes = Clientes;
    }

    public int getId_Maquinas() {
        return id_Maquinas;
    }

    public void setId_Maquinas(int id_Maquinas) {
        this.id_Maquinas = id_Maquinas;
    }

    public String getNumero_de_serie() {
        return Numero_de_serie;
    }

    public void setNumero_de_serie(String Numero_de_serie) {
        this.Numero_de_serie = Numero_de_serie;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getClientes() {
        return Clientes;
    }

    public void setClientes(String Clientes) {
        this.Clientes = Clientes;
    }
    
    

    
}
