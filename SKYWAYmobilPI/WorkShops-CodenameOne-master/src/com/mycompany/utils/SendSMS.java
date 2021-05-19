/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.utils;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

/**
 *
 * @author mega-pc
 */
public class SendSMS {
    
    
    public static final String ACCOUNT_SID = "AC8fa3666f9dd4ed903b184cb4b239346d";
    public static final String AUTH_TOKEN = "5b130ecd79d7d21f0e965cade0b2c610";
   
    public static void send(String messagecontent){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21652635795"),
                new com.twilio.type.PhoneNumber("+14133442783"),messagecontent).create();
        System.out.println(message.getSid());
    }
    
    public static void sendrandom(String messagecontent){
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("+21652635795"),
                new com.twilio.type.PhoneNumber("+14133442783"),"\n"+messagecontent).create();
        System.out.println(message.getSid());
    }
    
}
