/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans.deviceTypes;
import com.connection.DatabaseConnection;
import com.dao.DeviceTypeDao;
import com.persistence.entities.DeviceTypes;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.stream.Collectors;


/**
 *
 * @author Usuario
 */
@Named(value = "typesListBean")
@RequestScoped
public class TypesListBean {
    
    private DeviceTypeDao deviceTypeDao;
    private List<DeviceTypes> deviceTypesList;
    private DeviceTypes selectedDeviceType;
     private String searchTerm;//agregué esto-->
private List<DeviceTypes> filteredDeviceTypesList;//agregué esto-->

    
    @Inject TypesSessionBean typesSessionBean;

    public TypesListBean() {
        this.deviceTypeDao = new DeviceTypeDao(new DatabaseConnection());
        this.deviceTypesList = deviceTypeDao.getAllDeviceTypes();
        this.selectedDeviceType = new DeviceTypes();
    }
    public void searchDevicesTypes() {//agregue esto
    // Filtrar la lista de usuarios según el término de búsqueda
    filteredDeviceTypesList = deviceTypesList.stream()
            .filter(user -> user.getTypeName().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
    }
    public List<DeviceTypes> getDeviceTypesList() {//agregue esto
        if (deviceTypesList == null) {
            loadDeviceTypesList();
        }
        return deviceTypesList;
    }
    private void loadDeviceTypesList() {
      if (searchTerm == null || searchTerm.isEmpty()) {
        deviceTypesList = deviceTypeDao.getAllDeviceTypes();
    } else {
        searchDevicesTypes();
                }
    }

    // Métodos de acceso a datos y propiedades

   
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
    
    public String editUser(DeviceTypes type) {
        this.typesSessionBean.setSelectedType(type);
        return "type-form.xhtml?faces-redirect=true";
    }

    // Método para eliminar suavemente un DeviceType
    public void deleteSoftDeviceType(String deviceTypeId) {
        // Lógica para eliminar suavemente un DeviceType
        deviceTypeDao.softDeleteDeviceType(deviceTypeId);

        // Recargar la lista de DeviceTypes después de eliminar suavemente
        deviceTypesList = deviceTypeDao.getAllDeviceTypes();
    }
}
