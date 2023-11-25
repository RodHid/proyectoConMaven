/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans.userRoles;

import com.persistence.entities.UserRoles;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;

/**
 *
 * @author Usuario
 */

@Named(value = "userRoleSessionBean")
@SessionScoped
public class UserRolesSessionBean implements Serializable {
    
    private UserRoles selectedUserRoles;

    public UserRoles getSelectedUserRoles() {
        return selectedUserRoles;
    }

    public void setSelectedUserRoles(UserRoles selectedUserRoles) {
        this.selectedUserRoles = selectedUserRoles;
    }

    public UserRoles getActiveUserRoles() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session.getAttribute("activeUserRoles") != null) {
           return (UserRoles) session.getAttribute("activeUserRoles");
        }
        return null;
    }
}

