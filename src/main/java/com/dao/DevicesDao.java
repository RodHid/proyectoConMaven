/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.connection.DatabaseConnection;
import com.persistence.entities.Devices;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class DevicesDao {
    

 private final DatabaseConnection databaseConnection;

    public DevicesDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }
    // Métodos CRUD

    public void createDevices(Devices device) {
        String sql = "INSERT INTO devices (device_name, description, adquisition_date, device_type) VALUES (?, ?, ?, ?)";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, device.getDeviceName());
            preparedStatement.setString(2, device.getDescription());
            preparedStatement.setDate(3, (Date) device.getAdquisitionDate());
            preparedStatement.setString(4, device.getDeviceType());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public void softDeleteDevice(String deviceId) {
        String sql = "UPDATE devices SET is_active = 0, deleted_at = CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, deviceId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public List<Devices> getAllDevices() {
        String sql = "SELECT * FROM devices";
        List<Devices> devices = new ArrayList<>();
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                devices.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return devices;
    }

    public Devices getDeviceById(String deviceId) {
        String sql = "SELECT * FROM devices WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, deviceId);
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

    public void updateDevice(Devices device) {
        String sql = "UPDATE devices SET device_name=?, description=?, adquisition_date=?, device_type=?, updated_at=CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, device.getDeviceName());
            preparedStatement.setString(2, device.getDescription());
            preparedStatement.setDate(3, (Date) device.getAdquisitionDate());
            preparedStatement.setString(4, device.getDeviceType());
            preparedStatement.setString(5, device.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    private Devices mapResultSet(ResultSet resultSet) throws SQLException {
        Devices device = new Devices();
        device.setId(resultSet.getString("id"));
        device.setDeviceName(resultSet.getString("device_name"));
        device.setDescription(resultSet.getString("description"));
        device.setAdquisitionDate(resultSet.getTimestamp("adquisition_date"));
        device.setDeviceType(resultSet.getString("device_type"));
        device.setIsActive(resultSet.getBoolean("is_active"));
        device.setCreatedAt(resultSet.getDate("created_at"));
        device.setUpdatedAt(resultSet.getDate("updated_at"));
        device.setDeletedAt(resultSet.getDate("deleted_at") );
        return device;
    }
}

    

