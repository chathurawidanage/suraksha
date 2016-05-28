package com.romankaarayo.controllers;

import org.springframework.stereotype.Component;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

/**
 * @author Chathura Widanage
 */
@Component
@Path("/person")
public class PersonController extends AbstractController{
    @GET
    @Produces("application/json")
    public Response getAll(){
        return this.sendCustomSuccessResponse("test");
    }
}
