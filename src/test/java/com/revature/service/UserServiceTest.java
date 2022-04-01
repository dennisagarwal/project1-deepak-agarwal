package com.revature.service;

import com.revature.UserService;
import com.revature.dao.UserDao;
import com.revature.model.User;
import org.junit.jupiter.api.Test;

import javax.security.auth.login.FailedLoginException;
import java.sql.SQLException;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserServiceTest {

    @Test
    public void testLogin() throws FailedLoginException, SQLException {
        UserDao mockedObject = mock(UserDao.class);

        User fakeUser = new User(1, "bill_smith", "bill","smith" ,"bill.gmail.com","employee");
        when(mockedObject.getUserByUsernameAndPassword("abc","password")).thenReturn(fakeUser);
        UserService userService = new UserService(mockedObject);
        User user = userService.login("abc","password");
        System.out.println(user);
    }
}
