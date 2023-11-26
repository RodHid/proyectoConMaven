/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans.requests;

import com.connection.DatabaseConnection;
import com.dao.RequestDao;
import com.persistence.entities.Request;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Usuario
 */
@Named(value = "requestListBean")
@RequestScoped
public class RequestListBean {

    private RequestDao requestDao;
    private List<Request> requestList;
    private Request selectedRequest;
    private String searchTerm;//agregué esto-->
    private List<Request> filteredRequestList;//agregué esto-->

    public RequestListBean() {
        this.requestDao = new RequestDao(new DatabaseConnection());
        this.requestList = requestDao.getAllRequests();
        this.selectedRequest = new Request();
    }

    public void searchUsers() {//agregue esto
        // Filtrar la lista de usuarios según el término de búsqueda
        filteredRequestList = requestList.stream()
                .filter(request -> request.getId().toLowerCase().contains(searchTerm.toLowerCase()))
                .collect(Collectors.toList());
    }

    public List<Request> getRequestList() {//agregue esto
        if (requestList == null) {
            loadRequestList();
        }
        return requestList;
    }

    private void loadRequestList() {
        if (searchTerm == null || searchTerm.isEmpty()) {
            requestList = requestDao.getAllRequests();
        } else {
            searchUsers();
        }
    }

    // Métodos de acceso a datos y propiedades
    public Request getSelectedRequest() {
        return selectedRequest;
    }

    public String getSearchTerm() {
        return searchTerm;
    }

    public void setSearchTerm(String searchTerm) {
        this.searchTerm = searchTerm;
    }
    
    

    public void setSelectedRequest(Request selectedRequest) {
        this.selectedRequest = selectedRequest;
    }

    public String create() {
        return "/pages/request-form.xhtml?faces-redirect=true";
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
