/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.produits.gui;

import com.codename1.components.Accordion;
import com.codename1.components.MultiButton;
import com.codename1.ui.FontImage;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.util.Resources;
import com.produits.Entite.Question;
import com.produits.Entite.Reponse;
import com.produits.service.ServiceQuestion;
import com.produits.service.ServiceReponse;
import java.util.List;

/**
 *
 * @author PC
 */
public class ListReponses extends BaseForm{
   
    public ListReponses(com.codename1.ui.util.Resources resourceObjectInstance){
                initGuiBuilderComponents(resourceObjectInstance);
             installSidemenu(resourceObjectInstance);
                    

            FontImage arrowDown = FontImage.createMaterial(FontImage.MATERIAL_KEYBOARD_ARROW_DOWN, "Label", 3);

             ServiceReponse sr= new ServiceReponse();
             List<Reponse> list= sr.getAllReponses();
             for (Reponse r : list){
                 addButtonBottom(arrowDown,r.getQuestion().getQuestionArea(), 0xd997f1, true);
             
                  add(r.getRepQuestion());
                          
                    //add(r.getQuestion().getQuestionArea());
           
                
                
                      
             }
               

          
    }

    private void initGuiBuilderComponents(Resources resourceObjectInstance) {
          setLayout(new com.codename1.ui.layouts.BoxLayout(com.codename1.ui.layouts.BoxLayout.Y_AXIS));
        setTitle("Reponses");
        setName("TrendingForm");
        
    }
     private void addButtonBottom(Image arrowDown, String text, int color, boolean first) {
        MultiButton finishLandingPage = new MultiButton(text);
        finishLandingPage.setEmblem(arrowDown);
        finishLandingPage.setUIID("Container");
        finishLandingPage.setUIIDLine1("TodayEntry");
        finishLandingPage.setIcon(createCircleLine(color, finishLandingPage.getPreferredH(),  first));
        finishLandingPage.setIconUIID("Container");
        add(FlowLayout.encloseIn(finishLandingPage));
        
    }
    
    private Image createCircleLine(int color, int height, boolean first) {
        Image img = Image.createImage(height, height, 0);
        Graphics g = img.getGraphics();
        g.setAntiAliased(true);
        g.setColor(0xcccccc);
        int y = 0;
        if(first) {
            y = height / 6 + 1;
        }
        g.drawLine(height / 2, y, height / 2, height);
        g.drawLine(height / 2 - 1, y, height / 2 - 1, height);
        g.setColor(color);
        g.fillArc(height / 2 - height / 4, height / 6, height / 2, height / 2, 0, 360);
        return img;
    }

}
