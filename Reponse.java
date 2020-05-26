/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.Entite;

/**
 *
 * @author PC
 */
public class Reponse {
    private int idReponse;
    private String repQuestion;
    Question question;
            String test;

    public Reponse() {
    }

    public Reponse(int idReponse, String repQuestion, Question question) {
        
        this.idReponse = idReponse;
        this.repQuestion = repQuestion;
        this.question = question;
    }

    public int getIdReponse() {
        return idReponse;
    }

    public void setIdReponse(int idReponse) {
        this.idReponse = idReponse;
    }

    public String getRepQuestion() {
        return repQuestion;
    }

    public void setRepQuestion(String repQuestion) {
        this.repQuestion = repQuestion;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }

    @Override
    public String toString() {
        return "Reponse{" + "idReponse=" + idReponse + ", repQuestion=" + repQuestion + ", question=" + question.getQuestionArea() + '}';
    }

    public Reponse(String repQuestion, Question question) {
        this.repQuestion = repQuestion;
        this.question = question;
  
    }


  
    
    
}
