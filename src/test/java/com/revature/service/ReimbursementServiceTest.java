package com.revature.service;

import com.revature.ReimbursementService;
import com.revature.dao.ReimbursementDao;
import com.revature.dto.AddReimbursementDTO;
import com.revature.dto.GetReimbursementDTO;
import com.revature.dto.GetReimbursementPureDTO;
import com.revature.exception.ImageNotFoundException;
import com.revature.exception.InvalidImageException;
import com.revature.model.Reimbursement;
import com.revature.model.ReimbursementPure;
import com.revature.model.User;
//import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ReimbursementServiceTest {

    @Test
    public void testGetAllReimbursements() throws SQLException {
        ReimbursementDao mockObject = mock(ReimbursementDao.class);

        List<Reimbursement> mockReimbursements = new ArrayList<>();
        mockReimbursements.add(new Reimbursement(1,800,"2022-Jan-12","approve","travel",new User() ,new User(),"abc","manager"));
        mockReimbursements.add(new Reimbursement(1,800,"2022-Jan-12","approve","travel",new User() ,new User(),"abc","manager"));
        mockReimbursements.add(new Reimbursement(1,800,"2022-Jan-12","approve","travel",new User() ,new User(),"abc","manager"));

    when(mockObject.getAllReimbursements()).thenReturn(mockReimbursements);

        ReimbursementService reimbursementService= new ReimbursementService(mockObject);
        List<GetReimbursementDTO> reimbursements=reimbursementService.getAllReimbursements();
        System.out.println(reimbursements);

    }

    @Test
    public void testGetAllReimbursementsByUserId() throws SQLException {

        ReimbursementDao mockObject = mock(ReimbursementDao.class);

        List<Reimbursement> mockReimbursements = new ArrayList<>();
        mockReimbursements.add(new Reimbursement(1,800,"2022-Jan-12","approve","travel",new User() ,new User(),"abc","manager"));
        mockReimbursements.add(new Reimbursement(2,800,"2022-Jan-12","approve","travel",new User() ,new User(),"abc","manager"));
        mockReimbursements.add(new Reimbursement(3,800,"2022-Jan-12","approve","travel",new User() ,new User(),"abc","manager"));

        when(mockObject.getAllReimbursementsByUserId(2)).thenReturn(mockReimbursements);

        ReimbursementService reimbursementService= new ReimbursementService(mockObject);
        List<GetReimbursementDTO> reimbursements=reimbursementService.getAllReimbursementsByUserId(2);
        System.out.println(reimbursements);

    }

    @Test
    public void testGetReimbursementsImage() throws SQLException, ImageNotFoundException, FileNotFoundException {

        ReimbursementDao mockObject = mock(ReimbursementDao.class);

        InputStream testImage = new FileInputStream("C:\\Users\\denni\\OneDrive\\Pictures\\images.jpg");
        when(mockObject.getReimbursementImage(2)).thenReturn(testImage);
       InputStream reimbursementService= new ReimbursementService(mockObject).getReimbursementImage("2");
        System.out.println(reimbursementService);

    }

    @Test
    public void testAddReimbursements() throws SQLException, ImageNotFoundException, IOException, InvalidImageException {

        ReimbursementDao mockObject = mock(ReimbursementDao.class);

        InputStream testImage = new FileInputStream("C:\\Users\\denni\\OneDrive\\Pictures\\images.jpg");
        AddReimbursementDTO addReimbursementDTO = new AddReimbursementDTO(800,"submitDate","resolveDate","description",1,1,testImage);
//        GetReimbursementPureDTO getReimbursementPureDTO = new GetReimbursementPureDTO(1,1,"String",1,1,new User());
//        AddReimbursementDTO addReimbursementDTO = new AddReimbursementDTO();

//        GetReimbursementPureDTO expected = new GetReimbursementPureDTO(1,1,"String",1,1,new User());
//        getReimbursementPureDTO.setStatus(1);

        when(mockObject.addReimbursements(1,addReimbursementDTO)).thenReturn(new ReimbursementPure());
        GetReimbursementPureDTO getReimbursementPureDTO= new ReimbursementService(mockObject).addReimbursements(1,addReimbursementDTO);
        System.out.println(getReimbursementPureDTO);

    }

    @Test
    public void testResolveReimbursement() throws SQLException {

        ReimbursementDao mockObject = mock(ReimbursementDao.class);
        when(mockObject.resolveReimbursement(1,1,1)).thenReturn(new Reimbursement(1,1,"abc","abc","abc",new User(),new User(),"abc","abc"));
        ReimbursementService reimbursementService=new ReimbursementService();
        System.out.println(reimbursementService);
    }
}
