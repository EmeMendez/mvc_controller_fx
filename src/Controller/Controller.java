/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.V_User;

/**
 *
 * @author Melón
 */
public class Controller {
    
    public void getUser(){
        V_User v = new V_User();
        C_User c = new  C_User(v);
        v.setVisible(true);
    }
}
