package com.romankaarayo.controllers;

import com.romankaarayo.db.Alert;
import com.romankaarayo.repository.AlertRepository;
import com.romankaarayo.services.SmsService;
import com.romankaarayo.util.DialogConstants;
import hms.kite.samples.api.sms.messages.MoSmsReq;
import hms.kite.samples.api.sms.messages.MtSmsReq;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * @author Chathura Widanage
 */
@Component
@Path("/sms")
public class SmsController extends AbstractController {
    private final Logger logger = LogManager.getLogger(SmsController.class);

    @Autowired
    private AlertRepository alertRepository;

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
        Alert alert = new Alert();
        alert.setAlert(sms.getMessage());
        this.alertRepository.save(alert);
    }

    @GET
    @Produces("application/json")
    public Response getAlert() {
        Alert alert = this.alertRepository.findOneByShowed(false);
        if (alert != null) {
            alert.setShowed(true);
            this.alertRepository.save(alert);
            return this.sendSuccessResponse(alert);
        } else {
            return this.sendCustomResponse(404, "No messages");
        }
    }


}
