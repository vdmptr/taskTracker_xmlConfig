package vdm.controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.security.core.Authentication;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import vdm.entity.User;
import vdm.helpers.Role;
import vdm.repository.UserRepository;
import vdm.security.SecurityUser;
import vdm.service.UserService;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class InputControllerTest {

    @InjectMocks
    private InputController inputController= new InputController();

    @Mock
    private UserRepository userRepository;

    @Mock
    private User user;

    @Mock
    private Authentication authentication;

    @Mock
    private SecurityUser securityUser;

    @Before
    public void setUp() throws Exception {
        int userId = 1;
        when(securityUser.getUserId()).thenReturn(userId);
        when(user.getRole()).thenReturn(Role.ROLE_ADMIN,
                                        Role.ROLE_MANAGER,
                                        Role.ROLE_DEVELOPER);
        when(userRepository.findOne(userId))
                                        .thenReturn(user);
        when(authentication.getPrincipal()).thenReturn(securityUser);
    }

    @Test
    public void getPage() throws Exception {

        String resultPage = inputController.getPage(authentication);

        assertEquals(resultPage, "admin");

         resultPage = inputController.getPage(authentication);

        assertEquals(resultPage, "manager");

         resultPage = inputController.getPage(authentication);

        assertEquals(resultPage, "developer");

    }

}