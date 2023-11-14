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
 * @ClassName: Request
 * @Package: com.itca.entities
 * @Date: Nov 13, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */
@Entity
@Table(name = "request")
@NamedQueries({
    @NamedQuery(name = "Request.findAll", query = "SELECT r FROM Request r"),
    @NamedQuery(name = "Request.findById", query = "SELECT r FROM Request r WHERE r.id = :id"),
    @NamedQuery(name = "Request.findByLoanDate", query = "SELECT r FROM Request r WHERE r.loanDate = :loanDate"),
    @NamedQuery(name = "Request.findByCreatedAt", query = "SELECT r FROM Request r WHERE r.createdAt = :createdAt"),
    @NamedQuery(name = "Request.findByUpdatedAt", query = "SELECT r FROM Request r WHERE r.updatedAt = :updatedAt"),
    @NamedQuery(name = "Request.findByDeletedAt", query = "SELECT r FROM Request r WHERE r.deletedAt = :deletedAt")})
public class Request implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "loan_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date loanDate;
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
    @JoinColumn(name = "requested_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users requestedBy;
    @JoinColumn(name = "accepted_by", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Users acceptedBy;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestId")
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Request)) {
            return false;
        }
        Request other = (Request) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itca.entities.Request[ id=" + id + " ]";
    }

}
