package com.ispwproject.adoptme.engineering.dao;

import com.ispwproject.adoptme.model.UserModel;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface UserDAO {
    UserModel retrieveUserById(int userId) throws Exception;

    UserModel retrieveUserByEmail(String email) throws Exception;

}
