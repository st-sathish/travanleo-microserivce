package com.travanleo.user.api;

import com.travanleo.core.data.CommandProcessingResult;
import com.travanleo.user.api.commands.domain.CommandWrapper;
import com.travanleo.user.api.commands.service.CommandSourceWritePlatformService;
import com.travanleo.user.api.commands.service.CommandWrapperBuilder;
import com.travanleo.user.data.UserData;
import com.travanleo.user.service.UserReadPlatformService;
import com.travanleo.user.service.UserWritePlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
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

    private final UserReadPlatformService userReadPlatformService;
    private final CommandSourceWritePlatformService commandSourceWritePlatformService;

    @Autowired
    public UserApiResource(final UserReadPlatformService userReadPlatformService,
                           final CommandSourceWritePlatformService commandSourceWritePlatformService) {
        this.userReadPlatformService = userReadPlatformService;
        this.commandSourceWritePlatformService = commandSourceWritePlatformService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUsers() {
        UserData userData = new UserData();
        userData.setName("Hello Sathish");
        return Response.ok().entity(userData).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createUser(final String apiRequestBodyAsJson) {
        final CommandWrapper commandWrapper = new CommandWrapperBuilder()
                .createUser()
                .withJson(apiRequestBodyAsJson)
                .build();
        CommandProcessingResult result = commandSourceWritePlatformService.logCommandSource(commandWrapper);
        return Response.ok(result).build();
    }

    @PUT
    @Path("{userId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateUser(@PathParam("userId") final Long userId, final String apiRequestBodyAsJson) {
        String greeting = "Hello ";
        final CommandWrapper commandWrapper = new CommandWrapperBuilder()
                .updateUser(userId)
                .withJson(apiRequestBodyAsJson)
                .build();
        return Response.ok(greeting).build();
    }

    @DELETE
    @Path("{userId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response removeUser(@PathParam("userId") final Long userId) {
        String greeting = "Hello ";
        final CommandWrapper commandWrapper = new CommandWrapperBuilder()
                .deleteUser(userId)
                .build();
        return Response.ok(greeting).build();
    }
}
