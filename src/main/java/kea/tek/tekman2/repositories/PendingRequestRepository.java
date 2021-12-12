package kea.tek.tekman2.repositories;

import kea.tek.tekman2.databaseConnection.JdbcConnection;
import kea.tek.tekman2.models.ForeignUser;
import kea.tek.tekman2.models.Request;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PendingRequestRepository {

    @Autowired
    JdbcConnection jdbcConnection;

    /*
    public List<Request> findAllPendingRequestsByDestination(int destinationId){
        String query = "SELECT * FROM pending_requests " +
                "WHERE destination_user = ?";
        RowMapper<Request> rowMapper = new BeanPropertyRowMapper<>(Request.class);
        return jdbcTemplate.query(query, rowMapper);
    }
     */

    public ArrayList<Request> findAllPendingRequestsByDestinationId(int destinationId){
        String query = "SELECT " +
                    "pr.id, " +
                    "'Add' AS request_type, " +
                    "su.email AS source_email, " +
                    "sh.name AS source_host, " +
                    "du.email AS destination_email, " +
                    "dh.name AS destination_name " +
                "FROM pending_requests pr " +
                "JOIN users su ON pr.source_user = su.id " +
                "JOIN users du ON pr.destination_user = du.id " +
                "JOIN hosts sh ON su.host_id = sh.id " +
                "JOIN hosts dh ON du.host_id = dh.id " +
                "WHERE destination_user = ?;";
        PreparedStatement preparedStatement;
        ArrayList<Request> requests = new ArrayList<>();
        try{
            preparedStatement = jdbcConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, destinationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while(resultSet.next()){
                Request request = new Request(
                        resultSet.getInt("id"),
                        resultSet.getString("request_type"),
                        new ForeignUser(
                            resultSet.getString("source_email"),
                            resultSet.getString("source_host")
                        ),
                        new ForeignUser(
                            resultSet.getString("destination_email"),
                            resultSet.getString("destination_host")
                        )
                );
                requests.add(request);
            }
        } catch (SQLException sqlerr){
            System.out.println("Error in request finding: " + sqlerr.getMessage());
        }
        return requests;
    }

    /*
    public Request findPendingRequestBySourceAndDestination(int sourceId, int destinationId){
        String query = "SELECT * FROM pending_requests " +
                "WHERE source_user = ? AND destination_user = ?";
        RowMapper<Request> rowMapper = new BeanPropertyRowMapper<>(Request.class);
        return jdbcTemplate.queryForObject(query, rowMapper, sourceId, destinationId);
    }
     */

    public Request findPendingRequestBySourceIdAndDestinationId(int sourceId, int destinationId){
        String query = "SELECT " +
                    "pr.id, " +
                    "'Add' AS request_type, " +
                    "su.email AS source_email, " +
                    "sh.name AS source_host, " +
                    "du.email AS destination_email, " +
                    "dh.name AS destination_name " +
                "FROM pending_requests pr " +
                "JOIN users su ON pr.source_user = su.id " +
                "JOIN users du ON pr.destination_user = du.id " +
                "JOIN hosts sh ON su.host_id = sh.id " +
                "JOIN hosts dh ON du.host_id = dh.id " +
                "WHERE source_user = ? AND destination_user = ?;";
        PreparedStatement preparedStatement;
        Request request = null;
        try{
            preparedStatement = jdbcConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, sourceId);
            preparedStatement.setInt(2, destinationId);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                request = new Request(
                        resultSet.getInt("id"),
                        resultSet.getString("request_type"),
                        new ForeignUser(
                                resultSet.getString("source_email"),
                                resultSet.getString("source_host")
                        ),
                        new ForeignUser(
                                resultSet.getString("destination_email"),
                                resultSet.getString("destination_host")
                        )
                );
            }
        } catch (SQLException sqlerr){
            System.out.println("Error in request finding: " + sqlerr.getMessage());
        }
        return request;
    }

    /*
    public void createPendingRequest(int sourceId, int destinationId){
        String query = "INSERT INTO pending_requests (source_user, destination_user) " +
                "VALUES (?, ?)";
        jdbcTemplate.update(query, sourceId, destinationId);
    }
     */

    public void savePendingRequest(int sourceId, int destinationId){
        String query = "INSERT INTO pending_requests (source_user, destination_user) " +
                "VALUES (?, ?)";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = jdbcConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, sourceId);
            preparedStatement.setInt(2, destinationId);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlerr){
            System.out.println("Error in inserts: " + sqlerr.getMessage());
        }
    }

    /*
    public void removePendingRequest(int sourceId, int destinationId){
        String query = "DELETE FROM pending_requests " +
                "WHERE source_user = ? AND destination_user = ?";
        jdbcTemplate.update(query, sourceId, destinationId);
    }
     */

    public void deletePendingRequest(int sourceId, int destinationId){
        String query = "DELETE FROM pending_requests " +
                "WHERE source_user = ? AND destination_user = ?";
        PreparedStatement preparedStatement;
        try{
            preparedStatement = jdbcConnection.getConnection().prepareStatement(query);
            preparedStatement.setInt(1, sourceId);
            preparedStatement.setInt(2, destinationId);
            preparedStatement.executeUpdate();
        } catch (SQLException sqlerr){
            System.out.println("Error in delete: " + sqlerr.getMessage());
        }
    }
}
