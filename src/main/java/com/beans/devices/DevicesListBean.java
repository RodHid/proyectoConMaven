/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans.devices;
import com.connection.DatabaseConnection;
import com.dao.DevicesDao;
import com.persistence.entities.Devices;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Named;

import java.util.List;


/**
 *
 * @author Usuario
 */
@Named(value = "devicesListBean")
@RequestScoped
public class DevicesListBean {
    

    private DevicesDao devicesDao;
    private List<Devices> devicesList;
    private Devices selectedDevice;

    public DevicesListBean() {
        this.devicesDao = new DevicesDao(new DatabaseConnection());
        this.devicesList = devicesDao.getAllDevices();
        this.selectedDevice = new Devices();
    }

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

    // Método para eliminar suavemente un dispositivo
    public void deleteSoftDevice(String deviceId) {
        // Lógica para eliminar suavemente un dispositivo
        devicesDao.softDeleteDevice(deviceId);

        // Recargar la lista de dispositivos después de eliminar suavemente
        devicesList = devicesDao.getAllDevices();
    }
}
