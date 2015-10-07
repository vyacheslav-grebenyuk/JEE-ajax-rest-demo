package org.oa.ajax_rest_demo.repositories;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.oa.ajax_rest_demo.model.Pet;

import java.util.List;

public class PetRepository {

    private final SessionFactory sessionFactory;

    public PetRepository(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public Pet create(Pet item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.save(item);
        session.getTransaction().commit();
        return item;
    }

    public Pet update(Pet item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.update(item);
        session.getTransaction().commit();
        return item;
    }

    public void delete(Pet item){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        session.delete(item);
        session.getTransaction().commit();
    }

    public List<Pet> findAll(){
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        List<Pet> result = session.createQuery("from Pet").list();
        session.getTransaction().commit();
        return result;
    }

	public Pet findById(long id) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createQuery("from Pet where idpets = :id")
        					.setParameter("id", id);
        Pet result = (Pet) query.iterate().next();
        session.getTransaction().commit();
        return result;
	}
	
	public List<Pet> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createSQLQuery(
        		"select * from pets where name like :name")
        		.addEntity(Pet.class)
        		.setString("name", "%" + name + "%");
        List<Pet> result = (List<Pet>) query.list();
        session.getTransaction().commit();
        return result;
	}
	
	public List<Pet> findAtAgeRange(int start, int end) {
        Session session = sessionFactory.getCurrentSession();
        session.beginTransaction();
        Criteria criteria = session.createCriteria(Pet.class)
        		.add( Restrictions.between("age", start, end) );

        List<Pet> result = (List<Pet>) criteria.list();
        session.getTransaction().commit();
        return result;
	}
}