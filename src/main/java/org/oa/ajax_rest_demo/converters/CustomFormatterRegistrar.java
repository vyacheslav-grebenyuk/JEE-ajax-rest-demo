package org.oa.ajax_rest_demo.converters;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.FormatterRegistrar;
import org.springframework.format.FormatterRegistry;
import org.springframework.stereotype.Component;

@Component
public class CustomFormatterRegistrar implements FormatterRegistrar {
	@Autowired
	FoodToPetConverter foodToPetConverter;
	@Autowired
	ToolsToPetConverter toolsToPetConverter;
	@Autowired
	ListToGoodsName listToGoodsName;	


	@Override
	public void registerFormatters(FormatterRegistry aRegistry) {	
		aRegistry.addConverter(foodToPetConverter);
		aRegistry.addConverter(toolsToPetConverter);
		aRegistry.addConverter(listToGoodsName);
	}

}
