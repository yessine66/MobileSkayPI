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
public class Feedback {
    
    private int idF;
    private String objet,text,avis ;
    private int id ;

    public Feedback() {
    }

    public Feedback(int idF, String objet, String text, String avis, int id) {
        this.idF = idF;
        this.objet = objet;
        this.text = text;
        this.avis = avis;
        this.id = id;
    }

    public Feedback(String objet, String text, String avis, int id) {
        this.objet = objet;
        this.text = text;
        this.avis = avis;
        this.id = id;
    }

    public Feedback(int idF, String objet, String text, String avis) {
        this.idF = idF;
        this.objet = objet;
        this.text = text;
        this.avis = avis;
    }

     public Feedback( String objet, String text, String avis) {
       
        this.objet = objet;
        this.text = text;
        this.avis = avis;
    }

    public int getIdF() {
        return idF;
    }

    public void setIdF(int idF) {
        this.idF = idF;
    }

    public String getObjet() {
        return objet;
    }

    public void setObjet(String objet) {
        this.objet = objet;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getAvis() {
        return avis;
    }

    public void setAvis(String avis) {
        this.avis = avis;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    
    
}
