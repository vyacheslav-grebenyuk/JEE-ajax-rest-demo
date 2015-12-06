package org.oa.ajax_rest_demo.controllers;

import java.util.List;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Basket;
import org.oa.ajax_rest_demo.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;  
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;  

@Controller
@RequestMapping(value="/")
public class IndexController {
	private static final Logger LOG = Logger.getLogger(IndexController.class);
	@Autowired
    private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/shop.html", method = RequestMethod.GET)  
    public ModelAndView goToShopPage() {
		ModelAndView mav = new ModelAndView("shop");
		String login = ((User)SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
		List<Basket> baskets = storageRepository.getBasketRepository().findByCustomerName(login);
		LOG.info("Basket list: " + baskets);
		mav.addObject("goodslist", baskets);
		LOG.info("Redirect to shop home");
        return mav;  
    }
	
	@PreAuthorize("hasRole('ADMIN')")
	@RequestMapping(value="/", method = RequestMethod.GET)  
    public String goToIndexPage() { 
		LOG.info("Redirect to shop home");
        return "shop";  
    }
	
	@RequestMapping(value="/login")
	public String login() {
		return "login";
	}
}
