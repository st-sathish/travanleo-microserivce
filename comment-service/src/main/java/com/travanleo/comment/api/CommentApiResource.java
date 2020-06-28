package com.travanleo.comment.api;

import com.travanleo.comment.commands.data.CommandWrapper;
import com.travanleo.comment.commands.service.CommandSourceWritePlatformService;
import com.travanleo.comment.commands.service.CommandWrapperBuilder;
import com.travanleo.comment.data.CommandProcessingResult;
import com.travanleo.comment.service.CommentReadPlatformService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
@Scope("singleton")
@Component
public class CommentApiResource {

    private CommentReadPlatformService commentReadPlatformService;
    private CommandSourceWritePlatformService commandSourceWritePlatformService;

    @Autowired
    public CommentApiResource(final CommentReadPlatformService commentReadPlatformService,
                              final CommandSourceWritePlatformService commandSourceWritePlatformService) {
        this.commentReadPlatformService = commentReadPlatformService;
        this.commandSourceWritePlatformService = commandSourceWritePlatformService;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response createComment(final String apiRequestBodyAsJson) {
        final CommandWrapper commandWrapper = new CommandWrapperBuilder()
                .createComment()
                .withJson(apiRequestBodyAsJson)
                .build();
        CommandProcessingResult result = commandSourceWritePlatformService.logCommandSource(commandWrapper);
        return Response.ok(result).build();
    }
}
