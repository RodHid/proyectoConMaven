/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.itca.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @ClassName: Devices
 * @Package: com.itca.entities
 * @Date: Nov 13, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */
@Entity
@Table(name = "devices")
@NamedQueries({
    @NamedQuery(name = "Devices.findAll", query = "SELECT d FROM Devices d"),
    @NamedQuery(name = "Devices.findById", query = "SELECT d FROM Devices d WHERE d.id = :id"),
    @NamedQuery(name = "Devices.findByDeviceName", query = "SELECT d FROM Devices d WHERE d.deviceName = :deviceName"),
    @NamedQuery(name = "Devices.findByDescription", query = "SELECT d FROM Devices d WHERE d.description = :description"),
    @NamedQuery(name = "Devices.findByAdquisitionDate", query = "SELECT d FROM Devices d WHERE d.adquisitionDate = :adquisitionDate"),
    @NamedQuery(name = "Devices.findByIsActive", query = "SELECT d FROM Devices d WHERE d.isActive = :isActive"),
    @NamedQuery(name = "Devices.findByCreatedAt", query = "SELECT d FROM Devices d WHERE d.createdAt = :createdAt"),
    @NamedQuery(name = "Devices.findByUpdatedAt", query = "SELECT d FROM Devices d WHERE d.updatedAt = :updatedAt"),
    @NamedQuery(name = "Devices.findByDeletedAt", query = "SELECT d FROM Devices d WHERE d.deletedAt = :deletedAt")})
public class Devices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "device_name")
    private String deviceName;
    @Column(name = "description")
    private String description;
    @Basic(optional = false)
    @Column(name = "adquisition_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date adquisitionDate;
    @Basic(optional = false)
    @Column(name = "is_active")
    private boolean isActive;
    @Basic(optional = false)
    @Column(name = "created_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdAt;
    @Column(name = "updated_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedAt;
    @Column(name = "deleted_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deletedAt;
    @JoinColumn(name = "device_type", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private DeviceTypes deviceType;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users createdBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "devicesId")
    private List<RequestedDevices> requestedDevicesList;

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

    public List<RequestedDevices> getRequestedDevicesList() {
        return requestedDevicesList;
    }

    public void setRequestedDevicesList(List<RequestedDevices> requestedDevicesList) {
        this.requestedDevicesList = requestedDevicesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Devices)) {
            return false;
        }
        Devices other = (Devices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itca.entities.Devices[ id=" + id + " ]";
    }

}
