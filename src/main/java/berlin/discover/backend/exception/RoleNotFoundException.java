package berlin.discover.backend.exception;

import org.springframework.security.core.AuthenticationException;

public class RoleNotFoundException extends AuthenticationException {
    public RoleNotFoundException(String msg) {
        super(msg);
    }
}
