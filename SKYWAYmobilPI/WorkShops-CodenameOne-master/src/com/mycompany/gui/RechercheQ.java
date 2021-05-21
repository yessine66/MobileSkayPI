/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.MultiButton;
import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Question;
import com.mycompany.gui.BaseForm;
import com.mycompany.gui.ListeQuestionForm;
import com.mycompany.services.ServiceQuestion;
import java.util.ArrayList;

/**
 *
 * @author Mrad
 */
public class RechercheQ extends BaseForm {
    Form current;
    public RechercheQ(Resources res ) {
        
         super("Recherche Question",BoxLayout.y());
        Toolbar tb = new Toolbar(true);
        current =this;
        setToolbar(tb);
        super.addSideMenu(res);
        getTitleArea().setUIID("container");
        getContentPane().setScrollVisible(false);
        Toolbar.setGlobalToolbar(true);
             
 

    

        Form hi = new Form("Search", BoxLayout.y());
        hi.add(new InfiniteProgress());
        Display.getInstance().scheduleBackgroundTask(()-> {
            // this will take a while...
            ArrayList<Question> al = ServiceQuestion.getInstance().afficherQuestion();
            Display.getInstance().callSerially(() -> {
                
                for(Question c : al) {
                    MultiButton m = new MultiButton();
                    m.setTextLine1(c.getTextQ());
                    
                    hi.add(m);
                }
                Button b = new Button("Search");
                b.addActionListener(e -> {
                    new ListeQuestionForm(res).show();
                });
                current.add(b);
                hi.revalidate();
            });
        });

        
        tb.addSearchCommand(e -> {
            String text = (String)e.getSource();
                if(text == null || text.length() == 0) {
                // clear search
                    for(Component cmp : current.getContentPane()) {
                        cmp.setHidden(false);
                        cmp.setVisible(true);
                    }
                hi.getContentPane().animateLayout(150);
            } else {
                    text = text.toLowerCase();
                    for(Component cmp : current.getContentPane()) {
                        if(cmp instanceof MultiButton) {
                            MultiButton mb = (MultiButton)cmp;
                            String line1 = mb.getTextLine1();
                            String line2 = mb.getTextLine2();
                            boolean show = line1 != null && line1.toLowerCase().indexOf(text) > -1 ||
                            line2 != null && line2.toLowerCase().indexOf(text) > -1;
                            mb.setHidden(!show);
                            mb.setVisible(show);
                    }}
                        hi.getContentPane().animateLayout(150);
        }
        });
       
        }
        
    }
  
   

