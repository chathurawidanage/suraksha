package com.romankaarayo.controllers;

import com.romankaarayo.db.Person;
import com.romankaarayo.services.PersonService;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.FormDataParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.UUID;

/**
 * @author Chathura Widanage
 */
@Component
@Path("/person")
public class PersonController extends AbstractController {
    @Autowired
    private PersonService personService;

    @GET
    @Produces("application/json")
    public Response getAll() {
        return this.sendCustomSuccessResponse("test");
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response save(Person person) {
        Person savedPerson = this.personService.save(person);
        return this.sendSuccessResponse(savedPerson);
    }

    @POST
    @Path("/image")
    @Consumes("multipart/form-data")
    public Response saveByImage(HttpRequest request, FormDataMultiPart form) {
        try {
            Person person = this.personService.createPersonByImage(form.getField("file").getValueAs(InputStream.class));
            return this.sendSuccessResponse(person);
        } catch (IOException e) {
            return this.sendCustomResponse(500, "Image not uploaded");
        }
    }
}
