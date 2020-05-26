/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.Entite;

import com.produits.gui.PasswordUtil;

/**
 *
 * @author USER
 */
public class User {
    
  public  int id ;
   public String usenmae;
   public String email;
   public String password;
    PasswordUtil pass = new PasswordUtil();

    
    public User() {
    }

    public User(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsenmae() {
        return usenmae;
    }

    public void setUsenmae(String usenmae) {
        this.usenmae = usenmae;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
     String ch=pass.hashPassword(password);
        ch="$2y$"+ch.substring(4);
        this.password = password;
    }
    
    
      public void setPassword2(String password) {
     String ch=pass.hashPassword(password);
        ch="$2y$"+ch.substring(4);
        this.password = ch;
    }

    public User(int id, String usenmae, String email, String password) {
        this.id = id;
        this.usenmae = usenmae;
        this.email = email;
        this.password = password;
    }

    public User(String usenmae, String email, String password) {
        this.usenmae = usenmae;
        this.email = email;
         String ch=pass.hashPassword(password);
        ch="$2y$"+ch.substring(4);
        this.password = ch;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", usenmae=" + usenmae + ", email=" + email + ", password=" + password + ", pass=" + pass + '}';
    }

  


    public User(String usenmae) {
        this.usenmae = usenmae;
    }

   

  
    
    
    
}
