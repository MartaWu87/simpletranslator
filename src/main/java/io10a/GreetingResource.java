package io10a;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Set;

@RequestScoped
@Path("/hello")
public class GreetingResource {
    private Logger logger = LoggerFactory.getLogger(GreetingService.class.getName());
    @Inject
    private GreetingService greetingService;

    @Inject
    Validator validator;

    @GET
    @Path("/{name}")
    @Produces(MediaType.TEXT_PLAIN)
    public String sayHello(@PathParam("name") String name) {
        return greetingService.sayHello(name);
    }

    @PUT
    @Produces(MediaType.APPLICATION_JSON)
    public Response newHello(Language language) {
        final Set<ConstraintViolation<Language>> violations = validator.validate(language);
        if(violations.size() > 0) {
            return Response.status(400).build();
        }
        greetingService.newHello(language);
        logger.info("new code succesfully added  : " + language.getName()); //uwaga !!!!!
        return Response.ok(language).build();
    }

    @POST
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response editHello(@PathParam("name") String name, Language language) {
        final Set<ConstraintViolation<Language>> violations = validator.validate(language);
        if(violations.size() >0) {
            return Response.status(400).build();
        }
        greetingService.editHello(name, language);
        return Response.ok(language).build();
    }

    @GET
    @Path("/all")
    @Produces(MediaType.TEXT_PLAIN)
    public String showAllHello() {
        return greetingService.showAllHello();
    }

    @DELETE
    @Path("/{name}")
    public void deleteHello(@PathParam("name") String id) {
        greetingService.deleteHello("name");
    }
}
