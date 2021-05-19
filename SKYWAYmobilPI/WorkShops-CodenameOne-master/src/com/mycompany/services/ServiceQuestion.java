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
import com.mycompany.entities.Question;
import static com.mycompany.services.ServiceUtilisateur.resultOK;
import com.mycompany.utils.statics;
//import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class ServiceQuestion {

    public static ServiceQuestion instance = null;    
    private ConnectionRequest req; 
   
      public static ServiceQuestion getInstance ()
    {
        
        if (instance== null)
            instance = new ServiceQuestion();
        return instance;     
        
    }

    
    public ServiceQuestion()
    {   
        req= new ConnectionRequest();
      
    }
    
       public void ajouterQuestion (Question Question)
   {
       
      String url = statics.BASE_URL+"/question/add?textQ="+Question.getTextQ()+"&nbrPoint="+ Question.getNbrPoint()+"&nameT="+Question.getNameT();
      req.setUrl(url);
      req.addResponseListener((e)->{
      
      
      
      String str = new String (req.getResponseData());///reponse json
          System.out.println("data="+str);
      
      
      
      
      
      
      });
      
      
       NetworkManager.getInstance().addToQueueAndWait(req);
//execution tar equest       
   }
    
     
   public ArrayList <Question> afficherQuestion ()
           {
               
               
    ArrayList <Question> result = new ArrayList<>();
    
    String url =statics.BASE_URL+"/question/display";
    req.setUrl(url);
    
     req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            JSONParser jsonp;

  jsonp= new JSONParser();
            try {
                
                
                Map<String,Object>mapQuestion = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
                List <Map<String,Object>>listOfMaps = (List <Map<String,Object>>) mapQuestion.get("root");
                for (Map<String,Object> obj : listOfMaps)
                {
                    Question promo = new Question();
                    float idQ = Float.parseFloat(obj.get("idQ").toString());
                    String textQ = obj.get("textQ").toString();
                 //    float nbrPoint= Float.parseFloat(obj.get("nbrPoint").toString());
                         String nameT = obj.get("nameT").toString();
                     
                            
                            
                        promo.setIdQ((int) idQ);
                        promo.setTextQ(textQ);
                     //   promo.setNbrPoint((int) nbrPoint);
                        promo.setNameT(nameT);
                       
                         
                         
                         
                         
                         
                         
                         
                         
                  //inset data in array list result 
                   result.add(promo);
                        
                    
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
    
    
   
   
   //Detail Promo
    public Question detailQuestion (int idQ, Question Question)
   {
       String url =statics.BASE_URL+"question/detail?"+idQ;
       req.setUrl(url);
         String str= new String (req.getResponseData());
       
       req.addResponseListener((ev)->{ 
       
       
       JSONParser jsonp= new JSONParser();
       try{
           Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String (str).toCharArray())); 
           
      Question.setIdQ(Integer.parseInt(obj.get("IdQ").toString()));
      Question.setTextQ(obj.get("textQ").toString());
          Question.setNbrPoint(Integer.parseInt(obj.get("nbrPoint").toString()));
           Question.setNameT(obj.get("nameT").toString());
        
       }catch (IOException  ex )
       {
           
           System.out.println("erreur related to sql\n"+ex.getMessage());
          
       }
           System.out.println("data =>"+str);
       
       
       
       });
         NetworkManager.getInstance().addToQueueAndWait(req);
       return Question;
               
       
   }
   
   public boolean modifierQuestion (Question Question)
   { 
    ///Questionup?idProm=235&codeP=abbbb&reduction=545&dated=7/7/8&datef=1/1/1&idP=256&id=156   
   String url = statics.BASE_URL+"/questionupdate?idQ="+Question.getIdQ()+"&textQ="+Question.getTextQ()+"&nameT="+ Question.getNameT()+"&nbrPoint="+2;     
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
   
   
       public boolean supprimerQuestion (int idQ)
   {
       
       
       String url = statics.BASE_URL+"/questiondel?idQ="+idQ;   
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
   
   
    
    
    
}
