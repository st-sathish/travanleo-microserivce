package com.travanleo.comment.api;

import com.travanleo.comment.commands.data.CommandWrapper;
import com.travanleo.comment.commands.service.CommandSourceWritePlatformService;
import com.travanleo.comment.commands.service.CommandWrapperBuilder;
import com.travanleo.comment.data.CommandProcessingResult;
import com.travanleo.comment.data.CommentData;
import com.travanleo.comment.service.CommentReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
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
import java.util.List;

@Path("/comments")
@Scope("singleton")
@Component
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class CommentApiResource {

    private CommentReadPlatformService commentReadPlatformService;
    private CommandSourceWritePlatformService commandSourceWritePlatformService;

    @Autowired
    public CommentApiResource(final CommentReadPlatformService commentReadPlatformService,
                              final CommandSourceWritePlatformService commandSourceWritePlatformService) {
        this.commentReadPlatformService = commentReadPlatformService;
        this.commandSourceWritePlatformService = commandSourceWritePlatformService;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAuthority('USER')")
    public Response getMyComments() {
        List<CommentData> commentDataList = commentReadPlatformService.retrieveMyCommentList();
        return Response.ok().entity(commentDataList).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAuthority('USER')")
    @SuppressWarnings("unchecked")
    public Response createComment(final String apiRequestBodyAsJson) {
        final CommandWrapper commandWrapper = new CommandWrapperBuilder()
                .createComment()
                .withJson(apiRequestBodyAsJson)
                .build();
        CommandProcessingResult result = commandSourceWritePlatformService.logCommandSource(commandWrapper);
        return Response.ok(result).build();
    }

    @PUT
    @Path("{commentId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAuthority('USER')")
    public Response updateComment(@PathParam("commentId") final String commentId, final String apiRequestBodyAsJson) {
        final CommandWrapper commandWrapper = new CommandWrapperBuilder()
                .updateComment(commentId)
                .withJson(apiRequestBodyAsJson)
                .build();
        CommandProcessingResult result = commandSourceWritePlatformService.logCommandSource(commandWrapper);
        return Response.ok(result).build();
    }

    @DELETE
    @Path("{commentId}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @PreAuthorize("hasAuthority('USER')")
    public Response removeComment(@PathParam("commentId") final String commentId) {
        final CommandWrapper commandWrapper = new CommandWrapperBuilder()
                .deleteComment(commentId)
                .build();
        CommandProcessingResult result = commandSourceWritePlatformService.logCommandSource(commandWrapper);
        return Response.ok(result).build();
    }
}
