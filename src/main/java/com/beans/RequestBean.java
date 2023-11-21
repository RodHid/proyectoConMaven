/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans;

import com.connection.DatabaseConnection;
import com.dao.RequestDao;
import com.persistence.entities.Request;


import java.util.List;

/**
 *
 * @author Usuario
 */
public class RequestBean {
   


    private RequestDao requestDao;
    private List<Request> requestList;
    private Request selectedRequest;

    public RequestBean() {
        this.requestDao = new RequestDao(new DatabaseConnection());
        this.requestList = requestDao.getAllRequests();
        this.selectedRequest = new Request();
    }

    // Métodos de acceso a datos y propiedades

    public List<Request> getRequestList() {
        return requestList;
    }

    public Request getSelectedRequest() {
        return selectedRequest;
    }

    public void setSelectedRequest(Request selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    // Método para guardar o actualizar una solicitud
    public void saveRequest() {
        // Lógica para guardar o actualizar una solicitud
        if (selectedRequest.getId() == null || selectedRequest.getId().isEmpty()) {
            requestDao.createRequest(selectedRequest);
        } else {
            requestDao.updateRequest(selectedRequest);
        }

        // Recargar la lista de solicitudes después de guardar o actualizar
        requestList = requestDao.getAllRequests();

        // Limpiar la solicitud seleccionada
        selectedRequest = new Request();
    }

    // Método para eliminar suavemente una solicitud
    public void deleteSoftRequest(String requestId) {
        // Lógica para eliminar suavemente una solicitud
        requestDao.softDeleteRequest(requestId);

        // Recargar la lista de solicitudes después de eliminar suavemente
        requestList = requestDao.getAllRequests();
    }
}
