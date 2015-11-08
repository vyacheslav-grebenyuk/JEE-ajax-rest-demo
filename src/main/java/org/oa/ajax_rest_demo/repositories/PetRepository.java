package org.oa.ajax_rest_demo.repositories;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.oa.ajax_rest_demo.model.Pet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
@Transactional(readOnly = true)
public class PetRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Pet create(Pet item){
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        return item;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Pet update(Pet item){
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
        return item;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Pet item){
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Pet> findAll(){
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
		List<Pet> result = session.createQuery("from Pet").list();
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public Pet findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Pet where id = :id")
        					.setParameter("id", id);
        Pet result = (Pet) query.iterate().next();
        if(result!=null){
            Hibernate.initialize(result.getFoods());
            Hibernate.initialize(result.getTools());
        }
        return result;
	}
	
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Pet> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
        		"select * from pets, goods where pets.id = goods.id and name like :name")
        		.addEntity(Pet.class)
        		.setString("name", "%" + name + "%");
        @SuppressWarnings("unchecked")
		List<Pet> result = (List<Pet>) query.list();
        return result;
	}
	
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Pet> findAtPriceRange(long start, long end) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Pet.class)
        		.add( Restrictions.between("price", start, end) );

        @SuppressWarnings("unchecked")
		List<Pet> result = (List<Pet>) criteria.list();
        return result;
	}
}