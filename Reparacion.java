package Modelo;

public class Reparacion {
    
    private int id_Reparacion;
    private String codigo;
    private String estado;
    private String descripcion;
    private double precio;;
    private String maquinas;
    
    public Reparacion (){
        
    }

    public Reparacion(int id_Reparacion, String codigo, String estado, String descripcion, double precio, String maquinas) {
        this.id_Reparacion = id_Reparacion;
        this.codigo = codigo;
        this.estado = estado;
        this.descripcion = descripcion;
        this.precio = precio;
        this.maquinas = maquinas;
    }

    public int getId_Reparacion() {
        return id_Reparacion;
    }

    public void setId_Reparacion(int id_Reparacion) {
        this.id_Reparacion = id_Reparacion;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getMaquinas() {
        return maquinas;
    }

    public void setMaquinas(String maquinas) {
        this.maquinas = maquinas;
    }
    
    
    
}
