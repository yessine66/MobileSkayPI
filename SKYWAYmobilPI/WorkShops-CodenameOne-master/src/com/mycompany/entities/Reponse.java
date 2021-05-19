/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;

/**
 *
 * @author User-DELL
 */
public class Reponse {
  
private int idR,idQ,id;
private String textR1,textR2,textR3,textR4;
String nom;

    public Reponse(String textR1, String textR2, String textR3, String textR4) {
        this.textR1 = textR1;
        this.textR2 = textR2;
        this.textR3 = textR3;
        this.textR4 = textR4;
    }

    public Reponse(int idR, int idQ, int id, String textR1, String textR2, String textR3, String textR4, String nom) {
        this.idR = idR;
        this.idQ = idQ;
        this.id = id;
        this.textR1 = textR1;
        this.textR2 = textR2;
        this.textR3 = textR3;
        this.textR4 = textR4;
        this.nom = nom;
    }
    public Reponse() {
    }

    public Reponse(int idQ, int id, String textR1, String textR2, String textR3, String textR4, String nom) {
        this.idQ = idQ;
        this.id = id;
        this.textR1 = textR1;
        this.textR2 = textR2;
        this.textR3 = textR3;
        this.textR4 = textR4;
        this.nom = nom;
    }
    

    public Reponse(int idR, int idQ, int id, String textR1, String textR2, String textR3, String textR4) {
        this.idR = idR;
        this.idQ = idQ;
        this.id = id;
        this.textR1 = textR1;
        this.textR2 = textR2;
        this.textR3 = textR3;
        this.textR4 = textR4;
    }

    public Reponse(int idQ, int id, String textR1, String textR2, String textR3, String textR4) {
        this.idQ = idQ;
        this.id = id;
        this.textR1 = textR1;
        this.textR2 = textR2;
        this.textR3 = textR3;
        this.textR4 = textR4;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }




    public int getIdR() {
        return idR;
    }

    public void setIdR(int idR) {
        this.idR = idR;
    }

    public int getIdQ() {
        return idQ;
    }

    public void setIdQ(int idQ) {
        this.idQ = idQ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTextR1() {
        return textR1;
    }

    public void setTextR1(String textR1) {
        this.textR1 = textR1;
    }

    public String getTextR2() {
        return textR2;
    }

    public void setTextR2(String textR2) {
        this.textR2 = textR2;
    }

    public String getTextR3() {
        return textR3;
    }

    public void setTextR3(String textR3) {
        this.textR3 = textR3;
    }

    public String getTextR4() {
        return textR4;
    }

    public void setTextR4(String textR4) {
        this.textR4 = textR4;
    }

  








    
}

    

