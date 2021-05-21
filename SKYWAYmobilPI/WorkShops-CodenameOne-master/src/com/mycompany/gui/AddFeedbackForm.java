/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ToastBar;
import com.codename1.ui.Button;
import com.codename1.ui.Command;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextArea;
import com.codename1.ui.TextField;
import com.codename1.ui.events.ActionEvent;
import com.codename1.ui.events.ActionListener;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.plaf.UIManager;
import com.mycompany.entities.Feedback;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceFeedback;
import com.mycompany.services.ServiceReclamation;

/**
 *
 * @author IBTIHEL
 */
public class AddFeedbackForm extends Form{
    
    
    public AddFeedbackForm  (Form previous) {
        setTitle("Add a new Feedback");
        setLayout(BoxLayout.y());

       
        Label pic = new Label();

       

        TextField objet = new TextField(null, "Objet", 20, TextArea.ANY);
        TextField text = new TextField(null, "Text", 20, TextArea.ANY);
        TextField avis = new TextField(null, "Avis", 20, TextArea.ANY);
        
        Button btnValider = new Button("Add Reclamation");
        btnValider.getAllStyles().setTextDecoration(Style.TEXT_DECORATION_UNDERLINE);
        btnValider.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                if ((objet.getText().length() == 0) || (text.getText().length() == 0)  ) {
                    Dialog.show("Alert", "Please fill all the fields", new Command("OK"));
                    
                } 
               else {
                    if (objet.getText().equals(text.getText())) {
                        try {
                            Feedback S = new Feedback(objet.getText(), text.getText(), avis.getText(),1);
                                   
                            if (ServiceFeedback.getInstance().addFeedback(S)) {
                                Dialog.show("Success", "Connection accepted", new Command("OK"));
                                  ToastBar.Status s = ToastBar.getInstance().createStatus();
                            s.setMessage("Votre reclamation est envoyer avec succés !");
                            s.setMessageUIID("Title");
                            Image i = FontImage.createMaterial(FontImage.MATERIAL_ERROR, UIManager.getInstance().getComponentStyle("Title"));
                            s.setIcon(i);
                            s.setExpires(9000);
                            s.show();
                                new ListFeedbackForm(new HomeForm(), ServiceFeedback.getInstance().getAllFeedbacks()).show();
                                   
                            } else {
                                Dialog.show("ERROR", "Server error", new Command("OK"));
                            }
                        } catch (NumberFormatException e) {
                        }
                    } else {
                        Dialog.show("Alert", "Password doesn't match", new Command("OK"));

                    }
                }
            }
        });
 
        addAll(objet,text,avis,btnValider);
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    
}
