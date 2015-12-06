package org.oa.ajax_rest_demo.controllers;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Basket;
import org.oa.ajax_rest_demo.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Component
@Controller
@RequestMapping("/cont/basket")
public class BasketController {
	private static final Logger LOG = Logger.getLogger(BasketController.class);

    @Autowired
    private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(Model model) {
		ModelAndView mav = new ModelAndView("basket");
		List<Basket> basketItems = storageRepository.getBasketRepository().findAll();
		LOG.info("list " + basketItems);
		mav.addObject("basketitems", basketItems);
		return mav;
	}
	
	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public String update(@RequestParam(value = "id") int id, Model model,
			HttpSession session) throws Exception {
		model.addAttribute(storageRepository.getBasketRepository().findById(id));
		return "editbasket";
	}

	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public String update(@Valid Basket basket, Model model, SessionStatus status) {
		if(basket.getQuantity() > basket.getGood().getQuantity()){
			model.addAttribute(storageRepository.getBasketRepository().findById(basket.getId()));
			model.addAttribute("error", "To much to buy, try again");
			LOG.error("update too much: " + basket.getQuantity());
			return "editbasket";			
		}
		Basket newBasket = storageRepository.getBasketRepository().update(basket);
		LOG.info("update " + newBasket);
		status.setComplete();
		return "redirect:/shop.html";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public String delete(@RequestParam(value = "id") int id) {
		Basket basket = storageRepository.getBasketRepository().findById(id);
		storageRepository.getBasketRepository().delete(basket);
		LOG.info("delete " + basket);
		return "redirect:/shop.html";
	}
}
