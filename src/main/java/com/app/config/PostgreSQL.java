package com.app.config;

import lombok.extern.slf4j.Slf4j;

import java.sql.Connection;
import java.sql.DriverManager;

@Slf4j
public class PostgreSQL {
    private static final String url = "jdbc:postgresql://localhost:5432/crud";
    private static final String user = "postgres";
    private static final String password = "1qazse4";

    public static Connection getConnection() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
        } catch(ClassNotFoundException e) {
            System.err.println("Where is your PostgreSQL JDBC Driver? "
                    + "Include in your library path!");
            log.error(e.toString());
        }

        try {
            con = DriverManager.getConnection(url, user, password);
        } catch(Exception e) {
            log.error(e.toString());
        }
        return con;
    }
}
