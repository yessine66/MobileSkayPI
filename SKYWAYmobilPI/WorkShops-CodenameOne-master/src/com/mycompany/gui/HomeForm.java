/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.components.ScaleImageLabel;
import com.codename1.ui.Button;
import com.codename1.ui.Component;
import com.codename1.ui.Display;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Image;
import com.codename1.ui.Label;
import com.codename1.ui.TextSelection;
import com.codename1.ui.Toolbar;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.layouts.FlowLayout;
import com.codename1.ui.layouts.LayeredLayout;
import com.codename1.ui.layouts.Layout;
import com.codename1.ui.plaf.Style;
import com.codename1.ui.util.Resources;
import com.mycompany.services.ServiceFeedback;
import com.mycompany.services.ServiceReclamation;



/**
 *
 * @author bhk
 */
public class HomeForm extends Form {

   
    
    
    Form current;

    /*Garder traçe de la Form en cours pour la passer en paramètres 
    aux interfaces suivantes pour pouvoir y revenir plus tard en utilisant
    la méthode showBack*/
    public HomeForm() {
        current = this; //Récupération de l'interface(Form) en cours
        setTitle("Home");
        setLayout(BoxLayout.y());

        add(new Label("Choose an option"));
        Button btnAddStudent = new Button("Add Student");
        Button btnListStudents = new Button("List Students");

        Button btnAddTutor = new Button("Add Tutor");
        Button btnListTutors = new Button("List Tutors");
        Button btnAjoutReclamation = new Button("Envoyer une reclamation");
        Button btnListReclamation = new Button("Liste des réclamation");
        Button btnAjoutFeed = new Button("Envoyer votre avis");
        Button btnListFeed = new Button("Liste des feedbacks");
        
        
        btnAjoutReclamation.addActionListener(e -> new AddReclamationForm(current).show());
          btnAjoutFeed.addActionListener(e -> new AddFeedbackForm(current).show());
           btnListFeed.addActionListener(e -> new ListFeedbackForm(current, ServiceFeedback.getInstance().getAllFeedbacks()).show());
      
       
       ;
    btnListReclamation.addActionListener(e -> new ListReclamationForm(current, ServiceReclamation.getInstance().getAllReclamations()).show());

        addAll(btnAjoutReclamation,btnListReclamation,btnListFeed,btnAjoutFeed);

    }

}
