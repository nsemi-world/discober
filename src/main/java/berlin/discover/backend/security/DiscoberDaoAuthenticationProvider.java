package berlin.discover.backend.security;

import berlin.discover.backend.service.UserService;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetailsService;

public class DiscoberDaoAuthenticationProvider extends DaoAuthenticationProvider {

    private UserService userService;

    @Override
    public void setUserDetailsService(UserDetailsService userDetailsService) {
        super.setUserDetailsService(userDetailsService);
        this.userService = (UserService) userDetailsService;
    }

    public UserService getUserService() {
        return userService;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }
}
