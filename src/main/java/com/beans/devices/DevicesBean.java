/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans.devices;
import com.connection.DatabaseConnection;
import com.dao.DevicesDao;
import com.persistence.entities.Devices;
import jakarta.inject.Inject;
import java.io.Serializable;

import java.util.List;
/**
 *
 * @author Usuario
 */
public class DevicesBean implements Serializable{
    
    

    private DevicesDao devicesDao;
    private List<Devices> devicesList;
    private Devices selectedDevice;
    private String selectedType;

    public DevicesBean() {
        this.devicesDao = new DevicesDao(new DatabaseConnection());
        this.devicesList = devicesDao.getAllDevices();
       
    }
    @Inject
    DeviceSessionBean deviceSessionBean;

    // Métodos de acceso a datos y propiedades

    public List<Devices> getDevicesList() {
        return devicesList;
    }

    public Devices getSelectedDevice() {
        return selectedDevice;
    }

    public void setSelectedDevice(Devices selectedDevice) {
        this.selectedDevice = selectedDevice;
    }

    public String getSelectedType() {
        return selectedType;
    }

    public void setSelectedType(String selectedType) {
        this.selectedType = selectedType;
    }
    
    public String editDevices(Devices device) {
        this.deviceSessionBean.setSelectedDevices(device);
        return "user-form.xhtml?faces-redirect=true";
    }

    // Método para guardar o actualizar un dispositivo
    public void saveDevice() {
        // Lógica para guardar o actualizar un dispositivo
        if (selectedDevice.getId() == null || selectedDevice.getId().isEmpty()) {
            devicesDao.createDevices(selectedDevice, selectedType);
        } else {
            devicesDao.updateDevice(selectedDevice, selectedType);
        }

        // Recargar la lista de dispositivos después de guardar o actualizar
        devicesList = devicesDao.getAllDevices();

        // Limpiar el dispositivo seleccionado
        selectedDevice = new Devices();
    }

    // Método para eliminar suavemente un dispositivo
    public void deleteSoftDevice(String deviceId) {
        // Lógica para eliminar suavemente un dispositivo
        devicesDao.softDeleteDevice(deviceId);

        // Recargar la lista de dispositivos después de eliminar suavemente
        devicesList = devicesDao.getAllDevices();
    }
}
