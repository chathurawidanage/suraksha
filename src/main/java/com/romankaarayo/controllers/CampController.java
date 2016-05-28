package com.romankaarayo.controllers;

import com.romankaarayo.db.Camp;
import com.romankaarayo.services.CampService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * @author Chathura Widanage
 */
@Component
@Path("/camp")
public class CampController extends AbstractController {
    @Autowired
    private CampService campService;

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response save(Camp camp) {
        Camp savedCamp = this.campService.save(camp);
        return this.sendSuccessResponse(savedCamp);
    }

    @GET
    @Produces("application/json")
    public Response all() {
        return this.sendSuccessResponse(this.campService.all());
    }
}
