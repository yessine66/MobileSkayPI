/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;
import com.codename1.l10n.ParseException;
import com.codename1.l10n.SimpleDateFormat;
import com.codename1.ui.events.ActionListener;


import com.mycompany.entities.Reclamation;
import com.mycompany.utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author IBTIHEL
 */
public class ServiceReclamation {
     public ArrayList<Reclamation> Reclamations;

    public static ServiceReclamation instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceReclamation() {
        req = new ConnectionRequest();
    }

    public static ServiceReclamation getInstance() {
        if (instance == null) {
            instance = new ServiceReclamation();
        }
        return instance;
    }
    
     public boolean addReclamation(Reclamation s) {
        String url = statics.BASE_URL + "/reclamation/add/ok?objet=" + s.getObjet() + "&textR=" + s.getTextR() + "&dateEnvoi=" + s.getDateEnvoi() +"&id=" + s.getId()+ "&cours=" + s.getCours() + "&enseignant=" + s.getEnseignant();
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
    
      public boolean updateReclamation(Reclamation s) {
        String url = statics.BASE_URL + "/updateReclamation/ok/" + s.getIdRec() + "?objet=" + s.getObjet() + "&textR=" + s.getTextR() + "&dateEnvoi=" + s.getDateEnvoi() + "&cours=" + s.getCours() + "&enseignant=" + s.getEnseignant() ;
              
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
      
      
       public ArrayList<Reclamation> getAllReclamations() {
        String url = statics.BASE_URL + "/reclamation/x/ok";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                Reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclamations;
    }
       
           public ArrayList<Reclamation> parseReclamations(String jsonText) {
        try {
           Reclamations = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> ReclamationsListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(ReclamationsListJson);
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) ReclamationsListJson.get("root");
            System.out.println(list);
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
                Reclamation s = new Reclamation();
                float id = Float.parseFloat(obj.get("idRec").toString());
      
                s.setIdRec((int) id);
                s.setObjet(obj.get("objet").toString());
                s.setTextR(obj.get("textR").toString());
                s.setDateEnvoi(obj.get("dateEnvoi").toString());
                s.setCours(obj.get("cours").toString());
                s.setEnseignant(obj.get("enseignant").toString());
               
               
                
                //Ajouter la tâche extraite de la réponse Json à la liste
                Reclamations.add(s);
            }

        } catch (IOException ex) {
        }
        
        return Reclamations;
    }
       
        public ArrayList<Reclamation> getSearchedStudents(String value) {
        String url = statics.BASE_URL + "/reclamation/allStudents/search=" + value;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                Reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclamations;
    }
        
        public boolean deleteReclamation(Reclamation s) {
        String url = statics.BASE_URL + "/reclamation/deleteStudentJson/ok/" + s.getIdRec(); //création de l'URL
        req.setUrl(url);// Insertion de l'URL de notre demande de connexion
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                resultOK = req.getResponseCode() == 200; //Code HTTP 200 OK
                req.removeResponseListener(this); //Supprimer cet actionListener
                /* une fois que nous avons terminé de l'utiliser.
                La ConnectionRequest req est unique pour tous les appels de 
                n'importe quelle méthode du Service task, donc si on ne supprime
                pas l'ActionListener il sera enregistré et donc éxécuté même si 
                la réponse reçue correspond à une autre URL(get par exemple)*/
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return resultOK;
    }
        
        public ArrayList<Reclamation> getTriReclamation(String value) {
        String url = statics.BASE_URL + "/reclamation/allStudents/tri=" + value;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                Reclamations = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Reclamations;
    }
}
