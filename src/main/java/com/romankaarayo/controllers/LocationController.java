package com.romankaarayo.controllers;

import com.romankaarayo.db.Location;
import com.romankaarayo.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * @author Chathura Widanage
 */
@Component
@Path("/location")
public class LocationController extends AbstractController {
    @Autowired
    private LocationService locationService;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return this.sendSuccessResponse(this.locationService.getAll());
    }

    @POST
    @Consumes("application/json")
    public Response saveLocation(Location location) {
        this.locationService.saveLocation(location);
        return this.sendSuccessResponse(location);
    }
}
