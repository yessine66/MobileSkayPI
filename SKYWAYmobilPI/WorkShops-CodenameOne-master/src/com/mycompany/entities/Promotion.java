/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;
/**
 *
 * @author Lenovo
 */
public class Promotion {
    //enntities li f symfony
private int idProm,reduction,idP;
private String codeP,dated,datef;
String nom;

    public Promotion(int idProm, int reduction, String codeP, String dated, String datef, String nom) {
        this.idProm = idProm;
        this.reduction = reduction;
        this.codeP = codeP;
        this.dated = dated;
        this.datef = datef;
        this.nom = nom;
    }
    public Promotion(int idProm, int reduction, int idP, String codeP, String dated, String datef) {
        this.idProm = idProm;
        this.reduction = reduction;
        this.idP = idP;
        this.codeP = codeP;
        this.dated = dated;
        this.datef = datef;
    }

    public String getNom() {
        return nom;
    }

  /* public Promotion(int idProm, int reduction, String codeP, String dated, String datef) {
        this.idProm = idProm;
        this.reduction = reduction;
        this.codeP = codeP;
        this.dated = dated;
        this.datef = datef;
    }
*/
    public Promotion() {
    }

    public Promotion(int reduction, String codeP, String dated, String datef, int idP) {
        this.reduction = reduction;
       
        this.codeP = codeP;
        this.dated = dated;
        this.datef = datef;
         this.idP = idP;
    }

    public Promotion(int reduction, String codeP, String dated, String datef) {
        this.reduction = reduction;
        this.codeP = codeP;
        this.dated = dated;
        this.datef = datef;
    }

    public int getIdProm() {
        return idProm;
    }

    public void setIdProm(int idProm) {
        this.idProm = idProm;
    }

    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getCodeP() {
        return codeP;
    }

    public void setCodeP(String codeP) {
        this.codeP = codeP;
    }

    public String getDated() {
        return dated;
    }

    public void setDated(String dated) {
        this.dated = dated;
    }

    public String getDatef() {
        return datef;
    }

    public void setDatef(String datef) {
        this.datef = datef;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
  
    
    
    
    
    
    
    
    
    
    
}
