package com.beans.user;

import com.connection.DatabaseConnection;
import com.dao.UsersDao;
import com.persistence.entities.Users;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @ClassName UserListBean
 * @Package com.beans
 * @Author Rodrigo Hidalgo
 * @CopyRight Rodrigo Hidalgo
 * @Date Nov 21, 2023
 */

@Named("usersListBean")
@RequestScoped
public class UserListBean implements Serializable {

    private List<Users> userList;
    private UsersDao usersDao;
    private String searchTerm;//agregué esto-->
private List<Users> filteredUserList;//agregué esto-->

    @Inject
    UserSessionBean userSessionBean;
    
    public UserListBean() {
        this.usersDao = new UsersDao(new DatabaseConnection());
        this.userList = this.usersDao.getAllUsers();
    }
    public void searchUsers() {//agregue esto
    // Filtrar la lista de usuarios según el término de búsqueda
    filteredUserList = userList.stream()
            .filter(user -> user.getUsername().toLowerCase().contains(searchTerm.toLowerCase()))
            .collect(Collectors.toList());
}
    
    

    public List<Users> getUserList() {//agregue esto
        if (userList == null) {
            loadUserList();
        }
        return userList;
    }

    private void loadUserList() {
      if (searchTerm == null || searchTerm.isEmpty()) {
        userList = usersDao.getAllUsers();
    } else {
        searchUsers();
                }
    }

    public String editUser(Users user) {
        this.userSessionBean.setSelectedUser(user);
        return "user-form.xhtml?faces-redirect=true";
    }

    public void deleteSoftUser(String userId) {
        // Lógica para eliminar suavemente un usuario
        usersDao.softDeleteUser(userId);
        // Recargar la lista de usuarios después de eliminar suavemente
        loadUserList();
    }
}

