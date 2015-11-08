package org.oa.ajax_rest_demo.converters;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Tools;
import org.oa.ajax_rest_demo.repositories.StorageRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ToolsToPetConverter implements Converter<String, Tools> {
	private static final Logger log = Logger.getLogger(ToolsToPetConverter.class);

	@Autowired
	private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@Override
	public Tools convert(String element) {
		Integer id = Integer.parseInt((String)element);
		Tools tool = storageRepository.getToolsRepository().findById(id.intValue());
		log.info("Food converter : " + tool);
		return tool;		
    }
}
