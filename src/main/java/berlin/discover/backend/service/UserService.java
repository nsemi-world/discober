package berlin.discover.backend.service;

import berlin.discover.backend.model.Role;
import berlin.discover.backend.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {

    List<User> getUsers();
    User saveUser(User user);
    Role saveRole(Role role);
    void addRoleToUser(String username, String rolename);
}
