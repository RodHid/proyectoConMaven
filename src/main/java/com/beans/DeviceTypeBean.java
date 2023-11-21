/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans;
import com.connection.DatabaseConnection;
import com.dao.DeviceTypeDao;
import com.persistence.entities.DeviceTypes;

import java.util.List;


/**
 *
 * @author Usuario
 */
public class DeviceTypeBean {
    




    private DeviceTypeDao deviceTypeDao;
    private List<DeviceTypes> deviceTypesList;
    private DeviceTypes selectedDeviceType;

    public DeviceTypeBean() {
        this.deviceTypeDao = new DeviceTypeDao(new DatabaseConnection());
        this.deviceTypesList = deviceTypeDao.getAllDeviceTypes();
        this.selectedDeviceType = new DeviceTypes();
    }

    // Métodos de acceso a datos y propiedades

    public List<DeviceTypes> getDeviceTypesList() {
        return deviceTypesList;
    }

    public DeviceTypes getSelectedDeviceType() {
        return selectedDeviceType;
    }

    public void setSelectedDeviceType(DeviceTypes selectedDeviceType) {
        this.selectedDeviceType = selectedDeviceType;
    }

    // Método para guardar o actualizar un DeviceType
    public void saveDeviceType() {
        // Lógica para guardar o actualizar un DeviceType
        if (selectedDeviceType.getId() == null || selectedDeviceType.getId().isEmpty()) {
            deviceTypeDao.createDeviceType(selectedDeviceType);
        } else {
            deviceTypeDao.updateDeviceType(selectedDeviceType);
        }

        // Recargar la lista de DeviceTypes después de guardar o actualizar
        deviceTypesList = deviceTypeDao.getAllDeviceTypes();

        // Limpiar el DeviceType seleccionado
        selectedDeviceType = new DeviceTypes();
    }

    // Método para eliminar suavemente un DeviceType
    public void deleteSoftDeviceType(String deviceTypeId) {
        // Lógica para eliminar suavemente un DeviceType
        deviceTypeDao.softDeleteDeviceType(deviceTypeId);

        // Recargar la lista de DeviceTypes después de eliminar suavemente
        deviceTypesList = deviceTypeDao.getAllDeviceTypes();
    }
}
