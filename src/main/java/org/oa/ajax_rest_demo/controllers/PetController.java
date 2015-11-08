package org.oa.ajax_rest_demo.controllers;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Food;
import org.oa.ajax_rest_demo.model.Pet;
import org.oa.ajax_rest_demo.model.Tools;
import org.oa.ajax_rest_demo.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Component
@Controller
@SessionAttributes({"foodList", "toolsList"})
@RequestMapping("/cont/pets")
public class PetController {
	private static final Logger log = Logger.getLogger(PetController.class);

    @Autowired
    private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView getAll(Model model) {
		ModelAndView mav = new ModelAndView("pets");
		List<Pet> pets = storageRepository.getPetRepository().findAll();
		log.info("list " + pets);
		mav.addObject("petslist", pets);
		return mav;
	}
	
	@RequestMapping(value = "/add.html", method = RequestMethod.GET)
	public String add(Model model) throws Exception {
		model.addAttribute(new Pet());
		return "editpet";
	}

	@RequestMapping(value = "/add.html", method = RequestMethod.POST)
	public String add(@Valid Pet pet, SessionStatus status) {
		Pet newPet = storageRepository.getPetRepository().create(pet);
		log.info("add " + newPet);
		status.setComplete();
		return "redirect:/cont/pets";
	}
	
	@RequestMapping(value = "/update.html", method = RequestMethod.GET)
	public String update(@RequestParam(value = "id") int id, Model model,
			HttpSession session) throws Exception {
		model.addAttribute(storageRepository.getPetRepository().findById(id));
		session.setAttribute("foodList", initializeFoodList());
		session.setAttribute("toolsList", initializeToolsList());
		return "editpet";
	}

	@RequestMapping(value = "/update.html", method = RequestMethod.POST)
	public String update(@Valid Pet pet, SessionStatus status) {
		Pet newPet = storageRepository.getPetRepository().update(pet);
		log.info("update " + newPet);
		status.setComplete();
		return "redirect:/cont/pets";
	}
	
	@RequestMapping(value = "/delete", method = RequestMethod.GET)
	public ModelAndView delete(@RequestParam(value = "id") int id) {
		ModelAndView mav = new ModelAndView("redirect:/cont/pets");
		Pet pet = storageRepository.getPetRepository().findById(id);
		storageRepository.getPetRepository().delete(pet);
		mav.addObject("petslist", storageRepository.getPetRepository().findAll());
		log.info("delete " + pet);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/pricerange")
    public
    ModelAndView getAtAgeRange(@RequestParam("start") long start,
						 @RequestParam("end") long end) {
		ModelAndView mav = new ModelAndView("pets");
		List<Pet> pets = storageRepository.getPetRepository().findAtPriceRange(start, end);
		log.info("list by price range" + pets);
    	mav.addObject("petslist", pets);
		return mav;
	}
	
	@RequestMapping(method = RequestMethod.GET,
			value = "/byname")
    public
    ModelAndView getByName(@RequestParam(value = "filterPetByName") String filterPetByName) {
		ModelAndView mav = new ModelAndView("pets");
		List<Pet> pets = storageRepository.getPetRepository().findByName(filterPetByName);
		log.info("list by name" + pets);
    	mav.addObject("petslist", pets);
		return mav;
	}
	
	@ModelAttribute("foodList")
    public List<Food> initializeFoodList() {
        return storageRepository.getFoodRepository().findAll();
    }
	
	@ModelAttribute("toolsList")
    public List<Tools> initializeToolsList() {
        return storageRepository.getToolsRepository().findAll();
    }
}
