package com.example.web;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppBootstrap implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent e) {
        e.getServletContext().setAttribute("appName", "Servlets JSP Example");
        e.getServletContext().log("Example app started.");
    }
}
