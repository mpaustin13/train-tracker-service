package org.mpa.services;

import org.mpa.entities.Workout;
import org.mpa.repositories.WorkoutRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("workoutService")
public class WorkoutService {

    private WorkoutRepo repo;

    @Autowired
    public void setWorkoutRepo(WorkoutRepo workoutRepo) {
        this.repo = workoutRepo;
    }

    @Transactional
    public Workout getById(Integer id) {
        return (Workout)repo.getById(id);
    }

    @Transactional
    public List getAll(String username) {
        return repo.getAll(username);
    }

    @Transactional
    public void addWorkout(Workout workout) {
        repo.add(workout);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateWorkout(Workout workout) {
        repo.update(workout);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteWorkout(Workout workout) {
        repo.delete(workout);
    }
}
