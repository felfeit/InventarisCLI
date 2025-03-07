package org.felfeit.service;

import org.felfeit.dao.UsersDAO;
import org.felfeit.model.Users;

import java.util.List;

public class UsersService {
    private UsersDAO usersDAO;

    public UsersService() {
        usersDAO = new UsersDAO();
    }

    // Tambah user dengan validasi
    public void addUser(Users user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong!");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong!");
        }
        usersDAO.addUser(user);
    }

    // Ambil semua user
    public List<Users> getAllUsers() {
        return usersDAO.getAllUsers();
    }

    // Ambil user berdasarkan Username
    public Users getUserByUsername(String username) {
        return usersDAO.getUserByUsername(username);
    }

    // Update user
    public void updateUser(Users user) {
        if (user.getUsername() == null || user.getUsername().trim().isEmpty()) {
            throw new IllegalArgumentException("Username tidak boleh kosong!");
        }
        if (user.getPassword() == null || user.getPassword().trim().isEmpty()) {
            throw new IllegalArgumentException("Password tidak boleh kosong!");
        }
        usersDAO.updateUser(user);
    }

    // Hapus user
    public void deleteUser(int id) {
        usersDAO.deleteUser(id);
    }
}