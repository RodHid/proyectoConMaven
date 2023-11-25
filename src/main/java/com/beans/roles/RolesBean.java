/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.beans.roles;

import com.connection.DatabaseConnection;
import com.dao.RolesDao;
import com.persistence.entities.Roles;
import jakarta.inject.Inject;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 * @author Usuario
 */
public class RolesBean {
 


    private RolesDao rolesDao;
    private List<Roles> rolesList;
    private Roles selectedRole;
    private String searchTerm;//agregué esto-->
private List<Roles> filteredRolesList;//agregué esto-->

      @Inject
    roleSessionBean roleSessionBean;
    public RolesBean() {
        this.rolesDao = new RolesDao(new DatabaseConnection());
        this.rolesList = rolesDao.getAllRoles();
        
    }

    // Métodos de acceso a datos y propiedades

    public List<Roles> getRolesList() {
       if (rolesList == null) {
            loadRolesList();
        }
        return rolesList;
    }
    private void loadRolesList() {
      if (searchTerm == null || searchTerm.isEmpty()) {
        rolesList = rolesDao.getAllRoles();
    } else {
        searchRoles();
                }
    }
    public void searchRoles() {//agregue esto
    // Filtrar la lista de usuarios según el término de búsqueda
    filteredRolesList = rolesList.stream()
            .filter(roles -> roles.getName().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
    }

    public Roles getSelectedRole() {
        return selectedRole;
    }

    public void setSelectedRole(Roles selectedRole) {
        this.selectedRole = selectedRole;
    }
    public String editRoles(Roles roles) {
        this.roleSessionBean.setSelectedRoles(roles);
        return "user-form.xhtml?faces-redirect=true";
    }
    

   

    // Método para guardar o actualizar un rol
    public void saveRole() {
        // Lógica para guardar o actualizar un rol
        if (selectedRole.getId() == null || selectedRole.getId().isEmpty()) {
            rolesDao.createRoles(selectedRole);
        } else {
            rolesDao.updateRoles(selectedRole);
        }

        // Recargar la lista de roles después de guardar o actualizar
        rolesList = rolesDao.getAllRoles();

        // Limpiar el rol seleccionado
        selectedRole = new Roles();
    }

    // Método para eliminar suavemente un rol
    public void deleteSoftRole(String roleId) {
        // Lógica para eliminar suavemente un rol
        rolesDao.softDeleteRoles(roleId);

        // Recargar la lista de roles después de eliminar suavemente
        rolesList = rolesDao.getAllRoles();
    }

    // Método para eliminar un rol
    public void deleteRole(String roleId) {
        // Lógica para eliminar un rol
        rolesDao.deleteRoles(roleId);

        // Recargar la lista de roles después de eliminar
        rolesList = rolesDao.getAllRoles();
    }

    // Método para obtener un rol por su ID
    public Roles getRoleById(String roleId) {
        return rolesDao.getRolesById(roleId);
    }
}


