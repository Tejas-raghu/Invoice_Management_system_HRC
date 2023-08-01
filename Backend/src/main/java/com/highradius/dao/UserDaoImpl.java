package com.highradius.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.text.SimpleDateFormat;
import java.text.ParseException;


import com.highradius.connection.dbconnection;
import com.highradius.model.POJO;

public class UserDaoImpl implements UserDAO {

    private static final String INSERT_USER_SQL = "INSERT INTO h2h_oap (CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE, ORDER_CURRENCY, AMOUNT_IN_USD, ORDER_CREATION_DATE) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SELECT_ALL_USERS_SQL = "SELECT * FROM h2h_oap";
    private static final String DELETE_USER_SQL = "DELETE FROM h2h_oap WHERE CUSTOMER_ORDER_ID = ?";
    private static final String UPDATE_USER_SQL = "UPDATE h2h_oap SET SALES_ORG = ?, DISTRIBUTION_CHANNEL = ?, CUSTOMER_NUMBER = ?, COMPANY_CODE = ?, ORDER_CURRENCY = ?, AMOUNT_IN_USD = ?, ORDER_CREATION_DATE = ? WHERE CUSTOMER_ORDER_ID = ?";

    public void insertUser(POJO user) throws SQLException {
    	System.out.println("check1....");
        try (Connection connection = dbconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USER_SQL)) {
        	System.out.println("check2....");
            preparedStatement.setInt(1, user.getCustomerOrderID());
            preparedStatement.setString(2, user.getSalesOrg());
            preparedStatement.setString(3, user.getDistributionChannel());
            preparedStatement.setInt(4, user.getCustomerNumber());
            preparedStatement.setString(5, user.getCompanyCode());
            preparedStatement.setString(6, user.getOrderCurrency());
            preparedStatement.setDouble(7, user.getAmountUSD());
            preparedStatement.setDate(8, new java.sql.Date(user.getOrderCreationDate().getTime()));

            preparedStatement.executeUpdate();
        }
    }

    public List<POJO> selectAllUsers() {
        List<POJO> users = new ArrayList<>();

        // Modify the SELECT query to retrieve top 'n' users
//        String selectQuery = "SELECT * FROM h2h_oap ORDER BY CUSTOMER_ORDER_ID LIMIT ?";
//        System.out.println("check....");
        try (Connection connection = dbconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS_SQL)) {
//            preparedStatement.setInt(1, n);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
//                System.out.println("check2....");

                int CUSTOMER_ORDER_ID = resultSet.getInt("CUSTOMER_ORDER_ID");
                String SALES_ORG = resultSet.getString("SALES_ORG");
                String DISTRIBUTION_CHANNEL = resultSet.getString("DISTRIBUTION_CHANNEL");
                int CUSTOMER_NUMBER = resultSet.getInt("CUSTOMER_NUMBER");
                String COMPANY_CODE = resultSet.getString("COMPANY_CODE");
                String ORDER_CURRENCY = resultSet.getString("ORDER_CURRENCY");
                double AMOUNT_IN_USD = resultSet.getDouble("AMOUNT_IN_USD");
                String ORDER_CREATION_DATE_STRING = resultSet.getString("ORDER_CREATION_DATE");

                java.sql.Date ORDER_CREATION_DATE = null;
                try {
                    SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                    java.util.Date utilDate = dateFormat.parse(ORDER_CREATION_DATE_STRING);
                    ORDER_CREATION_DATE = new java.sql.Date(utilDate.getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                POJO user = new POJO(CUSTOMER_ORDER_ID, SALES_ORG, DISTRIBUTION_CHANNEL, CUSTOMER_NUMBER, COMPANY_CODE, ORDER_CURRENCY, AMOUNT_IN_USD, ORDER_CREATION_DATE);
                users.add(user);
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return users;
    }


    public boolean deleteUser(int CUSTOMER_ORDER_ID) throws SQLException {
        boolean rowDeleted = false;

        try (Connection connection = dbconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_USER_SQL)) {
            preparedStatement.setInt(1, CUSTOMER_ORDER_ID);
            rowDeleted = preparedStatement.executeUpdate() > 0;
        }

        return rowDeleted;
    }

    public boolean updateUser(POJO user) throws SQLException {
        boolean rowUpdated = false;

        try (Connection connection = dbconnection.getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_USER_SQL)) {
            preparedStatement.setString(1, user.getSalesOrg());
            preparedStatement.setString(2, user.getDistributionChannel());
            preparedStatement.setInt(3, user.getCustomerNumber());
            preparedStatement.setString(4, user.getCompanyCode());
            preparedStatement.setString(5, user.getOrderCurrency());
            preparedStatement.setDouble(6, user.getAmountUSD());
            preparedStatement.setDate(7, new java.sql.Date(user.getOrderCreationDate().getTime()));
            preparedStatement.setInt(8, user.getCustomerOrderID());

            rowUpdated = preparedStatement.executeUpdate() > 0;
        }

        return rowUpdated;
    }

	public List<POJO> selectAllUsers1(int n) {
		// TODO Auto-generated method stub
		return null;
	}
}