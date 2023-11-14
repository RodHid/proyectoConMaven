/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.itca.entities;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.io.Serializable;
import java.util.Date;

/**
 * @ClassName: RequestedDevices
 * @Package: com.itca.entities
 * @Date: Nov 13, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */
@Entity
@Table(name = "requested_devices")
@NamedQueries({
    @NamedQuery(name = "RequestedDevices.findAll", query = "SELECT r FROM RequestedDevices r"),
    @NamedQuery(name = "RequestedDevices.findById", query = "SELECT r FROM RequestedDevices r WHERE r.id = :id"),
    @NamedQuery(name = "RequestedDevices.findByLoanReason", query = "SELECT r FROM RequestedDevices r WHERE r.loanReason = :loanReason"),
    @NamedQuery(name = "RequestedDevices.findByCreatedAt", query = "SELECT r FROM RequestedDevices r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "RequestedDevices.findByUpdatedAt", query = "SELECT r FROM RequestedDevices r WHERE r.updatedAt = :updatedAt"),
    @NamedQuery(name = "RequestedDevices.findByDeletedAt", query = "SELECT r FROM RequestedDevices r WHERE r.deletedAt = :deletedAt")})
public class RequestedDevices implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "loan_reason")
    private String loanReason;
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
    @JoinColumn(name = "devices_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Devices devicesId;
    @JoinColumn(name = "request_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof RequestedDevices)) {
            return false;
        }
        RequestedDevices other = (RequestedDevices) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itca.entities.RequestedDevices[ id=" + id + " ]";
    }

}
