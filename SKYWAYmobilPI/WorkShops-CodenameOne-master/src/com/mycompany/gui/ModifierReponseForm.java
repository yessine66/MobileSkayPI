/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.FloatingHint;
import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import static com.codename1.ui.Component.TOP;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Question;
import com.mycompany.entities.Reponse;
import com.mycompany.services.ServiceQuestion;
import com.mycompany.services.ServiceReponse;
import java.util.ArrayList;


/**
 *
 * @author Lenovo
 */
public class ModifierReponseForm extends BaseForm {
    Form current;
    //combo
     private ArrayList<Question> partenaires;
     //graph combooo
     ComboBox comboQ = new ComboBox();
    public ModifierReponseForm (Resources res , Reponse pr)
    { 
        
        
         super("Modifier Reponse",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("AJouter Reponse");
        getContentPane().setScrollVisible(false);
        
     //combo  1
         partenaires = new ServiceQuestion().afficherQuestion();
       
        
      //  super.addSideMenu (res);
        
      TextField idR= new TextField(String.valueOf(pr.getIdR()), "Id",20,TextField.ANY);
        System.out.println("ID RRR MELOWEL li bech nmodifwh "+pr.getIdR());
      TextField TextR1= new TextField(pr.getTextR1(), "Bonne réponse ",20,TextField.ANY);
      TextField TextR2= new TextField(pr.getTextR2(), " premiere mauvaise reponse ",20,TextField.ANY);
      TextField TextR3= new TextField(pr.getTextR3(), "deuxieme mauvaise réponse ",20,TextField.ANY);
      TextField TextR4= new TextField(pr.getTextR4(), "troixieme mauvaise réponse ",20,TextField.ANY);
        System.out.println(TextR1.getText());
        System.out.println(TextR2.getText());
        System.out.println(TextR3.getText());
        System.out.println(TextR4.getText());
           for (Question p : partenaires) {
           // comboQ.addItem(p.getCodeP());
              comboQ.addItem(p.getIdQ());
            
        }  
           //combo 3
            this.add(comboQ);
        
        
        
        
   
        
        
     idR.setUIID("NewTopLine");
       TextR1.setUIID("NewTopLine");
        TextR2.setUIID("NewTopLine");
       TextR3.setUIID("NewTopLine"); 
        TextR4.setUIID("NewTopLine");
      //idP.setUIID("NewTopLine"); 
          
          
          
        TextR1.setSingleLineTextArea(true);
        TextR2.setSingleLineTextArea(true);
        TextR3.setSingleLineTextArea(true);
        TextR4.setSingleLineTextArea(true);
 //   idR.setSingleLineTextArea(true);   
           //idP.setSingleLineTextArea(true);
        
    
        
        
        Button btnModifier = new Button ("Modifier");
       // btnModifier.setUIID("button");
        
        btnModifier.addPointerPressedListener(l ->
        {
   //   pr.setReduction(Integer.parseInt(reduction.getText("reduction")));  
  //pr.setIdR(Integer.parseInt(idR.getText()));
              System.out.println("bech anmlou modif IDDDDDDDDDDDDDDDD"+idR.getText());
            System.out.println("ahna tawa kbal settttttttt   HEDHA text r1"+TextR1.getText());
        pr.setTextR1(TextR1.getText());
        System.out.println("ahna tawa baed settttttttt   HEDHA text r1"+TextR1.getText());
    pr.setTextR2(TextR2.getText());
     pr.setTextR3(TextR3.getText());
      pr.setTextR4(TextR4.getText());
            System.out.println(TextR1.getText());
        System.out.println(TextR2.getText());
        System.out.println(TextR3.getText());
        System.out.println(TextR4.getText());  
          int s = (Integer)comboQ.getSelectedItem();
             // promo.setIdP(s.getIdP());
              pr.setIdQ(s);
      
        
  // appel fct modif service
  if (ServiceReponse.getInstance().modifierReponse(pr))
  {  
            
   ToastBar.getInstance().setPosition(TOP);
  ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
   status.setIcon(res.getImage("ok.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
  status.setMessage("Modification Réussie!");
status.setExpires(10000); 

      new ListeReponseForm(res).show();
  
  }

      }
       
        
        );  
    Button btnAnnler = new Button ("Annuler");
   btnAnnler.addActionListener(e->{
       new ListeReponseForm(res).show();  
   
    });
   
   
   Label a = new Label ("");
   Label b = new Label ("");
   Label c = new Label ("");
    Label d = new Label ("");
   Label f = new Label ();
  
     
     Container content = BoxLayout.encloseY(
     
     a,f,
            
             new FloatingHint(TextR1),
             createLineSeparator(),
               new FloatingHint(TextR2),
             createLineSeparator(),
               new FloatingHint(TextR3),
             createLineSeparator(),
               new FloatingHint(TextR4),
             createLineSeparator(),
            // partCombo,
            createLineSeparator(),
             b,c,
               createLineSeparator(),
               d,
             btnModifier,
             btnAnnler
     
     
     
     
     
     
     );
     
   add(content);
   show();
     
     
     
     
     
     
     
     
     
     
     
   
   
   
   
 //  
  
    }
        
        
        
    
    
    
    
    
    
    
    
    
    
    
    
    
}
