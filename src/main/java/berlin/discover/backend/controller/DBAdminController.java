package berlin.discover.backend.controller;

import berlin.discover.backend.model.User;
import berlin.discover.backend.service.DBAdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(path = "api/admin/db/")
public class DBAdminController {

    @Autowired
    private DBAdminServiceImpl dbAdminService;

    @GetMapping
    @RequestMapping(value = "seed")
    @CrossOrigin
    public ResponseEntity<List<User>> seedDB() {
        this.dbAdminService.seedRoles();
        this.dbAdminService.seedUsers();
        return ResponseEntity.ok(dbAdminService.getAllUsers());
    }
}
