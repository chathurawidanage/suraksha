package com.romankaarayo.controllers;

import com.romankaarayo.db.Person;
import com.romankaarayo.services.PersonService;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Component;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.io.InputStream;

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
        return this.sendSuccessResponse(this.personService.all());
    }

    @POST
    @Produces("application/json")
    @Consumes("application/json")
    public Response save(Person person) {
        Person savedPerson = this.personService.save(person);
        return this.sendSuccessResponse(savedPerson);
    }

    @POST
    @Path("image")
    @Consumes("multipart/form-data")
    @Produces("application/json")
    public Response saveByImage(FormDataMultiPart form) {
        try {
            Person person = this.personService.createPersonByImage(form.getField("file").getValueAs(InputStream.class));
            return this.sendSuccessResponse(person);
        } catch (IOException e) {
            return this.sendCustomResponse(500, "Image not uploaded");
        }
    }
}
