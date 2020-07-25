package org.mpa.repositories;

import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mpa.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoArgsConstructor
@Repository("userRepo")
public class UserRepo implements Persistable {
    Logger LOG = Logger.getLogger(this.getClass());

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public boolean auth(String username, String password) {
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, username);
        if(user == null) {
            LOG.debug("Username not found");
            return false;
        } else {
            String p = user.getPassword();
            if(!password.equals(p)) {
                LOG.debug("Password not a match");
                return false;
            } else {
                LOG.info("Authenticated");
                return true;
            }
        }
    }

    @Override
    public Object getById(Object username) {
        LOG.info("Attempting to get user by username = " + username);
        Session session = sessionFactory.getCurrentSession();
        User user = session.get(User.class, (String)username);
        LOG.info("Successfully got user by username");
        return user;
    }

    public List<Object> getAll() {
        LOG.info("Attempting to get all users");
        String query = "FROM User";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        List users = q.list();
        LOG.info("Successfully got all users");
        return users;
    }

    @Override
    public void add(Object obj) {
        LOG.info("Attempting to add user");
        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
        LOG.info("Successfully added user");
    }

    @Override
    public void update(Object obj) {
        LOG.info("Attempting to update user");
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
        LOG.info("Successfully updated user");
    }

    @Override
    public void delete(Object obj) {
        LOG.info("Attempting to delete user");
        Session session = sessionFactory.getCurrentSession();
        session.delete(obj);
        LOG.info("Successfully deleted user");
    }
}
