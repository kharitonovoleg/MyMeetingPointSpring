package by.kharitonov.controller;

import by.kharitonov.model.User;
import by.kharitonov.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.IsInstanceOf.instanceOf;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(MockitoJUnitRunner.class)
@ContextConfiguration(locations = {"file:web/WEB-INF/mvc-dispatcher-servlet.xml", "file:web/WEB-INF/applicationContext.xml"})
@WebAppConfiguration
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void init() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/view/");
        viewResolver.setSuffix(".jsp");

        MockitoAnnotations.initMocks(this.userService);
        mockMvc = MockMvcBuilders.standaloneSetup(userController)
                .setViewResolvers(viewResolver)
                .build();
    }

    @Test
    public void listUser() throws Exception {
        List<User> list = new ArrayList<>();
        list.add(new User());
        list.add(new User());
        list.add(new User());
        when(userService.listUser()).thenReturn(list);
        mockMvc.perform(get("/user"))
                .andExpect(status().isOk())
                .andExpect(model().attribute("listUser", list));
    }

    @Test
    public void addUser() throws Exception {
        User user = new User();
        user.setUsername("test");
        user.setFirstName("Tom");
        user.setSecondName("Tom2");
        user.setEmail("1@mail.ru");
//        when(userService.addUser(Matchers.<User>any())).thenReturn(user);
//        verifyZeroInteractions(userService);
        mockMvc.perform(post("/user/add"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"))
                .andExpect(model().attribute("user", instanceOf(User.class)));
    }

    @Test
    public void removeUser() throws Exception {
        mockMvc.perform(get("/user/remove/1"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/user"));
        Mockito.verify(userService, times(1)).removeUser(1);
    }

    @Test
    public void updateUser() throws Exception {
        mockMvc.perform(get("/user/update/1"))
        .andExpect(status().isOk())
        .andExpect(view().name("user"));
    }

    @Test
    public void userData() throws Exception {
        mockMvc.perform(get("/usersdata/1"))
                .andExpect(status().isOk())
                .andExpect(view().name("usersdata"));
    }

}