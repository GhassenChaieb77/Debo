/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.service;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.ui.events.ActionListener;
import com.produits.Entite.Question;
import com.produits.Entite.Reponse;
import com.produits.Entite.Statics;
import com.produits.gui.SignInForm;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServiceReponse {
      public ArrayList<Reponse> listereponses;
    public boolean resultOk;
    public static ServiceReponse instance;
    private ConnectionRequest req;
     private int i=0;
     
     public ServiceReponse(){
        req= new ConnectionRequest();
    }
    public static ServiceReponse getInstance(){
         if (instance == null) 
            instance = new ServiceReponse();
        
        return instance;
    }
   
    
    public ArrayList<Reponse> parseReponses(String jsonText) throws ParseException{
                    listereponses=new ArrayList<>();
       // SignInForm s = null;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");
            
            for(Map<String,Object> obj : list){
              Reponse r = new Reponse();

              
               float idreponse = Float.parseFloat(obj.get("idreponse").toString());
               r.setIdReponse((int)idreponse);
               r.setRepQuestion(obj.get("repquestion").toString());
               String question = obj.get("questionarea").toString();
               
               r.setQuestion(new Question(question));

               listereponses.add(r);
                System.out.println(listereponses);
            }
                       
        } catch (NumberFormatException ex) {
           
        } catch (IOException ex) {
        }
        return listereponses;
    }
    public ArrayList<Reponse> getAllReponses() {
         ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"/response/all";
        con.setUrl(url);
        con.setPost(false); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    listereponses = parseReponses(new String(con.getResponseData()));
                    con.removeResponseListener(this);

                } catch (ParseException ex) {

                }
            }
        });
       NetworkManager.getInstance().addToQueueAndWait(con);
     return listereponses;
    }
}
