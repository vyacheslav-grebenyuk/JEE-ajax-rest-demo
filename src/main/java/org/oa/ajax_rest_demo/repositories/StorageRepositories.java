package org.oa.ajax_rest_demo.repositories;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class StorageRepositories {

    private PetRepository petRepository;
    private FoodRepository foodRepository;
    private ToolsRepository toolsRepository;

    private static class Holder{
    	private static final StorageRepositories  
    			INSTANCE = new StorageRepositories();
    }
    
    public static StorageRepositories getInstance(){
    	return Holder.INSTANCE;
    }
    
    protected StorageRepositories() {
        SessionFactory sessionFactory;

        try {
            // Create the SessionFactory from hibernate.cfg.xml
            sessionFactory = new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }

        this.petRepository = new PetRepository(sessionFactory);
        this.foodRepository = new FoodRepository(sessionFactory);
        this.toolsRepository = new ToolsRepository(sessionFactory);        
    }

    public PetRepository getPetRepository() {
        return petRepository;
    }
    public FoodRepository getFoodRepository() {
        return foodRepository;
    }
    public ToolsRepository getToolsRepository() {
        return toolsRepository;
    }
}