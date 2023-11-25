package com.beans.deviceTypes;

import com.persistence.entities.DeviceTypes;
import com.persistence.entities.Roles;
import com.persistence.entities.Users;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.context.ExternalContext;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Named;
import jakarta.servlet.http.HttpSession;
import java.io.Serializable;

/**
 * @ClassName: UserSessionBean
 * @Package: com.beans
 * @Date: Nov 21, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */

@Named(value = "typesSessionBean")
@SessionScoped
public class TypesSessionBean implements Serializable {
    
    private DeviceTypes selectedType;

    public DeviceTypes getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(DeviceTypes selectedType) {
        this.selectedType = selectedType;
    }

    
}
