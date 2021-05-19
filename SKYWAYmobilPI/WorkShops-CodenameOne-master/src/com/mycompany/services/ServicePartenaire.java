/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;
import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.MultipartRequest;
import com.codename1.io.NetworkEvent;
import com.codename1.io.NetworkManager;

import com.codename1.ui.events.ActionListener;
import com.codename1.util.Callback;
import com.mycompany.entities.Partenaire;
import static com.mycompany.services.ServicePartenaire.resultOK;
import com.mycompany.utils.statics;
//import java.io.CharArrayReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
//import java.util.ArrayList;
import java.util.List;
import java.util.Map;



/**
 *
 * @author Lenovo
 */
public class ServicePartenaire {
    
    
    
    
    
    //njibou crud json m syfony 
    //singleton
    public static ServicePartenaire instance = null;
    //initilalisation connection request 
    public static boolean resultOK = true;
    private ConnectionRequest req; 
   
    
    
    
    public static ServicePartenaire getInstance ()
    {
        
        if (instance== null)
            instance = new ServicePartenaire();
        return instance;
        
        
        
    }
       
    
    public ServicePartenaire()
    {
        
        req= new ConnectionRequest();
        
        
    }
    
      public void pictureUpload(final Callback<String> resultURL, String photo, String name) {
        
        if (photo != null) {
            
            MultipartRequest request = new MultipartRequest() {
                protected void readResponse(InputStream input) throws IOException {
                    JSONParser jp = new JSONParser(); 
                    Map<String, Object> result = jp.parseJSON(new InputStreamReader(input, "UTF-8"));
                    String url = (String) result.get("url");
                    if (url == null) {
                        resultURL.onError(null, null, 1, result.toString());
                        return;
                    }
                    resultURL.onSucess(url);
                }
            };
            request.setUrl("http://127.0.0.1:8000/partenaireadd");
            try {
                request.addData("file", photo, "image/jpeg");
                request.setFilename("file", name);
                NetworkManager.getInstance().addToQueue(request);
            } catch (IOException err) {
                err.printStackTrace();
            }
        }
    }
   public void ajouterPartenaire (Partenaire partenaire, String f)
   {

       
      //http://127.0.0.1:8000/partenaireadd?nomP=test1&domaine=alimentation&dateP=1/1/1&mailp=test1@gmail&logo=graduated.png 
      String url = statics.BASE_URL+"/partenaireadd?nomP="+partenaire.getNomP()+"&domaine="+ partenaire.getDomaine()+"&dateP="+partenaire.getDateP()+"&mailp="+partenaire.getMailp()+"&logop="+partenaire.getLogop();
      req.setUrl(url);
      req.addResponseListener((e)->{
  String str = new String (req.getResponseData());///reponse json
          System.out.println("data="+str);
   
      
      });
      
      
       NetworkManager.getInstance().addToQueueAndWait(req);
     
   }
    
   //AFFICHAGE 
    
 public ArrayList <Partenaire> afficherPartenaire ()
           {
               
               
    ArrayList <Partenaire> result = new ArrayList<>();
    
    String url =statics.BASE_URL+"/partenairelis";
    req.setUrl(url);
    
      req.addResponseListener(new ActionListener<NetworkEvent>() {
        @Override
        public void actionPerformed(NetworkEvent evt) {
            System.out.println("AAA");
            JSONParser jsonp;
   System.out.println("BBB");
  jsonp= new JSONParser();
            try {
                 
                Map<String,Object>mapPromotion = jsonp.parseJSON(new CharArrayReader(new String (req.getResponseData()).toCharArray()));
                   System.out.println("ddddd");
                List <Map<String,Object>>listOfMaps = (List <Map<String,Object>>) mapPromotion.get("root");
                     System.out.println("EEEEE");
                for (Map<String,Object> obj : listOfMaps)
                { 
                       System.out.println("FFF");
                    Partenaire pr = new Partenaire();
                   float idP = Float.parseFloat(obj.get("idP").toString());
                    String nomP = obj.get("nomP").toString();
                   //   String codeP = obj.get("codeP").toString();
                         String domaine = obj.get("domaine").toString();
                         String dateP = obj.get("dateP").toString();
                         String mailp = obj.get("mailp").toString();
                           String logop = obj.get("logop").toString();
                              
  

                            
                        pr.setIdP((int) idP);
                        pr.setNomP(nomP);
                        pr.setDomaine(domaine);
                        pr.setDateP(dateP);
                         pr.setMailp(mailp);
                          pr.setLogop(logop);

                         
                  //inset data in array list result 
                   result.add(pr);
                   System.out.println(pr);
                    
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
      
  //delete 
   
   public boolean supprimerPartenaire (int idP)
   {
       //http://127.0.0.1:8000/promotiondel?idProm=176
       
       String url = statics.BASE_URL+"/partenairedel?idP="+idP;   
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
   
   public boolean modifierPartenaire (Partenaire promotion)
   { 
    ///promotionup?idProm=235&codeP=abbbb&reduction=545&dated=7/7/8&datef=1/1/1&idP=256&id=156   
   String url = statics.BASE_URL+"/partenaireup?idP="+promotion.getIdP()+"&nomP="+promotion.getNomP()+"&domaine="+ promotion.getDomaine()+"&dateP="+promotion.getDateP()+"&mailp="+promotion.getMailp()+"&logop="+promotion.getLogop();     
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
