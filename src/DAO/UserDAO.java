/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.User;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author Melón
 */
public class UserDAO {
    Connection cn;
    User u;
    ResultSet rs;

    public UserDAO() {
    }
 
    public boolean insertUser(User u){
        boolean insert=false;
        try {
            cn = Conexion.GetConexion();
            CallableStatement cs  = cn.prepareCall("{call insert_user(?,?,?)}");           
            cs.setString(1, u.getName());
            cs.setString(2, u.getPass());
            cs.setString(3, u.getEmail());
            int rows = cs.executeUpdate();
            if(rows>0)
                insert = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,"No se pudo insertar el usuario " + e);
        }
        return insert;
    }

    public ArrayList<User> getUsers(){
        ArrayList<User> array = new ArrayList<User>();
        try {
            cn = Conexion.GetConexion();
            PreparedStatement ps = cn.prepareStatement("select * from user");
            rs = ps.executeQuery();
            while(rs.next()){
                u = new User();
                u.setName(rs.getString(1));
                u.setEmail(rs.getString(3));

                array.add(u);
            }
        } catch (SQLException e) {
            System.out.println("No se puede traer la lista de usuarios");
        }
        return array;
    }

    
}
