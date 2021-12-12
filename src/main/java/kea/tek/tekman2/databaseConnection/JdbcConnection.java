package kea.tek.tekman2.databaseConnection;

import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Service
public class JdbcConnection {

    private Connection connection;

    public JdbcConnection(){
        System.out.println("Establishing database connection");
        connect();
    }

    public Connection getConnection() {
        return connection;
    }

    private void connect(){
        String url = "jdbc:mysql://localhost:3306/tek-man-2?serverTimezone=UTC";
        try{
            connection = DriverManager.getConnection(url, "mads", "password");
            System.out.println("Database connection successful");
        } catch(SQLException sqlerr){
            System.out.println("Fejl i connect: " + sqlerr.getMessage());
        }
    }
}
