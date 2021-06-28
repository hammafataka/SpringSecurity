package com.example.demo.auth;


import java.util.Optional;

public interface applicationUserDAO {
    Optional<applicationUser> selectApplicationUserByUserName(String userName);
}
