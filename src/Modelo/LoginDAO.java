package Modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginDAO {
    
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Conexion cn = new Conexion();
    
    public login log(String usuario, String contraseña) {
        login l = new login ();
        String sql = "Select * FROM administrador WHERE usuario = ? AND contraseña = ?";
        try {
            con = cn.getConnection();
            ps = con.prepareStatement(sql);
            ps.setString(1, usuario);
            ps.setString(2, contraseña);
            rs=ps.executeQuery ();
            if (rs.next()) {
                l.setId(rs.getInt("id_Administrador"));
                l.setUsuario(rs.getString("usuario"));
                l.setContraseña(rs.getString("contraseña"));
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
            
        }
            
        return l;
        
        
    }
    
    
}