package stationery.store.config.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.security.oauth2.resource.OAuth2ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;

import java.security.SecureRandom;



@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final String[] PUBLIC_ENDPOINTS = {
            "/user/signIn",
            "/user/login",
            "/user/{type}/signUp",
            "/h2-console/**",
            "/product/**",
            "/category/**"
    };

    private final String[] ADMIN_ENDPOINTS = {
            "/user/all",
            "/user/{type}",
            "/delete/{id}"
    };

    private UserDetailsService userDetailsService;


    public SecurityConfig(@Qualifier("myUserDetailsService") UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }



    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {// @formatter:off
        auth.authenticationProvider(daoAuthenticationProvider());
    } // @formatter:on

    @Override
    protected void configure(HttpSecurity http) throws Exception {// @formatter:off
        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers(PUBLIC_ENDPOINTS).permitAll()
                .antMatchers(ADMIN_ENDPOINTS).hasAuthority("ADMIN")
                .anyRequest().authenticated()

                .and()
                .oauth2ResourceServer()
                .jwt();

    } // @formatter:on

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(10, new SecureRandom());
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider(){
        final DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService);
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

    @Bean
    JwtDecoder jwtDecoder(OAuth2ResourceServerProperties properties) {
        NimbusJwtDecoder jwtDecoder = NimbusJwtDecoder.withJwkSetUri(properties.getJwt().getJwkSetUri()).build();
        jwtDecoder.setClaimSetConverter(new OrganizationSubClaimAdapter());
        return jwtDecoder;
    }

}
