package org.oa.ajax_rest_demo.repositories;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
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
public class FoodRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Food create(Food item){
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        return item;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Food update(Food item){
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
        return item;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Food item){
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Food> findAll(){
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
		List<Food> result = session.createQuery("from Food").list();
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Food findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Food where id = :id ");
        query.setParameter("id", id);
        Food result = (Food) query.iterate().next();
        return result;
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Food> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
        		"select * from food, goods where food.id = goods.id and name like :name")
        		.addEntity(Food.class)
        		.setString("name", "%" + name + "%");
        @SuppressWarnings("unchecked")
		List<Food> result = (List<Food>) query.list();
        return result;
	}
	
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Food> findAtPriceRange(long start, long end) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Food.class)
        		.add( Restrictions.between("price", start, end) );

        @SuppressWarnings("unchecked")
		List<Food> result = (List<Food>) criteria.list();
        return result;
	}
}