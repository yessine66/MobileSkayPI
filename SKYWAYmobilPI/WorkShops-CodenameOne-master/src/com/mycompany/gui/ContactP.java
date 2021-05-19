/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ImageViewer;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.messaging.Message;
import com.codename1.notifications.LocalNotification;
import com.codename1.push.PushAction;
import com.codename1.push.PushActionCategory;
import com.codename1.ui.AutoCompleteTextField;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
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
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.list.DefaultListModel;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Partenaire;
import com.mycompany.services.ServicePartenaire;
import java.util.ArrayList;

/**
 *
 * @author Lenovo
 */
public class ContactP extends BaseForm {
    
      Form current;
     private String photo = "";
         int badgeNumber = 0;
             private ArrayList<Partenaire> partenaires;
    public ContactP(Resources res)
    { 
       super("Conatcter Parteanire",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("AJouter Partenaire");
        getContentPane().setScrollVisible(false);
        
        
        
        tb.addSearchCommand(s -> { 
        

        
        
        } );
        
        
        
        
        Tabs swipe = new Tabs();
        Label s1= new Label ();
         Label s2= new Label ();
         
        addTab(swipe,s1,res.getImage("nour.png"),"","",res);
        
        
        //IMAGE
        ///////////////////////
        
        
        
         // add image here
        ImageViewer imgV = new ImageViewer();
        EncodedImage placeholder =  EncodedImage.createFromImage(Image.createImage(current.getWidth(), current.getWidth() / 4, 0xffff0000), true);
        imgV.setImage(placeholder);
        // form
        Button getImgBtn = new Button("Liste des Partenaires");
        
        getImgBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               new ListePartenaireForm(res).show();
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
        //ALO
       // new ListePartenaireForm(res).show();//  ListReclamationForm a = new ListReclamationForm(res);
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
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
        
        
          partenaires = new ServicePartenaire().afficherPartenaire();

        
          final DefaultListModel<String> ok = new DefaultListModel<>();
         for (Partenaire p : partenaires ) {
           // comboPart.addItem(p.getCodeP());
 ok.addItem(p.getMailp());
            //partm=Result.fromContent(p.getMailp()).getAsStringArray("//description");
        }
          AutoCompleteTextField mailp = new AutoCompleteTextField(ok);
         

        
        
      
   // TextField mailpp = new TextField("", "Entrer Mail partenaire");
        mailp.setUIID("TextFieldBalck");
        addStringValue ("mail Partenaire",mailp);
  
        
         TextField body = new TextField("", "Sujet");
        body.setUIID("TextFieldBalck");
        addStringValue ("body",body);
        
        
          TextField mbody = new TextField("", "");
        body.setUIID("TextFieldBalck");
        addStringValue ("body",mbody);
        
  
        
        
        //IMG
       /* TextField idP = new TextField("", "Entrer id partenaire ");
        codeP.setUIID("TextFieldBalck");
        addStringValue ("Partenaire",idP);*/
        
        Button btnEnvoyer = new Button ("Envoyer");
        addStringValue("", btnEnvoyer);
        
        //on click btn event 
        
 
        btnEnvoyer.addActionListener((e)-> { 
        
        
        try { 
            if (mailp.getText() == "" || body.getText() == "")
            {
                Dialog.show("veuillez verifier vos donnees","", "annuler", "ok");
            }
            else 
            {
                 InfiniteProgress ip = new InfiniteProgress(); //lading after insert data
                // final Dialog iDialog = ip.showInfiniteBlocking();
                 //Integer.valueOf(reduction.getText())
                
                 String mail = String.valueOf(mailp.getText());
                 String sujet = String.valueOf(body.getText());
                 System.out.println("mail recup"+mail);
                   System.out.println("body recup"+sujet);
                  Message m = new Message(String.valueOf(mbody.getText()));
//m.getAttachments().put(textAttachmentUri, "text/plain");
//m.getAttachments().put(imageAttachmentUri, "image/png");
Display.getInstance().sendMessage(new String[] {mail}, sujet, m);
             //smart.kindergarten0@gmail.com
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
              
              
              
              
              
              
              
                 
                 refreshTheme(); 
                        /***************************************/       
                     
     
                    }
            
        } catch (Exception ex)
        {
            ex.printStackTrace();
            System.err.println("errrrrrrrrrror GUI AJOUTTTTTTTTTTTTTTTT ");
        }
        
        
        
        
        });
        
        
        
        
        
         current.add(getImgBtn);
        
        
    
        
        
        
        
        
        
        
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
