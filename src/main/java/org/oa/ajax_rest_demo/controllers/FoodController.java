package org.oa.ajax_rest_demo.controllers;

import org.apache.log4j.Logger;
import javax.validation.Valid;
import org.oa.ajax_rest_demo.model.Food;
import org.oa.ajax_rest_demo.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Component
@Controller
@RequestMapping("/cont/foods")
public class FoodController {
	private static final Logger log = Logger.getLogger(FoodController.class);

    @Autowired
    private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(Model model) {
		ModelAndView mav = new ModelAndView("foods");
		List<Food> foods = storageRepository.getFoodRepository().findAll();
		log.info("list " + foods);
		mav.addObject("foodslist", storageRepository.getFoodRepository().findAll());
		return mav;
	}
	
	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String add(Model model) throws Exception {
		model.addAttribute(new Food());
		return "editfood";
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public String add(@Valid Food food) {
		Food newFood = storageRepository.getFoodRepository().create(food);
		log.info("add " + newFood);
		return "redirect:/cont/foods";
	}
	
	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public String update(@RequestParam(value = "id") int id, Model model) throws Exception {
		log.info("update id: " + id);
		model.addAttribute(storageRepository.getFoodRepository().findById(id));
		return "editfood";
	}

	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public String update(@Valid Food food) {
		Food newFood = storageRepository.getFoodRepository().update(food);
		log.info("update " + newFood);
		return "redirect:/cont/foods";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/cont/foods");
		Food food = storageRepository.getFoodRepository().findById(id);
		storageRepository.getFoodRepository().delete(food);
		mav.addObject("foodslist", storageRepository.getFoodRepository().findAll());
		log.info("delete " + food);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/pricerange")
    public
    ModelAndView getAtPriceRange(@RequestParam("start") long start,
						 		 @RequestParam("end") long end) {
		ModelAndView mav = new ModelAndView("foods");
		List<Food> foods = storageRepository.getFoodRepository().findAtPriceRange(start, end);
		log.info("list by price range" + foods);
    	mav.addObject("foodslist", foods);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/byname")
    public
    ModelAndView getByName(@RequestParam(value = "filterFoodByName") String filterFoodByName) {
		ModelAndView mav = new ModelAndView("foods");
		List<Food> foods = storageRepository.getFoodRepository().findByName(filterFoodByName);
		log.info("list by name" + foods);
    	mav.addObject("foodslist", foods);
		return mav;
	}	
}
