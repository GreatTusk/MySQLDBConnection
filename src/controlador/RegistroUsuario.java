/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import bd.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import modelo.Usuario;

/**
 *
 * @author f_776
 */
public class RegistroUsuario {
    // Clase para sentencias SQL
    
    
    public RegistroUsuario() {
    }

    public boolean agregarUsuario(Usuario u) {
        try {
            Conexion connection = new Conexion();
            Connection ctn = connection.getConnection();
            //Query
            String query = "INSERT INTO USUARIO (id, nombre, apellido, cargo) VALUES (?, ?, ?, ?)";
            // 1,2,3,4
            // Definir Prepare statement
            PreparedStatement stmt = ctn.prepareStatement(query);
            // con el query asignado se le da valores a los ?
            stmt.setInt(1, u.getId());
            stmt.setString(2, u.getNombre());
            stmt.setString(3, u.getApellido());
            stmt.setString(4, u.getCargo());
            //ejecutar
            stmt.executeUpdate();
            //se cierra
            stmt.close();
            ctn.close();
            return true;
        } catch (SQLException e) {
            System.out.println("Error:" + e.getMessage());
            return false;
        } catch (Exception e) {
            System.out.println("Error:" + e.getMessage());
            return false;
        }
    }
    
//    Buscar por usuario
    public Usuario buscaPorID(int id) {
        Usuario u = new Usuario();
        try {
            Conexion connection = new Conexion();
            Connection ctn = connection.getConnection();
            //Query
            String query = "SELECT * FROM USUARIO WHERE id = ?";
            PreparedStatement stmt = ctn.prepareStatement(query);
            stmt.setInt(1, id);
            //ejecutar consulta
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                u.setId(id);
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setCargo(rs.getString("cargo"));
            }
            stmt.close();
            ctn.close();
        } catch (SQLException e) {
            System.out.println("Error SQL buscar usuario por ID:" + e.getMessage());
        } catch (Exception e) {
            System.out.println("Error buscar usuario por ID:" + e.getMessage());
        }
        return u;
    }
    
    /*
    Mostrar en la tabla todo
    */
    
    public ArrayList<Usuario> lisrarUsuarios() {
        ArrayList<Usuario> listaU = new ArrayList<Usuario>();
        try {
            Conexion connection = new Conexion();
            Connection ctn = connection.getConnection();
            //Query
            String query = "SELECT * FROM USUARIO ORDER BY id;";
            PreparedStatement stmt = ctn.prepareStatement(query);
            ResultSet rs = stmt.executeQuery();
            while(rs.next()) {
                Usuario u = new Usuario();
                u.setId(rs.getInt("id"));
                u.setNombre(rs.getString("nombre"));
                u.setApellido(rs.getString("apellido"));
                u.setCargo(rs.getString("cargo"));
                listaU.add(u);
            }
            stmt.close();
            ctn.close();
            
        } catch (SQLException e) {
            System.out.println("Error SQL listar usuario:" + e.getMessage());
            
        } catch (Exception e) {
            System.out.println("Error listar usuario:" + e.getMessage());
        }
        return listaU;
    }
    
    
}
