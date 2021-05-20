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
import com.mycompany.entities.Reclamation;
import com.mycompany.utils.statics;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 *
 * @author IBTIHEL
 */
public class ServiceReclamation {
     //njibou crud json m syfony 
    //singleton
    public static ServiceReclamation instance = null;
    //initilalisation connection request 
    public static boolean resultOK = true;
    private ConnectionRequest req; 
    
      public static ServiceReclamation getInstance ()
    {
        
        if (instance== null)
            instance = new ServiceReclamation();
        return instance;
        
    }
      
      public ServiceReclamation()
    {
        
        req= new ConnectionRequest();
        
    }
      
      // *************** Ajout Reclamation *****************
      
          
   public void ajouterReclamation (Reclamation reclamation)
   {
       String url = statics.BASE_URL+"/reclamation/add/ok?objet="+reclamation.getObjet()+"&textR="+ reclamation.getTextR()+"&dateEnvoi="+reclamation.getDateEnvoi()+"&id="+reclamation.getId()+"&cours="+reclamation.getCours()+"&enseignant="+reclamation.getEnseignant();
       req.setUrl(url);
       req.addResponseListener((e)->{
  
       String str = new String (req.getResponseData());///reponse json
       System.out.println("data="+str);
   
      });
   
       NetworkManager.getInstance().addToQueueAndWait(req);
//execution tar equest       
   }
   
     //***************** AFFICHAGE ***************************
    
   public ArrayList <Reclamation> afficherReclamation ()
           {
               
               
    ArrayList <Reclamation> result = new ArrayList<>();
    
    String url =statics.BASE_URL+"/reclamation/x/ok";
    req.setUrl(url);
    
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;
            jsonp= new JSONParser();
            try {
                   System.out.println("CCC");
                
                Map<String,Object>mapReclamation = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
                   System.out.println(new String (req.getResponseData()).toCharArray());
                List <Map<String,Object>>listOfMaps = (List <Map<String,Object>>) mapReclamation.get("root");
                for (Map<String,Object> obj : listOfMaps)
                { 
                       System.out.println("FFF");
                    Reclamation re = new Reclamation();
                   float idRec = Float.parseFloat(obj.get("idRec").toString());
                    String objet = obj.get("objet").toString();
                    String textR = obj.get("textR").toString();
                    String dateEnvoi = obj.get("dateEnvoi").toString();
                    //float id = Float.parseFloat(obj.get("id").toString());
                    String cours = obj.get("cours").toString();
                    String enseignant = obj.get("enseignant").toString();
            
                  //  float idP = Float.parseFloat(obj.get("idP").toString());
                            System.out.println("ggggggg");     
                            
                        re.setIdRec((int) idRec);
                        re.setObjet(objet);
                        re.setTextR( textR );
                        re.setDateEnvoi(dateEnvoi);
                    //    re.setId((int) id);
                        re.setCours(cours);
                        re.setEnseignant(enseignant);
                        // promo.setIdP((int) idP);
  
                  System.out.println("hhhhhhh"); 
         
                  //inset data in array list result 
                   result.add(re);
                   System.out.println("qqqqqq");
                    
                }
            }
   
           catch (Exception ex){
               ex.printStackTrace();
               System.out.println("arraaaaaaaaaaaaaaaay ");
           }
    
        }
    });

      //  return null;
    
       NetworkManager.getInstance().addToQueueAndWait(req);
       return result;
               
           }


  //********************delete**************************** 
   
   public boolean supprimerReclamation (int idRec)
   {
       //http://127.0.0.1:8000/promotiondel?idProm=176
       
       String url = statics.BASE_URL+"/deleteReclamation/ok="+idRec;   
       req.setUrl(url);
        req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
 
            req.removeResponseCodeListener(this);
            
            
        }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
       
       return resultOK;
   }
   //**************** Update ***************************
   
   public boolean modifierReclamation(Reclamation reclamation)
   { 
    ///promotionup?idProm=235&codeP=abbbb&reduction=545&dated=7/7/8&datef=1/1/1&idP=256&id=156   
    String url = statics.BASE_URL+"/updateReclamation/ok?idRec="+reclamation.getIdRec()+"&objet="+reclamation.getObjet()+"&textR="+ reclamation.getTextR()+"&dateEnvoi="+reclamation.getDateEnvoi()+"&id="+reclamation.getId()+"&cours="+reclamation.getCours()+"&enseignant="+reclamation.getEnseignant();     
    req.setUrl(url);
    req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
 
            //req.removeResponseCodeListener(this);
            resultOK= req.getResponseCode() == 200;// code resp 200 http ?
                    req.removeResponseListener(this);
      }
    });
    NetworkManager.getInstance().addToQueueAndWait(req);
    return resultOK;
       
   }


   
}

