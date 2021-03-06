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
import com.mycompany.entities.Reclamation;

import com.mycompany.services.ServiceReclamation;

import java.util.ArrayList;

/**
 *
 * @author IBTIHEL
 */
public class ListReclamationForm extends Form{

      Form current;

    public ListReclamationForm(Form previous, ArrayList<Reclamation> Reclamations) {
        current = this;
        setLayout(BoxLayout.y());
        setTitle("List Reclamations");
        //Add student
        Button btnAddStudent = new Button("Add Reclamation");
        btnAddStudent.addActionListener(e -> new AddReclamationForm(current).show());
        add(btnAddStudent);
        //Search
        TextField tfSearch = new TextField("", "Search by Objet");
        Button btnSearch = new Button("Search");
        btnSearch.addActionListener(e -> {
            if (!tfSearch.getText().isEmpty()) {
       new ListReclamationForm(new ListReclamationForm(new HomeForm(), ServiceReclamation.getInstance().getAllReclamations()), ServiceReclamation.getInstance().getSearchedStudents(tfSearch.getText())).show();
            }
        });
        addAll(tfSearch, btnSearch);
        
        //Tri
        ComboBox<String> triCB = new ComboBox<>("Objet", "text", "date", "Cours");
        Button btnTri = new Button("Tri");
        btnTri.addActionListener(e -> {
            new ListReclamationForm(new ListReclamationForm(new HomeForm(), ServiceReclamation.getInstance().getAllReclamations()), ServiceReclamation.getInstance().getTriReclamation(TriBy(triCB))).show();
        });
        addAll(triCB, btnTri);
        
        for (Reclamation s : Reclamations) {
            DisplayMusic(s);
        }
        getToolbar().addMaterialCommandToLeftBar("", FontImage.MATERIAL_ARROW_BACK, e -> previous.showBack());
    }

    ListReclamationForm(Resources res) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void DisplayMusic(Reclamation s) {
        Container con = new Container(BoxLayout.y());
        
        Label objet = new Label("objet: " + s.getObjet());
        Label text = new Label("text : " + s.getTextR());
        Label date = new Label("dateEnvoi : " + s.getDateEnvoi());
         Label cours = new Label("cours : " + s.getCours());
          Label enseignant = new Label("enseignant : " + s.getEnseignant());
        //show
       // Button btnShow = new Button("Show");
       // btnShow.addActionListener(e -> new StudentDetailsForm(current, s).show());
        //update
        Button btnUpdate = new Button("Update");
        btnUpdate.addActionListener(e -> new UpdateReclamationForm(current, s).show());
        //delete
        Button btnDel = new Button("Delete");
       btnDel.addActionListener(e -> {
            if (Dialog.show("Deleting " + s.getObjet(), "You sure you want to delete this item ?", "Ok", "Cancel")) {
                if (ServiceReclamation.getInstance().deleteReclamation(s)) {
                    Dialog.show("Success", "Connection accepted", new Command("OK"));
                    new ListReclamationForm(new HomeForm(), ServiceReclamation.getInstance().getAllReclamations()).show();
                } else {
                    Dialog.show("ERROR", "Server error", new Command("OK"));
                }
            }

       }); 
        con.addAll(objet,text,date,cours,enseignant,btnDel,btnUpdate/*, btnShow, , btnDel*/);
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
