package berlin.discover.backend.service;

import berlin.discover.backend.model.ERole;
import berlin.discover.backend.model.Role;
import berlin.discover.backend.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DBAdminServiceImpl implements DBAdminService {

    private final UserService userService;


    @Override
    public void seedRoles() {
        userService.saveRole(new Role(ERole.ROLE_ADMIN));
        userService.saveRole(new Role(ERole.ROLE_MODERATOR));
        userService.saveRole(new Role(ERole.ROLE_USER));
    }

    @Override
    public void seedUsers() {
        User zuaCaldeira = new User("zuacaldeira", "zuacaldeira@gmail.com", "Password:ZuaCaldeira", "Alexandre Zua Caldeira");
        userService.saveUser(zuaCaldeira);
        userService.addRoleToUser("zuacaldeira", ERole.ROLE_ADMIN.name());

        User elisaCSa = new User("elisacsa", "elisacsa@gmail.com", "Password:ElisaCaldeira", "Elisa Sá Zua Caldeira");
        userService.saveUser(elisaCSa);
        userService.addRoleToUser("elisacsa", ERole.ROLE_MODERATOR.name());

        User miaJaneZua = new User("miajanezua", "miajanezua@gmail.com", "Password:MiaJaneZua", "Mia Jane Zua");
        userService.saveUser(miaJaneZua);
        userService.addRoleToUser("miajanezua", ERole.ROLE_USER.name());
    }


    public List<User> getUsers() {
        return this.userService.getUsers();
    }
}
