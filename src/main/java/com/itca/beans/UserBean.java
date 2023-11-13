/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package com.itca.beans;

import jakarta.inject.Named;
import jakarta.faces.view.ViewScoped;
import java.io.Serializable;

/**
 *
 * @author Rodrigo Hidalgo
 */
@Named(value = "registrarUsuarios")
@ViewScoped
public class UserBean implements Serializable {

    private String givenName;
    private String familyName;
    private String username;

    // Getters and Setters

    public void registerUser() {
        // Construct the username based on the given conditions
        this.username = givenName.substring(0, 1).toLowerCase() + familyName.toLowerCase() + "@itca.edu.sv";

        // Perform the registration logic here
        // You can add database insertion or other actions as needed
        // Omitted fields: id, is_active, created_at, updated_at, deleted_at
    }
    
}
