/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.io.Log;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Calendar;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import static com.codename1.ui.Component.LEFT;
import static com.codename1.ui.Component.RIGHT;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.EncodedImage;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.URLImage;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.sun.mail.smtp.SMTPSSLTransport;
import com.sun.mail.smtp.SMTPTransport;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

/**
 *
 * @author Lenovo
 */
public class testFront extends BaseForm{
    Form current;
    public testFront(Resources res)
    {
        
        
        super("Passer un test",BoxLayout.y());
       
      Toolbar tb = new Toolbar(true);
        
        current=this;
        setToolbar(tb);
      getTitleArea().setUIID("Container");
    
      setTitle ("Passer un test");
        getContentPane().setScrollVisible(false);
        
                   super.addSideMenuFront(res);

        
        tb.addSearchCommand(s -> { 
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        } );
        
        
        
        
        
        Tabs swipe = new Tabs();
        Label s1= new Label ();
         Label s2= new Label ();
         
        addTab(swipe,s1,res.getImage("test.jpg"),"","",res);
        
        
        
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
        RadioButton mesListes = RadioButton.createToggle("Refresh", barGroup);
        mesListes.setUIID("SelectBar");

        RadioButton partage = RadioButton.createToggle("Test", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");


        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        new testFront(res).show();
        // ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
            refreshTheme();
        });
        
               partage.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
        
        new testFront(res).show();
        // ListReclamationForm a = new ListReclamationForm(res);
          //  a.show();
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
       // bindButtonSelection(liste, arrow);
        bindButtonSelection(partage, arrow);
        // special case for rotation
        addOrientationListener(e -> {
            updateArrowPosition(barGroup.getRadioButton(barGroup.getSelectedIndex()), arrow);
        });

       
     
     String urlImage="test.jpg";
     Image placeHolder =Image.createImage(12,90);
     EncodedImage enc= EncodedImage.createFromImage(placeHolder, false);
     URLImage urlimg=URLImage.createToStorage(enc, urlImage, urlImage, URLImage.RESIZE_SCALE);
             // addButton(null,promo.getCodeP(),promo.getReduction(),promo.getDated(),promo.getDatef());
    // addButton(null,promo.getCodeP(),promo.getReduction(),promo.getDated(),promo.getDatef());
   // addButton(urlimg,promo.getCodeP(),promo.getReduction(),promo.getDated(),promo.getDatef(),promo,res);
 addButton(urlimg,res);
   
   
   
     ScaleImageLabel image = new ScaleImageLabel(urlimg);
     Container containerImg= new Container();
     image.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);

 
   
        
        
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
                 
                 
      swipe.addTab ("",res.getImage("test.jpg"),page1);
      
      
      
    }
    private void addStringValue(String s, Component v) {
       add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
       add (createLineSeparator(0xeeeeee));
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
        
        
        
        
        //appel fct aaff
               // ServicePromotion servicePromotion = new ServicePromotion();
       //    ArrayList <Promotion>list =ServicePromotion.getInstance().afficherPromotion();
        
        
        
        
        );
    }

 
 
 
 
 
 
 
 
 
 
 
 
 
    private void updateArrowPosition(Button btn, Label l) {
     l.getUnselectedStyle().setMargin(LEFT, btn.getX() + btn.getWidth() / 2 - l.getWidth() / 2);
     l.getParent().repaint();;
           
        
        
    }
//   private void addButton(Image img ,String codeP, int reduction,String dated,String datef,Promotion promo,Resources res)
    private void addButton(Image img, Resources res) {
        int height = Display.getInstance().convertToPixels(11.5f);
            int width= Display.getInstance().convertToPixels(14f);
            
            Button image = new Button(img.fill(width,height));
            
            image.setUIID("Label");
            
      //  Container cnt= new Container ();
      Container cnt = BorderLayout.west(image);
     //   Container o = new Container ();
  
        TextField email = new TextField("","saisir votre email", 20, TextField.ANY);
 email.setUIID("TextFieldBalck");
     addStringValue ("email",email);
     
     
 Picker datePicker= new Picker();
 datePicker.setType(Display.PICKER_TYPE_CALENDAR);
  datePicker.setUIID("Choisir date du test");
        addStringValue("datePicker", datePicker);
  SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
 
        
    

    Button btnEmail = new Button("Envoyer mail");
      addStringValue("", btnEmail);
        btnEmail.addActionListener(e -> {
           
           
            
            try {
                //Properties props = new Properties()
                Properties props = new Properties();
                props.put("mail.smtp.host", "smtp.gmail.com"); //SMTP Host
                props.put("mail.smtp.port", "587"); //TLS Port
                props.put("mail.smtp.auth", "true"); //enable authentication
                 props.put("mail.smtp.starttls.enable", "true"); //enable STARTTLS
                javax.mail.Session session = javax.mail.Session.getInstance(props, null);
                MimeMessage msg = new MimeMessage(session);
                MimeMessage ms = new MimeMessage(session);
                msg.setFrom(new InternetAddress(" SKYWAY LEARNING Confirmation du  Yessine Fraj <monEmail@domaine.com>"));
                msg.setRecipients(Message.RecipientType.TO, email.getText().toString()   );
                msg.setSubject("Passage du test le "+format.format(datePicker.getDate()));
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
        //String filename="C:\\Users\\DELL\\Desktop\\skyway-fr.png";
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
        }});
           
    
    
    
    
    
    
}}
