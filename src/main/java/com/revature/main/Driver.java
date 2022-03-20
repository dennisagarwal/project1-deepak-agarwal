package com.revature.main;

import com.revature.controller.ReimbursementController;
import com.revature.controller.AuthenticationController;
import com.revature.controller.Controller;
import com.revature.controller.ExceptionController;
import io.javalin.Javalin;

public class Driver {

    public static void main(String[] args)  {
//        UserDao dao = new UserDao();
//         try{
//             System.out.println(dao.getUserByUsernameAndPassword("ryan_jordan","password123"));
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//        try {
//            ConnectionUtility.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Javalin app = Javalin.create();
        map(app, new AuthenticationController(), new ExceptionController(), new ReimbursementController());
          app.start(8081);

        }

        public static void map(Javalin app, Controller... controllers){
            for(Controller c : controllers){
                c.mapEndpoints(app);
            }
        }
}
