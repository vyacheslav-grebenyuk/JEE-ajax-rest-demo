package org.oa.ajax_rest_demo;

import org.oa.ajax_rest_demo.dao.StorageSession;
import org.oa.ajax_rest_demo.model.Tools;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/tools")
public class ToolsService {

    @GET
    @Produces("application/json")
    public Response getAll() {
        StorageSession session = StorageSession.getInstance();

        List<Tools> tools = session.getToolsDao().loadAll();
        return Response.ok(tools,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Tools tool) {
        StorageSession session = StorageSession.getInstance();

        Tools newTools = session.getToolsDao().create(tool);
        return Response.ok(newTools,
                MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Tools tools) {
        StorageSession session = StorageSession.getInstance();

        Tools newTool = session.getToolsDao().update(tools);
        return Response.ok(newTool,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
        StorageSession session = StorageSession.getInstance();

        Tools tool = session.getToolsDao().findById(id);
        session.getToolsDao().delete(tool);

        return Response.ok(tool,
                MediaType.APPLICATION_JSON_TYPE).build();
    }
}
