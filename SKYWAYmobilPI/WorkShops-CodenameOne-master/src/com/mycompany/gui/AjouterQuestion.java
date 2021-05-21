/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;


import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.ToastBar;
import com.codename1.components.ToastBar.Status;

import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
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
import javax.mail.Message;
import javax.mail.Session;
import javax.mail.internet.InternetAddress;

//import static java.awt.SystemColor.text;
//import static java.awt.SystemColor.text;
import com.codename1.ui.Graphics;
import com.codename1.ui.RadioButton;
import com.codename1.ui.layouts.GridLayout;
import com.mycompany.entities.Question;
import com.mycompany.services.ServiceQuestion;
import com.sun.mail.smtp.SMTPSSLTransport;
import com.sun.mail.smtp.SMTPTransport;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;


import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Lenovo
 */
public class AjouterQuestion extends BaseForm {
    Form current;
    public AjouterQuestion(Resources res)
    { 
        super("Ajout Question",BoxLayout.y());
       
       Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle ("AJouter une question");
        getContentPane().setScrollVisible(false);
  
        super.addSideMenu(res);
        
        tb.addSearchCommand(s -> { 
                } );

        /****************************************/
             Tabs swipe = new Tabs();
        Label s1= new Label ();
         Label s2= new Label ();
         
        addTab(swipe,s1,res.getImage("fatma.png"),"","",res);
        
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
        RadioButton mesListes = RadioButton.createToggle("Mes Questions", barGroup);
        mesListes.setUIID("SelectBar");
//        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
//        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        new ListeQuestionForm(res).show();

            refreshTheme();
        });
        
                partage.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        new AjouterQuestion(res).show();

            refreshTheme();
        });

        add(LayeredLayout.encloseIn(
                GridLayout.encloseIn(2, mesListes, partage),
                
                FlowLayout.encloseBottom(arrow)
        ));
        partage.setSelected(true);
        arrow.setVisible(false);
        addShowListener(e -> {
            arrow.setVisible(true);
            updateArrowPosition(partage, arrow);
        });
        bindButtonSelection(mesListes, arrow);
      //  bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

       
        

       
        /************************************/  
 
       
        TextField email = new TextField("","saisir votre email", 20, TextField.ANY);
 email.setUIID("TextFieldBalck");
      addStringValue ("email",email);
        TextField textQ = new TextField("", "Entrer une question");
        textQ.setUIID("TextFieldBalck");
        addStringValue ("textQ",textQ);
  
         
         TextField nbrPoint = new TextField("", "Entrer le nombre des points");
        nbrPoint.setUIID("TextFieldBalck");
        addStringValue ("nbrPoint",nbrPoint);
  
        
        
         TextField nameT = new TextField("", "Entrer la catégorie");
        nameT.setUIID("TextFieldBalck");
        addStringValue ("nameT",nameT);

    
        
        Button btnAjouter = new Button ("Ajouter");
        addStringValue("", btnAjouter);
        
        //on click btn event 
        

        
        
   Button btnEmail = new Button("Saraha");
        addStringValue("", btnEmail);
        btnEmail.addActionListener(e -> {
           
           
            
            try {
                //Properties props = new Properties()
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
                props.put("mail.smtp.port", "587"); //TLS Port
                props.put("mail.smtp.auth", "true"); //enable authentication
                 props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
                Session session = Session.getInstance(props, null);
                MimeMessage msg = new MimeMessage(session);
                MimeMessage ms = new MimeMessage(session);
                msg.setFrom(new InternetAddress(" Passage du test le 22-05-2021 <monEmail@domaine.com>"));
                msg.setRecipients(Message.RecipientType.TO, email.getText().toString()   );
                msg.setSubject("SKYWAY LEARNING: Confirmation du  Yessine Fraj");
                msg.setDescription("Bienvenue sur Skyway Learning cher Yessine Fraj! Votre test sera le ");
                msg.setSentDate(new Date(System.currentTimeMillis()));
                String txt =" Bienvenue sur Skyway Learning cher Yessine Fraj! Votre test sera le ";
             //   msg.setText(txt);
                   // Create the message part
         BodyPart messageBodyPart = new MimeBodyPart();

         // Now set the actual message
         messageBodyPart.setText(" Bienvenue sur Skyway Learning cher Yessine Fraj! Votre test sera le ");

         // Create a multipar message
         Multipart multipart = new MimeMultipart();
                 messageBodyPart = new MimeBodyPart();
         String filename = "C:\\Users\\mega-pc\\Documents\\CodeNameOne\\GITHUB\\SKYWAYmobilPI\\WorkShops-CodenameOne-master\\res\\theme\\skyway-fr.png";
         DataSource source = new FileDataSource(filename);
         messageBodyPart.setDataHandler(new DataHandler(source));
         messageBodyPart.setFileName(filename);
         multipart.addBodyPart(messageBodyPart);
msg.setContent(multipart);
                SMTPTransport st = (SMTPSSLTransport)session.getTransport("smtps");
                st.connect("smtp.gmail.com",465,"skyway.learning1@gmail.com","skyway123");
                st.sendMessage(msg, msg.getAllRecipients());
              
                System.out.println("server response: "+st.getLastServerResponse());
                System.out.println("haha");
               
        } catch (Exception ex)
        {
            ex.printStackTrace();
            System.err.println("erreur!!! ");
        }
           
        
            
           
        }
);
        btnAjouter.addActionListener((e)-> { 
        
        
        try { 
            if (textQ.getText() == "" || nbrPoint.getText() == "" || nameT.getText() == "")
            {
                Dialog.show("veuillez verifier vos donnees","", "annuler", "ok");
            }
            else 
            {
                 InfiniteProgress ip = new InfiniteProgress(); //lading after insert data
             //    final Dialog iDialog = ip.showInfiniteBlocking();
                 //Integer.valueOf(nbrPoint.getText())
                 Question promo = new Question(Integer.valueOf(nbrPoint.getText()),
                       String.valueOf(textQ.getText())
                         , String.valueOf(nameT.getText())
                        );
                 
              
               ServiceQuestion.getInstance().ajouterQuestion(promo);
                  ToastBar.getInstance().setPosition(BOTTOM);
                      ToastBar.Status status = ToastBar.getInstance().createStatus();
 status.setShowProgressIndicator(true);

   status.setIcon(res.getImage("fatma.png").scaledSmallerRatio(Display.getInstance().getDisplayWidth()/10, Display.getInstance().getDisplayWidth()/15));                    
                            status.setMessage("makch nrml");
                                                  status.setExpires(10000);  // only show the status for 3 seconds, then have it automatically clear

                      status.show();
               //  iDialog.dispose(); //NAHIW LOADING BAED AJOUT
                                   new ListeQuestionForm(res).show();

                 refreshTheme(); 
                 
                 
                    }
            
        } catch (Exception ex)
        {
            ex.printStackTrace();
            System.err.println("erreur!!! ");
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
                 
                 
      swipe.addTab ("",res.getImage("fatma.png"),page1);
      
      
      
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
    }
    

