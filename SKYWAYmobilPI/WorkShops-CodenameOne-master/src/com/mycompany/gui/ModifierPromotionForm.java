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
import static com.codename1.ui.Component.BOTTOM;
import com.codename1.ui.Container;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Partenaire;
import com.mycompany.entities.Promotion;
import com.mycompany.services.ServicePartenaire;
import com.mycompany.services.ServicePromotion;
import java.util.ArrayList;


/**
 *
 * @author Lenovo
 */
public class ModifierPromotionForm extends BaseForm {
    Form current;
    //combo
     private ArrayList<Partenaire> partenaires;
     //graph combooo
     ComboBox comboPart = new ComboBox();
    public ModifierPromotionForm (Resources res , Promotion pr)
    { 
        
        
         super("Modifier Promotion",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("AJouter Promotion");
        getContentPane().setScrollVisible(false);
                 super.addSideMenu (res);
  
     //combo  1
         partenaires = new ServicePartenaire().afficherPartenaire();
       
        
      //  super.addSideMenu (res);
        
    TextField idProm= new TextField(String.valueOf(pr.getIdProm()), "Id promotion",20,TextField.ANY);
      TextField codeP= new TextField(pr.getCodeP(), "Code promo ",20,TextField.ANY);
   TextField reduction = new TextField(String.valueOf(pr.getReduction()), "Reduction ",20,TextField.ANY);
       TextField dated= new TextField(pr.getDated(), "Date debut promotion ",20,TextField.ANY); 
        TextField datef= new TextField(pr.getDatef(), "Date fin promotion ",20,TextField.ANY);
           //combo 2
           for (Partenaire p : partenaires) {
           // comboPart.addItem(p.getCodeP());
              comboPart.addItem(p.getIdP());
            
        }  
           //combo 3
            this.add(comboPart);
        
        
        
        
   
        
        
     idProm.setUIID("NewTopLine");
       codeP.setUIID("NewTopLine");
reduction.setUIID("NewTopLine");
       dated.setUIID("NewTopLine"); 
        datef.setUIID("NewTopLine");
      //idP.setUIID("NewTopLine"); 
          
          
          
        codeP.setSingleLineTextArea(true);
        dated.setSingleLineTextArea(true);
        datef.setSingleLineTextArea(true);
    idProm.setSingleLineTextArea(true);   
      reduction.setSingleLineTextArea(true);
           //idP.setSingleLineTextArea(true);
        
        
        
        
        Button btnModifier = new Button ("Modifier");
       // btnModifier.setUIID("button");
        
        btnModifier.addPointerPressedListener(l ->
        {
   //   pr.setReduction(Integer.parseInt(reduction.getText("reduction")));  
 //  pr.setIdProm((idProm.getText()));
        pr.setCodeP(codeP.getText());
    pr.setReduction(Integer.parseInt(reduction.getText()));
        pr.setDated(dated.getText());
        pr.setDatef(datef.getText());
        
          int s = (Integer)comboPart.getSelectedItem();
             // promo.setIdP(s.getIdP());
              pr.setIdP(s);
      
        
  // appel fct modif service
  if (ServicePromotion.getInstance().modifierPromotion(pr))
  {  
          
                 ToastBar.getInstance().setPosition(BOTTOM);
                      ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
   status.setIcon(res.getImage("checked.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
  status.setMessage("Promotion ajouté avec succès");
                                                  status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();
      new ListePromotionForm(res).show();
  
  }

      }
       
        
        );  
    Button btnAnnler = new Button ("Annuler");
   btnAnnler.addActionListener(e->{
       new ListePromotionForm(res).show();  
   
    });
   
   
   Label a = new Label ("");
   Label b = new Label ("");
   Label c = new Label ("");
    Label d = new Label ("");
   Label f = new Label ();
  
     
     Container content = BoxLayout.encloseY(
     
     a,f,
             new FloatingHint(idProm),
             createLineSeparator(),
             new FloatingHint(codeP),
             createLineSeparator(),
               new FloatingHint(reduction),
             createLineSeparator(),
               new FloatingHint(dated),
             createLineSeparator(),
               new FloatingHint(datef),
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
