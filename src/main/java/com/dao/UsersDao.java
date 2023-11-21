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
    public void createUser(Users user, Users loggedUser, String roleId) {
        String createUserSQL = "INSERT INTO users (username, user_password, given_name, family_name) VALUES (?, ?, ?, ?)";
        String createUserRoleSQL = "INSERT INTO user_role (user_id, role_id, created_by) VALUES (?, ?, ?)";

        try (Connection connection = databaseConnection.connect(); PreparedStatement createUserStatement = connection.prepareStatement(createUserSQL); PreparedStatement createUserRoleStatement = connection.prepareStatement(createUserRoleSQL)) {

            connection.setAutoCommit(false);

            try {
                // Create user
                createUserStatement.setString(1, user.getUsername());
                createUserStatement.setString(2, user.getUserPassword());
                createUserStatement.setString(3, user.getGivenName());
                createUserStatement.setString(4, user.getFamilyName());
                createUserStatement.executeUpdate();

                // Assign default role "User" to the user
                createUserRoleStatement.setString(1, user.getId());
                createUserRoleStatement.setString(2, roleId);
                createUserRoleStatement.setString(3, loggedUser.getId()); // Created by the user
                createUserRoleStatement.executeUpdate();

                connection.commit();
                System.out.println("GUARDADO");
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }

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

        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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
        String sql = "SELECT * FROM users";

        try (Connection connection = databaseConnection.connect(); Statement statement = connection.createStatement()) {

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

        return null;
    }

    // Update
    public void updateUser(Users user, Users loggedUser, String roleId) {
        String updateUserSQL = "UPDATE users SET username = ?, user_password = ?, given_name = ?, family_name = ? WHERE id = ?";
        String updateUserRoleSQL = "UPDATE user_role SET role_id = ?, updated_by = ? WHERE user_id = ?";

        try (Connection connection = databaseConnection.connect(); PreparedStatement updateUserStatement = connection.prepareStatement(updateUserSQL); PreparedStatement updateUserRoleStatement = connection.prepareStatement(updateUserRoleSQL)) {

            connection.setAutoCommit(false);

            try {
                // Update user information
                updateUserStatement.setString(1, user.getUsername());
                updateUserStatement.setString(2, user.getUserPassword());
                updateUserStatement.setString(3, user.getGivenName());
                updateUserStatement.setString(4, user.getFamilyName());
                updateUserStatement.setString(5, user.getId());
                updateUserStatement.executeUpdate();

                // Update user role
                updateUserRoleStatement.setString(1, roleId);
                updateUserRoleStatement.setString(2, loggedUser.getId()); // Updated by the user
                updateUserRoleStatement.setString(3, user.getId());
                updateUserRoleStatement.executeUpdate();

                connection.commit();
            } catch (SQLException e) {
                connection.rollback();
                throw e;
            } finally {
                connection.setAutoCommit(true);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    // Soft Delete
    public void softDeleteUser(String userId) {
        String sql = "UPDATE users SET deleted_at = ?, is_active = 0 WHERE id = ?";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

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
