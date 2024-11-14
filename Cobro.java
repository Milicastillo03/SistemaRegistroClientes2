package Modelo;

public class Cobro {
    
    private int id_Cobro;
    private String clientes;
    private String maquinas;
    private String reparacion;
    private double total;
    
    public Cobro() {
        
    }

    public Cobro(int id_Cobro, String clientes, String maquinas, String reparacion, double total) {
        this.id_Cobro = id_Cobro;
        this.clientes = clientes;
        this.maquinas = maquinas;
        this.reparacion = reparacion;
        this.total = total;
    }

    public int getId_Cobro() {
        return id_Cobro;
    }

    public void setId_Cobro(int id_Cobro) {
        this.id_Cobro = id_Cobro;
    }

    public String getClientes() {
        return clientes;
    }

    public void setClientes(String clientes) {
        this.clientes = clientes;
    }

    public String getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(String maquinas) {
        this.maquinas = maquinas;
    }

    public String getReparacion() {
        return reparacion;
    }

    public void setReparacion(String reparacion) {
        this.reparacion = reparacion;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
    
    
}
