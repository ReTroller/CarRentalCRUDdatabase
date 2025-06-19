package app.controller;

import app.db.UserAuthenticationDAO;

public class UserAuthenticalController {

    private final UserAuthenticationDAO userDAO;

    public UserAuthenticalController() {
        this.userDAO = new UserAuthenticationDAO();
    }


    public String checkUser(String login, String password) {
        if (login == null || password == null || login.isBlank() || password.isBlank()) {
            return null;
        }
        return userDAO.checkUser(login.trim(), password.trim());
    }


}
