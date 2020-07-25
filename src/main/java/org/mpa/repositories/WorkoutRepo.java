package org.mpa.repositories;

import lombok.NoArgsConstructor;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.mpa.entities.Workout;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@NoArgsConstructor
@Repository("workoutRepo")
public class WorkoutRepo implements Persistable {
    Logger LOG = Logger.getLogger(this.getClass());

    private SessionFactory sessionFactory;

    @Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public Object getById(Object id) {
        LOG.info("Attempting to get workout by id = " + id);
        Session session = sessionFactory.getCurrentSession();
        Workout workout = session.get(Workout.class, (Integer)id);
        LOG.info("Successfully got workout by id");
        return workout;
    }

    public List<Object> getAll(String username) {
        LOG.info("Attempting to get all workouts by id");
        String query = "FROM Workout WHERE username = :username";
        Session session = sessionFactory.getCurrentSession();
        Query q = session.createQuery(query);
        q.setParameter("username", username);
        List workouts = q.list();
        LOG.info("Successfully got all workouts by id");
        return workouts;
    }

    @Override
    public void add(Object obj) {
        LOG.info("Attempting to add workout");
        Session session = sessionFactory.getCurrentSession();
        session.save(obj);
        LOG.info("Successfully added workout");
    }

    @Override
    public void update(Object obj) {
        LOG.info("Attempting to update workout");
        Session session = sessionFactory.getCurrentSession();
        session.update(obj);
        LOG.info("Successfully updated workout");
    }

    @Override
    public void delete(Object obj) {
        LOG.info("Attempting to delete workout");
        Session session = sessionFactory.getCurrentSession();
        session.delete(obj);
        LOG.info("Successfully deleted workout");
    }
}
