package org.oa.ajax_rest_demo;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Pet;
import org.oa.ajax_rest_demo.repositories.StorageRepositories;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pets")
public class PetService {
	private static final Logger log = Logger.getLogger(PetService.class);

    @GET
    @Produces("application/json")
    public Response getAll() {
    	StorageRepositories session = StorageRepositories.getInstance();
    	List<Pet> pets = session.getPetRepository().findAll();
    	log.info("list " + pets);

        return Response.ok(pets,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Pet pet) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Pet newPet = session.getPetRepository().create(pet);
    	log.info("add " + newPet);
   	
    	return Response.ok(newPet,
                MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Pet pet) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Pet newPet = session.getPetRepository().update(pet);
    	log.info("update " + newPet);
    	
        return Response.ok(newPet,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
    	StorageRepositories session = StorageRepositories.getInstance();
    	Pet pet = session.getPetRepository().findById(id);
    	session.getPetRepository().delete(pet);
    	log.info("delete " + pet);

        return Response.ok(pet,
                MediaType.APPLICATION_JSON_TYPE).build();
    }
}
