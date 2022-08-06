package berlin.discover.backend.service;

import berlin.discover.backend.model.ERole;
import berlin.discover.backend.model.User;

import java.util.List;

public interface DBAdminService {
    void seedRoles();
    void seedUsers();
    List<User> getUsers();
}
