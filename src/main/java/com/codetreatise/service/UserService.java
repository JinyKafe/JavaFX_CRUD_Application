package com.codetreatise.service;

import com.codetreatise.model.User;

public interface UserService
{

    boolean authenticate(String email, String password);

    User findByEmail(String email);

}
