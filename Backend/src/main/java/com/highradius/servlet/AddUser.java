package com.highradius.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.highradius.dao.UserDAO;
import com.highradius.dao.UserDaoImpl;
import com.highradius.model.POJO;

@WebServlet("/AddUser")
public class AddUser extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;

    public void init() {
        userDAO = new UserDaoImpl();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Set CORS headers
        response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
        response.setHeader("Access-Control-Allow-Methods", "POST");
        response.setHeader("Access-Control-Allow-Headers", "Content-Type");

        // Check if it's a preflight request
        if ("OPTIONS".equalsIgnoreCase(request.getMethod())) {
            // Preflight request is allowed, so just return the response with the CORS headers
            response.setStatus(HttpServletResponse.SC_OK);
            return;
        }

        try {
            // Parse the JSON from the request body
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
            JSONParser jsonParser = new JSONParser();
            JSONObject requestBody = (JSONObject) jsonParser.parse(reader);

            // Retrieve form values from the parsed JSON
            String customerOrderIdStr = (String) requestBody.get("CUSTOMER_ORDER_ID");
            System.out.println("Customer Order ID: " + customerOrderIdStr);

            String salesOrg = (String) requestBody.get("SALES_ORG");
            System.out.println("Sales Organization: " + salesOrg);

            String distributionChannel = (String) requestBody.get("DISTRIBUTION_CHANNEL");
            System.out.println("Distribution Channel: " + distributionChannel);

            String customerNumberStr = (String) requestBody.get("CUSTOMER_NUMBER");
            System.out.println("Customer Number: " + customerNumberStr);

            String companyCode = (String) requestBody.get("COMPANY_CODE");
            System.out.println("Company Code: " + companyCode);

            String orderCurrency = (String) requestBody.get("ORDER_CURRENCY");
            System.out.println("Order Currency: " + orderCurrency);

            String amountUsdStr = (String) requestBody.get("AMOUNT_IN_USD");
            System.out.println("Amount in USD: " + amountUsdStr);

            String orderCreationDateStr = (String) requestBody.get("ORDER_CREATION_DATE");
            System.out.println("Order Creation Date: " + orderCreationDateStr);

            int customerOrderID = Integer.parseInt(customerOrderIdStr);
            int customerNumber = Integer.parseInt(customerNumberStr);
            double amountUSD = Double.parseDouble(amountUsdStr);

            // Assuming the orderCreationDate is passed as a string in the format "dd-MM-yyyy"
            java.sql.Date orderCreationDate = null;
            try {
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                java.util.Date utilDate = dateFormat.parse(orderCreationDateStr);
                orderCreationDate = new java.sql.Date(utilDate.getTime());
            } catch (ParseException e) {
                e.printStackTrace();
            }

            // Create a new POJO object with the provided data
            POJO newUser = new POJO(customerOrderID, salesOrg, distributionChannel, customerNumber, companyCode,
                    orderCurrency, amountUSD, orderCreationDate);

            // Insert the new user
            userDAO.insertUser(newUser);

            // Send the response as JSON with the success message
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Data added successfully\"}");
        } catch (IOException | org.json.simple.parser.ParseException | NumberFormatException | SQLException e) {
            e.printStackTrace();
            // Send the response as JSON with the error message
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write("{\"message\": \"Failed to add data\"}");
        }
    }
}
