package api.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final String LOGIN_URL = "/api/v1/user/login";
    private static final String REGISTRATION_URL = "/api/v1/user/registration";
    private final UserDetailsService userDetailsService;
    private final BCryptPasswordEncoder cryptPasswordEncoder;

    @Autowired
    public SecurityConfig(
        UserDetailsService userDetailsService,
        BCryptPasswordEncoder cryptPasswordEncoder
    ) {
        this.userDetailsService = userDetailsService;
        this.cryptPasswordEncoder = cryptPasswordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable()
                .authorizeRequests()
                    .antMatchers(HttpMethod.POST, LOGIN_URL).permitAll()
                    .antMatchers(HttpMethod.POST, REGISTRATION_URL).permitAll()
                    .antMatchers("/api/v1/**").authenticated()
                .and()
                    .addFilterBefore(new CorsFilter(), JWTAuthorizationFilter.class)
                    .addFilter(new JWTAuthorizationFilter(authenticationManager()))
                    .sessionManagement()
                    .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    @Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(cryptPasswordEncoder);
    }

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers(
            "/", "/csrf", "/v2/api-docs", "/swagger-resources/configuration/ui",
            "/actuator/**", "/configuration/ui", "/swagger-resources/**",
            "/swagger-resources/configuration/security",
            "/configuration/security", "/swagger-ui.html**", "/webjars/**",
            "/api/v1/static/**", "/api/v1/dashboard/**", "/api/v1/datasource/**"
        );
    }
}
