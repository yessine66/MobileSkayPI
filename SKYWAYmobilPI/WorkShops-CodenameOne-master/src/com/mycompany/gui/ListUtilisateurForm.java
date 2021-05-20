/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.util.Resources;
import com.codename1.components.InfiniteProgress;
import com.codename1.components.ScaleImageLabel;
import com.codename1.components.SpanLabel;
import com.codename1.components.Switch;
import com.codename1.ui.Button;
import com.codename1.ui.ButtonGroup;
import com.codename1.ui.Component;
import static com.codename1.ui.Component.BOTTOM;
import static com.codename1.ui.Component.CENTER;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Graphics;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.RadioButton;
import com.codename1.ui.Tabs;
import com.codename1.ui.TextArea;
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
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author mega-pc
 */
public class ListUtilisateurForm extends BaseForm{
    
    Form current;
    
    Utilisateur userixo ;

public ListUtilisateurForm (Resources res){
     super("Newsfeed",BoxLayout.y()); //
        
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Create Account");
        getContentPane().setScrollVisible(false);
        
        super.addSideMenu(res);
        
        tb.addSearchCommand(e -> {
//            
//            String text=(String)e.getSource();
//           // int x=Integer.parseInt(text);
//            System.out.println("\n\n\n**********\n"+text);
//            
//            int taille =text.length();
//           System.out.println("taille " + taille);
//            if(taille !=0){
//                if(text.substring(text.length() - 1).equals(" ")){
//                    
//                      text = text.substring(0, text.length() - 1);
//                    
//                    userixo=ServiceUtilisateur.getInstance().DetailUtilisateur(240, userixo);
//                    
//                    
//                   // Dialog.show(text, userixo.getMail(), "OK",null);  
//                    
//                }
//               
//                
//            }
//           // else if(taille < 2)
//            
//
//            
            
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
        
        
                partage.addActionListener((e) -> {
               InfiniteProgress ip = new InfiniteProgress();
        final Dialog ipDlg = ip.showInifiniteBlocking();
         new AjouterUtilisateurForm(res).show();
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
        
        
        ArrayList<Utilisateur>ListU = ServiceUtilisateur.getInstance().affichageUtilisateur();
        
        for(Utilisateur us : ListU){
            
            
            String contentpic="";
            
            if(us.getRole().equals("admin"))
                contentpic="admin";
            else
                contentpic="client";
            
            
            Image urlImageadmin=res.getImage(contentpic+".png");

            
            addButton(urlImageadmin,us.getId(),us.getNom(),us.getPrenom(),us.getMail(),us.getAge(),us.getTel(),us.getGenre(),us.getDateNaiss(),us.getUsername(),us.getPassword(),us.getRole(),us.getCreCompte(),us,res);
            
            
        }

       
        
    
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
                        new SpanLabel(text, "LargeBlackText"),
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

    private void addButton(Image img,int id, String nom, String prenom, String mail , int age , int tel , String genre , String dateNaiss , String username , String password , String role , String creCompte, Utilisateur us,Resources res) {
        
        int heigh = Display.getInstance().convertToPixels(11.5f);
        int widht = Display.getInstance().convertToPixels(11.5f);
        
        Button image = new Button(img.fill(widht, heigh));
        image.setUIID("Label");
       
        
        Container cnt = BorderLayout.west(image);
        

        Label lId = new Label(String.valueOf(id),"NewTopLine2");
        Label lNom = new Label(nom,"NewTopLine2");
        Label lPrenom = new Label(prenom,"NewTopLine2");
        Label lMail = new Label(mail,"NewTopLine2");
        Label lAge = new Label(String.valueOf(age),"NewTopLine2");
        Label lTel = new Label(String.valueOf(tel),"NewTopLine2");
        Label lGenre = new Label(genre,"NewTopLine2");
        Label lDateNaiss = new Label(dateNaiss,"NewTopLine2");
        Label lUsername = new Label(username,"NewTopLine2");
        Label lPassword = new Label(password,"NewTopLine2");
        Label lRoke = new Label(role,"NewTopLine2");
        Label lCreCompte = new Label(creCompte,"NewTopLine2");
        
        
        
        //line after each account information 
        
        
        
        
        Label whiteLine = new Label(); 
        whiteLine.setShowEvenIfBlank(true);
        whiteLine.getUnselectedStyle().setBgColor(0xff2c54);
        whiteLine.getUnselectedStyle().setBgTransparency(255);
        whiteLine.getUnselectedStyle().setPaddingUnit(Style.UNIT_TYPE_DIPS);
        whiteLine.getUnselectedStyle().setPadding(1, 0, 200, 1);
    
        
        //cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(lId),BoxLayout.encloseX(lNom),BoxLayout.encloseX(lPrenom),BoxLayout.encloseX(lMail),BoxLayout.encloseX(lAge),BoxLayout.encloseX(lTel),BoxLayout.encloseX(lGenre),BoxLayout.encloseX(lDateNaiss),BoxLayout.encloseX(lUsername),BoxLayout.encloseX(lPassword),BoxLayout.encloseX(lRoke),BoxLayout.encloseX(lCreCompte),BoxLayout.encloseX(whiteLine)));
 
        //Lenna win bech tsir acction mtaa suppression
        
        Label lSupprimer = new Label("  ");
        lSupprimer.setUIID("NewTopLine2");
        Style SupprimerStyle = new Style(lSupprimer.getUnselectedStyle());
        SupprimerStyle.setFgColor(0xf21f1f);
        
        FontImage suppromerImage = FontImage.createMaterial(FontImage.MATERIAL_DELETE, SupprimerStyle);
        lSupprimer.setIcon(suppromerImage);
        lSupprimer.setTextPosition(RIGHT);
       //cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(lSupprimer)));
        
       lSupprimer.addPointerPressedListener(l -> {
           Dialog digSupp = new Dialog("Suppression");
           
           if(digSupp.show("Suppression","Vous voulez supprimer cet utilisateur ?","Annuler","Oui")){
               digSupp.dispose();
           }
           else{
               digSupp.dispose();;
               
               //lenna apprel mtaa fonction delete
               if(ServiceUtilisateur.getInstance().deleteUtilisateur(id)){
                   //new ListUtilisateurForm(res);
                    new ListUtilisateurForm(res).show();
               }
           }
       });
       
       //lena win bech node5lou f modification
       
        Label lModifier = new Label("  ");
        lModifier.setUIID("NewTopLine2");
        Style ModifierStyle = new Style(lModifier.getUnselectedStyle());
        ModifierStyle.setFgColor(0xf7ad02);
        
        FontImage modifImage = FontImage.createMaterial(FontImage.MATERIAL_MODE_EDIT, ModifierStyle);
        lModifier.setIcon(modifImage);
        lModifier.setTextPosition(LEFT);
        
        lModifier.addPointerPressedListener(l ->{
           //System.out.println("\n\n\n\n\n\nnezelna ala button modiiiiiiiiiiiiiiiiiiif**********\n\n");
           
          new  ModifierUtilisateurForm(res,us).show();
           
        });
       
       
       
       cnt.add(BorderLayout.CENTER,BoxLayout.encloseY(BoxLayout.encloseX(lId,lSupprimer,lModifier),BoxLayout.encloseX(lNom),BoxLayout.encloseX(lPrenom),BoxLayout.encloseX(lMail),BoxLayout.encloseX(lAge),BoxLayout.encloseX(lTel),BoxLayout.encloseX(lGenre),BoxLayout.encloseX(lDateNaiss),BoxLayout.encloseX(lUsername),BoxLayout.encloseX(lPassword),BoxLayout.encloseX(lRoke),BoxLayout.encloseX(lCreCompte),BoxLayout.encloseX(whiteLine)));
 
       
       
       
        add(cnt);
        
        


      

        
    }
    
    
    
}
