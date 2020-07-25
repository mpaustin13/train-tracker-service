package org.mpa.controllers;

import org.apache.log4j.Logger;
import org.mpa.dto.MessageDto;
import org.mpa.entities.Workout;
import org.mpa.services.WorkoutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/workouts")
public class WorkoutController {
    Logger LOG = Logger.getLogger(this.getClass());

    private WorkoutService service;

    @Autowired
    public void setWorkoutService(WorkoutService workoutService) {
        this.service = workoutService;
    }

    // get all workouts for a user
    @CrossOrigin
    @GetMapping(path = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Workout> getAllUserWorkouts(@RequestParam String username) {
        LOG.info("WorkoutController: attempting to get all workouts for a user");
        List<Workout> workouts = service.getAll(username);
        LOG.info("WorkoutController: Successfully got all workouts for a user");
        return workouts;
    }

    // add new workout for a user
    @CrossOrigin
    @PostMapping(path = "/new", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageDto addNewWorkout(@RequestBody Workout workout) {
        MessageDto dto = new MessageDto();
        LOG.info("WorkoutController: attempting to add workout");
        try {
            service.addWorkout(workout);
            dto.setMessage("Success");
            LOG.info("WorkoutController: successfully added workout");
        } catch (Exception ex) {
            dto.setMessage("Failure");
            LOG.error("WorkoutController: failed to add workout");
        }
        return dto;
    }
}
