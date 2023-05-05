package tn.leaguestorm.gui;

///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package tn.leaguestorm.gui;
//
//import com.vonage.client.VonageClient;
//import com.vonage.client.sms.MessageStatus;
//import com.vonage.client.sms.SmsSubmissionResponse;
//import com.vonage.client.sms.messages.TextMessage;
//import java.util.Random;
//
///**
// *
// * @author Bellalouna Iheb
// */
//public class SendMessage {
//
//    public static void main(String[] args) throws Exception {
//        
//        Random rand = new Random();
//        int code = rand.nextInt(999999) + 1; 
//        String formattedCode = String.format("%06d", code);
//        
//        VonageClient client = VonageClient.builder().apiKey("49f6cd4e").apiSecret("42mFN5nf0efKVgl3").build();
//        TextMessage message = new TextMessage("LeagueStorm",
//                "21690228814",
//                "Security code: "+formattedCode
//        );
//
//        SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);
//
//        if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
//            System.out.println("Message sent successfully.");
//        } else {
//            System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
//        }
//    }
//}
// Install the Java helper library from twilio.com/docs/java/install
//
//import com.twilio.Twilio;
//import com.twilio.rest.api.v2010.account.Message;
//import com.twilio.type.PhoneNumber;
//
//public class SendMessage {
//    private static final String ACCOUNT_SID = "";
//    private static final String AUTH_TOKEN = "";
//    private static final String TWILIO_PHONE_NUMBER = "";
//
//    public static void sendSms(String toPhoneNumber, String message) {
//        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
//        Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(TWILIO_PHONE_NUMBER), message).create();
//    }
//    public static void main(String[] args) {
//        sendSms("+216 90228814","test");
//    }
//}