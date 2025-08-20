package com.pahanaedu;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Main application class for Pahana Edu Bookshop Management System
 * 
 * This Spring Boot application provides a comprehensive web-based solution
 * for managing customer accounts, inventory, and billing operations.
 * 
 * Features:
 * - User authentication and authorization
 * - Customer account management
 * - Item/book inventory management
 * - Billing and transaction processing
 * - RESTful API endpoints
 * - Web-based user interface
 * 
 * @author Manus AI
 * @version 1.0.0
 */
@SpringBootApplication
@EnableJpaRepositories
public class PahanaEduApplication {

    public static void main(String[] args) {
        System.out.println("Starting Pahana Edu Bookshop Management System...");
        SpringApplication.run(PahanaEduApplication.class, args);
        System.out.println("Application started successfully!");
        System.out.println("API Documentation: http://localhost:8080/api/");
        System.out.println("Health Check: http://localhost:8080/api/health");
    }
}

