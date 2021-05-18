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
import com.mycompany.entities.Utilisateur;
import com.mycompany.services.ServiceUtilisateur;

/**
 *
 * @author mega-pc
 */
public class ModifierUtilisateurForm extends BaseForm{
    
    Form current;
    
    public ModifierUtilisateurForm(Resources res , Utilisateur u){
        
                super("Newsfeed",BoxLayout.y()); //
        
        Toolbar tb = new Toolbar(true);
        current=this;
        setToolbar(tb);
        getTitleArea().setUIID("Container");
        setTitle("Create Account");
        getContentPane().setScrollVisible(false);
        
        
        
        super.addSideMenu(res);
        
        TextField tfNom = new TextField(u.getNom(),"Nom...",50,TextField.ANY);
        TextField tfPrenom = new TextField(u.getPrenom(),"Prenom...",50,TextField.ANY);
        TextField tfMail = new TextField(u.getMail(),"Mail...",50,TextField.ANY);
        TextField tfAge = new TextField(String.valueOf(u.getAge()),"Age...",50,TextField.ANY);
        TextField tfTel = new TextField(String.valueOf(u.getTel()),"Telephone...",50,TextField.ANY);
        //TextField tfGenre = new TextField(u.getGenre(),"Genre...",50,TextField.ANY);
        
        ComboBox cbGenre = new ComboBox();
        
        cbGenre.addItem("femme");
        cbGenre.addItem("homme");
        
        
        if(u.getGenre().equals("femme")){
            cbGenre.setSelectedIndex(0);
        }
        else{
            cbGenre.setSelectedIndex(1);
        }
        
        TextField tfUsername = new TextField(u.getNom(),"Username...",50,TextField.ANY);
        TextField tfPassword = new TextField(u.getPassword(),"Password...",50,TextField.ANY);
        TextField tfRole = new TextField(u.getRole(),"Nom",50,TextField.ANY);
        tfRole.setEditable(false);
        
        
        tfNom.setUIID("NewTopLine");
        tfPrenom.setUIID("NewTopLine");
        tfMail.setUIID("NewTopLine");
        tfAge.setUIID("NewTopLine");
        tfTel.setUIID("NewTopLine");
        tfUsername.setUIID("NewTopLine");
        tfPassword.setUIID("NewTopLine");
        tfRole.setUIID("NewTopLine");

        cbGenre.setUIID("NewTopLine");
        
        
        tfNom.setSingleLineTextArea(true);
        tfPrenom.setSingleLineTextArea(true);
        tfMail.setSingleLineTextArea(true);
        tfAge.setSingleLineTextArea(true);
        tfTel.setSingleLineTextArea(true);
        tfUsername.setSingleLineTextArea(true);
        tfPassword.setSingleLineTextArea(true);
        tfRole.setSingleLineTextArea(true);
        
        Button btnModifier = new Button("Modifierx");
        btnModifier.setUIID("Button");
        
        
        
        btnModifier.addPointerPressedListener(l -> {
            
            
            u.setNom(tfNom.getText());
            u.setPrenom(tfPrenom.getText());
            u.setMail(tfMail.getText());
            u.setAge(Integer.parseInt(tfAge.getText()));
            u.setTel(Integer.parseInt(tfTel.getText()));
            
            if(cbGenre.getSelectedIndex()==1){
                u.setGenre("homme");;
            }
            if(cbGenre.getSelectedIndex()==0){
                u.setGenre("femme");
            }
            
          //  u.setGenre(cbGenre.getSelectCommandText());
            u.setUsername(tfUsername.getText());
            u.setPassword(tfPassword.getText());
            u.setRole(tfRole.getText());
        
        
            
           
            
            
            
        
            //tawa rassmi bech tsir modification chen3aytou lel fonction li f service 
        
            if(ServiceUtilisateur.getInstance().modifierUtilisateur(u)){
                System.out.println("\n\n\n\naffectaion**************************");
                new ListUtilisateurForm(res).show();
            }else
                System.out.println("\n\n\n******mat3adetech affection mtaa modif");
        
            
        
        });
        
        Button btnAnnuler = new Button("Annuler");
            btnAnnuler.addActionListener(x -> {
                new ListUtilisateurForm(res).show();
            });
        
        Label aa = new Label("");
        Label bb = new Label("");
        
        Label cc = new Label("");
        Label dd = new Label("");
        
        Label ee = new Label();

        
        Container content = BoxLayout.encloseY(
        
                ee,aa,
                new FloatingHint(tfNom),
                createLineSeparator(),
                new FloatingHint(tfPrenom),
                createLineSeparator(),
                new FloatingHint(tfMail),
                createLineSeparator(),
                new FloatingHint(tfAge),
                createLineSeparator(),
                new FloatingHint(tfTel),
                createLineSeparator(),
                cbGenre,
                createLineSeparator(),
                
                new FloatingHint(tfUsername),
                createLineSeparator(),
                new FloatingHint(tfPassword),
                createLineSeparator(),
                new FloatingHint(tfRole),
                createLineSeparator(),
                //cc,dd,
                btnModifier,
                btnAnnuler
         
                
        );
                
        
        add(content);
        show();
        
        
        
        
        
        
    }
    
    
}
