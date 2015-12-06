package org.oa.ajax_rest_demo.repositories;

import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.oa.ajax_rest_demo.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@Repository
@Transactional(readOnly = true)
public class UserRepository {
	private static final Logger LOG = Logger.getLogger(UserRepository.class);
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

    @Transactional(propagation = Propagation.REQUIRED)
    public Users findByLogin(String login) {
        Session session = sessionFactory.getCurrentSession();
        LOG.info("Login: " + login);
        Query query = session.createQuery("from Users where username = :username")
        					.setParameter("username", login);
        Users result = (Users) query.iterate().next();
        return result;
	}
}