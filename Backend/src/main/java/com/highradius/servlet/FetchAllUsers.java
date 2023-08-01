//package com.highradius.servlet;
//
//import java.io.IOException;
//import java.util.List;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import com.google.gson.Gson;
//import com.highradius.dao.UserDAO;
//import com.highradius.dao.UserDaoImpl;
//import com.highradius.model.POJO;
//
//@WebServlet("/FetchAllUsers")
//public class FetchAllUsers extends HttpServlet {
//    private static final long serialVersionUID = 1L;
//    private UserDAO userDAO;
//    private Gson gson;
//
//    public void init() {
//        userDAO = new UserDaoImpl();
//        gson = new Gson();
//    }
//
//    protected void doGet(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//        try {
//            // Enable CORS - Set the necessary response headers
//            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
//            response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
//            response.setHeader("Access-Control-Allow-Headers", "Content-Type");
//
//            // Check if it's a preflight request (OPTIONS)
//            if (request.getMethod().equals("OPTIONS")) {
//                response.setStatus(HttpServletResponse.SC_OK);
//                return;
//            }
//
//            // Fetch all users from the database
//            List<POJO> userList = userDAO.selectAllUsers(0);
//            gson = new Gson();
//            // Convert the userList to JSON string
//            String json = gson.toJson(userList);
//
//            // Set the content type to application/json
//            response.setContentType("application/json");
//            response.setCharacterEncoding("UTF-8");
//
//            // Write the JSON string as the response
//            response.getWriter().write(json);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//}


package com.highradius.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.highradius.dao.UserDAO;
import com.highradius.dao.UserDaoImpl;
import com.highradius.model.POJO;

@WebServlet("/FetchAllUsers")
public class FetchAllUsers extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDAO userDAO;
    private Gson gson;

    public void init() {
        userDAO = new UserDaoImpl();
        gson = new Gson();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // Enable CORS - Set the necessary response headers
            response.setHeader("Access-Control-Allow-Origin", "http://localhost:3000");
            response.setHeader("Access-Control-Allow-Methods", "GET, OPTIONS");
            response.setHeader("Access-Control-Allow-Headers", "Content-Type");

            // Check if it's a preflight request (OPTIONS)
            if (request.getMethod().equals("OPTIONS")) {
                response.setStatus(HttpServletResponse.SC_OK);
                return;
            }

            // Fetch all users from the database
            List<POJO> userList = userDAO.selectAllUsers();
            gson = new Gson();
           // Convert the userList to JSON string
            String json = gson.toJson(userList);

            // Set the content type to application/json
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");

            // Write the JSON string as the response
            response.getWriter().write(json);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

