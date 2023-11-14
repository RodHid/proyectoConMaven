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
 * @ClassName: DeviceTypes
 * @Package: com.itca.entities
 * @Date: Nov 13, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */
@Entity
@Table(name = "device_types")
@NamedQueries({
    @NamedQuery(name = "DeviceTypes.findAll", query = "SELECT d FROM DeviceTypes d"),
    @NamedQuery(name = "DeviceTypes.findById", query = "SELECT d FROM DeviceTypes d WHERE d.id = :id"),
    @NamedQuery(name = "DeviceTypes.findByTypeName", query = "SELECT d FROM DeviceTypes d WHERE d.typeName = :typeName"),
    @NamedQuery(name = "DeviceTypes.findByTypeDescription", query = "SELECT d FROM DeviceTypes d WHERE d.typeDescription = :typeDescription"),
    @NamedQuery(name = "DeviceTypes.findByIsActive", query = "SELECT d FROM DeviceTypes d WHERE d.isActive = :isActive"),
    @NamedQuery(name = "DeviceTypes.findByCreatedAt", query = "SELECT d FROM DeviceTypes d WHERE d.createdAt = :createdAt"),
    @NamedQuery(name = "DeviceTypes.findByUpdatedAt", query = "SELECT d FROM DeviceTypes d WHERE d.updatedAt = :updatedAt"),
    @NamedQuery(name = "DeviceTypes.findByDeletedAt", query = "SELECT d FROM DeviceTypes d WHERE d.deletedAt = :deletedAt")})
public class DeviceTypes implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "type_name")
    private String typeName;
    @Column(name = "type_description")
    private Integer typeDescription;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "deviceType")
    private List<Devices> devicesList;
    @JoinColumn(name = "created_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users createdBy;

    public DeviceTypes() {
    }

    public DeviceTypes(String id) {
        this.id = id;
    }

    public DeviceTypes(String id, String typeName, boolean isActive, Date createdAt) {
        this.id = id;
        this.typeName = typeName;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getTypeDescription() {
        return typeDescription;
    }

    public void setTypeDescription(Integer typeDescription) {
        this.typeDescription = typeDescription;
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

    public List<Devices> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Devices> devicesList) {
        this.devicesList = devicesList;
    }

    public Users getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Users createdBy) {
        this.createdBy = createdBy;
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
        if (!(object instanceof DeviceTypes)) {
            return false;
        }
        DeviceTypes other = (DeviceTypes) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itca.entities.DeviceTypes[ id=" + id + " ]";
    }

}
