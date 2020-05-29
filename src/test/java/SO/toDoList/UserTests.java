package SO.toDoList;
import SO.toDoList.model.dto.CreateUserDTO;
import SO.toDoList.model.dto.UserDTO;
import SO.toDoList.model.entity.User;
import SO.toDoList.model.service.UserService;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@TestMethodOrder(MethodOrderer.Alphanumeric.class)
public class UserTests {

    @Autowired
    private UserService userService;

    @Test
    public void test1CreateUser(){
        User user = new User();
        user.setUsername("test");
        user.setPassword("test");
        user.setRole(Role.ADMIN);
        user.setId((long)0);

        assertEquals("test", user.getUsername());
        assertEquals("test", user.getPassword());
        assertEquals(Role.ADMIN, user.getRole());
        assertEquals((long)0, user.getId());
    }

    @Test
    public void test2Test_Class_UserDTO(){
        UserDTO userDTO = new UserDTO();
        userDTO.setUsername("test");
        userDTO.setRole(Role.ADMIN);
        userDTO.setId((long)0);

        assertEquals("test", userDTO.getUsername());
        assertEquals(Role.ADMIN, userDTO.getRole());
        assertEquals((long)0, userDTO.getId());
    }

    @Test
    public void test3Test_Class_CreateUserDTO(){
        CreateUserDTO createUserDTO = new CreateUserDTO();
        createUserDTO.setUsername("test");
        createUserDTO.setPassword("test");

        assertEquals("test",createUserDTO.getUsername());
        assertEquals("test", createUserDTO.getPassword());
    }

    @Test
    public void test4Test_DateTimeFormatter() throws ParseException {
        LocalDateTime nu = LocalDateTime.now();
        String test = nu.toString();
        DateTimeFormatter dateFormatter = new DateTimeFormatter();
        LocalDateTime tijd = dateFormatter.parse(test, Locale.ENGLISH);

        assertEquals(nu.format(java.time.format.DateTimeFormatter.ISO_LOCAL_DATE_TIME), (dateFormatter.print(tijd,Locale.ENGLISH)));
    }

    @Test
    public void test5createUser_test_getUsers(){
        CreateUserDTO user = new CreateUserDTO();
        user.setUsername("testa");
        user.setPassword("test");

        userService.createUser(user);

        List<UserDTO> users = userService.getUsers();
        UserDTO testUser = users.get(0);

        assertEquals("testa", testUser.getUsername());
        assertEquals(Role.ADMIN, testUser.getRole());
        assertEquals((long) 1, testUser.getId());
    }

    @Test
    public void test6getUsers(){
        List<UserDTO> users = userService.getUsers();
        System.out.println(users);
        UserDTO testUser = users.get(0);
        System.out.println(testUser);

        assertNotNull(users);
        assertFalse(users.isEmpty());
        assertEquals(1, users.size());
        assertNotNull(testUser);
    }

    @Test
    public void test7loadUserByUsername(){
        UserDetails test = userService.loadUserByUsername("testa");

        UserDetails uitkomst = (new org.springframework.security.core.userdetails.User(test.getUsername(), test.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(test.getAuthorities().toString()))));

        assertEquals(userService.getUsers().get(0).getUsername(), test.getUsername());
        assertEquals(uitkomst, test);
        assertNotNull(test);

        Exception exception = assertThrows(UsernameNotFoundException.class, () -> {
            userService.loadUserByUsername("test");
        });

        String actualMessage = exception.getMessage();
        String expectedMessage = "User does not exist";

        assertTrue(actualMessage.contains(expectedMessage));
    }
}
