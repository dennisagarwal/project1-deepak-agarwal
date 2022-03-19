package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import javax.security.auth.login.FailedLoginException;

public class AuthenticationController implements Controller {

    private UserService userService;
    private Handler login = (ctx) -> {
        System.out.println("Login endpoint invoked");

        LoginDTO loginInfo = ctx.bodyAsClass(LoginDTO.class);
//        try {
            User user = userService.login(loginInfo.getUsername(), loginInfo.getPassword());
            ctx.json(user);
//        } catch (FailedLoginException e) {
//            ctx.json(e.getMessage());
//            ctx.status(400);
//        }

        System.out.println(loginInfo);
    };

    public AuthenticationController() {
        this.userService = new UserService();
    }

    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/login", login);
    }
}
