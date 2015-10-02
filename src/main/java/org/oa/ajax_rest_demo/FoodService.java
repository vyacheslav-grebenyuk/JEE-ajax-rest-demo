package org.oa.ajax_rest_demo;

import org.oa.ajax_rest_demo.dao.StorageRepositories;
import org.oa.ajax_rest_demo.dao.StorageSession;
import org.oa.ajax_rest_demo.model.Food;
import org.oa.ajax_rest_demo.model.Pet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/foods")
public class FoodService {

    @GET
    @Produces("application/json")
    public Response getAll() {
//        StorageSession session = StorageSession.getInstance();
//
//        List<Food> foods = session.getFoodDao().loadAll();
    	StorageRepositories session = StorageRepositories.getInstance();
    	List<Food> foods = session.getFoodRepository().findAll();

    	return Response.ok(foods,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Food food) {
//        StorageSession session = StorageSession.getInstance();
//
//        Food newFood = session.getFoodDao().create(food);
    	StorageRepositories session = StorageRepositories.getInstance();
    	Food newFood = session.getFoodRepository().create(food);

    	return Response.ok(newFood,
                MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Food food) {
//        StorageSession session = StorageSession.getInstance();
//
//        Food newFood = session.getFoodDao().update(food);
    	StorageRepositories session = StorageRepositories.getInstance();
    	Food newFood = session.getFoodRepository().update(food);

    	return Response.ok(newFood,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
//        StorageSession session = StorageSession.getInstance();
//
//        Food food = session.getFoodDao().findById(id);
//        session.getFoodDao().delete(food);
    	StorageRepositories session = StorageRepositories.getInstance();
    	Food food = session.getFoodRepository().findById(id);
    	session.getFoodRepository().delete(food);

        return Response.ok(food,
                MediaType.APPLICATION_JSON_TYPE).build();
    }
}
