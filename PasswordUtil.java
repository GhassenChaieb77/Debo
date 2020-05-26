/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.gui;

import org.mindrot.jbcrypt.BCrypt;






/**
 *
 * @author USER
 */
public class PasswordUtil {
    	private static final int workload = 15;

       public boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;
		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");
		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}
       
       
       
       
       public  String hashPassword(String password_plaintext) {
		String salt = BCrypt.gensalt(workload);

                String hashed_password = BCrypt.hashpw(password_plaintext, salt);

		return(hashed_password);
	}
    
}
