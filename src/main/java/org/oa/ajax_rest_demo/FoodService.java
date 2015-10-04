package org.oa.ajax_rest_demo;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Food;
import org.oa.ajax_rest_demo.model.Pet;
import org.oa.ajax_rest_demo.repositories.StorageRepositories;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/foods")
public class FoodService {
	private static final Logger log = Logger.getLogger(FoodService.class);

    @GET
    @Produces("application/json")
    public Response getAll() {
    	StorageRepositories session = StorageRepositories.getInstance();
    	List<Food> foods = session.getFoodRepository().findAll();
    	log.info("list " + foods);

    	return Response.ok(foods,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Food food) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Food newFood = session.getFoodRepository().create(food);
    	log.info("add " + newFood);

    	return Response.ok(newFood,
                MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Food food) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Food newFood = session.getFoodRepository().update(food);
    	log.info("update " + newFood);

    	return Response.ok(newFood,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Food food = session.getFoodRepository().findById(id);
    	session.getFoodRepository().delete(food);
    	log.info("delete " + food);

        return Response.ok(food,
                MediaType.APPLICATION_JSON_TYPE).build();
    }
}
