/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans.userRoles;

import com.connection.DatabaseConnection;
import com.dao.UserRolesDao;
import com.persistence.entities.UserRoles;
import jakarta.inject.Inject;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Usuario
 */
public class UserRolesBean implements Serializable {


    private UserRolesDao userRolesDao;
    private List<UserRoles> userRolesList;
     @Inject
    UserRolesSessionBean userRolesSessionBean;
   

    public UserRolesBean() {
        this.userRolesDao = new UserRolesDao(new DatabaseConnection());
        this.userRolesList = userRolesDao.getAllUserRoles();
       
    }    
   

    public List<UserRoles> getUserRolesList() {
       if (userRolesList == null) {
            loadUserRolesList();
        }
        return userRolesList;
    }
     
    private void loadUserRolesList() {
        userRolesList = userRolesDao.getAllUserRoles();
    }
    public String editUserRoles(UserRoles userRol) {
        this.userRolesSessionBean.setSelectedUserRoles(userRol);
        return "user-form.xhtml?faces-redirect=true";
    }

    public void deleteSoftUser(String userRolesId) {
        // Lógica para eliminar suavemente un usuario
        userRolesDao.softDeleteUserRole(userRolesId);
        // Recargar la lista de usuarios después de eliminar suavemente
        loadUserRolesList();
    }

       
}
