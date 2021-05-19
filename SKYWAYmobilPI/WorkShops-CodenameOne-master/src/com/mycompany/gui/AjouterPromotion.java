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
import com.mycompany.entities.Promotion;
import com.mycompany.services.ServicePromotion;
//import static java.awt.SystemColor.text;
//import static java.awt.SystemColor.text;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.entities.Partenaire;
import com.mycompany.services.ServicePartenaire;
import java.util.ArrayList;
import com.codename1.components.ToastBar;
import com.codename1.components.ToastBar.Status;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.list.DefaultListModel;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
import java.util.Map;
/////////////

import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.Log;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.processing.Result;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.spinner.Picker;
import java.util.ArrayList;
import java.util.Map;
import com.codename1.ui.util.Resources;
import java.io.ByteArrayInputStream;
import java.io.InputStreamReader;
///////////////
/**
 *
 * @author Lenovo
 */
public class AjouterPromotion extends BaseForm {
    Form current;
    //combo 0
     private ArrayList<Partenaire> partenaires;
     String [] partm;
       
     //graph combooo
     ComboBox comboPart = new ComboBox();
    public AjouterPromotion(Resources res)
    { 
       super("Ajout Promotion",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("AJouter Promotion");
        getContentPane().setScrollVisible(false);
        //combo  
                   super.addSideMenu (res);

         partenaires = new ServicePartenaire().afficherPartenaire();
    

   
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
        
        new ListePromotionForm(res).show();//  ListReclamationForm a = new ListReclamationForm(res);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
           for (Partenaire p : partenaires) {
           // comboPart.addItem(p.getCodeP());
              comboPart.addItem(p.getIdP());
            
        }  
             
          
           //combo 3
            this.add(comboPart);
  
        //
          for (Partenaire p : partenaires) {
          String x = p.getMailp();
            
        }  
           final DefaultListModel<String> ok = new DefaultListModel<>();
         for (Partenaire p : partenaires ) {
           // comboPart.addItem(p.getCodeP());
 ok.addItem(p.getMailp());
            //partm=Result.fromContent(p.getMailp()).getAsStringArray("//description");
        }
          AutoCompleteTextField tfmail = new AutoCompleteTextField(ok);
          this.add(tfmail);


        /*******************/
        
        
        TextField codeP = new TextField("", "Entrer code promo");
        codeP.setUIID("TextFieldBalck");
        addStringValue ("codeP",codeP);
  
        
         TextField reduction = new TextField("", "Entrer reduction");
        reduction.setUIID("TextFieldBalck");
        addStringValue ("reduction",reduction);
  
        
        
         TextField dated = new TextField("", "Entrer date debut");
        codeP.setUIID("TextFieldBalck");
        addStringValue ("dated",dated);
  
        
        
         TextField datef = new TextField("", "date fin");
        codeP.setUIID("TextFieldBalck");
        addStringValue ("datef",datef);
        
        
       /* TextField idP = new TextField("", "Entrer id partenaire ");
        codeP.setUIID("TextFieldBalck");
        addStringValue ("Partenaire",idP);
        */
        
//        this.add(comboPart);
        
        Button btnAjouter = new Button ("Ajouter");
        addStringValue("", btnAjouter);
        
        //on click btn event 
        
        btnAjouter.addActionListener((e)-> { 
        
        
        try { 
            if (codeP.getText() == "" || reduction.getText() == "" || dated.getText() == "" || datef.getText() == "")
            {
                Dialog.show("veuillez verifier vos donnees","", "annuler", "ok");
            }
            else 
            {
                 InfiniteProgress ip = new InfiniteProgress(); //lading after insert data
             //    final Dialog iDialog = ip.showInfiniteBlocking();
                  /// combooo
           
                 //Integer.valueOf(reduction.getText())
                 Promotion promo = new Promotion(Integer.valueOf(reduction.getText()),
                       String.valueOf(codeP.getText())
                         , String.valueOf(dated.getText())
                         ,String.valueOf(datef.getText())
                         
                 
                 
                 );
                 
               // Partenaire s = (Partenaire)comboPart.getSelectedItem();
         int s = (Integer)comboPart.getSelectedItem();
             // promo.setIdP(s.getIdP());
              promo.setIdP(s);
                 /* Promotion p = (Promotion)comboPart.getSelectedItem();
                
               promo.setIdP(p.getIdP());*/
                 System.out.println("data promo else gui"+promo);
                 // appel fct ajout service
                 ServicePromotion.getInstance().ajouterPromotion(promo);
                 
                 
                 
                 
                 
                 
                 ToastBar.getInstance().setPosition(BOTTOM);
                      ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
   status.setIcon(res.getImage("checked.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
  status.setMessage("Promotion ajouté avec succès");
                                                  status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();
               //  iDialog.dispose(); //NAHIW LOADING BAED AJOUT
                 

             

                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                 
                // iDialog.dispose(); //NAHIW LOADING BAED AJOUT
                 
                   new ListePromotionForm(res).show();
                 
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
    public ArrayList<Partenaire> getPartenaire() {
        return partenaires;
    }
    

    public void setPartenaires(ArrayList<Partenaire> partenaires) {
        this. partenaires=  partenaires;
    }
    
    
    
//String apiKey="AIzaSyC1oTSQwDtUrl-pClK43Gh5cEVJ4jgUrsA";
    String[] searchLocations(String text) {
         final DefaultListModel<String> ok = new DefaultListModel<>();
         for (Partenaire p : partenaires ) {
           // comboPart.addItem(p.getCodeP());
 ok.addItem(p.getMailp());
            //partm=Result.fromContent(p.getMailp()).getAsStringArray("//description");
        }
        try {
            if (text.length() > 0) {
           String[] res = partm;
                return res;
            }
        } catch (Exception err) {
            Log.e(err);
        }
        return null;
    }


//TextField apiKey = new TextField();

    
    
    
    
    
    
}
