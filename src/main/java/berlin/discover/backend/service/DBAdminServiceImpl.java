package berlin.discover.backend.service;

import berlin.discover.backend.model.ERole;
import berlin.discover.backend.model.Role;
import berlin.discover.backend.model.User;
import berlin.discover.backend.repo.RoleRepository;
import berlin.discover.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DBAdminServiceImpl implements DBAdminService {

    @Autowired private UserRepository userRepository;
    @Autowired private RoleRepository roleRepository;


    @Override
    public void seedRoles() {
        Role roleUser = new Role(ERole.ROLE_USER);
        Role roleModerator = new Role(ERole.ROLE_MODERATOR);
        Role roleAdmin = new Role(ERole.ROLE_ADMIN);
        this.roleRepository.save(roleUser);
        this.roleRepository.save(roleModerator);
        this.roleRepository.save(roleAdmin);
    }

    @Override
    public void seedUsers() {
        this.addUser("Zua Caldeira", "zuacaldeira@gmail.com", "Password:ZuaCaldeira", ERole.ROLE_ADMIN);
        this.addUser("Elisa Sá", "elisacsa@gmail.com", "Password:ElisaSa", ERole.ROLE_MODERATOR);
    }

    private void addUser(String name, String email, String password, ERole eRole) {
        Role role = roleRepository.findByName(eRole).get();
        User user = new User(name, email, password);
        user.addRole(role);
        userRepository.save(user);
    }

    public List<User> getAllUsers() {
        return this.userRepository.findAll();
    }
}
