/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;
import com.connection.DatabaseConnection;
import com.persistence.entities.RequestedDevices;
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
public class RequestedDevicesDao {

 private final DatabaseConnection databaseConnection;

    public RequestedDevicesDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    public void createRequestedDevices(RequestedDevices requestedDevices) {
        String sql = "INSERT INTO requested_devices (request_id, devices_id, loan_reason) VALUES (?, ?)";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //preparedStatement.setString(1, requestedDevices.getRequestId());
            //preparedStatement.setString(2, requestedDevices.getDevicesId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    

    public void softDeleteRequestedDevices(String requestedDevicesId) {
        String sql = "UPDATE requested_devices SET deleted_at = CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, requestedDevicesId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public List<RequestedDevices> getAllRequestedDevices() {
        String sql = "SELECT * FROM requested_devices";
        List<RequestedDevices> requestedDevicesList = new ArrayList<>();
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                requestedDevicesList.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return requestedDevicesList;
    }

    public RequestedDevices getRequestedDevicesById(String requestedDevicesId) {
        String sql = "SELECT * FROM requested_devices WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, requestedDevicesId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return mapResultSet(resultSet);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return null;
    }

   public void updateRequestedDevices(RequestedDevices requestedDevices) {
    String sql = "UPDATE requested_devices SET request_id=?, devices_id=?, loan_reason=?, updated_at=CURRENT_TIMESTAMP() WHERE id=?";
    try (Connection connection = databaseConnection.connect();
         PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
        //preparedStatement.setString(1, requestedDevices.getRequestId());
        //preparedStatement.setString(2, requestedDevices.getDevicesId());
        preparedStatement.setString(3, requestedDevices.getLoanReason());
        preparedStatement.setString(4, requestedDevices.getId());

        preparedStatement.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace(); // Manejo de la excepción según tus requisitos.
    } finally {
        // Cerrar la conexión en un bloque finally separado para garantizar su cierre.
        databaseConnection.disconnect();
    }
}

    private RequestedDevices mapResultSet(ResultSet resultSet) throws SQLException {
        RequestedDevices requestedDevices = new RequestedDevices();
        requestedDevices.setId(resultSet.getString("id"));
        //requestedDevices.setRequestId(resultSet.getString("request_id"));
        //requestedDevices.setDevicesId(resultSet.getString("devices_id"));
        requestedDevices.setLoanReason(resultSet.getString("loan_reason"));
        requestedDevices.setCreatedAt(resultSet.getDate("created_at"));
        requestedDevices.setUpdatedAt(resultSet.getDate("updated_at") );
        requestedDevices.setDeletedAt(resultSet.getDate("deleted_at"));
        return requestedDevices;
    }
}

