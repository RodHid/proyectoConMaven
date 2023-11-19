/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package com.beans;

import com.connection.DatabaseConnection;
import com.dao.UsersDao;
import com.persistence.entities.Users;
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
public class UsersBean {

    private UsersDao usersDao;
    private List<Users> userList;
    private Users selectedUser;

    public UsersBean() {
        // Inicialización del Managed Bean y la conexión a la base de datos
        this.usersDao = new UsersDao(new DatabaseConnection());
        this.userList = usersDao.getAllUsers();
        this.selectedUser = new Users();
    }

    // Métodos de acceso a datos y propiedades

    public List<Users> getUserList() {
        return userList;
    }

    public Users getSelectedUser() {
        return selectedUser;
    }

    public void setSelectedUser(Users selectedUser) {
        this.selectedUser = selectedUser;
    }

    // Método para guardar o actualizar un usuario
    public void saveUser() {
        // Encriptar la contraseña con MD5 antes de guardar o actualizar
        selectedUser.setUserPassword(encryptSHA256(selectedUser.getUserPassword()));

        // Determinar si se debe crear un nuevo usuario o actualizar uno existente
        if (selectedUser.getId() == null || selectedUser.getId().isEmpty()) {
            // Crear un nuevo usuario
            usersDao.createUser(selectedUser);
        } else {
            // Actualizar un usuario existente
            usersDao.updateUser(selectedUser);
        }

        // Recargar la lista de usuarios después de guardar o actualizar
        userList = usersDao.getAllUsers();

        // Limpiar el usuario seleccionado
        selectedUser = new Users();
    }

    // Método para eliminar suavemente un usuario
    public void deleteSoftUser(String userId) {
        // Eliminar suavemente el usuario
        usersDao.softDeleteUser(userId);

        // Recargar la lista de usuarios después de eliminar suavemente
        userList = usersDao.getAllUsers();
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