package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MaquinasDao {
    
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean RegistrarMaquinas(Maquinas maq) {
        
       String sql = "INSERT INTO maquinas (Clientes, Numero_de_serie, marca, modelo, tipo) VALUES (?, ?, ?, ?, ?)";
        
       try {
           con = cn.getConnection();
           ps = con.prepareStatement(sql);
           ps.setString(1, maq.getClientes());
           ps.setString(2, maq.getNumero_de_serie());
           ps.setString(3, maq.getMarca());
           ps.setString(4, maq.getModelo());
           ps.setString(5, maq.getTipo());
           
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
    
    public List ListarMaquinas() {
        List<Maquinas> ListaMaq = new ArrayList ();
        String sql = "SELECT * FROM maquinas";
        try {
            con =cn.getConnection();
            ps = con.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                
                Maquinas maq = new Maquinas();
                
                maq.setClientes(rs.getString("Clientes"));
                maq.setId_Maquinas(rs.getInt("id_Maquinas"));
                maq.setNumero_de_serie(rs.getString("Numero_de_serie"));
                maq.setMarca(rs.getString("Marca"));
                maq.setModelo(rs.getString("Modelo"));
                maq.setTipo(rs.getString("Tipo"));
                ListaMaq.add(maq);
            }
            
         } catch (SQLException e) {
            System.out.println(e.toString());
            
        }
        
        return ListaMaq; 
    }
    
    public boolean EliminarMaquina(int id) {
        
        String sql = "DELETE FROM maquinas WHERE id_Maquinas = ?";
        try {
            ps = con.prepareStatement(sql) ;
            ps.setInt(1, id);
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
    
    public boolean ModificarMaquinas(Maquinas maq) {
        String sql = "UPDATE maquinas SET clientes=?, Numero_de_serie=?, marca=?, modelo=?, tipo=?  WHERE id_Maquinas=?"; 
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql); 
            ps.setString(1, maq.getClientes());
            ps.setString(2, maq.getNumero_de_serie());
            ps.setString(3, maq.getMarca());
            ps.setString(4, maq.getModelo());
            ps.setString(5, maq.getTipo());
            ps.setInt(6, maq.getId_Maquinas());
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


