package com.romankaarayo.controllers;

import com.romankaarayo.db.Comment;
import com.romankaarayo.db.Person;
import com.romankaarayo.services.CommentService;
import com.romankaarayo.services.PersonService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.springframework.beans.factory.annotation.Autowired;
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
    private final Logger logger = LogManager.getLogger(PersonController.class);

    @Autowired
    private PersonService personService;

    @Autowired
    private CommentService commentService;

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

    @GET
    @Path("{id}")
    @Produces("application/json")
    public Response comments(@PathParam("id") Long id) {
        Person person = this.personService.findById(id);
        if (person == null) {
            return this.sendCustomResponse(404, "Person not found");
        }
        logger.info(person);
        return this.sendSuccessResponse(person);
    }

    /*Comments*/
    @POST
    @Path("{id}/comment")
    @Consumes("application/json")
    @Produces("application/json")
    public Response postComment(@PathParam("id") Long id, Comment comment) {
        Person person = this.personService.findById(id);
        if (person == null) {
            return this.sendCustomResponse(404, "Person not found");
        }
        comment.setPerson(person);
        return this.sendSuccessResponse(this.commentService.save(comment));
    }

    /*Images*/
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