package org.oa.ajax_rest_demo.dao;

import org.oa.ajax_rest_demo.model.Book;
import org.oa.ajax_rest_demo.model.Pet;

public class StorageSession {

    private final BookDao bookDao = new BookDao();
    private final PetDao petDao = new PetDao();


    public BaseDao<Book> getBookDao() {
        return bookDao;
    }
    
    public BaseDao<Pet> getPetDao() {
        return petDao;
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
