
package com.persistence.entities;

import java.util.Date;

/**
 * @ClassName: Users
 * @Package: com.persistence.entities
 * @Date: Nov 18, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */

public class Users {

    private String id;
    private String username;
    private String userPassword;
    private String givenName;
    private String familyName;
    private boolean isActive;
    private Date createdAt;
    private Date updatedAt;
    private Date deletedAt;

    public Users() {
    }

    public Users(String id) {
        this.id = id;
    }

    public Users(String id, String username, String givenName, String familyName) {
        this.id = id;
        this.username = username;
        this.givenName = givenName;
        this.familyName = familyName;
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
}
