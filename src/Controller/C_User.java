/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DAO.UserDAO;
import Model.User;
import View.V_User;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Melón
 */
public class C_User implements ActionListener,KeyListener {
    V_User v = new V_User();
    User  u;
    UserDAO dao = new UserDAO();

    public C_User(V_User v) {
        this.v = v;
        this.v.getBtn_add().addActionListener(this);
        this.v.getTxt_name().addKeyListener(this);//para solo entrada de letrassS
        this.Table();
    }

    //METODOS SOBRESCRITOS
    @Override
    public void actionPerformed(ActionEvent e) {
        insert_user(e);
    }   

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getSource() == v.getTxt_name()){
            char c = e.getKeyChar();
        if((c<'a' || c>'z') && (c<'A' || c>'Z') && (c!=(char)KeyEvent.VK_SPACE)){                e.consume();
            e.consume();
        }
      } 
    }
    
    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }
    
    //METODOS PROPIOS
    public boolean Empty(){
        boolean empty= false;
        boolean[] c = new boolean[3];
        c[0]=v.getTxt_name().getText().isEmpty();
        c[1]=v.getTxt_pass().getText().isEmpty();
        c[2]=v.getTxt_mail().getText().isEmpty();
        if(c[0] || c[1] || c[2]){
            empty = true;
        }
        return empty;
    }    
    
    public void Clean(){
        v.getTxt_name().setText("");
        v.getTxt_mail().setText("");
        v.getTxt_pass().setText("");
    }
    
    public void insert_user(ActionEvent e){
        if(e.getSource() == v.getBtn_add()){
           if(!Empty()){
            u=new User();
            u.setName(v.getTxt_name().getText());
            u.setPass(v.getTxt_pass().getText());
            u.setEmail(v.getTxt_mail().getText());
            if(dao.insertUser(u)){
                JOptionPane.showMessageDialog(null,"Registro exitosoo");
                Clean();
                Table();
            }
           }else{JOptionPane.showMessageDialog(null,"Debe llenar todos los campos");}
        }    
    }
    
    public void update_user(ActionEvent e){
   
    }

    public void delete_user(ActionEvent e){
    }
    

    public void Table(){
        clearTable();
        DefaultTableModel t = (DefaultTableModel) v.getTbl_user().getModel();
        try {
        ArrayList<User> array= dao.getUsers();
        Object []fila= new Object[2];
        for(User u : array){
            fila[0]=u.getName();
            fila[1]=u.getEmail();
      
            t.addRow(fila);
        }
           v.getTbl_user().setModel(t);
        }
        catch(Exception e ){
         JOptionPane.showMessageDialog(null, "no se puede llena la tabla" + e );
        }    
    
    }

    public void clearTable(){
        try {
            DefaultTableModel modelo=(DefaultTableModel) v.getTbl_user().getModel();
            int filas=v.getTbl_user().getRowCount();
            for (int i = 0;filas>i; i++) {
                modelo.removeRow(0);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al limpiar la tabla.");
        }    
    }     
    
    
        
}
