/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;
import com.codename1.components.FloatingHint;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Container;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Partenaire;
import com.mycompany.services.ServicePartenaire;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import com.codename1.capture.Capture;
import com.codename1.charts.ChartComponent;
import com.codename1.charts.models.CategorySeries;
import com.codename1.charts.renderers.DefaultRenderer;
import com.codename1.charts.renderers.SimpleSeriesRenderer;
import com.codename1.charts.util.ColorUtil;
import com.codename1.charts.views.PieChart;
import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.ext.filechooser.FileChooser;
import com.codename1.io.FileSystemStorage;
import com.codename1.io.Log;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.notifications.LocalNotificationCallback;
import com.codename1.push.PushAction;
import com.codename1.push.PushActionCategory;
import com.codename1.push.PushContent;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.CN;
import com.codename1.ui.CheckBox;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
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
import com.mycompany.entities.Partenaire;
import com.mycompany.services.ServicePartenaire;
//import static java.awt.SystemColor.text;
//import static java.awt.SystemColor.text;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.util.ImageIO;

import java.io.IOException;
import java.io.OutputStream;
import java.net.URI;
import java.net.URISyntaxException;
/**
 *
 * @author Lenovo
 */
public class ModifierPartenaireForm extends BaseForm {
   Form current;
   ///////////////////////////////
            //1-
protected String saveFileToDevice(String hi, String ext) throws IOException {

//hi heya file 
//ext extension
// fct hedhi trajalek esm l image li hatitha f app
        URI uri;
        try {
            uri = new URI(hi);
            String path = uri.getPath(); //path taswora
            int index = hi.lastIndexOf("/");
            hi = hi.substring(index + 1);
            return hi;
        } catch (URISyntaxException ex) {
        }
        return "hh";
    }
        
   ///////////////////////////////
    
    public ModifierPartenaireForm (Resources res , Partenaire pr)
    { 
 super("Modifier partenaire",BoxLayout.y());
 Toolbar tb = new Toolbar(true);
current=this;
setToolbar(tb);
  getTitleArea().setUIID("Container");
 setTitle ("Modifier partenaire");
 getContentPane().setScrollVisible(false);
            super.addSideMenu (res);

 TextField nomP= new TextField(pr.getNomP(), "Nom partenaire ",20,TextField.ANY);
TextField domaine= new TextField(pr.getDomaine(), "Domaine ",20,TextField.ANY); 
TextField dateP= new TextField(pr.getDateP(), "Date partenariat ",20,TextField.ANY);
TextField mailP= new TextField(pr.getMailp(), "Mail ",20,TextField.ANY);
Button btnModifier = new Button ("Modifier");
 Button btnAnnler = new Button ("Annuler");
 //action image
/*
 
              
        //IMG //ATTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTTT
        /***********/
        Button img1= new Button ("choisir une image");
        CheckBox multiSelect = new CheckBox("multi selct");
//btn img1
img1.addActionListener((ActionEvent e1) -> {
            if (FileChooser.isAvailable()) {
                FileChooser.setOpenFilesInPlace(true); 
                FileChooser.showOpenDialog(multiSelect.isSelected(), ".png, .jpeg, .png/plain",
                //FileChooser.showOpenDialog(multiSelect.isSelected(), ".jpg, .jpeg, .png/plain",
                        /*
halina dialog 
*/(ActionEvent e2) -> {
                    if (e2 == null || e2.getSource() == null) {
                        add("No file was selected");
                        revalidate();
                        return;
                    }
                    if (multiSelect.isSelected()) {
                        String[] paths = (String[]) e2.getSource();
                        for (String path : paths) {
                            System.out.println(path); //nekhdhou el path mta photo  
                            CN.execute(path);
                        }
                        return;
                    }

                    String file = (String) e2.getSource();
                    if (file == null) {
                        add("No file was selected");
                        revalidate();
                    } else {
                        Image logo;
//affichage photo baed ma khdhina path

                        try {
                            logo = Image.createImage(file).scaledHeight(500);;
                            current.add(logo);//ajoutina logo f app
//taw bech ysir save
                            String imageFile = FileSystemStorage.getInstance().getAppHomePath() + "photo.png";

                            try (OutputStream os = FileSystemStorage.getInstance().openOutputStream(imageFile)) {
                                System.out.println(imageFile);
                                ImageIO.getImageIO().save(logo, os, ImageIO.FORMAT_PNG, 1);
                            } catch (IOException err) {
                            }
                        } catch (IOException ex) {
                        }
//kharejna extension mta img
                        String extension = null;
                        if (file.lastIndexOf(".") > 0) {
                            extension = file.substring(file.lastIndexOf(".") + 1);
                            StringBuilder hi = new StringBuilder(file);
                            if (file.startsWith("file://")) {
                                hi.delete(0, 7);
                            }
                            int lastIndexPeriod = hi.toString().lastIndexOf(".");
                            Log.p(hi.toString());
                            String ext = hi.toString().substring(lastIndexPeriod);
                            String hmore = hi.toString().substring(0, lastIndexPeriod - 1);
                            try {
// khdina esm img 
                                String namePic = saveFileToDevice(file, ext);
                                System.out.println("namePiccccccc"+namePic);
                                //fct ajouter
                                /*****************ARTTTTTTTTTTTTTTT///////////////*/
                   
        
        
        
    
        
        btnModifier.addPointerPressedListener(l ->
        {

        pr.setNomP(nomP.getText());
   pr.setDomaine(domaine.getText());
        pr.setDateP(dateP.getText());
        pr.setMailp(mailP.getText());
         pr.setLogop(namePic);
        
        
      
        
  // appel fct modif service
  if (ServicePartenaire.getInstance().modifierPartenaire(pr))
  {  
          
                 ToastBar.getInstance().setPosition(BOTTOM);
                      ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
   status.setIcon(res.getImage("checked.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
  status.setMessage("Promotion modifié avec succès");
                                                  status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();
       
      new ListePartenaireForm(res).show();
  
  }

      }
       
        
        );  
   
   btnAnnler.addActionListener(e->{
       new ListePartenaireForm(res).show();  
   
    });
                                ////////////////////////////////////////////////////
                            } catch (IOException ex) {
                            }

                            revalidate();

                        
                    }
                    }
                        });
            }
                });
        
        
        
        
        
        
        /************/
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 
 




        
        
   
        
        

       nomP.setUIID("NewTopLine");
       domaine.setUIID("NewTopLine");
       dateP.setUIID("NewTopLine"); 
       mailP.setUIID("NewTopLine");
       
          
          
          
        nomP.setSingleLineTextArea(true);
        domaine.setSingleLineTextArea(true);
        dateP.setSingleLineTextArea(true);
        mailP.setSingleLineTextArea(true);   
      
           //idP.setSingleLineTextArea(true);
        

   
   
   Label a = new Label ("");
   Label b = new Label ("");
   Label c = new Label ("");
    Label d = new Label ("");
   Label f = new Label ();
  
     
     Container content = BoxLayout.encloseY(
     
     a,f,
             //new FloatingHint(idProm),
             createLineSeparator(),
             new FloatingHint(nomP),
             createLineSeparator(),
               new FloatingHint(domaine),
             createLineSeparator(),
               new FloatingHint(dateP),
             createLineSeparator(),
               new FloatingHint(mailP),
             createLineSeparator(),
            // partCombo,
            createLineSeparator(),
             b,c,
               createLineSeparator(),
               d,
             btnModifier,
             btnAnnler,
             img1 
     
     
     
     
     
     
     );
     
   add(content);
   show();
     
     
     

  
    }
        
        

}
