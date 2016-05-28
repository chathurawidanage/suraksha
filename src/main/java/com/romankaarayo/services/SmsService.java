package com.romankaarayo.services;

import com.romankaarayo.util.DialogConstants;
import hms.kite.samples.api.sms.messages.MtSmsReq;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * @author Chathura Widanage
 */
public class SmsService {
    public void sendSms(MtSmsReq mtSmsReq){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(DialogConstants.SMS_ENDPOINT);
        Response response=target.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.json(mtSmsReq));
    }
}
