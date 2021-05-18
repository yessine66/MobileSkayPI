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
import com.mycompany.entities.Utilisateur;
import com.mycompany.utils.statics;
import java.io.IOException;
import java.text.DateFormatPatterns;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author mega-pc
 */
public class ServiceUtilisateur {
    
    
    //singleton
    
    public static ServiceUtilisateur instance = null;
    
    public static boolean resultOK = true ;
    
    
    //initialisation connection request
    
    private ConnectionRequest req;
    
    
    
    public static ServiceUtilisateur getInstance(){
        if(instance == null)
            instance = new ServiceUtilisateur();
        return instance;
    }
    
    
    
    public ServiceUtilisateur(){
        req = new ConnectionRequest();
        
    }
    
    
    
    //ajout
    public void ajouterUtilisateur(Utilisateur u){
        
        
        System.out.println("\n\n\n date naiss affichage fel ajouuutotototo \n\n"+u.getDateNaiss()+"\n\n\n\n");
        
    String url=statics.BASE_URL+"/utilisateuraddUtilisateurJson?nom="+u.getNom()+"&prenom="+u.getPrenom()+"&mail="+u.getMail()+"&age="+u.getAge()+"&tel="+u.getTel()+"&genre="+u.getGenre()+"&dateNaiss="+u.getDateNaiss()+"&username="+u.getUsername()+"&password="+u.getPassword()+"&role="+u.getRole();
        
       
//     String url=statics.BASE_URL+"/utilisateuraddUtilisateurJson?nom="+u.getNom()+"&prenom="+u.getPrenom()+"&mail="+u.getMail()+"&age="+u.getAge()+
//             "&tel="+u.getTel()+"&genre="+u.getGenre()+/*"&dateNaiss="+u.getDateNaiss()+*/"&username="+u.getUsername()+"&password="+u.getPassword()+"&role="+u.getRole();
        
        req.setUrl(url);
        req.addResponseListener((e) ->{ 
            
            String str = new String(req.getResponseData());
            System.out.println("date == "+str);
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
    }
    
    
    //affichage 
    
    public ArrayList<Utilisateur> affichageUtilisateur(){
        
        ArrayList<Utilisateur> lista= new ArrayList<>();
        String url= statics.BASE_URL+"/utilisateurdisplayUtilisateurJson";
        
        req.setUrl(url);
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
           
                JSONParser jsonp;
                jsonp = new JSONParser();
                
                try {
                    Map<String,Object>mapUtilisateur = jsonp.parseJSON(new CharArrayReader(new String(req.getResponseData()).toCharArray()));
   
                    
                    List<Map<String,Object>> listOfMaps = (List<Map<String,Object>>) mapUtilisateur.get("root");
                    
                    for (Map<String,Object> obj : listOfMaps){
                      
                    Utilisateur u = new Utilisateur();
                    
                    float idu= Float.parseFloat(obj.get("id").toString());
                        
                    String nomu=obj.get("nom").toString();
                    String prenomu=obj.get("prenom").toString();
                    String mailu=obj.get("mail").toString();
                    float ageu=Float.parseFloat(obj.get("age").toString());
                    float telu=Float.parseFloat(obj.get("tel").toString());
                    String genreu=obj.get("genre").toString();
                    //String dateNaissu = obj.get("dateNaiss").toString();
                    String usernameu=obj.get("username").toString();
                    String passwordu=obj.get("password").toString();
                    String roleu = obj.get("role").toString();
                    //String creCompteu = obj.get("creCompte").toString();
                
                    
                    
                    
                    
//                    SimpleDateFormat format = new 
//                        SimpleDateFormat(DateFormatPatterns.ISO8601);
//                        Date datedeb = format.parse(dateNaissu);     
//                        Date datefin = format.parse(creCompteu);  
//                        
                     //   u.setDateNaiss(dateNaissu);
                      //  u.setCreCompte(creCompteu);
                        
                      
                    
                    u.setId((int)idu);
                    u.setNom(nomu);
                    u.setPrenom(prenomu);
                    u.setMail(mailu);
                    u.setAge((int)ageu);
                    u.setTel((int)telu);
                    u.setGenre(genreu);
                    //u.setDateNaiss(dateNaissu);
                    u.setDateNaiss("1998-11-11");
                    u.setUsername(usernameu);
                    u.setPassword(passwordu);
                    u.setRole(roleu);
                    //u.setCreCompte(creCompteu);
                    u.setCreCompte("2011-11-11");
                    //u.setDateNaiss("2011-11-11");
                    
                      
                     // System.out.println("\n\n\n\n***********************************************************************************************************************************************************\n test recuperation date naiss : "+"fsfe"+"\nend test****************************\n\n\n\n\n ");
                    
            /*        
                    Date datenassou = (Date) obj.get("dateNaiss");
                    String dateStr =  obj.get("dateNaiss").toString();
SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
Date birthDate = sdf.parse(dateStr);

                        System.out.println("\n\n\n\n***********************************************************************************************************************************************************\n test recuperation date naiss : "+birthDate+"\nend test****************************\n\n\n\n\n ");
                    
                    
                   /* String DateConverter2 = obj.get("dateNaiss").toString().substring(obj.get("dateNaiss").toString().indexOf("date") + 10 , obj.get("dateNaiss").toString().lastIndexOf("}"));
                    
                    Date dateNaissu = new Date(Double.valueOf(DateConverter2).longValue()*1000);
                    
                    
                    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-mm-dd");
                    String dateSringdateNaiss = formatter2.format(dateNaissu);
                    u.setDateNaiss(dateSringdateNaiss);
                    
                    
                    
                    */
               /*  String DateConverter = obj.get("creCompte").toString().substring(obj.get("creCompte").toString().indexOf("timeStamp") + 10 , obj.get("creCompte").toString().lastIndexOf("}"));
                    
                    Date currentTime = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                    
                    
                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                    String dateSringCreCompte = formatter.format(currentTime);
                    u.setCreCompte(dateSringCreCompte);
                    
                    //
                    
                   String DateConverter2 = obj.get("dateNaiss").toString().substring(obj.get("dateNaiss").toString().indexOf("timeStamp") + 10 , obj.get("dateNaiss").toString().lastIndexOf("}"));
                    
                    Date currentTime2 = new Date(Double.valueOf(DateConverter).longValue() * 1000);
                    
                    
                    SimpleDateFormat formatter2 = new SimpleDateFormat("yyyy-MM-dd");
                    String dateSringCreCompte2 = formatter.format(currentTime2);
                    u.setDateNaiss(dateSringCreCompte2);
                    
                    */
                    
                    //System.out.println("\n\n\n\n datawaza : "+ u.getId()+u.getNom()+u.getNom()+u.getPrenom()+u.getMail()+u.getTel()+u.getAge()+u.getGenre()+u.getDateNaiss()+u.getUsername()+u.getPassword()+u.getRole()+u.getRole()+u.getCreCompte() );
                    lista.add(u);
                    
                    
                    
                    
                    
                }
                    
                } catch (Exception e) {
                     System.out.println("\nErreur affichage Utilisateur\n");
                    e.printStackTrace();
                   
                }
                
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return lista;
        
    }
    
    //affichare parametreee
    
    public Utilisateur DetailUtilisateur(int id , Utilisateur u){
        
        String url=statics.BASE_URL+"utilisateurdetailUtilisateurJson?id="+id;
        
        
        req.setUrl(url);
        
        String str = new String(req.getResponseData());
        
        req.addResponseListener((evt) -> {
 
            JSONParser jsonp = new JSONParser();
            
            try {
                 Map<String,Object>obj  = jsonp.parseJSON(new CharArrayReader(new String(str).toCharArray()));
                
                u.setNom(obj.get("nom").toString());
                u.setPrenom(obj.get("prenom").toString());
                u.setMail(obj.get("mail").toString());
                u.setAge(Integer.parseInt(obj.get("age").toString()));
                u.setTel(Integer.parseInt(obj.get("tel").toString()));
                u.setGenre(obj.get("genre").toString());
                u.setDateNaiss(obj.get("dateNaiss").toString());
                u.setUsername(obj.get("username").toString());
                u.setPassword(obj.get("password").toString());
                u.setRole(obj.get("role").toString());
                u.setCreCompte(obj.get("creCompte").toString());
               
                
                
                 
            } catch (IOException e) {
                 System.out.println("\nErreur affichage parametreeeeeeeee Utilisateur\n"+e.getMessage());
                   
            }
            
            System.out.println("data ====="+str);
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        return u;
        
    }
    
    
    
    //DELEEEEEEEEEETE
    
    public boolean deleteUtilisateur(int id){
        String url =statics.BASE_URL+"/utilisateurdeleteUtilisateurJson?id="+id;
        
        
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
    
    
    
    //UPDATE xxxxxxxxxxxxxxxxxxxxxxxx
    
    public boolean modifierUtilisateur(Utilisateur us){
        
        
         System.out.println("\nn*****************felserviceID : "+us.getId());
            System.out.println("\nnnom baad mofication : "+us.getNom());
            System.out.println("\nnPrenom baad mofication : "+us.getPrenom());
            System.out.println("\nmail baad mofication : "+us.getMail());
            System.out.println("\nnage baad mofication : "+us.getAge());
            System.out.println("\nntel baad mofication : "+us.getTel());
            System.out.println("\ngenre baad mofication : "+us.getGenre());
            System.out.println("\nusername baad mofication : "+us.getUsername());
            System.out.println("\npassword baad mofication : "+us.getPassword());
            System.out.println("\nroke  baad mofication : "+us.getRole());
        
         
        String url = statics.BASE_URL+"/utilisateurupdateUtilisateurJson?id="+us.getId()+"&nom="+us.getNom()+"&prenom="+us.getPrenom()+"&mail="+us.getMail()+"&age="+us.getAge()+"&tel="+us.getTel()+"&genre="+us.getGenre()+"&username="+us.getUsername()+"&password="+us.getPassword();
        
                                                                                            
        req.setUrl(url);
        
        
        System.out.println("\n\n\n\n fonction update li f servicessss");
        
        
        
        
        req.addResponseListener(new ActionListener<NetworkEvent>() {
            @Override
            public void actionPerformed(NetworkEvent evt) {
                
                
                System.out.println("\n\n\n\n fonction update li f servicessss ama d5alna f ba3then url");
           
                resultOK = req.getResponseCode()==200;
                req.removeResponseListener(this);
                
            }
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        return resultOK;
        
    }
    
    
    
    
    
    
    
}
