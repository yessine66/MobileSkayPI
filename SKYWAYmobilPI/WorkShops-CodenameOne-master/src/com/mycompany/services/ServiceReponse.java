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
import com.mycompany.entities.Reponse;
import static com.mycompany.services.ServiceUtilisateur.resultOK;
import com.mycompany.utils.statics;
//import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ServiceReponse {

    public static ServiceReponse instance = null;    
    private ConnectionRequest req; 
   
      public static ServiceReponse getInstance ()
    {
        
        if (instance== null)
            instance = new ServiceReponse();
        return instance;     
        
    }

    
    public ServiceReponse()
    {   
        req= new ConnectionRequest();
      
    }
    
       public void ajouterReponse (Reponse Reponse)
   {
       
      String url = statics.BASE_URL+"/reponse/add?textR1="+Reponse.getTextR1()+"&textR2="+Reponse.getTextR2()+"&textR3="+Reponse.getTextR3()+"&textR4="+Reponse.getTextR4()+"&idQ="+ Reponse.getIdQ()+"&id="+Reponse.getId();
      req.setUrl(url);
      req.addResponseListener((e)->{
      
      
      
      String str = new String (req.getResponseData());///reponse json
          System.out.println("data="+str);
      
      
      
      
      
      
      });
      
      
       NetworkManager.getInstance().addToQueueAndWait(req);
//execution tar equest       
   }
    
    
   public ArrayList <Reponse> afficherReponse ()
           {
               
               
    ArrayList <Reponse> result = new ArrayList<>();
    
    String url =statics.BASE_URL+"/reponse/display";
    req.setUrl(url);
    
      req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            
            JSONParser jsonp;

  jsonp= new JSONParser();
            try {
                 
                
                Map<String,Object>mapReponse = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
         
                List <Map<String,Object>>listOfMaps = (List <Map<String,Object>>) mapReponse.get("root");
               
              
                for (Map<String,Object> obj : listOfMaps)
                {  
                 
                   Reponse promo = new Reponse();
               float idR = Float.parseFloat(obj.get("idR").toString());
                  String textR1 = obj.get("textR1").toString();
                  String textR2 = obj.get("textR2").toString();
                  String textR3 = obj.get("textR3").toString();
                  String textR4 = obj.get("textR4").toString();
                   
String A = obj.get("idQ").toString();
String strIdStudent = A.substring(6, A.length() - 1);
                      System.out.println("kk2"+strIdStudent);             
                                   
                                      System.out.println("tes3");
                    promo.setIdR((int) idR);
                 promo.setTextR1(textR1);
                 promo.setTextR2(textR2);
                 promo.setTextR3(textR3);
                 promo.setTextR4(textR4);
      
 
//promo.setIdP((int)x);
promo.setNom(strIdStudent);
System.out.println("kk"+promo.getNom());
                          System.out.println("tes5");

                   result.add(promo);

                 
                }
                    
               
            }
            
           catch (Exception ex){
               
               ex.printStackTrace();
               System.out.println("ERREUR AFFICHAGE QUESTION ");
           }
            
            
            
            
            
            
            
            
            
        }
    });
    
    
    
    
    
    
    
    
    
    
      //  return null;
    
       NetworkManager.getInstance().addToQueueAndWait(req);
       return result;
               
           }
    
   
   
   //Detail Promo


   
   
   
      public Reponse detailReponse (int idR, Reponse Reponse)
   {
       String url =statics.BASE_URL+"det?"+idR;
       req.setUrl(url);
         String str= new String (req.getResponseData());
       
       req.addResponseListener((ev)->{ 
       
       
       JSONParser jsonp= new JSONParser();
       try{
           Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String (str).toCharArray())); 
           
      Reponse.setIdR(Integer.parseInt(obj.get("idR").toString()));
      Reponse.setTextR1(obj.get("textR1").toString());
      Reponse.setTextR2(obj.get("textR2").toString());
      Reponse.setTextR3(obj.get("textR3").toString());
      Reponse.setTextR4(obj.get("textR4").toString());
        
             Reponse.setIdQ(Integer.parseInt(obj.get("idQ").toString()));
             Reponse.setId(Integer.parseInt(obj.get("id").toString()));
       }catch (IOException  ex )
       {
           
           System.out.println("erreur related to sql\n"+ex.getMessage());
          
       }
           System.out.println("data =>"+str);
       
       
       
       });
         NetworkManager.getInstance().addToQueueAndWait(req);
       return Reponse;
               
       
   }
   
   
   
   
   //delete 
   
   public boolean supprimerReponse (int idR)
   {
       //http://127.0.0.1:8000/Reponsedel?idProm=176
       
       String url = statics.BASE_URL+"/reponsedel?idR="+idR;   
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
   
   
   
 //Update
   
   public boolean modifierReponse (Reponse Reponse)
   { 
    
   String url = statics.BASE_URL+"/reponseupdate?idR="+Reponse.getIdR()+"&textR1="+Reponse.getTextR1()+"&textR2="+Reponse.getTextR2()+"&textR3="+Reponse.getTextR3()+"&textR4="+Reponse.getTextR4()+"&idQ="+ Reponse.getIdQ()+"&id="+Reponse.getId();
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
       
    
    
}}
