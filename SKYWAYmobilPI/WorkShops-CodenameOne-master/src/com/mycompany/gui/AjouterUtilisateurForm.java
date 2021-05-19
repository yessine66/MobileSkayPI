/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextComponentPassword;
import com.codename1.ui.TextField;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BorderLayout;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.GridLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Utilisateur;
import com.mycompany.services.ServiceUtilisateur;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author mega-pc
 */
public class AjouterUtilisateurForm extends BaseForm{
    
    Form current;
    
    
    public AjouterUtilisateurForm(Resources res){
        
        super("Newsfeed",BoxLayout.y()); //
        
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Create Account");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        
        
        tb.addSearchCommand(e -> {
            
            
        });
        
        Tabs swipe = new Tabs();
        
        Label s1= new Label();
        Label s2= new Label();
        
        
        addTab(swipe,s1,res.getImage("back-logo.jpeg"),"","",res);
        
        
        ////****////code masrou9 mta3 design
        
        
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
        RadioButton mesListes = RadioButton.createToggle("Accounts", barGroup);
        mesListes.setUIID("SelectBar");
//        RadioButton liste = RadioButton.createToggle("Autres", barGroup);
//        liste.setUIID("SelectBar");
        RadioButton partage = RadioButton.createToggle("Ajouter Compte", barGroup);
        partage.setUIID("SelectBar");
        Label arrow = new Label(res.getImage("news-tab-down-arrow.png"), "Container");

       
        
//        mesListes.addActionListener((e) -> {
//               InfiniteProgress ip = new InfiniteProgress();
//        final Dialog ipDlg = ip.showInifiniteBlocking();
//         new ListUtilisateurForm(res).show();
//        //  ListReclamationForm a = new ListReclamationForm(res);
//          //  a.show();
//            refreshTheme();
//        });
//        
        
        mesListes.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
         new ListUtilisateurForm(res).show();
         
        //  ListReclamationForm a = new ListReclamationForm(res);
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

       
        
        
        ////****////code masrou9 mta3 design
        
        
        
        
        
        TextField tfNom = new TextField("", "Nom...");
        tfNom.setUIID("TextFieldBlack");
        addStringValue("Nom ",tfNom);
        
        TextField tfPrenom = new TextField("", "Prenom...");
        tfPrenom.setUIID("TextFieldBlack");
        addStringValue("Prenom ",tfPrenom);
        
        TextField tfMail = new TextField("", "Mail...");
        tfMail.setUIID("TextFieldBlack");
        addStringValue("Mail ",tfMail);
        
        TextField tfAge = new TextField("", "Age...");
        tfAge.setUIID("TextFieldBlack");
        addStringValue("Age ",tfAge);
        
        TextField tfTel = new TextField("", "Telephone...");
        tfTel.setUIID("TextFieldBlack");
        addStringValue("Telephone ",tfTel);
        
        Switch genre = new  Switch();
        String lGenre="Femme";
        genre.setValue(false);
        addStringValue("Genre: "+lGenre,genre);
        Picker dateNaiss = new Picker();
        addStringValue("Date de Naissance ",dateNaiss);
        dateNaiss.setUIID("TextFieldBlack");
        TextField tfUsername = new TextField("", "Username...");
        tfUsername.setUIID("TextFieldBlack");
        addStringValue("Username",tfUsername);
        
        
        TextComponentPassword tcpPassword = new TextComponentPassword();
        tcpPassword.hint("Mot de passe...");
        addStringValue("Mot de passe",tcpPassword);
        TextComponentPassword tcpPasswordVerif = new TextComponentPassword();
        tcpPasswordVerif.hint("Mot de passe verification ...");
        addStringValue("Mot de passe verification",tcpPasswordVerif);
        
        Button btnAjouter = new Button("Create");
        addStringValue("",btnAjouter);
        
        //current.addAll(tfNom,tfPrenom);
        
        
        btnAjouter.addActionListener((e)-> {
            try {
                
                if(tfNom.getText() == ""||tfPrenom.getText() == ""||tfMail.getText() == ""||tfAge.getText() == ""||tfTel.getText() == ""||tfUsername.getText() == "")
                    Dialog.show("Warning", "Remplir tout les champs ", "OK",null);
               /* else if (tcpPassword.getText()!=tcpPasswordVerif.getText())
                    Dialog.show("Warning", "Le mot de passe n'est pas verifier  ", "OK",null);*/
                else{
                    InfiniteProgress ip = new InfiniteProgress();
                    
                    
                    final Dialog iDialog = ip.showInfiniteBlocking();
   
                    String GenreText="";
                    if(genre.isValue()){
                        GenreText="Homme";  
                    }
                  
                    else {
                        GenreText="Femme";
                    }
                        
                    System.out.println("\n\n\n\n\ntesttttttttttttt ***************"+dateNaiss.getText()+"\n\n\n\n\n");
                    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-mm-dd");
                    String dateSringdateNaiss = formatter2.format(dateNaiss.getDate());
                    
                    
                    Utilisateur u = new Utilisateur(tfPrenom.getText(), tfPrenom.getText(), tfMail.getText(), tfAge.getAsInt(BASELINE), tfTel.getAsInt(BASELINE),
                            GenreText, dateSringdateNaiss, tfUsername.getText(), tcpPassword.getText(), "apprenant");
                    
                    
                    
                    System.out.println("DATA Utilisateur = "+u+"\n c bon");
                    
                    
                    
                    ServiceUtilisateur.getInstance().ajouterUtilisateur(u);
                    
                    iDialog.dispose();
                    
                    
                    
                    //
                    
                  
                    
                   new ListUtilisateurForm(res).show();
                    
                    
                    refreshTheme();
                }
                
            } catch (Exception ex) {
                ex.printStackTrace();
                System.out.println("\nErreur ajout Utilisateur");
            }
        });
        
        
        
        
        
        
    }
    
   private void addStringValue(String s, Component v) {
       add(BorderLayout.west(new Label(s,"PaddedLabel")).add(BorderLayout.CENTER,v));
       add (createLineSeparator(0xeeeeee));
    }

    private void addTab(Tabs swipe,Label spacer, Image image, String string, String text,Resources res) {
       // int size = Math.min(Display.getInstance().getDisplayWidth(), Display.getInstance().getDisplayHeight());
        
       int size = Math.min(Display.getInstance().getDisplayWidth(),Display.getInstance().getDisplayHeight()); 
       
       
        if(image.getHeight() <  size){
            image = image.scaledHeight(size);
        }
        
        
        if(image.getHeight() > Display.getInstance().getDisplayHeight() / 2){
            image = image.scaledHeight(Display.getInstance().getDisplayHeight() / 2);
        }
        
        ScaleImageLabel imageScale = new ScaleImageLabel(image);
        imageScale.setUIID("Container");
        imageScale.setBackgroundType(Style.BACKGROUND_IMAGE_SCALED_FILL);
        
        Label overlay = new Label("","ImageOverlay");
        
        Container page1 = 
                LayeredLayout.encloseIn(
                imageScale,
                        overlay,
                        BorderLayout.south(
                        BoxLayout.encloseY(
                        new SpanLabel(text, "LargeWhiteText"),
                                        spacer
                        ))
                );
        swipe.addTab("",res.getImage("back-logo.jpeg"),page1);
        
    
    }
    
    
    public void  bindButtonSelection(Button btn , Label l ){
        btn.addActionListener(e -> {
            if(btn.isSelected()){
                updateArrowPosition(btn,l);
            }
            
        });
        
    }

    private void updateArrowPosition(Button btn, Label l) {
      
        l.getUnselectedStyle().setMargin(LEFT, btn.getX()+btn.getWidth()  /2 - l.getWidth()/ 2);
        l.getParent().repaint();
    }
    
    
    
    
}
