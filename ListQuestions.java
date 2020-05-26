/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.gui;

import com.codename1.components.MultiButton;

import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;

import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Slider;
import com.codename1.ui.SwipeableContainer;
import com.codename1.ui.TextArea;

import com.codename1.ui.animations.CommonTransitions;
import com.codename1.ui.geom.Rectangle;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.plaf.Border;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;

import com.produits.Entite.Question;
import com.produits.Entite.User;

import com.produits.service.ServiceQuestion;

import java.util.List;


/**
 *
 * @author PC
 */
public class ListQuestions extends BaseForm {

        Form hi = new Form("", new BoxLayout(BoxLayout.Y_AXIS));
        User t;
     public ListQuestions(Resources res,User u) {
     this.t=u;
        hi.getToolbar().setTitleComponent(
                FlowLayout.encloseCenterMiddle(
                        new Label("FAQS", "Title")
                        
                )
        );
        installSidemenu(res);
        
//       hi.getToolbar().addCommandToRightBar("", res.getImage(""), e -> {});
        ServiceQuestion serviceQuestion = new ServiceQuestion();
      

       List<Question> list = serviceQuestion.getAllQuestions(u);
          for (Question l:list)
      {
    
      hi.add(createRankWidget(l,l.getHeadline(),l.getQuestionArea(),res));
    hi.showBack();
    }
    
  }
  
          public SwipeableContainer createRankWidget(Question l,String headline,String questionArea ,Resources res) {
            MultiButton button = new MultiButton(headline);  
            Button reserver = new Button("Commender");
        //add(reserver);
    

      
        button.setHeight(100);

        //button.setIcon(Image);
        button.setTextLine1(headline);
        
      //  button.setTextLine3("" + prix);
        
             
         //button.setTextLine4(Contenu);
         button.addActionListener(e->{
            
               //displayoneBook a = new displayoneBook(l,res);

             dialog(l,res);
         });
          
        reserver.addActionListener(e -> {
         
           // System.out.println(idl);
           
        });
       
    return new SwipeableContainer(FlowLayout.encloseCenterMiddle(createStarRankSlider()), 
            button);
}
private Slider createStarRankSlider() {
    Slider starRank = new Slider();

     return starRank;
}
   private void initStarRankStyle(Style s, Image star) {
    s.setBackgroundType(Style.BACKGROUND_IMAGE_TILE_BOTH);
    s.setBorder(Border.createEmpty());
    s.setBgImage(star);
    s.setBgTransparency(0);
}
       private void dialog(Question e,Resources res) {
       
        Dialog d = new Dialog(e.getHeadline());
       // String img=e.getProductpic();
        TextArea popupBody = new TextArea( e.getQuestionArea()+ "\n"    , 8, 12);
  
        popupBody.setUIID("Label");
        popupBody.setEditable(false);
        Button b = new Button("test");
        d.setLayout(new BorderLayout());
        
        d.addComponent(BorderLayout.CENTER, popupBody);
      //  d.add(BorderLayout.SOUTH,imgv);
   
        d.setTransitionInAnimator(CommonTransitions.createEmpty());
        d.setTransitionOutAnimator(CommonTransitions.createFade(300));
        Rectangle rec = new Rectangle();
        rec.setX(700);
        rec.setY(1000);
        d.showPopupDialog(rec);
    }
        private void notif()
  {
         LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("your book has been added to your Order list");
        n.setAlertTitle("Order added!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );
  }

    public void installSidemenu(Resources res) {
//       Image selection = res.getImage("selection-in-sidemenu.png");
//        
//        Image inboxImage = null;
//        if(isCurrentInbox()) inboxImage = selection;
//
//        Image trendingImage = null;
//        if(isCurrentTrending()) trendingImage = selection;
//        
//        Image calendarImage = null;
//        if(isCurrentCalendar()) calendarImage = selection;
//        
//        Image statsImage = null;
//        if(isCurrentStats()) statsImage = selection;
        
        Button inboxButton = new Button("Inbox");
        inboxButton.setUIID("SideCommand");
        inboxButton.getAllStyles().setPaddingBottom(0);
        Container inbox = FlowLayout.encloseMiddle(inboxButton, 
                new Label("18", "SideCommandNumber"));
        inbox.setLeadComponent(inboxButton);
        inbox.setUIID("SideCommand");
       inboxButton.addActionListener(e -> new InboxForm().show());
        hi.getToolbar().addComponentToSideMenu(inbox);
        
        //
        
        //getToolbar().addCommandToSideMenu("Stats", statsImage, e -> new StatsForm(res).show());
      // getToolbar().addCommandToSideMenu("Calendar", calendarImage, e -> new CalendarForm(res).show());
        hi.getToolbar().addCommandToSideMenu("Produits", null, e -> {
         displayProduits a = new displayProduits(res);
          a.hi.show();
        });
       // 
     hi.getToolbar().addCommandToSideMenu("Category", null, e -> {
         displayCategory a = new displayCategory(res);
          a.hi.show();
        });
         getToolbar().addCommandToSideMenu("FAQS", null, e-> {
              ListQuestions a= new ListQuestions(res,t);
              a.hi.show();
          });

            hi.getToolbar().addCommandToSideMenu("LogOut", null, e -> {
                new SignInForm().showBack();
        });
        
        // spacer
    
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
 
}
