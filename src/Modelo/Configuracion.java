package Modelo;

public class Configuracion {
    private int id_Configuracion;
    private String nombre;
    private String descripcion;
    private String telefono;
    private String direccion;
    
    public Configuracion() {
      
}

    public Configuracion(int id_Configuracion, String nombre, String descripcion, String telefono, String direccion) {
        this.id_Configuracion = id_Configuracion;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public int getId_Configuracion() {
        return id_Configuracion;
    }

    public void setId_Configuracion(int id_Configuracion) {
        this.id_Configuracion = id_Configuracion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    
    
}
