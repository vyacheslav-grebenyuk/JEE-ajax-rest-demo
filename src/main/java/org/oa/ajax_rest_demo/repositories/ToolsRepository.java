package org.oa.ajax_rest_demo.repositories;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.oa.ajax_rest_demo.model.Tools;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Component
@Repository
@Transactional
public class ToolsRepository {

    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
	public Tools create(Tools item){
        Session session = sessionFactory.getCurrentSession();
        session.save(item);
        return item;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public Tools update(Tools item){
        Session session = sessionFactory.getCurrentSession();
        session.update(item);
        return item;
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRED)
    public void delete(Tools item){
        Session session = sessionFactory.getCurrentSession();
        session.delete(item);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public List<Tools> findAll(){
        Session session = sessionFactory.getCurrentSession();
        @SuppressWarnings("unchecked")
		List<Tools> result = session.createQuery("from Tools").list();
        return result;
    }

    @Transactional(propagation = Propagation.REQUIRED)
	public Tools findById(int id) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createQuery("from Tools where id = :id ");
        query.setParameter("id", id);
        Tools result = (Tools) query.iterate().next();
        return result;
	}
    
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Tools> findByName(String name) {
        Session session = sessionFactory.getCurrentSession();
        Query query = session.createSQLQuery(
        		"select * from tools, goods where tools.id = goods.id and name like :name")
        		.addEntity(Tools.class)
        		.setString("name", "%" + name + "%");
        @SuppressWarnings("unchecked")
		List<Tools> result = (List<Tools>) query.list();
        return result;
	}
	
    @Transactional(propagation = Propagation.REQUIRED)
    public List<Tools> findAtPriceRange(long start, long end) {
        Session session = sessionFactory.getCurrentSession();
        Criteria criteria = session.createCriteria(Tools.class)
        		.add( Restrictions.between("price", start, end) );

        @SuppressWarnings("unchecked")
		List<Tools> result = (List<Tools>) criteria.list();
        return result;
	}
}