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

import com.mycompany.entities.Promotion;
import com.mycompany.utils.statics;
//import java.io.CharArrayReader;
import java.io.IOException;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Map;
//import jdk.nashorn.internal.parser.JSONParser;
//import java.util.ArrayList;
/**
 *
 * @author Lenovo
 */
public class ServicePromotion {
    //njibou crud json m syfony 
    //singleton
    public static ServicePromotion instance = null;
    //initilalisation connection request 
    public static boolean resultOK = true;
    private ConnectionRequest req; 
   
    
    
    
    public static ServicePromotion getInstance ()
    {
        
        if (instance== null)
            instance = new ServicePromotion();
        return instance;
        
        
        
    }
       
    
    public ServicePromotion()
    {
        
        req= new ConnectionRequest();
        
        
    }
    
    
   public void ajouterPromotion (Promotion promotion)
   {
       
      String url = statics.BASE_URL+"/promotionadd?codeP="+promotion.getCodeP()+"&reduction="+ promotion.getReduction()+"&dated="+promotion.getDated()+"&datef="+promotion.getDatef()+"&idP="+promotion.getIdP();
      req.setUrl(url);
      req.addResponseListener((e)->{
      
      
      
      String str = new String (req.getResponseData());///reponse json
          System.out.println("data="+str);
      
      
      
      
      
      
      });
      
      
       NetworkManager.getInstance().addToQueueAndWait(req);
//execution tar equest       
   }
    
    
   //AFFICHAGE 
    
   public ArrayList <Promotion> afficherPromotion ()
           {
               
               
    ArrayList <Promotion> result = new ArrayList<>();
    
    String url =statics.BASE_URL+"/promotiondisplayi";
    req.setUrl(url);
    
      req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            System.out.println("AAA");
            JSONParser jsonp;
   System.out.println("BBB");
  jsonp= new JSONParser();
            try {
                   System.out.println("CCC");
                
                Map<String,Object>mapPromotion = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
          System.out.println("dd");
                List <Map<String,Object>>listOfMaps = (List <Map<String,Object>>) mapPromotion.get("root");
                System.out.println("fff");
              
                for (Map<String,Object> obj : listOfMaps)
                {   System.out.println("tes1");
                 
                   Promotion promo = new Promotion();
                    float idProm = Float.parseFloat(obj.get("idProm").toString());
                  String codeP = obj.get("codeP").toString();

                     float reduction= Float.parseFloat(obj.get("reduction").toString());
                    String dated = obj.get("dated").toString();
                    String datef = obj.get("datef").toString();
                    System.out.println("tes2");
//ATT  
String A = obj.get("idP").toString();
                    System.out.println("kk"+A);
String strIdStudent = A.substring(6, A.length() - 1);
                      System.out.println("kk2"+strIdStudent);             
                                   
                                      System.out.println("tes3");
                        promo.setIdProm((int) idProm);
                 promo.setCodeP(codeP);
                        promo.setReduction((int) reduction);
                        promo.setDated(dated);
                        promo.setDatef(datef);
       
                       System.out.println("tes4");
//ATT                       
//float x = Float.parseFloat(strIdStudent);
 
//promo.setIdP((int)x);
promo.setNom(strIdStudent);
System.out.println("kk"+promo.getNom());
                          System.out.println("tes5");

                   result.add(promo);

                 
                }
                    
               
            }
            
           catch (Exception ex){
               
               ex.printStackTrace();
               System.out.println("ERREUR AFFICHAGE PARTENAAIRE ");
           }
            
            
            
            
            
            
            
            
            
        }
    });
    
    
    
    
    
    
    
    
    
    
      //  return null;
    
       NetworkManager.getInstance().addToQueueAndWait(req);
       return result;
               
           }
    

   //hkimi fct
   
   
   

    
  /*  public ArrayList<Promotion> convertPromotion(String json) {

     ArrayList <Promotion> result = new ArrayList<>();

        try {
            System.out.println(json);
            JSONParser j = new JSONParser();

            Map<String, Object> Promotion = j.parseJSON(new CharArrayReader(json.toCharArray()));
            System.out.println(Promotion);
           
            List<Map<String, Object>> list = (List<Map<String, Object>>) Promotion.get("root");

            for (Map<String, Object> obj : list) {
                Promotion promo = new Promotion();

                // System.out.println(obj.get("id"));
               /* float id = Float.parseFloat(obj.get("id").toString());
                System.out.println(id);
                e.setId((int) id);
                e.setRating(Float.parseFloat(obj.get("rating").toString()));
                e.setReduction(Float.parseFloat(obj.get("reduction").toString()));
                //e.setId(Integer.parseInt(obj.get("id").toString().trim()));
                e.setName(obj.get("name").toString());
                e.setDescription(obj.get("description").toString());
                e.setPrice(Float.parseFloat(obj.get("price").toString()));
                e.setImage_name(obj.get("imageName").toString());
                System.out.println(e);
                listProduits.add(e);*/
             /*   float idProm = Float.parseFloat(obj.get("idProm").toString());
                    String codeP = obj.get("codeP").toString();
                     float reduction= Float.parseFloat(obj.get("reduction").toString());
                         String dated = obj.get("dated").toString();
                            String datef = obj.get("datef").toString();
                                     float idP = Float.parseFloat(obj.get("idP").toString());
                            System.out.println("ggggggg");     
                            
                        promo.setIdProm((int) idProm);
                        promo.setCodeP(codeP);
                        promo.setReduction((int) reduction);
                        promo.setDated(dated);
                        promo.setDatef(datef);
                         promo.setIdP((int) idP);
                         
                         
                             System.out.println("hhhhhhh"); 
                         
                         
                         
                         
                         
                         
                  //inset data in array list result 
                   result.add(promo);
                             System.out.println("qqqqqq");

            }

        } catch (IOException ex) {
        }
        
        return result;

    }
    */
 /*   ArrayList<Promotion> Promotions = new ArrayList<>();
    public ArrayList<Promotion> afficherPromotion(){
        ConnectionRequest con = new ConnectionRequest();
        // String url =statics.BASE_URL+"/promotionlis";
        con.setUrl("http://127.0.0.1:8000/promotionlis");  
        con.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                Promotions = convertPromotion(new String(con.getResponseData()));
            }
        });
        NetworkManager.getInstance().addToQueueAndWait(con);
        return Promotions;
    }
   
   
   
   */
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   
   //Detail Promo
   public Promotion detailPromotion (int idProm, Promotion promotion)
   {
       String url =statics.BASE_URL+"det?"+idProm;
       req.setUrl(url);
         String str= new String (req.getResponseData());
       
       req.addResponseListener((ev)->{ 
       
       
       JSONParser jsonp= new JSONParser();
       try{
           Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String (str).toCharArray())); 
           
      promotion.setIdProm(Integer.parseInt(obj.get("IdProm").toString()));
      promotion.setCodeP(obj.get("codeP").toString());
          promotion.setReduction(Integer.parseInt(obj.get("reduction").toString()));
           promotion.setDated(obj.get("dated").toString());
            promotion.setDatef(obj.get("datef").toString());
             promotion.setIdP(Integer.parseInt(obj.get("IdP").toString()));
       }catch (IOException  ex )
       {
           
           System.out.println("erreur related to sql\n"+ex.getMessage());
          
       }
           System.out.println("data =>"+str);
       
       
       
       });
         NetworkManager.getInstance().addToQueueAndWait(req);
       return promotion;
               
       
   }
   
   
   
   
   //delete 
   
   public boolean supprimerPromotion (int idProm)
   {
       //http://127.0.0.1:8000/promotiondel?idProm=176
       
       String url = statics.BASE_URL+"/promotiondel?idProm="+idProm;   
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
   
   public boolean modifierPromotion (Promotion promotion)
   { 
    ///promotionup?idProm=235&codeP=abbbb&reduction=545&dated=7/7/8&datef=1/1/1&idP=256&id=156   
   String url = statics.BASE_URL+"/promotionup?idProm="+promotion.getIdProm()+"&codeP="+promotion.getCodeP()+"&reduction="+ promotion.getReduction()+"&dated="+promotion.getDated()+"&datef="+promotion.getDatef()+"&idP="+promotion.getIdP();     
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
 /*  public ArrayList<Promotion> getProductsBySubcat(int idP,Promotion promotion){
       
     String url = statics.BASE_URL+"/promotiondetp?idP="+idP;
       req.setUrl(url);
         String str= new String (req.getResponseData());
       
       req.addResponseListener((ev)->{ 
       
       
       JSONParser jsonp= new JSONParser();
       try{
           Map<String,Object>obj = jsonp.parseJSON(new CharArrayReader(new String (str).toCharArray())); 
           
      promotion.setIdProm(Integer.parseInt(obj.get("IdProm").toString()));
      promotion.setCodeP(obj.get("codeP").toString());
          promotion.setReduction(Integer.parseInt(obj.get("reduction").toString()));
           promotion.setDated(obj.get("dated").toString());
            promotion.setDatef(obj.get("datef").toString());
             promotion.setIdP(Integer.parseInt(obj.get("IdP").toString()));
       }catch (IOException  ex )
       {
           
           System.out.println("erreur related to sql\n"+ex.getMessage());
          
       }
           System.out.println("data =>"+str);
       
       
       
       });
         NetworkManager.getInstance().addToQueueAndWait(req);
       return promotion;
               
   
   
   
    
}*/
   
   
 












}