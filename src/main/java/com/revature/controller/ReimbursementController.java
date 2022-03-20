package com.revature.controller;

import com.revature.model.User;
import com.revature.service.JWTService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;

public class ReimbursementController implements Controller {

    private JWTService jwtService;

    public ReimbursementController(){
        this.jwtService = new JWTService();
    }
//    private Handler test = (ctx) -> {
//        User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("current_user");
//
//        System.out.println(currentlyLoggedInUser);
//        if(currentlyLoggedInUser != null){
//            ctx.json(currentlyLoggedInUser);
//        } else {
//            ctx.json("No user is logged in");
//        }
//
//    };

    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization");
                this.jwtService.validateJwt(jwt);
//        User currentlyLoggedInUser = (User) ctx.req.getSession().getAttribute("current_user");
//        if(currentlyLoggedInUser != null && currentlyLoggedInUser.getUserRole().equals("manager")){
//        ctx.json("You are authorized to access the code inside here");
//        //execute the code here if you are authorized as a manager
//        ctx.json("some stuff happening");
//        }else {
//            throw new UnauthorizedResponse("You are not authorized to use the account /endpoint");
////            ctx.status(401);
////            ctx.json("You are either not logged in or not authorized");
//        }
    };

    @Override
    public void mapEndpoints(Javalin app) {
//        app.get("/test", test);
        app.get("/reimbursements", getAllReimbursements);
    }
}
