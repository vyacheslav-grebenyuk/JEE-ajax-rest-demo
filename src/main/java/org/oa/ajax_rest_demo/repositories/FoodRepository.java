package org.oa.ajax_rest_demo.repositories;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.ajax_rest_demo.model.Food;
import org.oa.ajax_rest_demo.model.Pet;

import java.util.List;

public class FoodRepository {

    private final SessionFactory sessionFactory;

    public FoodRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Food create(Food item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        return item;
    }

    public Food update(Food item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        return item;
    }

    public void delete(Food item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
    }

    public List<Food> findAll(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Food> result = session.createQuery("from Food").list();
        session.getTransaction().commit();
        return result;
    }

	public Food findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Food where idfood = :id ");
        query.setParameter("id", id);
        Food result = (Food) query.iterate().next();
        session.getTransaction().commit();
        return result;
	}
}