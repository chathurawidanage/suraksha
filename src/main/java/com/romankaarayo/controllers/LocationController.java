package com.romankaarayo.controllers;

import com.romankaarayo.db.Location;
import com.romankaarayo.services.LocationService;
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
@Path("/location")
public class LocationController extends AbstractController {
    private final Logger logger = LogManager.getLogger(LocationController.class);

    @Autowired
    private LocationService locationService;

    @GET
    @Produces("application/json")
    public Response getAll() {
        logger.info("Inside the controller");
        return this.sendSuccessResponse(this.locationService.getAll());
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response saveLocation(Location location) {
        return this.sendSuccessResponse(this.locationService.saveLocation(location));
    }
}
