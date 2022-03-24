package com.revature.service;

import com.revature.dao.ReimbursementDao;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.GetReimbursementDTO;
import com.revature.dto.GetReimbursementPureDTO;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementPure;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ReimbursementService {

    private ReimbursementDao reimbursementDao;

    public ReimbursementService(){
      this.reimbursementDao = new ReimbursementDao();
    }

    public ReimbursementService(ReimbursementDao mockDao){
        this.reimbursementDao = mockDao;
    }


    public GetReimbursementPureDTO addReimbursements(int authorId,AddReimbursementDTO dto) throws SQLException{
        ReimbursementPure reimbursementAdded = this.reimbursementDao.addReimbursements(authorId,dto);

        return new GetReimbursementPureDTO(reimbursementAdded.getId(),reimbursementAdded.getAmount(),reimbursementAdded.getSubmittedDate(),
              reimbursementAdded.getStatus(),reimbursementAdded.getType(), reimbursementAdded.getAuthor());
    }


    public List<GetReimbursementDTO> getAllReimbursements() throws SQLException{
        List<Reimbursement> reimbursements =  this.reimbursementDao.getAllReimbursements();

        List<GetReimbursementDTO> reimbursementDTOs = new ArrayList<>();

        for (Reimbursement reimbursement : reimbursements){

            reimbursementDTOs.add(new GetReimbursementDTO(reimbursement.getId(), reimbursement.getAmount(), reimbursement.getSubmittedDate(),
                    reimbursement.getResolvedDate(),reimbursement.getStatus(), reimbursement.getType(),reimbursement.getAuthor().getId(),
                    reimbursement.getAuthor().getUsername(),
                    reimbursement.getAuthor().getFirstName(),reimbursement.getAuthor().getLastName(),reimbursement.getAuthor().getEmailId(),
                    reimbursement.getAuthor().getUserRole(),
                    reimbursement.getResolver().getId(),reimbursement.getResolver().getUsername(),reimbursement.getResolver().getFirstName(),
                    reimbursement.getResolver().getLastName(),reimbursement.getResolver().getEmailId(), reimbursement.getResolver().getUserRole()
                    ));
        }

        return reimbursementDTOs;
    }


//    public void addReimbursements() {
//    }
}
