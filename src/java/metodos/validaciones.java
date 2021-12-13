/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import Usuario.usuario;
import Usuario.usuarios;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 *
 * @author oscar
 */
public class validaciones {
     public static void usuarioArrayList() {
        Connection c = null;
        Statement stmt = null;
        ResultSet rs;
        //Creacion de objeto usuario
        
        try {
            Class.forName("org.postgresql.Driver");
                c = DriverManager
                        .getConnection("jdbc:postgresql://localhost:5432/ProyectoWeb",//5432
                                "postgres", "root1234");
                
                stmt = c.createStatement();
                String sql="select *from \"SitioWeb\".usuarios;";
                rs=stmt.executeQuery(sql);
                
                while(rs.next()){
                  usuario u= new usuario();
                  u.setNombre(rs.getString("nombre"));
                  u.setApellido(rs.getString("apellido"));
                  u.setEmail(rs.getString("email"));
                  u.setUser(rs.getString("usuario"));
                  u.setContraseña(rs.getString("contra"));
                  usuarios.usuario.add(u);
                  }  
                
                
                
                rs.close();
                stmt.close();
                c.close();
            

        } catch (Exception e) {
            System.out.println(e);
        }
    }

    
    
    //metodo para validar si usuario esta en la db por medio de un arraylist
    public static String validarUsuario(String user, String email) {
        String estado = null;
        for (int i = 0; i < usuarios.usuario.size(); i++) {
            if (String.valueOf(usuarios.usuario.get(i).getUser()).equals(user) && 
                    String.valueOf(usuarios.usuario.get(i).getEmail()).equals(email)) {
                estado = "registrado";
                break;
            } else {
                estado = "noregistrado";
            }

        }
        return estado;
    }

    
    
    public static String validarLoginUsuario(String user, String pass) {
        String estado = null;
        for (int i = 0; i < usuarios.usuario.size(); i++) {
            if (String.valueOf(usuarios.usuario.get(i).getUser()).equals(user) && String.valueOf(usuarios.usuario.get(i).getContraseña()).equals(pass)) {
                estado = "correcto";
            } else {
                estado = "incorrecto";
            }

        }
        return estado;
    }
}
