package org.oa.ajax_rest_demo.converters;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Food;

import org.oa.ajax_rest_demo.repositories.StorageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class FoodToPetConverter implements Converter<String, Food> {
	private static final Logger log = Logger.getLogger(FoodToPetConverter.class);

	@Autowired
	private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@Override
	public Food convert(String element) {
		Integer id = Integer.parseInt((String)element);
		Food food = storageRepository.getFoodRepository().findById(id.intValue());
		log.info("Food converter : " + food);
		return food;		
    }
}
