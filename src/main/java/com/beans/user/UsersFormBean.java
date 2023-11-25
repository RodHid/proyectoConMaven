package com.beans;

import com.connection.DatabaseConnection;
import com.dao.RolesDao;
import com.dao.UsersDao;
import com.persistence.entities.Roles;
import com.persistence.entities.Users;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

/**
 * @ClassName: UsersBean
 * @Package: com.beans
 * @Date: Nov 18, 2023
 * @CopyRigth: Rodrigo Hidalgo
 * @author Rodrigo Hidalgo
 */
@Named("usersFormBean")
@RequestScoped
public class UsersFormBean implements Serializable {

    private UsersDao usersDao;
    private Users selectedUser;
    private List<Roles> allRoles; // Agregamos una lista de todos los roles disponibles
    private DatabaseConnection connection;
    private RolesDao rolesDao;
    private Users loggedUser;
    private String selectedRole;

    @Inject
    private UserSessionBean sessionBean;

    public UsersFormBean() {
        this.connection = new DatabaseConnection();
        // Inicialización del Managed Bean y la conexión a la base de datos
        this.usersDao = new UsersDao(this.connection);
        this.rolesDao = new RolesDao(this.connection);
        this.allRoles = rolesDao.getAllRoles();
        this.selectedUser = new Users();
    }

    public String getRoleSelected() {
        return selectedRole;
    }

    public void setRoleSelected(String roleSelected) {
        this.selectedRole = roleSelected;
    }

    public Users getSelectedUser() {
        return (this.sessionBean.getSelectedUser() != null) ? this.sessionBean.getSelectedUser() : selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    public List<Roles> getAllRoles() {
        return allRoles;
    }

    public void setAllRoles(List<Roles> allRoles) {
        this.allRoles = allRoles;
    }

    // Método para guardar o actualizar un usuario
    public String saveUser() {
        // Get Logged User from session
        this.loggedUser = this.sessionBean.getActiveUser();
        // Encriptar la contraseña con MD5 antes de guardar o actualizar
        selectedUser.setUserPassword(encryptSHA256(selectedUser.getUserPassword()));
        // Determinar si se debe crear un nuevo usuario o actualizar uno existente
        if (selectedUser.getId() == null || selectedUser.getId().isEmpty()) {
            // Crear un nuevo usuario
            usersDao.createUser(selectedUser, this.loggedUser, this.selectedRole);
        } else {
            // Actualizar un usuario existente
            usersDao.updateUser(selectedUser, this.loggedUser, this.selectedRole);
        }

        // Limpiar el usuario seleccionado
        selectedUser = new Users();

        // Redirigir a la lista
        return "/pages/user/users-list.xhtml?faces-redirect=true";
    }

    private static String encryptSHA256(String password) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            md.update(password.getBytes());
            byte[] bytes = md.digest();
            StringBuilder sb = new StringBuilder();
            for (byte b : bytes) {
                sb.append(String.format("%02x", b));
            }
            return sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }
}
