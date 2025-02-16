package tn.esprit.atlas.controllers;

import tn.esprit.atlas.entities.User;
import tn.esprit.atlas.services.UserService;

import java.util.List;

import java.sql.SQLIntegrityConstraintViolationException;

public class UserController {

    private UserService userService = new UserService();

    public void createUser(User user) throws SQLIntegrityConstraintViolationException {
        userService.addUser(user);
    }

    public void listUsers() {
        List<User> users = UserService.getAllUsers();
        users.forEach(user -> System.out.println(user.getName() + " - " + user.getEmail()));
    }

    public void modifyUser(User user) {
        userService.updateUser(user);
    }

    public void removeUser(int userId) {
        userService.deleteUser(userId);
    }
}
