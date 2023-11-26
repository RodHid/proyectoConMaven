/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;

/**
 * @ClassName: RolesDao
 * @Package: com.dao
 * @Date: Nov 19, 2023
 * @CopyRigth: Manuel Molina
 * @author Manolo
 */
import com.connection.DatabaseConnection;
import com.persistence.entities.Roles;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolesDao {

    private final DatabaseConnection databaseConnection;

    public RolesDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }

    // Métodos CRUD
    public void createRoles(Roles role) {
        String sql = "INSERT INTO roles (name, description) VALUES (?, ?)";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public void softDeleteRoles(String roleId) {
        String sql = "UPDATE roles SET is_active = 0 WHERE id=?";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public void updateRoles(Roles role) {
        String sql = "UPDATE roles SET name=?, description=? WHERE id=?";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, role.getName());
            preparedStatement.setString(2, role.getDescription());
            preparedStatement.setString(3, role.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }
    // Get Roles By Name (case-insensitive search using LIKE)

    public List<Roles> getRolesFilteringByName(String searchTerm) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Roles> rolesList = new ArrayList<>();

        try {
            connection = databaseConnection.connect();
            String sql = "SELECT * FROM roles WHERE LOWER(roles_name) LIKE ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, "%" + searchTerm.toLowerCase() + "%");
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Roles roles = mapResultSet(resultSet);
                rolesList.add(roles);
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

        return rolesList;
    }

    public void deleteRoles(String roleId) {
        String sql = "DELETE FROM roles WHERE id=?";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, roleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public Roles getRolesById(String roleId) {
        String sql = "SELECT * FROM roles WHERE id=?";
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, roleId);
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

    public List<Roles> getAllRoles() {
        String sql = "SELECT * FROM roles";
        List<Roles> roles = new ArrayList<>();
        try (Connection connection = databaseConnection.connect(); PreparedStatement preparedStatement = connection.prepareStatement(sql); ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                roles.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return roles;
    }

    private Roles mapResultSet(ResultSet resultSet) throws SQLException {
        Roles role = new Roles();
        role.setId(resultSet.getString("id"));
        role.setName(resultSet.getString("name"));
        role.setDescription(resultSet.getString("description"));
        role.setIsActive(resultSet.getBoolean("is_active"));
        role.setCreatedAt(resultSet.getDate("created_at"));
        return role;
    }
}
