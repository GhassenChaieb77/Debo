/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.gui;
import com.codename1.capture.Capture;
import com.codename1.components.MultiButton;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.io.Util;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextField;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.codename1.ui.util.Resources;
import com.codename1.ui.util.UIBuilder;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.push.Push;
import com.codename1.push.PushCallback;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.produits.Entite.Question;
import com.produits.Entite.User;
import com.produits.service.ServiceQuestion;
import java.io.IOException;
import java.util.Date;


/**
 *
 * @author PC
 */
public class AjoutQuestion extends BaseForm implements PushCallback,LocalNotificationCallback{
User t;
   Resources theme;
    public AjoutQuestion(com.codename1.ui.util.Resources resourceObjectInstance,User u) {
            this.t=u;
        initGuiBuilderComponents(resourceObjectInstance);
            installSidemenu(resourceObjectInstance);
//        getTitleArea().setUIID("Container");
//        getToolbar().setUIID("Container");
//        
//       getToolbar().getTitleComponent().setUIID("SigninTitle");
//       // getContentPane().setUIID("SignInForm");

    
    }
     private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.Button loginButton = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
  
    
        TextField question= new TextField("","Votre Question ..");
        TextField headline= new TextField("","Titre ..");
        Button btnajoutquestion= new Button("Ajouter");
   
        
        private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Ajouter Question");
       // setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(question);
        gui_Component_Group_1.addComponent(headline);
        gui_Component_Group_1.addComponent(btnajoutquestion);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Component_Group_1.setName("Component_Group_1");
     
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        
    }// </editor-fold>

  private void guiBuilderBindComponentListeners() {
        AjoutQuestion.EventCallbackClass callback = new AjoutQuestion.EventCallbackClass();
        btnajoutquestion.addActionListener(callback);
    }

  
    @Override
    public void push(String value) {
        System.out.println("Received push message: "+value);
    }

    @Override
    public void registeredForPush(String deviceId) {
        System.out.println("The Push ID for this device is "+Push.getPushKey());
    }

    @Override
    public void pushRegistrationError(String error, int errorCode) {
        System.out.println("An error occurred during push registration.");
    }

    @Override
    public void localNotificationReceived(String notificationId) {
        System.out.println("Notification Reçu : "+notificationId);
    }
  
  class EventCallbackClass implements com.codename1.ui.events.ActionListener, com.codename1.ui.events.DataChangedListener {
        private com.codename1.ui.Component cmp;
        public EventCallbackClass(com.codename1.ui.Component cmp) {
            this.cmp = cmp;
        }

        public EventCallbackClass() {
        }

        public void actionPerformed(com.codename1.ui.events.ActionEvent ev) {
            com.codename1.ui.Component sourceComponent = ev.getComponent();
            if(sourceComponent.getParent().getLeadParent() != null) {
                sourceComponent = sourceComponent.getParent().getLeadParent();
            }

            if(sourceComponent == btnajoutquestion) {
                onButton_2ActionEvent();

            }
            
          
      
        }

        public void dataChanged(int type, int index) {
        }

    
       public void onButton_2ActionEvent() {

             btnajoutquestion.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                   if ((question.getText().length()==0)||(headline.getText().length()==0 ) )
                       Dialog.show("Information", "Vérifier votre champ", new Command("Ok"));
                   else {

                       try {
                         Question q= new Question(question.getText(),headline.getText());
                            if (new ServiceQuestion().ajouterQuestion(q,u) ){
                                Dialog.show("Information", "Question Envoyé", new Command("Ok"));
                                LocalNotification n = new LocalNotification();
                                n.setId("demo-notification");
                                n.setAlertBody("Votre Question a été envoyé avec succés ");
                                n.setAlertTitle("Envoyé!");
                                n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


                                Display.getInstance().scheduleLocalNotification(
                                        n,
                                        System.currentTimeMillis() + 10 * 1000, // fire date/time
                                        LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
                                );
                                        ListQuestions a=new ListQuestions(theme, u);
                                        a.hi.show();
                                        a.refreshTheme();
                                                        }else{
                                Dialog.show("Erreur","Erreur de Serveur", new Command("Ok"));
                            }
                       } catch (NumberFormatException e) {
                           Dialog.show("Erreur", "", new Command("Ok"));
                       }
                   }
            }
        });
             
            }
               
         
      
           
    }
}
  
