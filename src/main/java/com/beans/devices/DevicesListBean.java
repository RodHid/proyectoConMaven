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
import java.util.stream.Collectors;


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
    private String searchTerm;//agregué esto-->
private List<Devices> filteredDevicesList;//agregué esto-->

    public DevicesListBean() {
        this.devicesDao = new DevicesDao(new DatabaseConnection());
        this.devicesList = devicesDao.getAllDevices();
        this.selectedDevice = new Devices();
    }
    public void searchDevices() {//agregue esto
    // Filtrar la lista de usuarios según el término de búsqueda
    filteredDevicesList = devicesList.stream()
            .filter(user -> user.getDeviceName().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
}
    public List<Devices> getDevicesList() {//agregue esto
        if (devicesList == null) {
            loadDevicesList();
        }
        return devicesList;
    }
     private void loadDevicesList() {
      if (searchTerm == null || searchTerm.isEmpty()) {
        devicesList = devicesDao.getAllDevices();
    } else {
        searchDevices();
                }
    }

    // Métodos de acceso a datos y propiedades

    

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
