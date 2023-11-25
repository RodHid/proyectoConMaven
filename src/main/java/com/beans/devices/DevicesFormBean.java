/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans;
import com.connection.DatabaseConnection;
import com.dao.DeviceTypeDao;
import com.dao.DevicesDao;
import com.persistence.entities.DeviceTypes;
import com.persistence.entities.Devices;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;


/**
 *
 * @author Usuario
 */
@Named(value = "devicesFormBean")
@RequestScoped
public class DevicesFormBean {
    

    private DatabaseConnection databaseConnection;
    private DevicesDao devicesDao;
    private DeviceTypeDao deviceTypeDao;
    private Devices selectedDevice;
    private List<DeviceTypes> listOfTypes;
    private String selectedType;

    public DevicesFormBean() {
        this.databaseConnection = new DatabaseConnection();
        this.devicesDao = new DevicesDao(this.databaseConnection);
        this.deviceTypeDao = new DeviceTypeDao(this.databaseConnection);
        this.selectedDevice = new Devices();
        this.listOfTypes = this.deviceTypeDao.getAllDeviceTypes();
    }

    // Métodos de acceso a datos y propiedades
    public Devices getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(Devices selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    public List<DeviceTypes> getListOfTypes() {
        return listOfTypes;
    }

    public void setListOfTypes(List<DeviceTypes> listOfTypes) {
        this.listOfTypes = listOfTypes;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
    
    
    
    // Método para guardar o actualizar un dispositivo
    public void saveDevice() {
        // Lógica para guardar o actualizar un dispositivo
        if (selectedDevice.getId() == null || selectedDevice.getId().isEmpty()) {
            devicesDao.createDevices(selectedDevice, this.selectedType);
        } else {
            devicesDao.updateDevice(selectedDevice, this.selectedType);
        }

        // Limpiar el dispositivo seleccionado
        selectedDevice = new Devices();
    }
}
