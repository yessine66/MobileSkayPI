/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.gui;

import com.codename1.io.Preferences;

/**
 *
 * @author mega-pc
 */
public class Session {
    
    
    public static Preferences pref ; // 3ibara memoire sghira nsajlo fiha data 
    
    
    
    // hethom données ta3 user lyt7b tsajlhom fi session  ba3d login 
    private static int id ; 
    private static String userName ; 
    private static String mail; 
    private static String passowrd ;
    private static String role;

    public static Preferences getPref() {
        return pref;
    }

    public static void setPref(Preferences pref) {
        Session.pref = pref;
    }

    public static int getId() {
        return pref.get("id",id);// kif nheb njib id user connecté apres njibha men pref 
    }

    public static void setId(int id) {
        pref.set("id",id);//nsajl id user connecté  w na3tiha identifiant "id";
    }

    public static String getUserName() {
        return pref.get("username",userName);
    }

    public static void setUserName(String userName) {
         pref.set("username",userName);
    }

    public static String getMail() {
        return pref.get("mail",mail);
    }

    public static void setMail(String mail) {
         pref.set("mail",mail);
    }

    public static String getPassowrd() {
        return pref.get("passowrd",passowrd);
    }

    public static void setPassowrd(String passowrd) {
         pref.set("passowrd",passowrd);
    }

    public static String getRole() {
        return pref.get("role",role);
    }

    public static void setRole(String role) {
         pref.set("role",role);
    }
    
    
    
    
}
