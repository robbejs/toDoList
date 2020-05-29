package SO.toDoList.model.service;
import SO.toDoList.Role;
import SO.toDoList.model.dto.CreateUserDTO;
import SO.toDoList.model.dto.TaskDTO;
import SO.toDoList.model.dto.UserDTO;
import SO.toDoList.model.entity.User;
import SO.toDoList.model.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
	private final UserRepository repository;
	private final PasswordEncoder passwordEncoder;

	public UserServiceImpl(UserRepository repository, PasswordEncoder passwordEncoder) {
		this.repository = repository;
		this.passwordEncoder = passwordEncoder;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("User does not exist");
		}
		return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(user.getRole().name())));
	}

	@Override
	public UserDTO createUser(CreateUserDTO userDTO) {
		User user = new User();
		user.setUsername(userDTO.getUsername());
		user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
		user.setRole(userDTO.getUsername().endsWith("a") ? Role.ADMIN : Role.CLIENT);
		user = repository.save(user);
		return convert(user);
	}

	@Override
	public UserDTO convert(User user) {
		UserDTO dto = new UserDTO();
		dto.setId(user.getId());
		dto.setUsername(user.getUsername());
		dto.setRole(user.getRole());
		return dto;
	}

	@Override
	public List<UserDTO> getUsers() {
		return repository.findAll().stream().map(h -> {
			UserDTO userDTO = new UserDTO();
			userDTO.setUsername(h.getUsername());
			userDTO.setRole(h.getRole());
			userDTO.setId(h.getId());
			return userDTO;
		}).collect(Collectors.toList());
	}


}
