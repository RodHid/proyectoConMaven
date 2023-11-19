/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.dao;

/**
 * @ClassName: UsersDao
 * @Package: com.dao
 * @Date: Nov 18, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */
import com.connection.DatabaseConnection;
import com.persistence.entities.Users;
import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class UsersDao {

    private Users serviceLocator;

    private final DatabaseConnection databaseConnection;

    public UsersDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // Create
    public void createUser(Users user) {
        String sql = "INSERT INTO users (username, user_password, given_name, family_name) VALUES (?, ?, ?, ?)";

        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getGivenName());
            preparedStatement.setString(4, user.getFamilyName());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    // Read
    public Users getUserById(String userId) {
        Users user = null;
        String sql = "SELECT * FROM users WHERE id = ? AND deleted_at IS NULL";

        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                user = mapResultSetToUser(resultSet);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }

        return user;
    }

    public List<Users> getAllUsers() {
        List<Users> usersList = new ArrayList<>();
        String sql = "SELECT * FROM users WHERE deleted_at IS NULL and is_active IS TRUE";

        try (Connection connection = databaseConnection.connect();
             Statement statement = connection.createStatement()) {

            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Users user = mapResultSetToUser(resultSet);
                usersList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }

        return usersList;
    }
    
    //Get User By Username
    public Users getUserByUsername(String username) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = databaseConnection.connect();
            String sql = "SELECT * FROM users WHERE username = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                Users user = new Users();
                user.setId(resultSet.getString("id"));
                user.setUsername(resultSet.getString("username"));
                user.setUserPassword(resultSet.getString("user_password"));
                user.setGivenName(resultSet.getString("given_name"));
                user.setFamilyName(resultSet.getString("family_name"));
                user.setIsActive(resultSet.getBoolean("is_active"));
                user.setCreatedAt(resultSet.getTimestamp("created_at"));
                user.setUpdatedAt(resultSet.getTimestamp("updated_at"));
                user.setDeletedAt(resultSet.getTimestamp("deleted_at"));

                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return null;
    }

    // Update
    public void updateUser(Users user) {
        String sql = "UPDATE users SET username = ?, user_password = ?, given_name = ?, family_name = ?, is_active = ?, updated_at = ? WHERE id = ?";

        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getGivenName());
            preparedStatement.setString(4, user.getFamilyName());
            preparedStatement.setBoolean(5, user.getIsActive());
            preparedStatement.setTimestamp(6, new Timestamp(new Date().getTime()));
            preparedStatement.setString(7, user.getId());

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    // Soft Delete
    public void softDeleteUser(String userId) {
        String sql = "UPDATE users SET deleted_at = ? WHERE id = ?";

        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setTimestamp(1, new Timestamp(new Date().getTime()));
            preparedStatement.setString(2, userId);

            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

     private Users mapResultSetToUser(ResultSet resultSet) throws SQLException {
        Users user = new Users();
        user.setId(resultSet.getString("id"));
        user.setUsername(resultSet.getString("username"));
        user.setUserPassword(resultSet.getString("user_password"));
        user.setGivenName(resultSet.getString("given_name"));
        user.setFamilyName(resultSet.getString("family_name"));
        user.setIsActive(resultSet.getBoolean("is_active"));
        user.setCreatedAt(resultSet.getTimestamp("created_at"));
        user.setUpdatedAt(resultSet.getTimestamp("updated_at"));
        user.setDeletedAt(resultSet.getTimestamp("deleted_at"));
        return user;
    }

    private Users getServiceLocator() {
        if (serviceLocator == null) {
            serviceLocator = new Users();
        }
        return serviceLocator;
    }
}