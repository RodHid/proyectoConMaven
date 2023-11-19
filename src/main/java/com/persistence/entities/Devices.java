/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.persistence.entities;

import java.util.Date;
import java.util.List;

/**
 * @ClassName: Devices
 * @Package: com.persistence.entities
 * @Date: Nov 18, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */

public class Devices {

    private String id;
    private String deviceName;
    private String description;
    private Date adquisitionDate;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;
    private DeviceTypes deviceType;
    private Users createdBy;

    public Devices() {
    }

    public Devices(String id) {
        this.id = id;
    }

    public Devices(String id, String deviceName, Date adquisitionDate, boolean isActive, Date createdAt) {
        this.id = id;
        this.deviceName = deviceName;
        this.adquisitionDate = adquisitionDate;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getAdquisitionDate() {
        return adquisitionDate;
    }

    public void setAdquisitionDate(Date adquisitionDate) {
        this.adquisitionDate = adquisitionDate;
    }

    public boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
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

    public DeviceTypes getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(DeviceTypes deviceType) {
        this.deviceType = deviceType;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
    }    
}
