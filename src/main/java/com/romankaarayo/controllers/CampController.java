package com.romankaarayo.controllers;

import com.romankaarayo.db.Camp;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

/**
 * @author Chathura Widanage
 */
@Component
@Path("/camp")
public class CampController {
    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public void save(Camp camp){

    }
}
