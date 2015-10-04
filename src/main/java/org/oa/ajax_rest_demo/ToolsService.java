package org.oa.ajax_rest_demo;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Pet;
import org.oa.ajax_rest_demo.model.Tools;
import org.oa.ajax_rest_demo.repositories.StorageRepositories;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tools")
public class ToolsService {
	private static final Logger log = Logger.getLogger(ToolsService.class);
    
	@GET
    @Produces("application/json")
    public Response getAll() {
    	StorageRepositories session = StorageRepositories.getInstance();
    	List<Tools> tools = session.getToolsRepository().findAll();
    	log.info("list " + tools);

    	return Response.ok(tools,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Tools tool) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Tools newTool = session.getToolsRepository().create(tool);
    	log.info("add " + newTool);

    	return Response.ok(newTool,
                MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Tools tool) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Tools newTool = session.getToolsRepository().update(tool);
    	log.info("update " + newTool);

    	return Response.ok(newTool,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Tools tool = session.getToolsRepository().findById(id);
    	session.getToolsRepository().delete(tool);
    	log.info("delete " + tool);
    	
        return Response.ok(tool,
                MediaType.APPLICATION_JSON_TYPE).build();
    }
}
