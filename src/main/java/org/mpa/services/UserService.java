package org.mpa.services;

import org.mpa.entities.User;
import org.mpa.repositories.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
public class UserService {

    private UserRepo userRepo;

    @Autowired
    public void setUserRepo(UserRepo userRepo) {
        this.userRepo = userRepo;
    }

    @Transactional
    public boolean auth(String username, String password) {
        return userRepo.auth(username, password);
    }

    @Transactional
    public User getById(String username) {
        return (User)userRepo.getById(username);
    }

    @Transactional
    public List getAll() {
        return userRepo.getAll();
    }

    @Transactional
    public void addUser(User user) {
        userRepo.add(user);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void updateUser(User user) {
        userRepo.update(user);
    }

    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void deleteUser(User user) {
        userRepo.delete(user);
    }
}
