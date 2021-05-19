/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.notifications.LocalNotification;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Reponse;
import com.mycompany.services.ServiceReponse;
//import static java.awt.SystemColor.text;
//import static java.awt.SystemColor.text;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.entities.Question;
import com.mycompany.services.ServiceQuestion;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class AjouterReponse extends BaseForm {
    Form current;
    //combo 0
     private ArrayList<Question> partenaires;
     //graph combooo
     ComboBox comboQ = new ComboBox();
    public AjouterReponse(Resources res)
    { 
       super("Ajout Reponse",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("AJouter Reponse");
        getContentPane().setScrollVisible(false);
        //combo  1
         partenaires = new ServiceQuestion().afficherQuestion();
    

   
        tb.addSearchCommand(s -> { 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        } );
        
        
        
        
        
        Tabs swipe = new Tabs();
        Label s1= new Label ();
         Label s2= new Label ();
         
        addTab(swipe,s1,res.getImage("nour.png"),"","",res);
        
        
        ///********************************************************/
          swipe.setUIID("Container");
        swipe.getContentPane().setUIID("Container");
        swipe.hideTabs();

        ButtonGroup bg = new ButtonGroup();
        int size = Display.getInstance().convertToPixels(1);
        Image unselectedWalkthru = Image.createImage(size, size, 0);
        Graphics g = unselectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAlpha(100);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        Image selectedWalkthru = Image.createImage(size, size, 0);
        g = selectedWalkthru.getGraphics();
        g.setColor(0xffffff);
        g.setAntiAliased(true);
        g.fillArc(0, 0, size, size, 0, 360);
        RadioButton[] rbs = new RadioButton[swipe.getTabCount()];
        FlowLayout flow = new FlowLayout(CENTER);
        flow.setValign(BOTTOM);
        Container radioContainer = new Container(flow);
        for (int iter = 0; iter < rbs.length; iter++) {
            rbs[iter] = RadioButton.createToggle(unselectedWalkthru, bg);
            rbs[iter].setPressedIcon(selectedWalkthru);
            rbs[iter].setUIID("Label");
            radioContainer.add(rbs[iter]);
        }

        rbs[0].setSelected(true);
        swipe.addSelectionListener((i, ii) -> {
            if (!rbs[ii].isSelected()) {
                rbs[ii].setSelected(true);
            }
        });

        Component.setSameSize(radioContainer, s1, s2);
        add(LayeredLayout.encloseIn(swipe, radioContainer));

        ButtonGroup barGroup = new ButtonGroup();
        RadioButton mesListes = RadioButton.createToggle("Mes Reclamations", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Reclamer", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        new ListeReponseForm(res).show();//  ListReclamationForm a = new ListReclamationForm(res);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(3, mesListes, liste, partage),
                FlowLayout.encloseBottom(arrow)
        ));

        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
        bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

       
        
        
        
        
        
        
        
        
        
        
        
        
            
        /*******************************************************************/
        //combo 2
           for (Question p : partenaires) {
           // comboQ.addItem(p.getCodeP());
              comboQ.addItem(p.getIdQ());
            
        }  
           //combo 3
            this.add(comboQ);
        
        
        
        
        TextField textR1 = new TextField("", "Entrer La bonne réponse");
        textR1.setUIID("TextFieldBalck");
        addStringValue ("textR1",textR1);
  TextField textR2 = new TextField("", "Entrer La première réponse incorrecte");
        textR2.setUIID("TextFieldBalck");
        addStringValue ("textR2",textR2);
        
         TextField textR3 = new TextField("", "Entrer La deuxieme réponse incorrecte");
        textR3.setUIID("TextFieldBalck");
        addStringValue ("textR3",textR3);
        
        TextField textR4 = new TextField("", "Entrer La troisieme réponse incorrecte");
        textR4.setUIID("TextFieldBalck");
        addStringValue ("textR4",textR4);


        
//        this.add(comboQ);
        
        Button btnAjouter = new Button ("Ajouter");
        addStringValue("", btnAjouter);
        
        //on click btn event 
        
        btnAjouter.addActionListener((e)-> { 
        
        
        try { 
            if (textR1.getText() == "" || textR2.getText() == "" || textR3.getText() == "" || textR4.getText() == "")
            {
                Dialog.show("veuillez verifier vos donnees","", "annuler", "ok");
            }
            else 
            {
                 InfiniteProgress ip = new InfiniteProgress(); //lading after insert data
                 final Dialog iDialog = ip.showInfiniteBlocking();
                  /// combooo
           
                 //Integer.valueOf(reduction.getText())
                 Reponse promo = new Reponse(String.valueOf(textR1.getText()),
                       String.valueOf(textR2.getText())
                         , String.valueOf(textR3.getText())
                         ,String.valueOf(textR4.getText())
                         
                 
                 
                 );
               // Question s = (Question)comboQ.getSelectedItem();
         int s = (Integer)comboQ.getSelectedItem();
             // promo.setIdP(s.getIdP());
              promo.setIdQ(s);
                 /* Reponse p = (Reponse)comboQ.getSelectedItem();
                
               promo.setIdP(p.getIdP());*/
                 System.out.println("data promo else gui"+promo);
                 // appel fct ajout service
                 ServiceReponse.getInstance().ajouterReponse(promo);
                 
                 iDialog.dispose(); //NAHIW LOADING BAED AJOUT
                 
                   new ListeReponseForm(res).show();
                 
                 refreshTheme(); 
   
      
         
                    }
            
        } catch (Exception ex)
        {
            ex.printStackTrace();
            System.err.println("errrrrrrrrrror GUI AJOUTTTTTTTTTTTTTTTT ");
        }
        
        
        
        
        });
        
        
        
        
  
        
        
  
        
        
  
        
        
        
        
        
        
        
        
    }

    private void addStringValue(String s, Component v) {
       add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
       add (createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text, Resources res) {
        int size = Math.min (Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight());
        if (image.getHeight()< size)
        {
            image = image.scaledHeight(size);
            
            
            
        }
      if (image.getHeight()> Display.getInstance().getDisplayHeight() / 2)
      {  
          image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);    
      
      }
      
      ScaleImageLabel imageScale = new ScaleImageLabel(image);
      imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
         Label overLay = new Label ("","ImageOverlay");
         Container page1=
                 LayeredLayout.encloseIn(
                 
                 imageScale,
                         overLay,
                                 BorderLayout.south(
                                         BoxLayout.encloseY(
                                                 new SpanLabel (text,"LargeWhiteText"),
                                                 //nooo

                                         spacer
                                         
                                         
                                 )
                 )
                 
                 
                 );
                 
                 
      swipe.addTab ("",res.getImage("nour.png"),page1);
      
      
      
    }
    
    
    public void bindButtonSelection  (Button btn , Label l)
    {
        btn.addActionListener(e-> 
        
        
        { 
            if (btn.isSelected())
            { 
                updateArrowPosition(btn,l);
            }
        }
        
        
        
        
        
        
        
        
        
        );
    }

    private void updateArrowPosition(Button btn, Label l) {
     l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
     l.getParent().repaint();;
           
        
        
    }
    
    
    
    
    //cobo
    public ArrayList<Question> getQuestion() {
        return partenaires;
    }

    public void setQuestions(ArrayList<Question> partenaires) {
        this. partenaires=  partenaires;
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
