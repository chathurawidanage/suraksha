package com.romankaarayo.controllers;

import com.romankaarayo.exceptions.ServiceException;
import com.romankaarayo.resources.CustomMessageResource;

import javax.ws.rs.core.Response;

/**
 * @author Chathura Widanage
 */
public abstract class AbstractController {

    public Response sendCustomSuccessResponse(String message) {
        return Response.status(200).entity(new CustomMessageResource(message)).build();
    }

    public Response sendSuccessResponse(Object obj) {
        return Response.status(200).entity(obj).build();
    }

    public Response sendCustomResponse(int responseCode, String message) {
        return Response.status(responseCode).entity(new CustomMessageResource(message)).build();
    }

    public Response handleServiceException(ServiceException se) {
        return Response.status(500).entity(new CustomMessageResource(se.getMessage())).build();
    }

    public Response handleServiceException(int responseCode, ServiceException se) {
        return Response.status(responseCode).entity(new CustomMessageResource(se.getMessage())).build();
    }
}
