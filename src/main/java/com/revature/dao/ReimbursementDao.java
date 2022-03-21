package com.revature.dao;

import com.revature.model.Reimbursement;
import com.revature.model.User;
import com.revature.utility.ConnectionUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementDao {

    public List<Reimbursement> getAllReimbursements() throws SQLException {
        try (Connection con = ConnectionUtility.getConnection()) {
            List<Reimbursement> reimbursements = new ArrayList<>();
          String sql = "Select reimbursements.id as rs_id, reimbursements.reimbursements_amount as rs_amount , " +
                  " reimbursements.reimbursements_submitted as rs_submitted, reimbursements.reimbursements_resolved as rs_resolved, " +
                  " employee_user.username as employee_name,employee_user.password as employee_password, " +
                  " manager_user.username as manager_name,manager_user.password as manager_password, " +
                  " rs.status ,rt.type   " +
                  "from reimbursements " +
                  "inner join users employee_user " +
                  "on employee_user.id = reimbursements.reimbursements_author " +
                  "inner join users manager_user  " +
                  "on manager_user.id = reimbursements.reimbursements_resolver   " +
                  "inner join reimbursement_status rs " +
                  "on rs.id = reimbursements.reimbursements_status_id " +
                  "inner join reimbursement_type rt  " +
                  "on rt.id = reimbursements.reimbursements_type_id  ";

          PreparedStatement pstmt = con.prepareStatement(sql);

          ResultSet rs = pstmt.executeQuery();
          while (rs.next()){
              int rsId = rs.getInt("rs_id");
              int rsAmount = rs.getInt("rs_amount");
              String rsSubmitted = rs.getString("rs_submitted");
              String rsResolved = rs.getString("rs_resolved");

              //user employee
              String eName = rs.getString("employee_user");
              String ePassword = rs.getString("employee_password");
              String eRole = "employee";

              User employee = new User(eName, ePassword, eRole);

              //user manager
               String mName = rs.getString("manager_user") ;
              String mPassword = rs.getString("manager_password");
              String mRole = "manager";

              User manager = new User(mName, mPassword, mRole);

              Reimbursement r = new Reimbursement(rsId, rsAmount, rsSubmitted,rsResolved);


          };
        }

    }
}


