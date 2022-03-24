package com.revature.controller;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.GetReimbursementDTO;
import com.revature.dto.GetReimbursementPureDTO;
import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.service.JWTService;
import com.revature.service.ReimbursementService;
import io.javalin.Javalin;
import io.javalin.http.Handler;
import io.javalin.http.UnauthorizedResponse;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

import java.util.List;

public class ReimbursementController implements Controller {

    private JWTService jwtService;
    private ReimbursementService reimbursementService;

    public ReimbursementController(){
        this.jwtService = new JWTService();
        this.reimbursementService = new ReimbursementService();
    }


    private Handler getAllReimbursements = (ctx) -> {
        String jwt = ctx.header("Authorization").split(" ")[1];

                Jws<Claims> token = this.jwtService.parseJwt(jwt);

                if(!token.getBody().get("user_role").equals("manager")){
                    throw new UnauthorizedResponse("You must be a manager to access ths endpoint");
                }
                List<GetReimbursementDTO> reimbursements = this.reimbursementService.getAllReimbursements();
                 ctx.json(reimbursements);
        System.out.println(reimbursements);
//                ctx.json("Successfully accessed GET /reimbursements");

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
        AddReimbursementDTO dto = ctx.bodyAsClass(AddReimbursementDTO.class);
        GetReimbursementPureDTO getDto =this.reimbursementService.addReimbursements(id,dto);
        ctx.json(getDto);
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
