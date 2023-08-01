package com.highradius.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.highradius.dao.UserDAO;
import com.highradius.dao.UserDaoImpl;

@WebServlet("/DeleteUser")
public class DeleteUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String customerOrderIDStr = request.getParameter("CUSTOMER_ORDER_ID");

        if (customerOrderIDStr != null && !customerOrderIDStr.isEmpty()) {
            int customerOrderID = Integer.parseInt(customerOrderIDStr);

            try {
                boolean deleted = userDAO.deleteUser(customerOrderID);
                if (deleted) {
                    response.getWriter().println("User deleted successfully.");
                } else {
                    response.getWriter().println("Failed to delete user.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                response.getWriter().println("An error occurred while deleting the user.");
            }
        } else {
            response.getWriter().println("Invalid or missing CUSTOMER_ORDER_ID parameter.");
        }
    }
}