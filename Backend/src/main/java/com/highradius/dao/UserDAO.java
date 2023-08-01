package com.highradius.dao;

import java.sql.SQLException;
import java.util.List;

import com.highradius.model.POJO;

public interface UserDAO {

    void insertUser(POJO user) throws SQLException;

    List<POJO> selectAllUsers();

    boolean deleteUser(int customerOrderIds) throws SQLException;

    boolean updateUser(POJO user) throws SQLException;


}