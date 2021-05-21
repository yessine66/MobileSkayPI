/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.codename1.ui.ComboBox;
import java.util.ArrayList;

/**
 *
 * @author admin
 */
public class States {

    public static ComboBox<String> states;

    public static ComboBox<String> getStatesCB() {
        states = new ComboBox<>();
        states.addItem("Ariana");
        states.addItem("Béja");
        states.addItem("Ben Arous");
        states.addItem("Bizerte");
        states.addItem("Gabès");
        states.addItem("Gafsa");
        states.addItem("Jendouba");
        states.addItem("Kairouan");
        states.addItem("Kasserine");
        states.addItem("Kebili");
        states.addItem("Kef");
        states.addItem("Mahdia");
        states.addItem("Manouba");
        states.addItem("Medenine");
        states.addItem("Monastir");
        states.addItem("Nabeul");
        states.addItem("Sfax");
        states.addItem("Sidi Bouzid");
        states.addItem("Siliana");
        states.addItem("Sousse");
        states.addItem("Tataouine");
        states.addItem("Tozeur");
        states.addItem("Tunis");
        states.addItem("Zaghouan");
        return states;
    }

}
