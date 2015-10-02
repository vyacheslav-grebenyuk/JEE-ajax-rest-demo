package org.oa.ajax_rest_demo.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.ajax_rest_demo.model.Book;

import java.util.List;

public class BookRepository {

    private final SessionFactory sessionFactory;

    public BookRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void create(Book item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
    }

    public void update(Book item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
    }

    public void delete(Book item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
    }

    public List<Book> findAll(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Book> result = session.createQuery("from Book").list();
        session.getTransaction().commit();
        return result;
    }
}