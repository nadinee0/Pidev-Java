/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tn.leaguestorm.gui;

/**
 *
 * @author Nadine
 */
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;

public class TwilioSMS {
    public static final String ACCOUNT_SID = "YOUR_ACCOUNT_SID";
    public static final String AUTH_TOKEN = "YOUR_AUTH_TOKEN";
    public static final String TWILIO_PHONE_NUMBER = "YOUR_TWILIO_PHONE_NUMBER";

    public static void main(String[] args) {
        Twilio.init(ACCOUNT_SID, AUTH_TOKEN);

        Message message = Message.creator(
                new PhoneNumber("+1234567890"),  // client's phone number
                new PhoneNumber(TWILIO_PHONE_NUMBER),
                "Update on our products! Check out our latest products at https://example.com/products"
        ).create();

        System.out.println(message.getSid());
    }
}

