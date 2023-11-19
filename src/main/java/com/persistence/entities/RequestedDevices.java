/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.persistence.entities;

import java.util.Date;

/**
 * @ClassName: RequestedDevices
 * @Package: com.persistence.entities
 * @Date: Nov 18, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */

public class RequestedDevices {

    private String id;
    private String loanReason;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private Devices devicesId;
    private Request requestId;

    public RequestedDevices() {
    }

    public RequestedDevices(String id) {
        this.id = id;
    }

    public RequestedDevices(String id, String loanReason, Date createdAt) {
        this.id = id;
        this.loanReason = loanReason;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoanReason() {
        return loanReason;
    }

    public void setLoanReason(String loanReason) {
        this.loanReason = loanReason;
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

    public Devices getDevicesId() {
        return devicesId;
    }

    public void setDevicesId(Devices devicesId) {
        this.devicesId = devicesId;
    }

    public Request getRequestId() {
        return requestId;
    }

    public void setRequestId(Request requestId) {
        this.requestId = requestId;
    }
}
