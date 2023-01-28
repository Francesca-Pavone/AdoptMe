package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.model.UserModel;

public interface UserDAO {
    UserModel retrieveUserById(int userId) throws Exception;

    UserModel retrieveUserByEmail(String email) throws Exception;

}
