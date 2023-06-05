package org.ibs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.core.JdbcTemplate;
/*import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;*/
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.util.List;
import java.util.Objects;

@SpringBootApplication
@EnableJpaRepositories(basePackages="org.ibs.data")
@EnableTransactionManagement
@EntityScan(basePackages="org.ibs.domain")
/*@EnableGlobalMethodSecurity(jsr250Enabled=true)*/
public class IBSApplication
{
/*    private final String url = "jdbc:postgresql://localhost/dvdrental";
    private final String user = "postgres";
    private final String password = "<add your password>";





    *//**
     *
     * @author postgresqltutorial.com
     *//*
    public class App{*/

        private final String url = "145.90.88.155:6000";
        private final String user = "457";
        private final String password = "<System99>";

    public static void main(String[] args) {
        SpringApplication.run(IBSApplication.class, args);
    }
        /**
         * Connect to the PostgreSQL database
         *
         * @return a Connection object
         */
        public Connection connect() {
            Connection conn = null;
            try {
                conn = DriverManager.getConnection(url, user, password);
                System.out.println("Connected to the PostgreSQL server successfully.");
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }

            return conn;
        }

    public void run(String... args) throws Exception {
        String sql = "INSERT INTO public.Fysiotherapeut " +
                "VALUES (" + "'10', 'Sjaak', 'test@gmail.com', '2004-11-09', 'Nederland', 'Utrecht', 'Man', '24515132'" + ")";

        JdbcTemplate jdbcTemplate = new JdbcTemplate();
        int rows = jdbcTemplate.update(sql);
        if (rows > 0) {
            System.out.println("A new row has been inserted.");
        }
    }

        /**
         * @param args the command line arguments
         */

    }


    /*public static void main( String[] args ) throws IOException {*/
