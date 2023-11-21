/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans;

import com.connection.DatabaseConnection;
import com.dao.UserRolesDao;
import com.persistence.entities.UserRoles;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;





/**
 *
 * @author Usuario
 */
public class UserRolesBean {


    private UserRolesDao userRolesDao;
    private List<UserRoles> userRolesList;
    private UserRoles selectedUserRole;

    public UserRolesBean() {
        this.userRolesDao = new UserRolesDao(new DatabaseConnection());
        this.userRolesList = userRolesDao.getAllUserRoles();
        this.selectedUserRole = new UserRoles();
    }

    // Métodos de acceso a datos y propiedades

    public List<UserRoles> getUserRolesList() {
        return userRolesList;
    }

    public UserRoles getSelectedUserRole() {
        return selectedUserRole;
    }

    public void setSelectedUserRole(UserRoles selectedUserRole) {
        this.selectedUserRole = selectedUserRole;
    }

    // Método para guardar o actualizar un UserRole
    public void saveUserRole() {
        // Lógica para guardar o actualizar un UserRole
        if (selectedUserRole.getId() == null || selectedUserRole.getId().isEmpty()) {
            userRolesDao.createUserRole(selectedUserRole);
        } else {
            userRolesDao.updateUserRole(selectedUserRole);
        }

        // Recargar la lista de UserRoles después de guardar o actualizar
        userRolesList = userRolesDao.getAllUserRoles();

        // Limpiar el UserRole seleccionado
        selectedUserRole = new UserRoles();
    }

    // Método para eliminar suavemente un UserRole
    public void deleteSoftUserRole(String userRoleId) {
        // Lógica para eliminar suavemente un UserRole
        userRolesDao.softDeleteUserRole(userRoleId);

        // Recargar la lista de UserRoles después de eliminar suavemente
        userRolesList = userRolesDao.getAllUserRoles();
    }
}
