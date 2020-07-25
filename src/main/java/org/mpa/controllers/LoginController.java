package org.mpa.controllers;

import org.apache.log4j.Logger;
import org.mpa.dto.MessageDto;
import org.mpa.dto.CredsDto;
import org.mpa.entities.User;
import org.mpa.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/login")
public class LoginController {
    Logger LOG = Logger.getLogger(this.getClass());

    private UserService service;

    @CrossOrigin
    @Autowired
    public void setService(UserService userService) {
        this.service = userService;
    }

    @CrossOrigin
    @PostMapping(path = "", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public MessageDto auth(@RequestBody CredsDto creds) {
        MessageDto dto = new MessageDto();
        boolean authenticated;

        authenticated = service.auth(creds.getUsername(),creds.getPassword());

        if(authenticated) {
            LOG.info("LoginController: Authenticated");
            dto.setMessage("authenticated");
        }
        else {
            LOG.info("LoginController: Not Authenticated");
            dto.setMessage("not authenticated");
        }
        return dto;
    }

    @CrossOrigin
    @PostMapping(path = "/newuser", consumes = MediaType.APPLICATION_JSON_VALUE)
    public MessageDto newUser(@RequestBody User user) {
        MessageDto dto = new MessageDto();
        LOG.info("LoginController: Attempting to add new user");
        try {
            service.addUser(user);
            dto.setMessage("Success");
            LOG.info("LoginController: Successfully added new user");
        } catch(Exception ex) {
            dto.setMessage("Failure");
            LOG.error("LoginController: Error adding new user");
            ex.printStackTrace();
        }
        return dto;
    }
}
