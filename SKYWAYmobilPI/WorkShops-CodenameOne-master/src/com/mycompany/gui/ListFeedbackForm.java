/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.ui.Button;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Command;
import com.codename1.ui.Container;
import com.codename1.ui.Dialog;
import com.codename1.ui.FontImage;
import com.codename1.ui.Form;
import com.codename1.ui.Label;
import com.codename1.ui.TextField;
import com.codename1.ui.layouts.BoxLayout;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Feedback;
import com.mycompany.entities.Reclamation;
import com.mycompany.services.ServiceFeedback;
import java.util.ArrayList;

/**
 *
 * @author IBTIHEL
 */
public class ListFeedbackForm extends Form {
    
      Form current;

    public ListFeedbackForm(Form previous, ArrayList<Feedback> Feedbacks) {
        current = this;
        setLayout(BoxLayout.y());
        setTitle("List Feedbacks");
        //Add student
        Button btnAddStudent = new Button("Add Feedback");
        btnAddStudent.addActionListener(e -> new AddFeedbackForm(current).show());
        add(btnAddStudent);
        //Search
        TextField tfSearch = new TextField("", "Search by Avis");
        Button btnSearch = new Button("Search");
        btnSearch.addActionListener(e -> {
            if (!tfSearch.getText().isEmpty()) {
                new ListFeedbackForm(new ListFeedbackForm(new HomeForm(), ServiceFeedback.getInstance().getAllFeedbacks()), ServiceFeedback.getInstance().getSearchedFeeds(tfSearch.getText())).show();
            }
        });
        addAll(tfSearch, btnSearch);
        
        //Tri
        ComboBox<String> triCB = new ComboBox<>("Objet", "text", "Avis");
        Button btnTri = new Button("Tri");
        btnTri.addActionListener(e -> {
            new ListFeedbackForm(new ListFeedbackForm(new HomeForm(), ServiceFeedback.getInstance().getAllFeedbacks()), ServiceFeedback.getInstance().getSearchedFeeds(TriBy(triCB))).show();
        });
        addAll(triCB, btnTri);
        
        for (Feedback s : Feedbacks) {
            DisplayMusic(s);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    ListFeedbackForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DisplayMusic(Feedback s) {
        Container con = new Container(BoxLayout.y());
        
        Label objet = new Label("objet: " + s.getObjet());
        Label text = new Label("text : " + s.getText());
        Label avis = new Label("dateEnvoi : " + s.getAvis());
         
        //show
       // Button btnShow = new Button("Show");
       // btnShow.addActionListener(e -> new StudentDetailsForm(current, s).show());
        //update
        Button btnUpdate = new Button("Update");
//        btnUpdate.addActionListener(e -> new UpdateReclamationForm(current, s).show());
        //delete
        Button btnDel = new Button("Delete");
      btnDel.addActionListener(e -> {
            if (Dialog.show("Deleting " + s.getObjet(), "You sure you want to delete this item ?", "Ok", "Cancel")) {
                if (ServiceFeedback.getInstance().deleteFeedback(s)) {
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                    new ListFeedbackForm(new HomeForm(), ServiceFeedback.getInstance().getAllFeedbacks()).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }

       }); 
        con.addAll(objet,text,avis,btnDel,btnUpdate/*, btnShow, , btnDel*/);
        add(con);
    }

    private String TriBy(ComboBox<String> triCB) {
        switch (triCB.getSelectedItem()) {
            case "ID":
                return "id";
            case "Username":
                return "username";
            case "First Name":
                return "firstName";
            case "Last Name":
                return "lastName";
            default:
                return "id";
        }
    }
    
}
