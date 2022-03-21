package com.revature.controller;

import com.revature.model.User;
import com.revature.service.JWTService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public class ReimbursementController implements Controller {

    private JWTService jwtService;

    public ReimbursementController(){
        this.jwtService = new JWTService();
    }


    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];

                Jws<Claims> token = this.jwtService.parseJwt(jwt);

                if(!token.getBody().get("user_role").equals("manager")){
                    throw new UnauthorizedResponse("You must be a manager to access ths endpoint");
                }

                ctx.json("Successfully accessed GET /reimbursements");

    };

    //Authorization logic
    //1. user role should be employee (logged in)
    //2. user id should match who is actually logged in
    private Handler addReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];

        Jws<Claims> token = this.jwtService.parseJwt(jwt);

        if(!token.getBody().get("user_role").equals("employee")){
            throw new UnauthorizedResponse(("You must be an employee to access this endpoint"));
        }
        String userId = ctx.pathParam("user_id");
        int id = Integer.parseInt(userId);

        if(!token.getBody().get("user_id").equals(id)){
            throw new UnauthorizedResponse("You cannot add a reimbursement for other than yourself");
        };

    };

//    private Handler getAllReimbursements=(ctx)->{
//
//    };


    @Override
    public void mapEndpoints(Javalin app) {
//        app.get("/test", test);
        app.get("/reimbursements", getAllReimbursements);
        app.post("/users/{user_id}/reimbursements",addReimbursements);
    }
}
