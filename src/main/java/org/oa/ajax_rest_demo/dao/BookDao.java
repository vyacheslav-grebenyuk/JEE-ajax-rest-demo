package org.oa.ajax_rest_demo.dao;

import org.oa.ajax_rest_demo.model.Book;

import java.util.ArrayList;
import java.util.List;

class BookDao implements BaseDao<Book> {

    private final List<Book> books =
            new ArrayList<>();

    public BookDao() {
        create(new Book("Book1", 1990, "Author1"));
        create(new Book("Book2", 1990, "Author2"));
        create(new Book("Book3", 1990, "Author3"));
    }

    @Override
    public List<Book> loadAll() {
        return books;
    }

    @Override
    public Book findById(long id) {
        return books.stream().filter(item ->
                item.getId() == id).findAny().get();
    }

    @Override
    public Book create(Book item) {
        books.add(item);
        item.setId(books.size());
        return item;
    }

    @Override
    public Book update(Book item) {
        Book book = findById(item.getId());
        if(book != null){
            book.setName(item.getName());
            book.setYear(item.getYear());
            book.setAuthor(item.getAuthor());
        }
        return book;
    }

    @Override
    public boolean delete(Book item) {
        return books.remove(item);
    }
}
