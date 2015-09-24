package org.oa.ajax_rest_demo.dao;

import org.oa.ajax_rest_demo.model.Book;

public class StorageSession {

    private final BookDao bookDao = new BookDao();

    public BaseDao<Book> getBookDao() {
        return bookDao;
    }

    private StorageSession(){

    }

    private final static StorageSession instance = new StorageSession();

    public static StorageSession getInstance(){
        return instance;
    }
}
