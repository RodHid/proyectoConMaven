/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.beans.user;

import com.connection.DatabaseConnection;
import com.dao.RolesDao;
import com.dao.UserRolesDao;
import com.dao.UsersDao;
import com.persistence.entities.Roles;
import com.persistence.entities.UserRoles;
import com.persistence.entities.Users;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.util.List;

/**
 * @ClassName LoginBean
 * @Package com.beans
 * @Author Rodrigo Hidalgo
 * @CopyRight Rodrigo Hidalgo
 * @Date Nov 18, 2023
 */
@Named(value = "loginBean")
@RequestScoped
public class LoginBean implements Serializable {

    private DatabaseConnection databaseConnection;
    private UsersDao usersDao;
    private UserRolesDao userRolesDao;
    private RolesDao roleDao;
    private String username;
    private String password;

    public LoginBean() {
        this.databaseConnection = new DatabaseConnection();
        this.usersDao = new UsersDao(this.databaseConnection);
        this.userRolesDao = new UserRolesDao(this.databaseConnection);
        this.roleDao = new RolesDao(this.databaseConnection);
    }

    public String validateLogin() {
        Users user = usersDao.getUserByUsername(username);
        if (user != null && validatePassword(user.getUserPassword(), password)) {
            List<UserRoles> userRoles = userRolesDao.getUserRolesByUserId(user.getId());
            String roleId = userRoles.get(0).getRoleId();
            Roles role = roleDao.getRolesById(roleId);
            // Crear la variable de sesión 'activeUser'
            Users activeUser = new Users(user.getId(), user.getUsername(), user.getGivenName(), user.getFamilyName());
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("activeUser", activeUser);
            session.setAttribute("userRole", role.getName());
            // Add a success message to the Growl component
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Success",
                            "Login successful"
                    )
            );
            return "pages/landing/home.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales inválidas"));
            return null;
        }
    }

    private boolean validatePassword(String storedPassword, String enteredPassword) {
        return storedPassword.equals(enteredPassword);
    }

    public String logout() {
        // Restablecer credenciales y otros datos relacionados con la autenticación
        username = null;
        password = null;
        HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.removeAttribute("activeUser");
            session.removeAttribute("userRole");
        // Otros pasos para restablecer el estado
        return "index.xhtml"; // Volver a la página de inicio de sesión
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
