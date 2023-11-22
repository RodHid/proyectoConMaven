/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package com.beans;

import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;


/**
 * @ClassName MenuBean
 * @Package com.beans
 * @Author Rodrigo Hidalgo
 * @CopyRight Rodrigo Hidalgo
 * @Date Nov 21, 2023
 */
@Named(value = "menuBean")
@RequestScoped
public class MenuBean {

        public String navigateToUsers() {
        // Lógica de navegación para la página de usuarios
        return "pages/user/users-list.xhtml?faces-redirect=true";
    }

    public String navigateToRequest() {
        // Lógica de navegación para la página de solicitudes
        return "pages/request/requests-list.xhtml?faces-redirect=true";
    }

    public String navigateToDevices() {
        // Lógica de navegación para la página de dispositivos
        return "pages/devices/devices-list.xhtml?faces-redirect=true";
    }
}

