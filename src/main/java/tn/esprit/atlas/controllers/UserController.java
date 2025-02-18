package tn.esprit.atlas.controllers;

import tn.esprit.atlas.entities.User;
import tn.esprit.atlas.services.UserService;

import java.util.List;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserController {

    private UserService userService = new UserService();

    // ➤ Create User
    public void createUser(User user) throws SQLIntegrityConstraintViolationException {
        userService.addUser(user);
    }

    // ➤ List All Users
    public void listUsers() {
        List<User> users = UserService.getAllUsers();
        users.forEach(user -> System.out.println(user.getName() + " - " + user.getEmail()));
    }

    // ➤ Modify User
    public void modifyUser(User user) {
        userService.updateUser(user);
    }

    // ➤ Remove User
    public void removeUser(int userId) {
        userService.deleteUser(userId);
    }

    // ➤ Find User by Email or ID
    public void findUser(String email, int id) {
        userService.findUser(email, id);
    }

    // ➤ Sign In User and Return the User Object
    public User signInUser(String email, String password) {
        return userService.signInUser(email, password); // Returns the user if credentials are correct, null otherwise
    }
}