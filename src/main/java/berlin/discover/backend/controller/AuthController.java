package berlin.discover.backend.controller;

import berlin.discover.backend.model.ERole;
import berlin.discover.backend.model.Role;
import berlin.discover.backend.model.User;
import berlin.discover.backend.payload.LoginRequest;
import berlin.discover.backend.payload.MessageResponse;
import berlin.discover.backend.payload.SignupRequest;
import berlin.discover.backend.service.UserService;
import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/auth")
@Slf4j
public class AuthController {

    @Autowired
    private UserService userService;



    @PostMapping("/register")
    @CrossOrigin
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws URISyntaxException {
        log.info("Will try to register user {}", signUpRequest);

        User user = new User();
        user.setName(signUpRequest.getName());
        user.setUsername(signUpRequest.getUsername());
        user.setPassword(signUpRequest.getPassword());
        user.setEmail(signUpRequest.getEmail());

        userService.saveUser(user);
        return ResponseEntity.ok().body(new MessageResponse("User registered successfully!"));
    }

    @PostMapping("/logout")
    public ResponseEntity<?> logoutUser() {
        return ResponseEntity.ok(new MessageResponse("User logged out successfully!"));
    }


    @GetMapping("/token/refresh")
    @CrossOrigin
    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String authorizationHeader = request.getHeader(AUTHORIZATION);
            log.debug("Authorization header = {}", authorizationHeader);

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
                try {
                    String refreshToken = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier verifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT = verifier.verify(refreshToken);
                    String username = decodedJWT.getSubject();
                    User user = userService.getUser(username);
                    String accessToken = JWT.create()
                            .withSubject(user.getUsername())
                            .withExpiresAt(new Date(System.currentTimeMillis() + 10*60*1000))
                            .withIssuer(request.getRequestURL().toString())
                            .withClaim("roles", user.getRoles().stream().map(Role::getName).map(ERole::name).collect(Collectors.toList()))
                            .sign(algorithm);
                    Map<String, String> tokens = new HashMap<>();
                    tokens.put("access_token", accessToken);
                    tokens.put("refresh_token", refreshToken);
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), tokens);
                } catch (Exception exception) {
                    log.error("Error during login: {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());

                    Map<String, String> error = new HashMap<>();
                    error.put("error_message", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), error);
                }
            } else {
                throw new RuntimeException(("Refresh token is missing"));
            }
    }
}
