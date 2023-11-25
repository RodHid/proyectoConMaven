/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans.requestedDevices;
import com.connection.DatabaseConnection;
import com.dao.RequestedDevicesDao;
import com.persistence.entities.RequestedDevices;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Usuario
 */
public class RequestedDevicesBean {
    


    private RequestedDevicesDao requestedDevicesDao;
    private List<RequestedDevices> requestedDevicesList;
    private RequestedDevices selectedRequestedDevices;

    public RequestedDevicesBean() {
        this.requestedDevicesDao = new RequestedDevicesDao(new DatabaseConnection());
        this.requestedDevicesList = requestedDevicesDao.getAllRequestedDevices();
        this.selectedRequestedDevices = new RequestedDevices();
    }

    // Métodos de acceso a datos y propiedades

    public List<RequestedDevices> getRequestedDevicesList() {
        return requestedDevicesList;
    }

    public RequestedDevices getSelectedRequestedDevices() {
        return selectedRequestedDevices;
    }

    public void setSelectedRequestedDevices(RequestedDevices selectedRequestedDevices) {
        this.selectedRequestedDevices = selectedRequestedDevices;
    }

    // Método para guardar o actualizar un RequestedDevices
    public void saveRequestedDevices() {
        // Lógica para guardar o actualizar un RequestedDevices
        if (selectedRequestedDevices.getId() == null || selectedRequestedDevices.getId().isEmpty()) {
            requestedDevicesDao.createRequestedDevices(selectedRequestedDevices);
        } else {
            requestedDevicesDao.updateRequestedDevices(selectedRequestedDevices);
        }

        // Recargar la lista de RequestedDevices después de guardar o actualizar
        requestedDevicesList = requestedDevicesDao.getAllRequestedDevices();

        // Limpiar el RequestedDevices seleccionado
        selectedRequestedDevices = new RequestedDevices();
    }

    // Método para eliminar suavemente un RequestedDevices
    public void deleteSoftRequestedDevices(String requestedDevicesId) {
        // Lógica para eliminar suavemente un RequestedDevices
        requestedDevicesDao.softDeleteRequestedDevices(requestedDevicesId);

        // Recargar la lista de RequestedDevices después de eliminar suavemente
        requestedDevicesList = requestedDevicesDao.getAllRequestedDevices();
    }
}
