package org.oa.ajax_rest_demo.services;

import org.apache.log4j.Logger;
import org.oa.ajax_rest_demo.model.Tools;
import org.oa.ajax_rest_demo.repositories.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Component
@Controller
@RequestMapping("/api/tools")
public class ToolsService {
	private static final Logger log = Logger.getLogger(ToolsService.class);
	@Autowired
	private StorageRepository storageRepository;

	public void setStorageRepository(StorageRepository storageRepository) {
		this.storageRepository = storageRepository;
	}

	@RequestMapping(method = RequestMethod.GET,
            produces = "application/json")
    public
    @ResponseBody
    List<Tools> getAll() {
    	List<Tools> tools = storageRepository.getToolsRepository().findAll();
    	log.info("list " + tools);

    	return tools;
    }

    @RequestMapping(method = RequestMethod.POST,
    		consumes = "application/json",
    		produces = "application/json")
    public 
    @ResponseBody
    Tools add(Tools tool) {
    	Tools newTool = storageRepository.getToolsRepository().create(tool);
    	log.info("add " + newTool);

    	return newTool;
    }


    @RequestMapping(method = RequestMethod.PUT,
    		consumes = "application/json",
    		produces = "application/json")
    public 
    @ResponseBody
    Tools update(Tools tool) {
    	Tools newTool = storageRepository.getToolsRepository().update(tool);
    	log.info("update " + newTool);

    	return newTool;
    }

	@RequestMapping(method = RequestMethod.DELETE,
			value = "{id}",
            produces = "application/json")
    public
    @ResponseBody
    Tools delete(@PathVariable("id") int id) {
    	Tools tool = storageRepository.getToolsRepository().findById(id);
    	storageRepository.getToolsRepository().delete(tool);
    	log.info("delete " + tool);
    	
        return tool;
    }
}