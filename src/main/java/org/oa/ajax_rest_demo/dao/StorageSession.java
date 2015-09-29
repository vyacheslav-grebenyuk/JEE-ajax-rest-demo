package org.oa.ajax_rest_demo.dao;

import org.oa.ajax_rest_demo.model.Book;
import org.oa.ajax_rest_demo.model.Food;
import org.oa.ajax_rest_demo.model.Pet;
import org.oa.ajax_rest_demo.model.Tools;

public class StorageSession {

    private final BookDao bookDao = new BookDao();
    private final PetDao petDao = new PetDao();
    private final FoodDao foodDao = new FoodDao();
    private final ToolsDao toolsDao = new ToolsDao();


    public BaseDao<Book> getBookDao() {
        return bookDao;
    }
    
    public BaseDao<Pet> getPetDao() {
        return petDao;
    }

    public BaseDao<Food> getFoodDao() {
        return foodDao;
    }

    public BaseDao<Tools> getToolsDao() {
        return toolsDao;
    }
    
    private StorageSession(){

    }

    private final static StorageSession instance = new StorageSession();
    static{
    	try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
    }

    public static StorageSession getInstance(){
        return instance;
    }
}
