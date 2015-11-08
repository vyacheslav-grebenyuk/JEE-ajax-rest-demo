package org.oa.ajax_rest_demo.repositories;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class StorageRepository {
	@Autowired
	private PetRepository petRepository;
	@Autowired
	private FoodRepository foodRepository;
	@Autowired
	private ToolsRepository toolsRepository;
	
	public void setPetRepository(PetRepository petRepository) {
		this.petRepository = petRepository;
	}
	public PetRepository getPetRepository() {
		return petRepository;
	}
	public FoodRepository getFoodRepository() {
		return foodRepository;
	}
	public void setFoodRepository(FoodRepository foodRepository) {
		this.foodRepository = foodRepository;
	}
	public ToolsRepository getToolsRepository() {
		return toolsRepository;
	}
	public void setToolsRepository(ToolsRepository toolsRepository) {
		this.toolsRepository = toolsRepository;
	}
}
