package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CobroDao {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    int r;
    
    public int IdCobro() {
        int id = 0;
        String sql = "SELECT MAX(id) FROM cobro";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            if (rs.next()) {
                id = rs.getInt(1);
            }
            
            
        }catch (SQLException e) {
            System.out.println(e.toString());
        }
        return id;
    }
    
    public int RegistrarCobro(Cobro c) {
        
       String sql = "INSERT INTO cobro (clientes, maquinas, reparacion, total) VALUES (?, ?, ?, ?)";
        
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, c.getClientes());
           ps.setString(2, c.getMaquinas());
           ps.setString(3, c.getReparacion());
           ps.setDouble(4, c.getTotal());
           ps.execute();
           
       } catch (SQLException e) {
           System.out.println(e.toString());
       } finally{
            try {
                con.close();
            }catch (SQLException e) {
                System.out.println(e.toString());
                
            }
        }
       
       return r;
    }
    
    public int RegistrarDetalle(Detalle Dc) {
        String sql = "INSERT INTO detalle (codigo_Reparacion, cantidad, precio, id_Cobros) VALUES (?, ?, ?, ?)";
        
        try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, Dc.getCodigo_Reparacion());
           ps.setString(2, Dc.getCantidad());
           ps.setDouble(3, Dc.getPrecio());
           ps.setInt(4, Dc.getId_Cobros());
           ps.execute();
           
       } catch (SQLException e) {
           System.out.println(e.toString());
       } finally{
            try {
                con.close();
            }catch (SQLException e) {
                System.out.println(e.toString());
                
            }
        }
       return r;
        
    }
    
    public List ListarCobro() {
        List<Cobro> ListaCobro = new ArrayList ();
        String sql = "SELECT * FROM Cobro";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Cobro cob= new Cobro();
                
                cob.setId_Cobro(rs.getInt("id_Cobro"));
                cob.setClientes(rs.getString("clientes"));
                cob.setMaquinas(rs.getString("maquinas"));
                cob.setReparacion(rs.getString("Reparacion"));
                cob.setTotal(rs.getDouble("total"));
                ListaCobro.add(cob);
            }
            
         } catch (SQLException e) {
            System.out.println(e.toString());
            
        }
        
        return ListaCobro; 
    }
    
}
