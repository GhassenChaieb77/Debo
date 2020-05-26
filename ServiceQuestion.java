/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.service;

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;

import com.codename1.io.CharArrayReader;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;

import com.codename1.ui.events.ActionListener;
import com.produits.Entite.Question;
import com.produits.Entite.Statics;
import com.produits.Entite.User;
import com.produits.gui.SignInForm;



import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author PC
 */
public class ServiceQuestion {
    public ArrayList<Question> questions;
    public boolean resultOk;
    public static ServiceQuestion instance;
    private ConnectionRequest req;
     private int i=0;
     
     public ServiceQuestion(){
        req= new ConnectionRequest();
    }
    public static ServiceQuestion getInstance(){
         if (instance == null) 
            instance = new ServiceQuestion();
        
        return instance;
    }
    public boolean ajouterQuestion(Question q,User id){
        String url = Statics.BASE_URL+"/questions/"+id.getId()+"/add?questionArea="+q.getQuestionArea()+"&headline="+q.getHeadline();
        req.setUrl(url);
        System.out.println(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {         
                    resultOk = req.getResponseCode()==200;
                    req.removeResponseListener(this);
            }
        });
       NetworkManager.getInstance().addToQueueAndWait(req);
               System.out.println(resultOk);

        return resultOk;
    }
    
    public ArrayList<Question> parseQuestions(String jsonText) throws ParseException{
                    questions=new ArrayList<>();
       // SignInForm s = null;
        try {
            JSONParser j = new JSONParser();

            Map<String, Object> experiences = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));

            List<Map<String, Object>> list = (List<Map<String, Object>>) experiences.get("root");
            
            for(Map<String,Object> obj : list){
                Question q = new Question();
              
               float idquestion = Float.parseFloat(obj.get("idquestion").toString());
               q.setIdQuestion((int)idquestion);
               q.setQuestionArea(obj.get("questionarea").toString());
               q.setHeadline(obj.get("headline").toString());
//               float id = Float.parseFloat(obj.get("id").toString());
//               q.setUser((int) id);
               
             //  q.setSlug(obj.get("slug").toString());

               questions.add(q);
                System.out.println(questions);
            }
                       
        } catch (NumberFormatException ex) {
           
        } catch (IOException ex) {
        }
        return questions;
    }
    public ArrayList<Question> getAllQuestions(User id) {
         ConnectionRequest con = new ConnectionRequest();
       String url = Statics.BASE_URL+"/questions/user/"+id.getId();
        con.setUrl(url);
        System.out.println(url);
        con.setPost(false); 
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                try {
                    questions = parseQuestions(new String(con.getResponseData()));
                    con.removeResponseListener(this);

                } catch (ParseException ex) {

                }
            }
        });
       NetworkManager.getInstance().addToQueueAndWait(con);
     return questions;
    }
}
