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

import com.codename1.io.AccessToken;
import com.codename1.social.Login;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.util.Resources;
import com.produits.Entite.User;
import com.produits.service.ServiceUser;
import java.util.ArrayList;

/**
 * GUI builder created Form
 *
 * @author Shai Almog
 */
public class SignInForm extends com.codename1.ui.Form {
          PasswordUtil pass = new PasswordUtil();
          ArrayList<User> users=ServiceUser.getInstance().getAllTasks();
          private AccessToken token;
          int u;
          User user;
          ServiceUser s;
          Resources res;
        public SignInForm() {
        this(com.codename1.ui.util.Resources.getGlobalResources());
    }
    
    public SignInForm(com.codename1.ui.util.Resources resourceObjectInstance) {
        initGuiBuilderComponents(resourceObjectInstance);
        getTitleArea().setUIID("Container");
        getToolbar().setUIID("Container");
        getToolbar().getTitleComponent().setUIID("SigninTitle");
        FontImage mat = FontImage.createMaterial(FontImage.MATERIAL_CLOSE, "SigninTitle", 3.5f);
        getToolbar().addCommandToLeftBar("", mat, e -> new SplashForm().show());
        getContentPane().setUIID("SignInForm");
    }

//-- DON'T EDIT BELOW THIS LINE!!!
    private com.codename1.ui.Container gui_Container_1 = new com.codename1.ui.Container(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
    private com.codename1.ui.Label gui_Label_1 = new com.codename1.ui.Label();
    private com.codename1.ui.ComponentGroup gui_Component_Group_1 = new com.codename1.ui.ComponentGroup();
    private com.codename1.ui.TextField login = new com.codename1.ui.TextField();
    private com.codename1.ui.TextField password = new com.codename1.ui.TextField();
    private com.codename1.ui.Button loginButton = new com.codename1.ui.Button();
    private com.codename1.ui.Button gui_Button_3 = new com.codename1.ui.Button();
    private com.codename1.ui.Button signup = new com.codename1.ui.Button();


// <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void guiBuilderBindComponentListeners() {
        EventCallbackClass callback = new EventCallbackClass();
        loginButton.addActionListener(callback);
        signup.addActionListener(callback);
       
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

            if(sourceComponent == loginButton) {
                onButton_2ActionEvent();
            }
            
            if(sourceComponent == signup) {
                signup_ActionEvent();
            }
      
        }

        public void dataChanged(int type, int index) {
        }

        private void signup_ActionEvent() {
                 signup.addActionListener(new ActionListener() {
            
         @Override
         public void actionPerformed(ActionEvent evt) {
             SignUpForm su= new SignUpForm();
             su.show();
         }
         }  );                   
        }
    }
    private void initGuiBuilderComponents(com.codename1.ui.util.Resources resourceObjectInstance) {
        guiBuilderBindComponentListeners();
        setLayout(new com.codename1.ui.layouts.BorderLayout());
        setTitle("Sign In");
        setName("SignInForm");
        addComponent(com.codename1.ui.layouts.BorderLayout.CENTER, gui_Container_1);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        gui_Container_1.addComponent(gui_Label_1);
        gui_Container_1.addComponent(gui_Component_Group_1);
        gui_Component_Group_1.setName("Component_Group_1");
        gui_Component_Group_1.addComponent(login);
        gui_Component_Group_1.addComponent(password);
        login.setHint("Login");
        password.setName("Text_Field_2");
        
        password.setHint("mot de passe");
        login.setName("Text_Field_1");
        gui_Container_1.addComponent(loginButton);
        gui_Container_1.addComponent(gui_Button_3);
        gui_Label_1.setUIID("CenterLabel");
        gui_Label_1.setName("Label_1");
        gui_Label_1.setIcon(resourceObjectInstance.getImage("logo.png"));
        gui_Component_Group_1.setName("Component_Group_1");
        loginButton.setText("Sign In");
        loginButton.setName("Button_2");
        gui_Button_3.setText("Forgot Your Password");
        gui_Button_3.setUIID("CenterLabelSmall");
        gui_Button_3.setName("Button_3");
        addComponent(com.codename1.ui.layouts.BorderLayout.SOUTH, signup);
        gui_Container_1.setScrollableY(true);
        gui_Container_1.setName("Container_1");
        signup.setText("Create New Account");
        signup.setUIID("CenterLabel");
        signup.setName("Button_1");
    }// </editor-fold>

//-- DON'T EDIT ABOVE THIS LINE!!!
    public User onButton_2ActionEvent() {
              loginButton.addActionListener(new ActionListener() {
             int i=0;
            
         @Override
         public void actionPerformed(ActionEvent evt) {
                 for (int i = 0; i < users.size(); i++) {
                     if(users.get(i).getUsenmae().equals(login.getText()) && pass.checkPassword(password.getText(), users.get(i).getPassword()))
                     {
                         
                      
                         System.out.println(users.get(i).getId());
                        InboxForm n=  new InboxForm();
                    
                              user= users.get(i);
                             // new UpdateUser(res,user).show();
                                                   //  new AddQuestionUser(user).show();
                        n.setU(user);
                        n.show();
                        BaseForm p= new BaseForm();
                        p.setU(user);
                      
                        
                       break;
                     }
                    
                     
                  
                 
                }
                 
         }
            
                          
     
     });
      return user;        
    }

  
    

}
