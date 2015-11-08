package org.oa.ajax_rest_demo.services;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Food;

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
@RequestMapping("/api/foods")
public class FoodService {
	private static final Logger log = Logger.getLogger(FoodService.class);
	@Autowired
	private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public 
    @ResponseBody
    List<Food> getAll() {
    	List<Food> foods = storageRepository.getFoodRepository().findAll();
    	log.info("list " + foods);

    	return foods;
    }

	@RequestMapping(method = RequestMethod.POST,
			consumes = "application/json",
            produces = "application/json")
    public
    @ResponseBody
    Food add(Food food) {
    	Food newFood = storageRepository.getFoodRepository().create(food);
    	log.info("add " + newFood);

    	return newFood;
    }


	@RequestMapping(method = RequestMethod.PUT,
			consumes = "application/json",
            produces = "application/json")
    public
    @ResponseBody
    Food update(Food food) {
    	Food newFood = storageRepository.getFoodRepository().update(food);
    	log.info("update " + newFood);

    	return newFood;
    }

	@RequestMapping(method = RequestMethod.DELETE,
			value = "{id}", 
            produces = "application/json")
    public
    @ResponseBody
    Food delete(@PathVariable("id") int id) {
    	Food food = storageRepository.getFoodRepository().findById(id);
    	storageRepository.getFoodRepository().delete(food);
    	log.info("delete " + food);

        return food;
    }
}