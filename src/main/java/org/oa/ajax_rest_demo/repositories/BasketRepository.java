package org.oa.ajax_rest_demo.repositories;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.oa.ajax_rest_demo.model.Basket;
import org.oa.ajax_rest_demo.model.Food;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
@Transactional(readOnly = true)
public class BasketRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Basket create(Basket item){
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        return item;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Basket update(Basket item){
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
        return item;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Basket item){
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Basket> findAll(){
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
		List<Basket> result = session.createQuery("from Basket").list();
        return result;
    }
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Basket> findByCustomerName(String login) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Basket where client.username = :login ");
        query.setParameter("login", login);
        @SuppressWarnings("unchecked")
		List<Basket> result = query.list();
        return result;
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    public Basket findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Basket where id = :id ");
        query.setParameter("id", id);
        Basket result = (Basket) query.iterate().next();
        return result;
	}
}