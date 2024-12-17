package com.example.proj.listener;

import com.example.proj.dao.AccountDao;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppStartupListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            AccountDao accountDAO = new AccountDao();
            // Check if the default admin exists, if not, create it
            accountDAO.createDefaultAdmin();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        // No cleanup required
    }
}
