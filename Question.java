/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.Entite;

import java.util.Date;

/**
 *
 * @author PC
 */
public class Question {
    private int idQuestion;
        int user;

    private String questionArea, headline,slug;
    private Date created_at, updated_at;
    
    public Question() {
    }

    public Question(int idQuestion, String questionArea , int user,String headline, Date created_at, Date updated_at, String slug) {
        this.idQuestion = idQuestion;
        this.questionArea = questionArea;
        this.headline = headline;
        this.slug = slug;
        this.created_at = created_at;
        this.updated_at = updated_at;
        this.user = user;
    }

    public Question(String questionArea, int user, String headline) {
        this.questionArea = questionArea;
        this.headline = headline;
        this.user = user;
    }
  

    public Question(String questionArea, String headline) {
        this.questionArea = questionArea;
        this.headline = headline;
    }

  
    public int getIdQuestion() {
        return idQuestion;
    }

    public void setIdQuestion(int idQuestion) {
        this.idQuestion = idQuestion;
    }

   

    public String getQuestionArea() {
        return questionArea;
    }

    public void setQuestionArea(String questionArea) {
        this.questionArea = questionArea;
    }

    public String getHeadline() {
        return headline;
    }

    public void setHeadline(String headline) {
        this.headline = headline;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public Date getCreated_at() {
        return created_at;
    }

    public void setCreated_at(Date created_at) {
        this.created_at = created_at;
    }

    public Date getUpdated_at() {
        return updated_at;
    }

    public void setUpdated_at(Date updated_at) {
        this.updated_at = updated_at;
    }

    public int getUser() {
        return user;
    }

    public void setUser(int user) {
        this.user = user;
    }

  

    public Question(String questionArea) {
        this.questionArea = questionArea;
    }

    @Override
    public String toString() {
        return "Question{" + "questionArea=" + questionArea + '}';
    }

   


    

    
    
    
}
