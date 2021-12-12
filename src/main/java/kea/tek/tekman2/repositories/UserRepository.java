package kea.tek.tekman2.repositories;

import kea.tek.tekman2.databaseConnection.JdbcConnection;
import kea.tek.tekman2.models.ForeignUser;
import kea.tek.tekman2.models.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserRepository {

    @Autowired
    JdbcConnection jdbcConnection;

    /*
    public User findLocalUserByEmail(String email){
        String query = "SELECT id, email FROM users " +
                "WHERE email = ? AND host_id = 1";
        RowMapper<User> rowMapper = new BeanPropertyRowMapper<>(User.class);
        return jdbcTemplate.queryForObject(query, rowMapper, email);
    }
     */

    public User findUserByEmail(String email){
        String query = "SELECT id, email FROM users " +
                "WHERE email = ? AND host_id = 1";
        PreparedStatement preparedStatement;
        User user = null;
        try{
            preparedStatement = jdbcConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new User(resultSet.getInt("id"), resultSet.getString("email"));
            }
        } catch (SQLException sqlerr){
            System.out.println("Error in finding user: " + sqlerr.getMessage());
        }
        return user;
    }

    /*
    public ForeignUser findForeignUserByEmailAndHost(String email, String hostName){
        String query = "SELECT u.id, u.email, h.name AS host FROM users u " +
                "JOIN hosts h ON u.host_id = h.id " +
                "WHERE email = ? and h.name = ?";
        RowMapper<ForeignUser> rowMapper = new BeanPropertyRowMapper<>(ForeignUser.class);
        return jdbcTemplate.queryForObject(query, rowMapper, email, hostName);
    }
     */

    public ForeignUser findForeignUserByEmailAndHostName(String email, String hostName){
        String query = "SELECT u.id, u.email, h.name AS host FROM users u " +
                "JOIN hosts h ON u.host_id = h.id " +
                "WHERE email = ? and h.name = ?";
        PreparedStatement preparedStatement;
        ForeignUser user = null;
        try{
            preparedStatement = jdbcConnection.getConnection().prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, hostName);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                user = new ForeignUser(
                        resultSet.getInt("id"),
                        resultSet.getString("email"),
                        resultSet.getString("host"));
            }
        } catch (SQLException sqlerr){
            System.out.println("Error in finding user: " + sqlerr.getMessage());
        }
        return user;
    }
}
