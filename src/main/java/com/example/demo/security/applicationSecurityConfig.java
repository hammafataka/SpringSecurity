package com.example.demo.security;

import com.example.demo.auth.applicationUserService;
import com.example.demo.jwt.jwtTokenVerifier;
import com.example.demo.jwt.jwtUserNameAndPasswordAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.crypto.SecretKey;

import static com.example.demo.security.applicationUserRoles.*;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class applicationSecurityConfig extends WebSecurityConfigurerAdapter {

    private final PasswordEncoder passwordEncoder;
    private final applicationUserService applicationUserService;
    private final com.example.demo.jwt.jwtConfig jwtConfig;
    private final SecretKey secretKey;
    @Autowired
    public applicationSecurityConfig(PasswordEncoder passwordEncoder,
                                     com.example.demo.auth.applicationUserService applicationUserService,
                                     com.example.demo.jwt.jwtConfig jwtConfig, SecretKey secretKey) {
        this.passwordEncoder = passwordEncoder;
        this.applicationUserService = applicationUserService;
        this.jwtConfig = jwtConfig;
        this.secretKey = secretKey;
    }


    @Override
    protected void configure(HttpSecurity httpSecurity){
        try{
            httpSecurity
                    .csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilter(new jwtUserNameAndPasswordAuthenticationFilter(authenticationManager(), jwtConfig, secretKey))
                    .addFilterAfter(new jwtTokenVerifier(jwtConfig, secretKey),jwtUserNameAndPasswordAuthenticationFilter.class)
                    .authorizeRequests()
                    .antMatchers("/","index","/css/*","/js/*").permitAll()
                    .antMatchers("/api/**").hasRole(student.name())
                        .anyRequest()
                        .authenticated();

        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(applicationUserService);
        return provider;
    }
}
