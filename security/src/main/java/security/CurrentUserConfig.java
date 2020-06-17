package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.context.WebApplicationContext;
import security.model.AuthUser;

/**
 * @author Georgy Sorokin
 */
@Configuration
public class CurrentUserConfig {

    @Bean
    @Scope(value = WebApplicationContext.SCOPE_REQUEST, proxyMode = ScopedProxyMode.TARGET_CLASS)
    public AuthUser getCurrentUser() {
        final var principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if(principal == null){
            throw new IllegalStateException();
        }

        if(principal instanceof AuthUser){
            return (AuthUser)principal;
        }
        throw new IllegalStateException();
    }
}
