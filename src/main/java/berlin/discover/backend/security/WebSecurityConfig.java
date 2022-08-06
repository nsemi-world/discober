package berlin.discover.backend.security;

import berlin.discover.backend.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@RequiredArgsConstructor
public class WebSecurityConfig {

    private final UserService userService;

    @Bean
    public UserDetailsService userDetailsService() {
        return userService;
    }

    /*
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    */

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return null;
    }

    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return null;
    }

}
