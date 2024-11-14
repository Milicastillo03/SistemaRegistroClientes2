package Modelo;

public class Detalle {
    private int id_Detalle;
    private String codigo_Reparacion;
    private String cantidad;
    private double precio;
    private int id_Cobros;
    
    public Detalle() {
        
    }

    public Detalle(int id_Detalle, String codigo_Reparacion, String cantidad, double precio, int id_Cobros) {
        this.id_Detalle = id_Detalle;
        this.codigo_Reparacion = codigo_Reparacion;
        this.cantidad = cantidad;
        this.precio = precio;
        this.id_Cobros = id_Cobros;
    }

    public int getId_Detalle() {
        return id_Detalle;
    }

    public void setId_Detalle(int id_Detalle) {
        this.id_Detalle = id_Detalle;
    }

    public String getCodigo_Reparacion() {
        return codigo_Reparacion;
    }

    public void setCodigo_Reparacion(String codigo_Reparacion) {
        this.codigo_Reparacion = codigo_Reparacion;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getId_Cobros() {
        return id_Cobros;
    }

    public void setId_Cobros(int id_Cobros) {
        this.id_Cobros = id_Cobros;
    }
    
    
}
