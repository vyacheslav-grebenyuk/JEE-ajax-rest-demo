package org.oa.ajax_rest_demo;

import org.oa.ajax_rest_demo.dao.StorageRepositories;
import org.oa.ajax_rest_demo.dao.StorageSession;
import org.oa.ajax_rest_demo.model.Pet;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/pets")
public class PetService {

    @GET
    @Produces("application/json")
    public Response getAll() {
//        StorageSession session = StorageSession.getInstance();
//
//        List<Pet> pets = session.getPetDao().loadAll();
    	StorageRepositories session = StorageRepositories.getInstance();
    	List<Pet> pets = session.getPetRepository().findAll();

        return Response.ok(pets,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @POST
    @Consumes("application/json")
    @Produces("application/json")
    public Response add(Pet pet) {
//        StorageSession session = StorageSession.getInstance();
//
//        Pet newPet = session.getPetDao().create(pet);
    	StorageRepositories session = StorageRepositories.getInstance();
    	Pet newPet = session.getPetRepository().create(pet);
   	
    	return Response.ok(newPet,
                MediaType.APPLICATION_JSON_TYPE).build();
    }


    @PUT
    @Consumes("application/json")
    @Produces("application/json")
    public Response update(Pet pet) {
//        StorageSession session = StorageSession.getInstance();
//
//        Pet newPet = session.getPetDao().update(pet);
    	StorageRepositories session = StorageRepositories.getInstance();
    	Pet newPet = session.getPetRepository().update(pet);
    	
        return Response.ok(newPet,
                MediaType.APPLICATION_JSON_TYPE).build();
    }

    @DELETE
    @Produces("application/json")
    @Path("{id}")
    public Response delete(@PathParam("id") long id) {
//        StorageSession session = StorageSession.getInstance();
//
//        Pet pet = session.getPetDao().findById(id);
//        session.getPetDao().delete(pet);
    	StorageRepositories session = StorageRepositories.getInstance();
    	Pet pet = session.getPetRepository().findById(id);
    	session.getPetRepository().delete(pet);

        return Response.ok(pet,
                MediaType.APPLICATION_JSON_TYPE).build();
    }
}
