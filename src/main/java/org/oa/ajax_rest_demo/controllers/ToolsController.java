package org.oa.ajax_rest_demo.controllers;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Basket;
import org.oa.ajax_rest_demo.model.Food;
import org.oa.ajax_rest_demo.model.Tools;
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

import javax.validation.Valid;

@Component
@Controller
@RequestMapping("/cont/tools")
public class ToolsController {
	private static final Logger LOG = Logger.getLogger(ToolsController.class);

    @Autowired
    private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(Model model) {
		ModelAndView mav = new ModelAndView("tools");
		List<Tools> tools = storageRepository.getToolsRepository().findAll();
		LOG.info("list " + tools);
		mav.addObject("toolslist", tools);
		return mav;
	}
	
	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String add(Model model) throws Exception {
		model.addAttribute(new Tools());
		return "edittool";
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public String add(@Valid Tools tool) {
		Tools newTool = storageRepository.getToolsRepository().create(tool);
		LOG.info("add " + newTool);
		return "redirect:/cont/tools";
	}
	
	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public String update(@RequestParam(value = "id") int id, Model model) throws Exception {
		LOG.info("update id: " + id);
		model.addAttribute(storageRepository.getToolsRepository().findById(id));
		return "edittool";
	}

	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public String update(@Valid Tools tool) {
		Tools newTool = storageRepository.getToolsRepository().update(tool);
		LOG.info("update " + newTool);
		return "redirect:/cont/tools";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/cont/tools");
		Tools tool = storageRepository.getToolsRepository().findById(id);
		storageRepository.getToolsRepository().delete(tool);
		mav.addObject("toolslist", storageRepository.getToolsRepository().findAll());
		LOG.info("delete " + tool);
		return mav;
	}

	@RequestMapping(value = "/tobasket.html", method = RequestMethod.GET)
	public ModelAndView toBasket(@RequestParam(value = "id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/shop.html");
		Tools tool = storageRepository.getToolsRepository().findById(id);
		String login = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		Users user = storageRepository.getUserRepository().findByLogin(login);
		Basket good = new Basket(user, tool, 1);
		Basket newGood = storageRepository.getBasketRepository().create(good);
		LOG.info("add tool to basket: " + newGood + "->" + tool);
		return mav;
	}
	@RequestMapping(method = RequestMethod.GET,
			value = "/pricerange")
    public
    ModelAndView getAtAgeRange(@RequestParam("start") long start,
						 @RequestParam("end") long end) {
		ModelAndView mav = new ModelAndView("tools");
		List<Tools> tools = storageRepository.getToolsRepository().findAtPriceRange(start, end);
		LOG.info("list by price range" + tools);
    	mav.addObject("toolslist", tools);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/byname")
    public
    ModelAndView getByName(@RequestParam(value = "filterToolsByName") String filterToolsByName) {
		ModelAndView mav = new ModelAndView("tools");
		List<Tools> tools = storageRepository.getToolsRepository().findByName(filterToolsByName);
		LOG.info("list by name" + tools);
    	mav.addObject("toolslist", tools);
		return mav;
	}	
}
