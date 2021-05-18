/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.entities;


import java.util.Date;


/**
 *
 * @author mega-pc
 */
public class Utilisateur {
    
    private int id;
    private String nom;
    private String prenom;
    private String mail;
    private int age ;
    private int tel;
    private String genre;
    private String dateNaiss;
    private String username;
    private String password;
    private String role;
    private String creCompte;

    public Utilisateur() {
    }

    public Utilisateur(String nom, String prenom, String mail, int age, int tel, String genre, String dateNaiss, String username, String password, String role) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.age = age;
        this.tel = tel;
        this.genre = genre;
        this.dateNaiss = dateNaiss;
        this.username = username;
        this.password = password;
        this.role = role;
    }
    
    

    public Utilisateur(String nom, String prenom, String mail, int age, int tel, String genre, String dateNaiss, String username, String password, String role, String creCompte) {
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.age = age;
        this.tel = tel;
        this.genre = genre;
        this.dateNaiss = dateNaiss;
        this.username = username;
        this.password = password;
        this.role = role;
        this.creCompte = creCompte;
    }
    
    
    

    public Utilisateur(int id, String nom, String prenom, String mail, int age, int tel, String genre, String dateNaiss, String username, String password, String role, String creCompte) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.mail = mail;
        this.age = age;
        this.tel = tel;
        this.genre = genre;
        this.dateNaiss = dateNaiss;
        this.username = username;
        this.password = password;
        this.role = role;
        this.creCompte = creCompte;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getTel() {
        return tel;
    }

    public void setTel(int tel) {
        this.tel = tel;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getDateNaiss() {
        return dateNaiss;
    }

    public void setDateNaiss(String dateNaiss) {
        this.dateNaiss = dateNaiss;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getCreCompte() {
        return creCompte;
    }

    public void setCreCompte(String creCompte) {
        this.creCompte = creCompte;
    }
    
    
    
    
    
    
    
}
