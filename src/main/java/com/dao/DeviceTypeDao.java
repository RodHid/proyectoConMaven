/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

import com.connection.DatabaseConnection;
import com.persistence.entities.DeviceTypes;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


/**
 * @ClassName: DeviceTypeDao
 * @Package: com.dao
 * @Date: Nov 19, 2023
 * @CopyRigth: Manuel Molina
 * @author Manolo
 */
public class DeviceTypeDao {
    private final DatabaseConnection databaseConnection;

    public DeviceTypeDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }


    // Métodos CRUD

    public void createDeviceType(DeviceTypes deviceType) {
        String sql = "INSERT INTO device_types (type_name, type_description, created_by) VALUES (?, ?, ?)";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, deviceType.getTypeName());
            preparedStatement.setString(2, deviceType.getTypeDescription());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public void softDeleteDeviceType(String deviceTypeId) {
        String sql = "UPDATE device_types SET is_active = 0, deleted_at = CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, deviceTypeId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public List<DeviceTypes> getAllDeviceTypes() {
        String sql = "SELECT * FROM device_types";
        List<DeviceTypes> deviceTypes = new ArrayList<>();
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                deviceTypes.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return deviceTypes;
    }

    public void updateDeviceType(DeviceTypes deviceType) {
        String sql = "UPDATE device_types SET type_name=?, type_description=?, updated_at=CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, deviceType.getTypeName());
            preparedStatement.setString(2, deviceType.getTypeDescription());
            preparedStatement.setString(3, deviceType.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    private DeviceTypes mapResultSet(ResultSet resultSet) throws SQLException {
        DeviceTypes deviceType = new DeviceTypes();
        deviceType.setId(resultSet.getString("id"));
        deviceType.setTypeName(resultSet.getString("type_name"));
        deviceType.setTypeDescription(resultSet.getString("type_description"));
        deviceType.setIsActive(resultSet.getBoolean("is_active"));
        deviceType.setCreatedAt(resultSet.getDate("created_at"));
        deviceType.setUpdatedAt(resultSet.getDate("updated_at") );
        deviceType.setDeletedAt(resultSet.getTimestamp("deleted_at") );
        return deviceType;
    }
}

    

