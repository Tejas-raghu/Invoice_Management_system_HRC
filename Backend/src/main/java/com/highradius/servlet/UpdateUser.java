package com.highradius.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class UpdateUser
 */
import com.highradius.dao.UserDAO;
import com.highradius.dao.UserDaoImpl;
import com.highradius.model.POJO;

@WebServlet("/UpdateUser")
public class UpdateUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int customerOrderID = Integer.parseInt(request.getParameter("CUSTOMER_ORDER_ID"));
        String salesOrg = request.getParameter("SALES_ORG");
        String distributionChannel = request.getParameter("DISTRIBUTION_CHANNEL");
        int customerNumber = Integer.parseInt(request.getParameter("CUSTOMER_NUMBER"));
        String companyCode = request.getParameter("COMPANY_CODE");
        String orderCurrency = request.getParameter("ORDER_CURRENCY");
        double amountUSD = Double.parseDouble(request.getParameter("AMOUNT_IN_USD"));
        // Assuming the orderCreationDate is passed as a string in the format "dd-MM-yyyy"
        String orderCreationDateStr = request.getParameter("ORDER_CREATION_DATE");

        java.sql.Date orderCreationDate = null;
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = dateFormat.parse(orderCreationDateStr);
            orderCreationDate = new java.sql.Date(utilDate.getTime());
        } catch (ParseException e) {
            e.printStackTrace();
        }

        try {
            // Create a new POJO object with the updated data
            POJO updatedUser = new POJO(customerOrderID, salesOrg, distributionChannel, customerNumber, companyCode,
                    orderCurrency, amountUSD, orderCreationDate);

            // Update the user
            userDAO.updateUser(updatedUser);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}