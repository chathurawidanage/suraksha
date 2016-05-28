package com.romankaarayo.controllers;

import com.romankaarayo.services.SmsService;
import com.romankaarayo.util.DialogConstants;
import hms.kite.samples.api.sms.messages.MoSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * @author Chathura Widanage
 */
@Component
@Path("/sms")
public class SmsController extends AbstractController {
    private final Logger logger = LogManager.getLogger(SmsController.class);

    @Autowired
    private SmsService smsService;

    @POST
    @Consumes("application/json")
    public void receive(MoSmsReq sms) {
        logger.info(sms.getMessage(), sms.getSourceAddress());
        MtSmsReq mtSmsReq = new MtSmsReq();
        mtSmsReq.setApplicationId(sms.getApplicationId());
        mtSmsReq.setPassword(DialogConstants.APP_PASSWORD);
        mtSmsReq.setMessage("Received your message, wait for our team.");
        smsService.sendSms(mtSmsReq);
    }
}
