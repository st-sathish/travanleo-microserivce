package com.travanleo.user.api;

import com.travanleo.user.data.UserData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/users")
@Component
@Scope("singleton")
public class UserApiResource {

    @Autowired
    public UserApiResource() {
        System.out.println("Hello instance created");
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        UserData userData = new UserData();
        userData.setName("Hello Sathish");
        return Response.ok().entity(userData).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveUser() {
        String greeting = "Hello ";
        return Response.ok(greeting).build();
    }

    @PUT
    @Path("/:userId")
    public Response updateUser() {
        String greeting = "Hello ";
        return Response.ok(greeting).build();
    }

    @DELETE
    @Path("/:userId")
    public Response removeUser() {
        String greeting = "Hello ";
        return Response.ok(greeting).build();
    }
}
