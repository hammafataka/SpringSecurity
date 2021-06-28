package com.example.demo.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class applicationUserService implements UserDetailsService {
    private final applicationUserDAO applicationUserDao;

    @Autowired
    public applicationUserService(@Qualifier("fake") applicationUserDAO applicationUserDao) {
        this.applicationUserDao = applicationUserDao;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return applicationUserDao.selectApplicationUserByUserName(username)
                .orElseThrow(
                        ()->new UsernameNotFoundException(String.format("UserName %s not found",username
                            )));
    }
}
