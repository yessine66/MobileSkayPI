/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.spinner.Picker;
import com.mycompany.entities.Reclamation;

import com.mycompany.services.ServiceReclamation;


import java.util.Date;

/**
 *
 * @author IBTIHEL
 */
public class UpdateReclamationForm  extends Form {
    
    public UpdateReclamationForm(Form previous, Reclamation s) {
        setTitle("Update : " + s.getObjet());
        setLayout(BoxLayout.y());

                TextField objet = new TextField(null, "Objet", 20, TextArea.ANY);
        TextField text = new TextField(null, "Text", 20, TextArea.ANY);
        TextField date = new TextField(null, "Date Envoi", 20, TextArea.ANY);
        TextField cours = new TextField(null, "Cours", 20, TextArea.ANY);
        TextField enseignant = new TextField(null, "Enseignant", 20, TextArea.ANY);
        Button btnValider = new Button("Update Reclamation");
        btnValider.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
               if ((objet.getText().length() == 0) || (text.getText().length() == 0) || (date.getText().length() == 0) ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
          
                } else {
                    try {
          Reclamation S = new Reclamation(objet.getText(), text.getText(), date.getText(), cours.getText(), enseignant.getText());                          
                        if (ServiceReclamation.getInstance().updateReclamation(S)) {
                            Dialog.show("Success", "Connection accepted", new Command("OK"));
                            new ListReclamationForm(new HomeForm(), ServiceReclamation.getInstance().getAllReclamations()).show();
                        } else {
                            Dialog.show("ERROR", "Server error", new Command("OK"));
                        }
                    } catch (NumberFormatException e) {
                    }
                }
            }
        });

        addAll(objet,text,date,cours,enseignant, btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }
    
}
