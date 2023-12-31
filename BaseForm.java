/*
 * Copyright (c) 2016, Codename One
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated 
 * documentation files (the "Software"), to deal in the Software without restriction, including without limitation 
 * the rights to use, copy, modify, merge, publish, distribute, sublicense, and/or sell copies of the Software, 
 * and to permit persons to whom the Software is furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all copies or substantial portions 
 * of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR IMPLIED, 
 * INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY, FITNESS FOR A 
 * PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT 
 * HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF 
 * CONTRACT, TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE SOFTWARE 
 * OR THE USE OR OTHER DEALINGS IN THE SOFTWARE. 
 */

package com.produits.gui;

import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.produits.Entite.User;

/**
 * Utility methods common to forms e.g. for binding the side menu
 *
 * @author Shai Almog
 */
public class BaseForm extends Form {
    
    public static  User u;
    
    public void installSidemenu(Resources res) {
        Image selection = res.getImage("selection-in-sidemenu.png");
        
        Image inboxImage = null;
        if(isCurrentInbox()) inboxImage = selection;

        Image trendingImage = null;
        if(isCurrentTrending()) trendingImage = selection;
        
        Image calendarImage = null;
        if(isCurrentCalendar()) calendarImage = selection;
        
        Image statsImage = null;
        if(isCurrentStats()) statsImage = selection;
        
        Button inboxButton = new Button("Accueil", inboxImage);
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
                new Label("", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
        inboxButton.addActionListener(e -> new InboxForm(res).show());
        getToolbar().addComponentToSideMenu(inbox);
          getToolbar().addCommandToSideMenu("Réclamations", null, e -> { displayClaim a = new displayClaim(res);
          a.hi.show();});
                getToolbar().addCommandToSideMenu("Ajout Réclamation", null, e -> { AjoutRéclamation a = new AjoutRéclamation(u);
          a.su.show();});
        getToolbar().addCommandToSideMenu("Employe", null, e -> { displayEmploye a = new displayEmploye(res);
                  a.hi.show();});
         getToolbar().addCommandToSideMenu("Absence", null, e -> { addAbsence a = new addAbsence();
          a.su.show();});
                getToolbar().addCommandToSideMenu("Commandes", null, e -> { ValiderPanierForm a = new ValiderPanierForm(res,u);
          a.show();});
        //getToolbar().addCommandToSideMenu("Stats", statsImage, e -> new StatsForm(res).show());
       getToolbar().addCommandToSideMenu("Produits", null, e -> { displayProduits a = new displayProduits(res);
          a.hi.show();});
     getToolbar().addCommandToSideMenu("Category", null, e -> { displayCategory a = new displayCategory(res);
          a.hi.show();});
           
          getToolbar().addCommandToSideMenu("Ajouter Question", null, e-> {
             AjoutQuestion a= new AjoutQuestion(res,u);
              a.show();
          });
          getToolbar().addCommandToSideMenu("FAQs", null, e-> {
               ListReponses a= new ListReponses(res);
              a.show();
          });
        
           /* getToolbar().addCommandToSideMenu("Vos Questions", null, e-> {
               ListQuestions a= new ListQuestions(res,u);
              a.hi.show();
          });*/
             getToolbar().addCommandToSideMenu("Profile", null, e-> {
                 Profile a = new Profile(res,u);
              a.show();
          });
         /*  getToolbar().addCommandToSideMenu("Ajouter Question", null, e-> {
              AjoutQuestion a= new AjoutQuestion(res,u);
              a.show();
          });*/
           getToolbar().addCommandToSideMenu("Ajouter Produit", null, e-> {
              AjoutProd a= new AjoutProd();
              a.su.show();
          });     
            getToolbar().addCommandToSideMenu("LogOut", null, e -> {
                new SignInForm().showBack();
        });
// spacer
      /*  getToolbar().addComponentToSideMenu(new Label(" ", "SideCommand"));
        getToolbar().addComponentToSideMenu(new Label(res.getImage("profile_image.png"), "Container"));
        getToolbar().addComponentToSideMenu(new Label("Detra Mcmunn", "SideCommandNoPad"));
        getToolbar().addComponentToSideMenu(new Label("Long Beach, CA", "SideCommandSmall"));*/
        
    }

        
    protected boolean isCurrentInbox() {
        return false;
    }
    
    protected boolean isCurrentTrending() {
        return false;
    }

    protected boolean isCurrentCalendar() {
        return false;
    }

    protected boolean isCurrentStats() {
        return false;
    }

    public User getU() {
        return u;
    }

    public void setU(User u) {
        this.u = u;
    }
    
    
}
