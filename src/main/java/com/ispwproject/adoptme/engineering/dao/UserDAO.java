package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.engineering.exception.NotFoundException;
import com.ispwproject.adoptme.model.UserModel;

public interface UserDAO {
    UserModel retrieveUserById(int userId) throws NotFoundException;

    UserModel retrieveUserByEmail(String email) throws NotFoundException;

}
