package pl.devdioniz.controller;

import pl.devdioniz.dao.UserDao;
import pl.devdioniz.model.User;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class ControllerServlet extends HttpServlet {

    Map<String, String> views;
    private final String SUFFIX = ".jsp";
    private final String PREFIX = "/WEB-INF/view/";

    private Connection conn;
    UserDao userDao;

    @Override
    public void init(ServletConfig config) throws ServletException {
        // Create views
        views = new HashMap<>();
        views.put("home", "index");
        views.put("error", "error");
        views.put("insertUser", "index");
        views.put("deleteUser", "index");


        // Initialize database connection
        try {
            InitialContext initialCtx = new InitialContext();
            DataSource ds = (DataSource) initialCtx.lookup("java:/comp/env/jdbc/webshop");

            try {
                conn = ds.getConnection();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        } catch (NamingException e) {
            e.printStackTrace();
        }

        // Initialize DAO
        userDao = new UserDao(conn);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try (PrintWriter out = response.getWriter()) {

            out.println("POST METHOD");

            String action = request.getParameter("action");

            if (action == null || !views.containsKey(action)) {
                request.setAttribute("message", "View not found.");
                request.getRequestDispatcher(PREFIX + "error" + SUFFIX).forward(request, response);
                return;
            }

            String viewPage = views.get(action);
            System.out.println("forwarding user to: " + PREFIX + viewPage + SUFFIX);

            if ("insertUser".equals(action)) {
                String email = request.getParameter("email");
                String password = request.getParameter("password");

                User user = new User(email, password);

                try {
                    // if user does not validate, dispatch to error page and return
                    if (!user.validate()) {
                        request.setAttribute("message", user.getMessage());
                        request.getRequestDispatcher(PREFIX + "error" + SUFFIX).forward(request, response);
                        return;
                    }

                    // if user validated, add it to database
                    userDao.addUser(user);

                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Unable to add new user to database.");
                    request.getRequestDispatcher(PREFIX + "error" + SUFFIX).forward(request, response);
                    return;
                }
            } else if ("deleteUser".equals(action)) {

                String stringID = request.getParameter("id");
                Long id = Long.parseLong(stringID);

                try {
                    userDao.deleteUserById(id);
                } catch (SQLException e) {
                    e.printStackTrace();
                    request.setAttribute("message", "Cannot delete user with ID: " + id);
                    request.getRequestDispatcher(PREFIX + "error" + SUFFIX).forward(request, response);
                    return;
                }

            }

            request.getRequestDispatcher(PREFIX + viewPage + SUFFIX).forward(request, response);


        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        doPost(request, response);
    }
}
