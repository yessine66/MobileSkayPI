/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.services;

import com.codename1.io.CharArrayReader;
import com.codename1.io.ConnectionRequest;
import com.codename1.io.JSONParser;
import com.codename1.io.NetworkManager;
import com.codename1.ui.ComboBox;
import com.codename1.ui.Dialog;
import com.codename1.ui.TextField;
import com.codename1.ui.util.Resources;
import com.mycompany.entities.Utilisateur;
import com.mycompany.gui.ListUtilisateurForm;
import com.mycompany.gui.MenuBack;
import com.mycompany.gui.Session;
import com.mycompany.utils.statics;
import java.io.IOException;
import java.util.Map;
import java.util.Vector;


/**
 *
 * @author mega-pc
 */
public class ServiceUtilisateurFront {
    
    
        
    //singleton
    
    public static ServiceUtilisateurFront instance = null;
    
    public static boolean resultOK = true ;
    
    
    //initialisation connection request
    
    private ConnectionRequest req;
    
    
    
    public static ServiceUtilisateurFront getInstance(){
        if(instance == null)
            instance = new ServiceUtilisateurFront();
        return instance;
    }
    
    
    
    public ServiceUtilisateurFront(){
        req = new ConnectionRequest();
        
    }
    
    
    //MAKE ACCCOUUUUUUUUUUUUNT 
    
    public void signup(TextField nom,TextField prenom,TextField mail,TextField age,TextField tel,ComboBox<String> genres ,TextField username ,TextField password ,TextField ConfirmPassword,Resources  res){
        
        String x=age.getText();
        String y=tel.getText();
        
        
        
        String url=statics.BASE_URL+"/utilisateursignupJSON?nom="+nom.getText()+"&prenom="+prenom.getText()+"&mail="+mail.getText()+
                "&age="+Integer.parseInt(x)+"&tel="+Integer.parseInt(y)+
                "&genre="+genres.getSelectedItem().toString()+"&dateNaiss=2011-11-11&username="+username.getText()+"&password="+password.getText()+"&role=apprenant";
        
        
        req.setUrl(url);
        
        if(username.getText().equals("")||prenom.getText().equals("")||mail.getText().equals("")||username.getText().equals("")||password.getText().equals("")||ConfirmPassword.getText().equals("")){
            Dialog.show("Attention", "3abbi ya walid ","OK",null);
        }
        
        req.addResponseListener(a -> {
            byte[]data=(byte[]) a.getMetaData();
            String responseData=new String(data);
            System.out.println("\nDate : "+responseData);
        });
        
        
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
        
    }
    
    
    public void signin(TextField username , TextField password,Resources res){
        
    
        
        String url=statics.BASE_URL+"/utilisateursigninJSON?username="+username.getText()+"&password="+password.getText();
        
        req = new ConnectionRequest(url, false);
        
        req.setUrl(url);
        
        req.addResponseListener((e)-> {
            
            JSONParser j = new JSONParser();
            String json = new String(req.getResponseData())+"";
            
            try{
           
            
            if(json.equals("Username incorrect")||json.equals("Password incorrect")){
                Dialog.show("Echec", "username ou mot de passe incorrect   verifier votre cordonnées ","OK",null);
             
            }
            else{
                System.out.println("Data === "+json);
                
                Map<String,Object> utilisateurl = j.parseJSON(new CharArrayReader(json.toCharArray()));
                
                
                //SESSIONNNNNNNNNNNNN
                
                float id = Float.parseFloat(utilisateurl.get("id").toString());
                Session.setId((int)id);//jibt id ta3 user ly3ml login w sajltha fi session ta3i
                
                Session.setPassowrd(utilisateurl.get("password").toString());
                Session.setUserName(utilisateurl.get("username").toString());
                Session.setMail(utilisateurl.get("mail").toString());
                Session.setRole(utilisateurl.get("role").toString());
                

                System.out.println("Utilisateur connectééééé  : "+Session.getId()+"  "+Session.getUserName()+"  "+Session.getMail()+"  "+Session.getPassowrd()+"  "+Session.getRole());
                
                if(utilisateurl.size()>0){
                    //new ListUtilisateurForm(res).show();
                    new MenuBack(res,res.getImage("menux.png")).show();
                }
            }
            
            }catch(Exception ex){
                ex.printStackTrace();
            }
            
        });
        
        NetworkManager.getInstance().addToQueueAndWait(req);
        
        
    }
    
    
}
