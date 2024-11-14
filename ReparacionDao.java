package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ReparacionDao {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarReparacion(Reparacion rep) {
        
       String sql = "INSERT INTO reparacion (maquinas, codigo, estado, descripcion, precio) VALUES (?, ?, ?, ?, ?)";
        
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, rep.getMaquinas());
           ps.setString(2, rep.getCodigo());
           ps.setString(3, rep.getEstado());
           ps.setString(4, rep.getDescripcion());
           ps.setDouble(5, rep.getPrecio());
           
           ps.execute();
           return true;
           
       } catch (SQLException e) {
           System.out.println(e.toString());
           return false;
           
       } finally {
           try {
               con.close();
           } catch (SQLException e) {
               System.out.println(e.toString());
               
           }
       }
       
    }
    
    public List ListarReparacion() {
        List<Reparacion> ListaRep = new ArrayList ();
        String sql = "SELECT * FROM reparacion";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Reparacion rep = new Reparacion();
                
                rep.setMaquinas(rs.getString("Maquinas"));
                rep.setId_Reparacion(rs.getInt("id_Reparacion"));
                rep.setCodigo(rs.getString("Codigo"));
                rep.setEstado(rs.getString("Estado"));
                rep.setDescripcion(rs.getString("Descripcion"));
                rep.setPrecio(rs.getDouble("Precio"));
                ListaRep.add(rep);
            }
            
         } catch (SQLException e) {
            System.out.println(e.toString());
            
        }
        
        return ListaRep; 
    }
    
    public boolean EliminarReparacion(int id_Reparacion) {
        
        String sql = "DELETE FROM reparacion WHERE id_Reparacion = ?";
        try {
            ps = con.prepareStatement(sql) ;
            ps.setInt(1, id_Reparacion);
            ps.execute();
            return true;
           
        }catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }finally {
            try {
                con.close();
            } catch (SQLException ex) {
                System.out.println(ex.toString());
            }
        } 
        
    }
    
    public boolean ModificarReparacion(Reparacion rep) {
        String sql = "UPDATE reparacion SET maquinas=?, Codigo=?, estado=?, descripcion=?, precio=?  WHERE id_Reparacion=?"; 
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setString(1, rep.getMaquinas());
            ps.setString(2, rep.getCodigo());
            ps.setString(3, rep.getEstado());
            ps.setString(4, rep.getDescripcion());
            ps.setDouble(5, rep.getPrecio());
            ps.setInt(6, rep.getId_Reparacion());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
            
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    
    
    
    }
    
    public Reparacion BuscarRep(String cod) {
        Reparacion reparaciones = new Reparacion();
        String sql = "SELECT * FROM reparacion WHERE codigo =?";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, cod);
            rs = ps.executeQuery();
            if (rs.next()) {
                reparaciones.setEstado(rs.getString("estado"));
                reparaciones.setDescripcion(rs.getString("descripcion"));
                reparaciones.setMaquinas(rs.getString("maquinas"));
                reparaciones.setPrecio(rs.getDouble("precio"));
                
            }
        
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
        return reparaciones;
 
    }     
    
    public Configuracion BuscarDatos() {
        Configuracion conf = new Configuracion();
        String sql = "SELECT * FROM configuracion";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                conf.setId_Configuracion(rs.getInt("id_Configuracion"));
                conf.setNombre(rs.getString("nombre"));
                conf.setDescripcion(rs.getString("descripcion"));
                conf.setTelefono(rs.getString("telefono"));
                conf.setDireccion(rs.getString("direccion"));
                
            }
        
    } catch (SQLException e) {
        System.out.println(e.toString());
    }
        return conf;
 
    }
    
     public boolean ModificarDatos(Configuracion conf) {
        String sql = "UPDATE configuracion SET nombre=?, descripcion=?, telefono=?, direccion=?  WHERE id_Configuracion=?"; 
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setString(1, conf.getNombre());
            ps.setString(2, conf.getDescripcion());
            ps.setString(3, conf.getTelefono());
            ps.setString(4, conf.getDireccion());
            ps.setInt(5, conf.getId_Configuracion());
            ps.execute();
            return true;
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
            
        } finally{
            try {
                con.close();
            } catch (SQLException e) {
                System.out.println(e.toString());
            }
        }
    
    
    
    }
        
}
