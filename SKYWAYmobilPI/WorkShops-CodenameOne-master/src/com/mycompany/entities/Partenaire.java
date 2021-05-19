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
public class Partenaire {
  int idP; 
String nomP,domaine,dateP,mailp,logop;

    public Partenaire() {
    }


    public Partenaire(int idP, String nomP, String domaine, String dateP, String mailp, String logop) {
        this.idP = idP;
        this.nomP = nomP;
        this.domaine = domaine;
        this.dateP = dateP;
        this.mailp = mailp;
        this.logop = logop;
    }

    public Partenaire(String nomP, String domaine, String dateP, String mailp, String logop) {
        this.nomP = nomP;
        this.domaine = domaine;
        this.dateP = dateP;
        this.mailp = mailp;
        this.logop = logop;
    }

    public Partenaire(String nomP, String domaine, String dateP, String mailp) {
        this.nomP = nomP;
        this.domaine = domaine;
        this.dateP = dateP;
        this.mailp = mailp;
    }

    public int getIdP() {
        return idP;
    }

    public void setIdP(int idP) {
        this.idP = idP;
    }

    public String getNomP() {
        return nomP;
    }

    public void setNomP(String nomP) {
        this.nomP = nomP;
    }

    public String getDomaine() {
        return domaine;
    }

    public void setDomaine(String domaine) {
        this.domaine = domaine;
    }

    public String getDateP() {
        return dateP;
    }

    public void setDateP(String dateP) {
        this.dateP = dateP;
    }

    public String getMailp() {
        return mailp;
    }

    public void setMailp(String mailp) {
        this.mailp = mailp;
    }

    public String getLogop() {
        return logop;
    }

    public void setLogop(String logop) {
        this.logop = logop;
    }


    
    
    
    
    
    
    
    
    
    
    
    
}
