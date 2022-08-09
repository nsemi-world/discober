package berlin.discover.backend.service;

import berlin.discover.backend.exception.DuplicateRoleException;
import berlin.discover.backend.exception.RoleNotFoundException;
import berlin.discover.backend.model.ERole;
import berlin.discover.backend.model.Role;
import berlin.discover.backend.model.User;
import berlin.discover.backend.repo.RoleRepository;
import berlin.discover.backend.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user  = userRepository.findByUsername(username).get();

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for(Role role: user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName().name()));
        }
        return new org.springframework.security.core.userdetails.User(username, user.getPassword(), authorities);
    }

    @Override
    public List<User> getUsers() {
        return userRepository.findAll();
    }

    @Override
    public User saveUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User userDb = userRepository.save(user);
        log.info("Successfully saved User {} in the database", userDb.getUsername());
        return userDb;
    }

    @Override
    public Role saveRole(Role role) {
        if(!roleRepository.findByName(role.getName()).isPresent()) {
            Role roleDb = roleRepository.save(role);
            log.info("Successfully saved role {} in the database", roleDb.getName().name());
            return roleDb;
        }
        else {
            log.error("Role {} already in the database", role.getName().name());
            throw new DuplicateRoleException("Role already in database: " + role.getName().name());
        }
    }

    @Override
    public void addRoleToUser(String username, String rolename) {
        User user = userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("Username not found: " + username)
        );

        Role role = roleRepository.findByName(ERole.valueOf(rolename)).orElseThrow(
                () -> new RoleNotFoundException("Role not found: " + rolename)
        );

        user.addRole(role);
        userRepository.save(user);
    }

    @Override
    public User getUser(String username) {
        return userRepository.findByUsername(username).orElseThrow(
                () -> new UsernameNotFoundException("User not found: " + username)
        );
    }

}
