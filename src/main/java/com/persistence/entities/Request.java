/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.persistence.entities;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: Request
 * @Package: com.persistence.entities
 * @Date: Nov 18, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */

public class Request {

    private String id;
    private Date loanDate;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Users requestedBy;
    private Users acceptedBy;
    private List<RequestedDevices> requestedDevicesList;

    public Request() {
    }

    public Request(String id) {
        this.id = id;
    }

    public Request(String id, Date loanDate, Date createdAt) {
        this.id = id;
        this.loanDate = loanDate;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    public Date getDeletedAt() {
        return deletedAt;
    }

    public void setDeletedAt(Date deletedAt) {
        this.deletedAt = deletedAt;
    }

    public Users getRequestedBy() {
        return requestedBy;
    }

    public void setRequestedBy(Users requestedBy) {
        this.requestedBy = requestedBy;
    }

    public Users getAcceptedBy() {
        return acceptedBy;
    }

    public void setAcceptedBy(Users acceptedBy) {
        this.acceptedBy = acceptedBy;
    }

    public List<RequestedDevices> getRequestedDevicesList() {
        return requestedDevicesList;
    }

    public void setRequestedDevicesList(List<RequestedDevices> requestedDevicesList) {
        this.requestedDevicesList = requestedDevicesList;
    }
}
