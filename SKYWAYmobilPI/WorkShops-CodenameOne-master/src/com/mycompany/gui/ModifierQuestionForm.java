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
import com.mycompany.services.ServiceQuestion;
/**
 *
 * @author Lenovo
 */
public class ModifierQuestionForm extends BaseForm {
   Form current;
    
    public ModifierQuestionForm (Resources res , Question pr)
    { 
        
        
         super("Modifier partenaire",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("Modifier partenaire");
        getContentPane().setScrollVisible(false);
        
        
        
      //  super.addSideMenu (res);
       /*
      
      
       TextField textQ = new TextField("", "Entrer nom partenaire");
        textQ.setUIID("TextFieldBalck");
        addStringValue ("nom Question",textQ);
  
        
         TextField nameT = new TextField("", "Entrer nameT");
        nameT.setUIID("TextFieldBalck");
        addStringValue ("nameT",nameT);
  
        
        
         TextField nbrPoint = new TextField("", "Entrer date debut");
       nbrPoint.setUIID("TextFieldBalck");
        addStringValue ("date début partenariat",nbrPoint);
  
        
        
         TextField mailp = new TextField("", "Entrer le mail du partenaire");
     mailp.setUIID("TextFieldBalck");
        addStringValue ("mail parteanire",mailp);
        
      
      
      
      
      
      */ 
  //  TextField idProm= new TextField(String.valueOf(pr.getIdProm()), "Id promotion",20,TextField.ANY);
      TextField textQ= new TextField(pr.getTextQ(), "La question ",20,TextField.ANY);
       TextField nameT= new TextField(pr.getNameT(), "Categorie ",20,TextField.ANY); 
    TextField nbrPoint= new TextField(String.valueOf(pr.getNbrPoint()), "nombre des points",20,TextField.ANY);
        //ComboBox partCombo = new ComboBox();
        //partCombo.addItem("a");
        
        
        
   
        
        
   //  idProm.setUIID("NewTopLine");
      textQ.setUIID("NewTopLine");
nameT.setUIID("NewTopLine");
       nbrPoint.setUIID("NewTopLine"); 
       
          
          
          
        textQ.setSingleLineTextArea(true);
        nameT.setSingleLineTextArea(true);
        nbrPoint.setSingleLineTextArea(true);
      
           //idP.setSingleLineTextArea(true);
        
        
        
        
        Button btnModifier = new Button ("Modifier");
       // btnModifier.setUIID("button");
        
        btnModifier.addPointerPressedListener(l ->
        {
   //   pr.setReduction(Integer.parseInt(reduction.getText("reduction")));  
 //  pr.setIdProm((idProm.getText()));
        pr.setTextQ(textQ.getText());
   pr.setNameT(nameT.getText());
        pr.setNbrPoint(Integer.parseInt(nbrPoint.getText()));
        
        
      
        
  // appel fct modif service
  if (ServiceQuestion.getInstance().modifierQuestion(pr))
  {  
       ToastBar.getInstance().setPosition(TOP);
  ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
   status.setIcon(res.getImage("ok.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
  status.setMessage("Modification Réussie!");
status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show(); 
      new ListeQuestionForm(res).show();
  
  }

      }
       
        
        );  
    Button btnAnnler = new Button ("Annuler");
   btnAnnler.addActionListener(e->{
       new ListeQuestionForm(res).show();  
   
    });
   
   
   Label a = new Label ("");
   Label b = new Label ("");
   Label c = new Label ("");
    Label d = new Label ("");
   Label f = new Label ();
  
     
     Container content = BoxLayout.encloseY(
     
     a,f,
             //new FloatingHint(idProm),
             createLineSeparator(),
             new FloatingHint(textQ),
             createLineSeparator(),
               new FloatingHint(nameT),
             createLineSeparator(),
               new FloatingHint(nbrPoint),
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
