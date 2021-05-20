/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author IBTIHEL
 */
public class Reclamation {
    
    private int idRec;
    private String objet,textR,dateEnvoi ;
    private int id;
    private String cours,enseignant;

    public Reclamation() {
    }

    public Reclamation(int idRec, String objet, String textR, String dateEnvoi, String cours, String enseignant) {
        this.idRec = idRec;
        this.objet = objet;
        this.textR = textR;
        this.dateEnvoi = dateEnvoi;
        this.cours = cours;
        this.enseignant = enseignant;
    }
    
    

    public Reclamation(int idRec, String objet, String textR, String dateEnvoi, int id, String cours, String enseignant) {
        this.idRec = idRec;
        this.objet = objet;
        this.textR = textR;
        this.dateEnvoi = dateEnvoi;
        this.id = id;
        this.cours = cours;
        this.enseignant = enseignant;
    }

    public Reclamation(String objet, String textR, String dateEnvoi, String cours, String enseignant) {
        this.objet = objet;
        this.textR = textR;
        this.dateEnvoi = dateEnvoi;
        this.cours = cours;
        this.enseignant = enseignant;
    }

    public Reclamation(String objet, String textR, String dateEnvoi, int id, String cours, String enseignant) {
        this.objet = objet;
        this.textR = textR;
        this.dateEnvoi = dateEnvoi;
        this.id = id;
        this.cours = cours;
        this.enseignant = enseignant;
    }

    public int getIdRec() {
        return idRec;
    }

    public void setIdRec(int idRec) {
        this.idRec = idRec;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getTextR() {
        return textR;
    }

    public void setTextR(String textR) {
        this.textR = textR;
    }

    public String getDateEnvoi() {
        return dateEnvoi;
    }

    public void setDateEnvoi(String dateEnvoi) {
        this.dateEnvoi = dateEnvoi;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCours() {
        return cours;
    }

    public void setCours(String cours) {
        this.cours = cours;
    }

    public String getEnseignant() {
        return enseignant;
    }

    public void setEnseignant(String enseignant) {
        this.enseignant = enseignant;
    }
 
    
    
}
