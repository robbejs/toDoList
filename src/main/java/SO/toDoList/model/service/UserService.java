package SO.toDoList.model.service;
import SO.toDoList.model.dto.CreateUserDTO;
import SO.toDoList.model.dto.UserDTO;
import SO.toDoList.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
	UserDTO createUser(CreateUserDTO userDTO);

	UserDetails loadUserByUsername(String username);

	UserDTO convert(User user);

	List<UserDTO> getUsers();
}
