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
import com.codename1.ui.events.ActionListener;
import com.mycompany.entities.Feedback;
import com.mycompany.utils.statics;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author IBTIHEL
 */
public class ServiceFeedback {
    
    public ArrayList<Feedback> Feedbacks;

    public static  ServiceFeedback instance = null;
    public boolean resultOK;
    private ConnectionRequest req;

    public ServiceFeedback() {
        req = new ConnectionRequest();
    }

    public static  ServiceFeedback getInstance() {
        if (instance == null) {
            instance = new  ServiceFeedback();
        }
        return instance;
    }
    
    
      public boolean addFeedback(Feedback s) {
        String url = statics.BASE_URL + "/feedback/addFeed/ok?objet=" + s.getObjet() + "&text=" + s.getText() + "&avis=" + s.getAvis() +"&id=" +s.getId()  ;
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
      
       public boolean updateFeedback(Feedback s) {
           String url = statics.BASE_URL + "/feedback/updateFeedback/ok?objet=" + s.getObjet() + "&text=" + s.getText() + "&avis=" + s.getAvis() + "&id=" + s.getId() ;
              
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
    
       
       
       public ArrayList<Feedback> getAllFeedbacks() {
        String url = statics.BASE_URL + "/feedback/y/ok";
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                Feedbacks = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }

            
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Feedbacks;
    }
       
           public ArrayList<Feedback> parseReclamations(String jsonText) {
        try {
          Feedbacks = new ArrayList<>();
            JSONParser j = new JSONParser();
            Map<String, Object> FeedbacksListJson = j.parseJSON(new CharArrayReader(jsonText.toCharArray()));
            System.out.println(FeedbacksListJson);
            
            List<Map<String, Object>> list = (List<Map<String, Object>>) FeedbacksListJson.get("root");
            System.out.println(list);
            //Parcourir la liste des tâches Json
            for (Map<String, Object> obj : list) {
                //Création des tâches et récupération de leurs données
               Feedback s = new Feedback();
                float id = Float.parseFloat(obj.get("idF").toString());
      
                s.setIdF((int) id);
                s.setObjet(obj.get("objet").toString());
                s.setText(obj.get("text").toString());
                s.setAvis(obj.get("avis").toString());
         //Ajouter la tâche extraite de la réponse Json à la liste
                Feedbacks.add(s);
            }

        } catch (IOException ex) {
        }
        
        return Feedbacks;
    }
       
        public ArrayList<Feedback> getSearchedFeeds(String value) {
        String url = statics.BASE_URL + "/feedback/allFeedbacks/search=" + value;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                Feedbacks = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Feedbacks;
    }
        
          public boolean deleteFeedback(Feedback s) {
        String url = statics.BASE_URL + "/feedback/deleteFeed/ok/" + s.getIdF(); //création de l'URL
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
        
        public ArrayList<Feedback> getTriReclamation(String value) {
        String url = statics.BASE_URL + "/Feedback/allFeedbacks/tri=" + value;
        req.setUrl(url);
        req.setPost(false);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                System.out.println(new String(req.getResponseData()));
                Feedbacks = parseReclamations(new String(req.getResponseData()));
                req.removeResponseListener(this);
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(req);
        return Feedbacks;
    }

    
}
