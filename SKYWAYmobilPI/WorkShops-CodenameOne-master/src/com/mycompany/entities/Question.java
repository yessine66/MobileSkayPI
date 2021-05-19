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
public class Question {
  
private int idQ,nbrPoint;
private String textQ,nameT;

    public Question(int nbrPoint, String textQ, String nameT) {
        this.nbrPoint = nbrPoint;
        this.textQ = textQ;
        this.nameT = nameT;
    }

    public Question() {
    }

    public Question(int idQ, int nbrPoint, String textQ, String nameT) {
        this.idQ = idQ;
        this.nbrPoint = nbrPoint;
        this.textQ = textQ;
        this.nameT = nameT;
    }

    public int getIdQ() {
        return idQ;
    }

    public void setIdQ(int idQ) {
        this.idQ = idQ;
    }

    public int getNbrPoint() {
        return nbrPoint;
    }

    public void setNbrPoint(int nbrPoint) {
        this.nbrPoint = nbrPoint;
    }

    public String getTextQ() {
        return textQ;
    }

    public void setTextQ(String textQ) {
        this.textQ = textQ;
    }

    public String getNameT() {
        return nameT;
    }

    public void setNameT(String nameT) {
        this.nameT = nameT;
    }











    
}

    

