/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dao;
/**
 * @ClassName: UserRolesDao
 * @Package: com.dao
 * @Date: Nov 19, 2023
 * @CopyRigth: Manuel Molina
 * @author Manolo
 */

import com.connection.DatabaseConnection;
import com.persistence.entities.Roles;
import com.persistence.entities.UserRoles;
import com.persistence.entities.Users;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;




public class UserRolesDao {

    private final DatabaseConnection databaseConnection;

    public UserRolesDao(DatabaseConnection databaseConnection) {
        this.databaseConnection = databaseConnection;
    }   
   
    public void createUserRole(UserRoles userRole) {
        String sql = "INSERT INTO user_role (user_id, role_id, created_by) VALUES (?, ?, ?)";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            //preparedStatement.setString(1, userRole.getUserId());
            //preparedStatement.setString(2, (Roles) userRole.getRoleId());
            //preparedStatement.setString(3, userRole.getCreatedBy());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }
    
    
    
      public void softDeleteUserRole(String userRoleId) {
        String sql = "UPDATE user_role SET is_active = 0, deleted_at = CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userRoleId);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    public List<UserRoles> getUserRolesByUserId(String userId) {
        String sql = "SELECT * FROM user_role WHERE user_id=?";
        List<UserRoles> userRoles = new ArrayList<>();
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    userRoles.add(mapResultSet(resultSet));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return userRoles;
    }

    

    public List<UserRoles> getAllUserRoles() {
        String sql = "SELECT * FROM user_role";
        List<UserRoles> userRoles = new ArrayList<>();
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql);
             ResultSet resultSet = preparedStatement.executeQuery()) {
            while (resultSet.next()) {
                userRoles.add(mapResultSet(resultSet));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
        return userRoles;
    }
   
    public void updateUserRole(UserRoles userRole) {
        String sql = "UPDATE user_role SET role_id=?, updated_at=CURRENT_TIMESTAMP() WHERE id=?";
        try (Connection connection = databaseConnection.connect();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userRole.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            databaseConnection.disconnect();
        }
    }

    private UserRoles mapResultSet(ResultSet resultSet) throws SQLException {
        UserRoles userRole = new UserRoles();
        userRole.setId(resultSet.getString("id"));
        //userRole.setUserId(resultSet.getUsers("user_id"));
        //userRole.setRoleId(resultSet.getRoles("role_id"));
        userRole.setIsActive(resultSet.getBoolean("is_active"));
        userRole.setCreatedAt(resultSet.getDate("created_at"));
        //userRole.setCreatedBy(resultSet.getUsers("created_by"));
        userRole.setUpdatedAt(resultSet.getDate("updated_at")) ;
        userRole.setDeletedAt(resultSet.getDate("deleted_at")) ;
        return userRole;
    }
}

