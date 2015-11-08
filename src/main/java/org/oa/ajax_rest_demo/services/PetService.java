package org.oa.ajax_rest_demo.services;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Pet;

import org.oa.ajax_rest_demo.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@Controller
@RequestMapping("/api/pets")
public class PetService {
	private static final Logger log = Logger.getLogger(PetService.class);
	@Autowired
	private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@RequestMapping(method = RequestMethod.GET,
			value = "{start}/{end}",
            produces = "application/json")
    public
    @ResponseBody
    List<Pet> getAtAgeRange(@PathVariable("start") int start,
							@PathVariable("end") int end) {
		List<Pet> pets = storageRepository.getPetRepository().findAtPriceRange(start, end);
		log.info("list by age range" + pets);
		
		return pets;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "{name}",
            produces = "application/json")
    public
    @ResponseBody
    List<Pet> getByName(@PathVariable("name") String name) {
		List<Pet> pets = storageRepository.getPetRepository().findByName(name);
		log.info("list by name" + pets);
		
		return pets;
	}
	
	@RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public
    @ResponseBody
    List<Pet> getAll() {
    	List<Pet> pets = storageRepository.getPetRepository().findAll();
    	log.info("list " + pets);

        return pets;
    }

    @RequestMapping(method = RequestMethod.POST,
    		consumes = "application/json",
    		produces = "application/json")
    public 
    @ResponseBody
    Pet add(Pet pet) {
    	Pet newPet = storageRepository.getPetRepository().create(pet);
    	log.info("add " + newPet);
   	
    	return newPet;
    }


    @RequestMapping(method = RequestMethod.PUT,
    		consumes = "application/json",
    		produces = "application/json")
    public 
    @ResponseBody
    Pet update(Pet pet) {
    	Pet newPet = storageRepository.getPetRepository().update(pet);
    	log.info("update " + newPet);
    	
        return newPet;
    }

	@RequestMapping(method = RequestMethod.DELETE,
			value = "{id}",
            produces = "application/json")
    public
    @ResponseBody
    Pet delete(@PathVariable("id") int id) {
    	Pet pet = storageRepository.getPetRepository().findById(id);
    	storageRepository.getPetRepository().delete(pet);
    	log.info("delete " + pet);

        return pet;
    }
}
