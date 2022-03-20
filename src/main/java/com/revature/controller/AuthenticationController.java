package com.revature.controller;

import com.revature.dto.LoginDTO;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.UserService;
import io.javalin.Javalin;
import io.javalin.http.Handler;

import javax.security.auth.login.FailedLoginException;
import javax.servlet.http.HttpSession;

public class AuthenticationController implements Controller {

    private UserService userService;
    private JWTService jwtService;
    private Handler login = (ctx) -> {
        System.out.println("Login endpoint invoked");

        LoginDTO loginInfo = ctx.bodyAsClass(LoginDTO.class);
//        try {

        User user = userService.login(loginInfo.getUsername(), loginInfo.getPassword());

        //if failedLoginException did not occur then we move on the subsequent line of code
        //code down here wil not run if FailedLoginException happened from .login (...,...) method

//        HttpSession session = ctx.req.getSession(); // ctx.req is an httpServeletRequest object

        //Behind the scene, Javalin utilizes the Java EE Services to handle http requests. We are making use of the
        //Servlet API to create an HTTPSession

//        session.setAttribute("current_user", user); // set the user object to this session
        String jwt = this.jwtService.createJWT(user);
        ctx.json(jwt);
//        } catch (FailedLoginException e) {
//            ctx.json(e.getMessage());
//            ctx.status(400);
//        }

        System.out.println(loginInfo);
    };

    public AuthenticationController() {
        this.userService = new UserService();
        this.jwtService = new JWTService();
    }
//    private Handler logout = (ctx) -> {
//        HttpSession session = ctx.req.getSession();
//
//        session.invalidate(); //invalidate the http session on backend
//    };

    @Override
    public void mapEndpoints(Javalin app) {
        app.post("/login", login);
//        app.post("/logout", logout);

    }
}
