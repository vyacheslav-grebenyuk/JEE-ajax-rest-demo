package org.oa.ajax_rest_demo.repositories;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.ajax_rest_demo.model.Pet;
import org.oa.ajax_rest_demo.model.Tools;

import java.util.List;

public class ToolsRepository {

    private final SessionFactory sessionFactory;

    public ToolsRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Tools create(Tools item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        return item;
    }

    public Tools update(Tools item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        return item;
    }

    public void delete(Tools item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
    }

    public List<Tools> findAll(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Tools> result = session.createQuery("from Tools").list();
        session.getTransaction().commit();
        return result;
    }

	public Tools findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Tools where idtools = :id ");
        query.setParameter("id", id);
        Tools result = (Tools) query.iterate().next();
        session.getTransaction().commit();
        return result;
	}
}