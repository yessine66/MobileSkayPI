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
import com.mycompany.gui.ListUtilisateurForm;
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
        
       System.out.println("\n\n\n\n\n\n******************FONNCTION LOGIN f service\n\n\n"+username.getText()+"   passe "+password.getText());
        
        String url=statics.BASE_URL+"/utilisateursigninJSON?username="+username.getText()+"&password="+password.getText();
        
         System.out.println("\n\n\n d5alna lel response listner\n\n"+url);
        
        
        req.setUrl(url);
        
        
        
        req.addResponseListener(e->{ 
            
            
            
            JSONParser j = new JSONParser();
            
            String json = new String(req.getResponseData())+"";
           
            System.out.println("\n\n\n d5alna lel response listner\n\n");
            
            try{
                
            
            
                if(json.equals("Username incorrect")){
                    Dialog.show("Username X", "Verifier votre username","OK",null);
                }else if(json.equals("Password incorrect")){
                    Dialog.show("Password X", "Verifier votre Mot de passe","OK",null);
                }
                else{
                    System.out.println("Date : "+json);
                    
                    Map<String,Object> utilisateurlog = j.parseJSON(new CharArrayReader(json.toCharArray()));
                    
                    if(utilisateurlog.size()>0){
                        System.out.println("\n\n\nbech tod5el***************");
                        new ListUtilisateurForm(res).show();
                    }
                    
                }
            
            } catch (IOException ex) {
                System.out.println("\n\n\nErreuuuur login***********////////*******\n\n");
                ex.printStackTrace();
                
            }
            
            
        });
        
        
    }
    
    
}
