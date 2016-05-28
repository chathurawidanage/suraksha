package com.romankaarayo.controllers;

import com.romankaarayo.db.Camp;
import com.romankaarayo.db.Requirement;
import com.romankaarayo.services.CampService;
import com.romankaarayo.services.RequirementService;
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

    @Autowired
    private RequirementService requirementService;

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

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    @Path("{id}/requirement")
    public Response addRequirement(@PathParam("id") Long campId, Requirement requirement) {
        Camp camp = this.campService.getById(campId);
        if (camp == null) {
            return this.sendCustomResponse(404, "Camp not found");
        }
        requirement.setCamp(camp);
        Requirement savedRequirement = this.requirementService.save(requirement);
        return this.sendSuccessResponse(savedRequirement);
    }

    @GET
    @Path("{id}/requirement")
    @Consumes("application/json")
    @Produces("application/json")
    public Response getRequirements(@PathParam("id") Long campId) {
        Camp camp = this.campService.getById(campId);
        if (camp == null) {
            return this.sendCustomResponse(404, "Camp not found");
        }
        return this.sendSuccessResponse(this.requirementService.getByCamp(camp));
    }
}
