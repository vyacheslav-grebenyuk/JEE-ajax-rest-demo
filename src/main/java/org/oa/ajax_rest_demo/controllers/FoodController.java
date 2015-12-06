package org.oa.ajax_rest_demo.controllers;

import org.apache.log4j.Logger;
import javax.validation.Valid;

import org.oa.ajax_rest_demo.model.Basket;
import org.oa.ajax_rest_demo.model.Food;
import org.oa.ajax_rest_demo.model.Pet;
import org.oa.ajax_rest_demo.model.Users;
import org.oa.ajax_rest_demo.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
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
	private static final Logger LOG = Logger.getLogger(FoodController.class);

    @Autowired
    private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(Model model) {
		ModelAndView mav = new ModelAndView("foods");
		List<Food> foods = storageRepository.getFoodRepository().findAll();
		LOG.info("list " + foods);
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
		LOG.info("add " + newFood);
		return "redirect:/cont/foods";
	}
	
	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public String update(@RequestParam(value = "id") int id, Model model) throws Exception {
		LOG.info("update id: " + id);
		model.addAttribute(storageRepository.getFoodRepository().findById(id));
		return "editfood";
	}

	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public String update(@Valid Food food) {
		Food newFood = storageRepository.getFoodRepository().update(food);
		LOG.info("update " + newFood);
		return "redirect:/cont/foods";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/cont/foods");
		Food food = storageRepository.getFoodRepository().findById(id);
		storageRepository.getFoodRepository().delete(food);
		mav.addObject("foodslist", storageRepository.getFoodRepository().findAll());
		LOG.info("delete " + food);
		return mav;
	}
	
	@RequestMapping(value = "/tobasket.html", method = RequestMethod.GET)
	public ModelAndView toBasket(@RequestParam(value = "id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/shop.html");
		Food food = storageRepository.getFoodRepository().findById(id);
		String login = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		Users user = storageRepository.getUserRepository().findByLogin(login);
		Basket good = new Basket(user, food, 1);
		Basket newGood = storageRepository.getBasketRepository().create(good);
		LOG.info("add food to basket: " + newGood + "->" + food);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/pricerange")
    public
    ModelAndView getAtPriceRange(@RequestParam("start") long start,
						 		 @RequestParam("end") long end) {
		ModelAndView mav = new ModelAndView("foods");
		List<Food> foods = storageRepository.getFoodRepository().findAtPriceRange(start, end);
		LOG.info("list by price range" + foods);
    	mav.addObject("foodslist", foods);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/byname")
    public
    ModelAndView getByName(@RequestParam(value = "filterFoodByName") String filterFoodByName) {
		ModelAndView mav = new ModelAndView("foods");
		List<Food> foods = storageRepository.getFoodRepository().findByName(filterFoodByName);
		LOG.info("list by name" + foods);
    	mav.addObject("foodslist", foods);
		return mav;
	}	
}
