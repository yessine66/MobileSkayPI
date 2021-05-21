/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

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
public class AjouterPartenaire extends BaseForm  {
    Form current;
     private String photo = "";
         int badgeNumber = 0;
         /*************************/
         
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
         
  
         /*******************************/
  
    public  AjouterPartenaire(Resources res)
    { 
       super("Ajout Parteanire",BoxLayout.y());
     // addSideMenu (res); 
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("AJouter Partenaire");
        getContentPane().setScrollVisible(false);
      
        super.addSideMenu (res);
       
        tb.addSearchCommand(s -> { 
        

        
        
        } );
        
        
        
        
        
        Tabs swipe = new Tabs();
        Label s1= new Label ();
         Label s2= new Label ();
         
        addTab(swipe,s1,res.getImage("nourx.png"),"","",res);
        
        
        //IMAGE
        ///////////////////////
        
        
        
         // add image here
        ImageViewer imgV = new ImageViewer();
        EncodedImage placeholder =  EncodedImage.createFromImage(Image.createImage(current.getWidth(), current.getWidth() / 4, 0xffff0000), true);
        imgV.setImage(placeholder);
        // form
        Button getImgBtn = new Button("afficher");
        
        getImgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               //new ListePartenaireForm(res).show();
               /*Message m = new Message("Body of message");
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {"smart.kindergarten0@gmail.com"}, "code aa", m);*/
               
                
            }
        });
        
        
        
  
        
        
        
        
        
        /*/////////////////////////////////
        
        
        
        
        
        
        
        
        
        
        
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
        RadioButton mesListes = RadioButton.createToggle("Partrenaires", barGroup);
        mesListes.setUIID("SelectBar");
        RadioButton liste = RadioButton.createToggle("Contact", barGroup);
        liste.setUIID("SelectBar");
        RadioButton stat = RadioButton.createToggle("Statistics", barGroup);
        stat.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter ", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        //ALO
  new ListePartenaireForm(res).show();//  ListReclamationForm a = new ListReclamationForm(res);
 
            refreshTheme();
        });
        
                liste.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        //ALO
  new ContactP(res).show();//  ListReclamationForm a = new ListReclamationForm(res);
 
            refreshTheme();
        });
         stat.addActionListener((el) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        //ALO
    new CatDomaine(res).show();//  ListReclamationForm a = new ListReclamationForm(res);

            refreshTheme();
        });
          partage.addActionListener((es) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        //ALO
    new AjouterPartenaire(res).show();//  ListReclamationForm a = new ListReclamationForm(res);
        
   
          //  a.show();
            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(4, mesListes, liste,stat, partage),
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

       
      
        
        
         
        Button btnAjouter = new Button ("Ajouter");
        addStringValue("", btnAjouter);
        
        
        
        
        TextField nomP = new TextField("", "Entrer nom partenaire");
        nomP.setUIID("TextFieldBalck");
        addStringValue ("nom Partenaire",nomP);
  
        
         TextField domaine = new TextField("", "Entrer domaine");
        domaine.setUIID("TextFieldBalck");
        addStringValue ("domaine",domaine);
  
        
        
         TextField dateP = new TextField("", "Entrer date debut");
       dateP.setUIID("TextFieldBalck");
        addStringValue ("date début partenariat",dateP);
  
        
        
         TextField mailp = new TextField("", "Entrer le mail du partenaire");
     mailp.setUIID("TextFieldBalck");
        addStringValue ("mail parteanire",mailp);

               
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
                                       btnAjouter.addActionListener((e)-> { 
        
        
        try { 
            if (nomP.getText() == "" || domaine.getText() == "" || dateP.getText() == "" || mailp.getText() == "")
            {
                Dialog.show("veuillez verifier vos donnees","", "annuler", "ok");
            }
            else 
            {
                 InfiniteProgress ip = new InfiniteProgress(); //lading after insert data
                 //final Dialog iDialog = ip.showInfiniteBlocking();
                 //Integer.valueOf(reduction.getText())
                Partenaire promo = new Partenaire(
                       String.valueOf(nomP.getText()),
                          String.valueOf(domaine.getText()),
                         String.valueOf(dateP.getText()),
                         String.valueOf(mailp.getText()),
                        namePic
                 
                 
                 );
                 
                 System.out.println("data promo else gui"+promo);
                 // appel fct ajout service
                 //ALO
              ServicePartenaire.getInstance().ajouterPartenaire(promo,photo);
                 
          
 ToastBar.getInstance().setPosition(BOTTOM);
  ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);
   status.setIcon(res.getImage("checked.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
  status.setMessage("Partenaire ajouté avec succès");
status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();  
              
              
              
              
              
              
                // iDialog.dispose(); //NAHIW LOADING BAED AJOUT
                 //ALO
              new ListePartenaireForm(res).show();
                 
                 refreshTheme(); 
                        /***************************************/       
                     
     
                    }
            
        } catch (Exception ex)
        {
            ex.printStackTrace();
            System.err.println("errrrrrrrrrror GUI AJOUTTTTTTTTTTTTTTTT ");
        }
        
        
        
        
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
    
        
         
       
        //on click btn event 
        
 
 
        
        
        
        
      
        
         Button b = new Button("Send Notification");
        b.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
               // new PushAction("maybe", "Maybe", null, "Enter reason", "Reply");
                System.out.println("alo nott ");
                LocalNotification n = new LocalNotification();
              n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
       // n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound
 //Display.getInstance().notify();
               // n.setBadgeNumber(badgeNumber++);
                System.out.println("alo nott 2222");
              //  Display.getInstance();
Display.getInstance().scheduleLocalNotification(
           
                n,
                System.currentTimeMillis(), // fire date/time
                LocalNotification.REPEAT_NONE// Whether to repeat and what frequency
        );
          System.out.println("alo nott 333");      
            }
        });
      
        
        
        current.add(img1);
        
        
        
        
        
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
                 
                 
      swipe.addTab ("",res.getImage("nourx.png"),page1);
      
        
      
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
    
    
    
    
/*LocalNotification n = new LocalNotification();
        n.setId("demo-notification");
        n.setAlertBody("It's time to take a break and look at me");
        n.setAlertTitle("Break Time!");
        n.setAlertSound("/notification_sound_bells.mp3"); //file name must begin with notification_sound


        Display.getInstance().scheduleLocalNotification(
                n,
                System.currentTimeMillis() + 10 * 1000, // fire date/time
                LocalNotification.REPEAT_MINUTE  // Whether to repeat and what frequency
        );

      
    */
    
    
    
   public PushActionCategory[] getPushActionCategories() {
    return new PushActionCategory[]{
        new PushActionCategory("fo", new PushAction[]{
            new PushAction("yes", "Yes"),
            new PushAction("no", "No"),
            new PushAction("maybe", "Maybe", null, "Enter reason", "Reply")
        })

    };
}
    

    
    
}
