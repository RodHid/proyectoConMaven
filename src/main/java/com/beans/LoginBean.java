/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.beans;

import com.connection.DatabaseConnection;
import com.dao.UsersDao;
import com.persistence.entities.Users;
import jakarta.enterprise.context.RequestScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

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

    private UsersDao usersDao;
    private String username;
    private String password;

    public LoginBean() {
        this.usersDao = new UsersDao(new DatabaseConnection());
    }

    public String validateLogin() {
        Users user = usersDao.getUserByUsername(username);
        System.out.println("USER:\n"+user.getGivenName());
        if (user != null && validatePassword(user.getUserPassword(), password)) {
            // Crear la variable de sesión 'activeUser'
            Users activeUser = new Users(user.getId(), user.getUsername(), user.getGivenName(), user.getFamilyName());
            HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(true);
            session.setAttribute("activeUser", activeUser);
            // Add a success message to the Growl component
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(
                            FacesMessage.SEVERITY_INFO,
                            "Success",
                            "Login successful"
                    )
            );
            return "pages/user/registration.xhtml?faces-redirect=true";
        } else {
            FacesContext.getCurrentInstance().addMessage(null,
                    new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Credenciales inválidas"));
            return null;
        }
    }

    private boolean validatePassword(String storedPassword, String enteredPassword) {
        return storedPassword.equals(enteredPassword);
    }

    public String resetLogin() {
        // Restablecer credenciales y otros datos relacionados con la autenticación
        username = null;
        password = null;

        // Otros pasos para restablecer el estado
        return "login.xhtml"; // Volver a la página de inicio de sesión
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
