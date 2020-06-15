package security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import security.filter.AuthenticationFilter;
import security.filter.JwtAccessFilter;
import security.filter.JwtRefreshFilter;
import security.service.ITokenService;
import security.service.IUserSecurityService;
import security.service.impl.MasterProvider;

import java.util.List;
import java.util.Map;

/**
 * @author Sorokin Georgy
 */
@Configuration
@ComponentScan("security")
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService service;

    private final JwtAccessFilter accessFilter;

    private final AuthenticationSuccessHandler successHandler;

    private final AuthenticationFailureHandler failureHandler;

    private final ITokenService tokenService;

    private final IUserSecurityService userService;

    /**
     * Constructor.
     * Instantiates Security config.
     * Params used to provide security configuration by Spring Framework.
     */
    public SecurityConfig(UserDetailsService service,
                          JwtAccessFilter accessFilter,
                          AuthenticationSuccessHandler successHandler,
                          AuthenticationFailureHandler failureHandler,
                          ITokenService tokenService,
                          IUserSecurityService userService) {
        super();
        this.service = service;
        this.accessFilter = accessFilter;
        this.successHandler = successHandler;
        this.failureHandler = failureHandler;
        this.tokenService = tokenService;
        this.userService = userService;
    }

    /**
     * return {@link PasswordEncoder} bean, used to create
     * and authenticate first user in the system.
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


    @Override
    protected void configure(final AuthenticationManagerBuilder auth) {
        final var dao = new MasterProvider(userService);
        dao.setPasswordEncoder(passwordEncoder());
        dao.setUserDetailsService(service);
        auth.authenticationProvider(dao);
        auth.eraseCredentials(false);
    }

    @Override
    protected void configure(final HttpSecurity http) throws Exception {
        final var refresh = new JwtRefreshFilter(authenticationManager(), successHandler, tokenService, userService);
        http.csrf().disable()
                .httpBasic().disable()
                .cors().disable()
                .sessionManagement().disable()
                .authorizeRequests()
                .antMatchers(Url.LOGIN).not().authenticated()
                .antMatchers(Url.REFRESH).not().authenticated()
                .antMatchers(Url.PUBLIC.concat("/**")).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilter(new AuthenticationFilter(successHandler, failureHandler, authenticationManager()))
                .addFilterBefore(accessFilter, AuthenticationFilter.class)
                .addFilterBefore(refresh, JwtAccessFilter.class);
    }

    private CorsConfigurationSource corsConfigurationSource() {
        final var source = new UrlBasedCorsConfigurationSource();
        final var corsConfig = new CorsConfiguration();
        corsConfig.setAllowedMethods(List.of("GET", "POST", "DELETE", "PUT", "OPTIONS"));
        corsConfig.addAllowedOrigin("*");
        corsConfig.setAllowedHeaders(List.of("*"));
        source.setPathMatcher(new AntPathMatcher());
        source.setCorsConfigurations(Map.of("/**", corsConfig));
        return source;
    }
}
