package berlin.discover.backend.controller;

import berlin.discover.backend.payload.LoginRequest;
import berlin.discover.backend.payload.MessageResponse;
import berlin.discover.backend.payload.SignupRequest;
import berlin.discover.backend.repo.RoleRepository;
import berlin.discover.backend.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;


    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        System.out.println("****************************************************************");
        System.out.println(loginRequest);
        System.out.println("****************************************************************");
        return ResponseEntity.ok(new MessageResponse("User logged in successfully!"));

    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
    }
}
