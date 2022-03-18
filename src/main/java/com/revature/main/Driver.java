package com.revature.main;

import com.revature.dao.UserDao;
import com.revature.utility.ConnectionUtility;

import java.sql.SQLException;

public class Driver {

    public static void main(String[] args)  {
        UserDao dao = new UserDao();
         try{
             System.out.println(dao.getUserByUsernameAndPassword("ryan_jordan","password123"));
         } catch (SQLException e) {
             e.printStackTrace();
         }
//        try {
//            ConnectionUtility.getConnection();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
    }
}
