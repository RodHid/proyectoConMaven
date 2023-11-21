package com.beans;

import com.persistence.entities.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import javax.management.relation.Role;

/**
 * @ClassName: UserSessionBean
 * @Package: com.beans
 * @Date: Nov 21, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */

@Named(value = "sessionBean")
@SessionScoped
public class UserSessionBean implements Serializable {
    
    private Users selectedUser;
    private Role activeUser;

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public Users getActiveUser() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session.getAttribute("activeUser") != null) {
           return (Users) session.getAttribute("activeUser");
        }
        return null;
    }
    
    public Role getUserRole() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session.getAttribute("userRole") != null) {
           return (Role) session.getAttribute("userRole");
        }
        return null;
    }
}
