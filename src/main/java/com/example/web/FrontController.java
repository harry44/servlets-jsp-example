package com.example.web;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import java.util.Map;

/** Front controller: *.do -> service -> JSP view under WEB-INF. */
@WebServlet("*.do")
public class FrontController extends HttpServlet {

    private static final Map<String, String> USERS = Map.of(
            "admin@groovygrails.in", "admin123",
            "user@groovygrails.in", "user123");

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String action = req.getServletPath();
        if ("/users.do".equals(action)) {
            req.setAttribute("users", List.of(
                    Map.of("name", "Priya Sharma", "email", "priya@example.com"),
                    Map.of("name", "Ravi Kumar", "email", "ravi@example.com")));
            req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);
        } else if ("/login.do".equals(action)) {
            req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
        } else if ("/logout.do".equals(action)) {
            HttpSession s = req.getSession(false);
            if (s != null) s.invalidate();
            resp.sendRedirect(req.getContextPath() + "/login.do");
        } else {
            resp.sendError(404);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        if ("/login.do".equals(req.getServletPath())) {
            String email = req.getParameter("email");
            String password = req.getParameter("password");
            if (email != null && password != null
                    && password.equals(USERS.get(email))) {
                req.getSession().setAttribute("user", email);
                resp.sendRedirect(req.getContextPath() + "/users.do");
            } else {
                req.setAttribute("error", "Invalid email or password.");
                req.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(req, resp);
            }
        } else {
            resp.sendError(404);
        }
    }
}
