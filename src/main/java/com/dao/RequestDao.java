/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.connection.DatabaseConnection;
import com.persistence.entities.Request;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class RequestDao {

    private final DatabaseConnection databaseConnection;

    public RequestDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    public void createRequest(Request request) {
        String sql = "INSERT INTO request (requested_by, loan_date, accepted_by) VALUES (?, ?, ?)";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //preparedStatement.setString(1, request.getRequestedBy());
            //preparedStatement.setTimestamp(2, request.getLoanDate());
            //preparedStatement.setString(3, request.getAcceptedBy());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }
    // Get request By id (case-insensitive search using LIKE)

    public List<Request> getRequestByUsername(String searchTerm) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Request> requestList = new ArrayList<>();

        try {
            connection = databaseConnection.connect();
            String sql = "SELECT * FROM request WHERE LOWER(id) LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchTerm.toLowerCase() + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Request request = mapResultSetRequest(resultSet);
                requestList.add(request);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return requestList;
    }

    public void softDeleteRequest(String requestId) {
        String sql = "UPDATE request SET deleted_at = CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, requestId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public List<Request> getAllRequests() {
        String sql = "SELECT * FROM request";
        List<Request> requests = new ArrayList<>();
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                requests.add(mapResultSetRequest(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return requests;
    }

    public Request getRequestById(String requestId) {
        String sql = "SELECT * FROM request WHERE id=?";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, requestId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSetRequest(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return null;
    }

    public void updateRequest(Request request) {
        String sql = "UPDATE request SET loan_date=?, accepted_by=?, updated_at=CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, (java.sql.Date) request.getLoanDate());
            preparedStatement.setString(2, request.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    private Request mapResultSetRequest(ResultSet resultSet) throws SQLException {
        Request request = new Request();
        request.setId(resultSet.getString("id"));
        request.setLoanDate(resultSet.getTimestamp("loan_date"));
        request.setCreatedAt(resultSet.getDate("created_at"));
        request.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        request.setDeletedAt(resultSet.getTimestamp("deleted_at"));
        return request;
    }
}
