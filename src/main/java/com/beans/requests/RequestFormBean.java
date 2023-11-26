/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */

package com.beans.requests;

import com.connection.DatabaseConnection;
import com.dao.RequestDao;
import com.dao.RequestedDevicesDao;
import com.persistence.entities.Request;
import com.persistence.entities.RequestedDevices;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @ClassName RequestFormBean
 * @Package com.beans.requests
 * @Author Rodrigo Hidalgo
 * @CopyRight Rodrigo Hidalgo
 * @Date Nov 24, 2023
 */
@Named(value="requestFormBean")
@RequestScoped
public class RequestFormBean {

    private RequestDao requestDAO;
    private RequestedDevicesDao requestedDevicesDAO;
    private Request request = new Request();
    private RequestedDevices requestedDevices = new RequestedDevices();
    private List<RequestedDevices> requestedDevicesList = new ArrayList<>();
    private DatabaseConnection connection;
    
    public RequestFormBean() {
        this.connection = new DatabaseConnection();
        // Inicializacion de las DAOS
        this.requestDAO = new RequestDao(connection);
        this.requestedDevicesDAO = new RequestedDevicesDao(connection);
    }
    // Getters y Setters

    public Request getRequest() {
        return request;
    }

    public void setRequest(Request request) {
        this.request = request;
    }

    public RequestedDevices getRequestedDevices() {
        return requestedDevices;
    }

    public void setRequestedDevices(RequestedDevices requestedDevices) {
        this.requestedDevices = requestedDevices;
    }

    public List<RequestedDevices> getRequestedDevicesList() {
        return requestedDevicesList;
    }

    public void setRequestedDevicesList(List<RequestedDevices> requestedDevicesList) {
        this.requestedDevicesList = requestedDevicesList;
    }
    

    public void addRequestedDevices() {
        requestedDevicesList.add(requestedDevices);
        requestedDevices = new RequestedDevices();
    }

    public void saveRequest() {
        request.setId(UUID.randomUUID().toString()); // Generar un ID único para la solicitud
        requestDAO.createRequest(request);

        for (RequestedDevices rd : requestedDevicesList) {
            rd.setId(UUID.randomUUID().toString()); // Generar un ID único para cada dispositivo solicitado
            rd.setRequestId(request.getId()); // Asignar el ID de la solicitud al dispositivo solicitado
            requestedDevicesDAO.createRequestedDevices(rd);
        }

        // Limpia después de guardar
        request = new Request();
        requestedDevicesList.clear();
    }
}
