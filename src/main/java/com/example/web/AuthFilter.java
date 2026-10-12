package com.example.web;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter("*.do")
public class AuthFilter implements Filter {
    @Override
    public void doFilter(ServletRequest rq, ServletResponse rs, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) rq;
        HttpServletResponse resp = (HttpServletResponse) rs;
        HttpSession s = req.getSession(false);
        boolean loggedIn = s != null && s.getAttribute("user") != null;
        boolean open = req.getServletPath().equals("/login.do");
        if (loggedIn || open) {
            chain.doFilter(rq, rs);
        } else {
            resp.sendRedirect(req.getContextPath() + "/login.do");
        }
    }
}
