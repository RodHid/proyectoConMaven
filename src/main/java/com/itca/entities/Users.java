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
 * @ClassName: Users
 * @Package: com.itca.entities
 * @Date: Nov 13, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */
@Entity
@Table(name = "users")
@NamedQueries({
    @NamedQuery(name = "Users.findAll", query = "SELECT u FROM Users u"),
    @NamedQuery(name = "Users.findById", query = "SELECT u FROM Users u WHERE u.id = :id"),
    @NamedQuery(name = "Users.findByUsername", query = "SELECT u FROM Users u WHERE u.username = :username"),
    @NamedQuery(name = "Users.findByUserPassword", query = "SELECT u FROM Users u WHERE u.userPassword = :userPassword"),
    @NamedQuery(name = "Users.findByGivenName", query = "SELECT u FROM Users u WHERE u.givenName = :givenName"),
    @NamedQuery(name = "Users.findByFamilyName", query = "SELECT u FROM Users u WHERE u.familyName = :familyName"),
    @NamedQuery(name = "Users.findByIsActive", query = "SELECT u FROM Users u WHERE u.isActive = :isActive"),
    @NamedQuery(name = "Users.findByCreatedAt", query = "SELECT u FROM Users u WHERE u.createdAt = :createdAt"),
    @NamedQuery(name = "Users.findByUpdatedAt", query = "SELECT u FROM Users u WHERE u.updatedAt = :updatedAt"),
    @NamedQuery(name = "Users.findByDeletedAt", query = "SELECT u FROM Users u WHERE u.deletedAt = :deletedAt")})
public class Users implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "id")
    private String id;
    @Basic(optional = false)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @Column(name = "user_password")
    private String userPassword;
    @Basic(optional = false)
    @Column(name = "given_name")
    private String givenName;
    @Basic(optional = false)
    @Column(name = "family_name")
    private String familyName;
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
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "requestedBy")
    private List<Request> requestList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "acceptedBy")
    private List<Request> requestList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<UserRole> userRoleList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "userId")
    private List<UserRole> userRoleList1;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<Devices> devicesList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "createdBy")
    private List<DeviceTypes> deviceTypesList;

    public Users() {
    }

    public Users(String id) {
        this.id = id;
    }

    public Users(String id, String username, String userPassword, String givenName, String familyName, boolean isActive, Date createdAt) {
        this.id = id;
        this.username = username;
        this.userPassword = userPassword;
        this.givenName = givenName;
        this.familyName = familyName;
        this.isActive = isActive;
        this.createdAt = createdAt;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getGivenName() {
        return givenName;
    }

    public void setGivenName(String givenName) {
        this.givenName = givenName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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

    public List<Request> getRequestList() {
        return requestList;
    }

    public void setRequestList(List<Request> requestList) {
        this.requestList = requestList;
    }

    public List<Request> getRequestList1() {
        return requestList1;
    }

    public void setRequestList1(List<Request> requestList1) {
        this.requestList1 = requestList1;
    }

    public List<UserRole> getUserRoleList() {
        return userRoleList;
    }

    public void setUserRoleList(List<UserRole> userRoleList) {
        this.userRoleList = userRoleList;
    }

    public List<UserRole> getUserRoleList1() {
        return userRoleList1;
    }

    public void setUserRoleList1(List<UserRole> userRoleList1) {
        this.userRoleList1 = userRoleList1;
    }

    public List<Devices> getDevicesList() {
        return devicesList;
    }

    public void setDevicesList(List<Devices> devicesList) {
        this.devicesList = devicesList;
    }

    public List<DeviceTypes> getDeviceTypesList() {
        return deviceTypesList;
    }

    public void setDeviceTypesList(List<DeviceTypes> deviceTypesList) {
        this.deviceTypesList = deviceTypesList;
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
        if (!(object instanceof Users)) {
            return false;
        }
        Users other = (Users) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.itca.entities.Users[ id=" + id + " ]";
    }

}
