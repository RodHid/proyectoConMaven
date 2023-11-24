/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans;

/**
 *
 * @author Usuario
 */


import com.persistence.entities.Devices;

import com.persistence.entities.Devices;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;

@Named(value = "deviceSessionBean")

@SessionScoped
public class DeviceSessionBean implements Serializable  {
    

   
    private Devices selectedDevices;

    public Devices getSelectedDevices() {
        return selectedDevices;
    }

    public void setSelectedDevices(Devices selectedDevices) {
        this.selectedDevices = selectedDevices;
    }

    public Devices getActiveDevices() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        ExternalContext externalContext = facesContext.getExternalContext();
        HttpSession session = (HttpSession) externalContext.getSession(false);
        if (session.getAttribute("activeDevices") != null) {
           return (Devices) session.getAttribute("activeDevices");
        }
        return null;
    }
}
