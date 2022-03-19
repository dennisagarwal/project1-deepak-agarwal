package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

public class AuthenticationController implements Controller {

    private UserService userService;

    public AuthenticationController(){
        this.userService= new UserService();
    }

    private Handler login = (ctx) -> {
        System.out.println("Login endpoint invoked");

        LoginDTO loginInfo = ctx.bodyAsClass(LoginDTO.class);
       User user = userService.login(loginInfo.getUsername(), loginInfo.getPassword());
       ctx.json(user);
        System.out.println(loginInfo);
    };

    @Override
    public void mapEndpoints(Javalin app) {
    app.post("/login", login);
    }
}
