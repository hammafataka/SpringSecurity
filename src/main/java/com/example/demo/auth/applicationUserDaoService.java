package com.example.demo.auth;

import com.google.common.collect.Lists;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.example.demo.security.applicationUserRoles.*;

@Repository("fake")
public class applicationUserDaoService implements applicationUserDAO{
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public applicationUserDaoService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Optional<applicationUser> selectApplicationUserByUserName(String userName) {
        return getApplicationUser().stream().filter(applicationUser -> userName.equals(applicationUser.getUsername()
        )).findFirst();
    }
    private List<applicationUser> getApplicationUser(){
        List<applicationUser> applicationUsers= Lists.newArrayList(
                new applicationUser(
                        "anasmith",
                        passwordEncoder.encode("password"),
                        student
                        .getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new applicationUser(
                        "linda",
                        passwordEncoder.encode("password"),
                        admin.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                ),
                new applicationUser(
                        "tom",
                        passwordEncoder.encode("password"),
                        adminTraine.getGrantedAuthorities(),
                        true,
                        true,
                        true,
                        true
                )
        );
        return applicationUsers;
    }

}
