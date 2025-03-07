package org.felfeit;

import org.felfeit.model.Users;
import org.felfeit.service.UsersService;

import java.util.List;
import java.util.Scanner;

public class UsersMenu {
    private UsersService service;
    private Scanner scanner;

    public UsersMenu(UsersService usersService, Scanner scanner) {
        this.service = usersService;
        this.scanner = scanner;
    }

    public void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Kelola User ===");
            System.out.println("1. Tambah User");
            System.out.println("2. Lihat Daftar User");
            System.out.println("3. Edit User");
            System.out.println("4. Hapus User");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    addUser();
                    break;
                case 2:
                    viewListUser();
                    break;
                case 3:
                    updateUser();
                    break;
                case 4:
                    deleteUser();
                    break;
                case 5:
                    isRunning = false;
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void addUser() {
        System.out.println("\n=== Tambah User ===");
        System.out.print("Username: ");
        String username = scanner.nextLine();
        System.out.print("Password: ");
        String password = scanner.nextLine();
        System.out.print("Role (admin/employee): ");
        String role = scanner.nextLine();

        // Buat objek Users
        Users user = new Users();
        user.setUsername(username);
        user.setPassword(password);
        user.setRole(role);

        // Tambahkan user ke database
        try {
            service.addUser(user);
            System.out.println("User berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewListUser() {
        System.out.println("\n=== Daftar User ===");
        List<Users> users = service.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("Tidak ada user yang tersedia.");
        } else {
            for (Users user : users) {
                System.out.println("ID: " + user.getId());
                System.out.println("Username: " + user.getUsername());
                System.out.println("Role: " + user.getRole());
                System.out.println("-----------------------------");
            }
        }
    }

    private void updateUser() {
        System.out.println("\n=== Edit User ===");
        System.out.print("Masukkan username User yang akan diedit: ");
        String inputUsername = scanner.nextLine();
        scanner.nextLine(); // Consume newline

        // Ambil user berdasarkan username
        Users user = service.getUserByUsername(inputUsername);
        if (user == null) {
            System.out.println("User dengan username " + inputUsername + " tidak ditemukan.");
            return;
        }

        // Tampilkan data user saat ini
        System.out.println("Data saat ini:");
        System.out.println("Username: " + user.getUsername());
        System.out.println("Role: " + user.getRole());

        // Minta input baru
        System.out.print("Username (kosongkan jika tidak ingin mengubah): ");
        String username = scanner.nextLine();
        System.out.print("Password (kosongkan jika tidak ingin mengubah): ");
        String password = scanner.nextLine();
        System.out.print("Role (kosongkan jika tidak ingin mengubah): ");
        String role = scanner.nextLine();

        // Update data user jika input tidak kosong
        if (!username.isEmpty()) user.setUsername(username);
        if (!password.isEmpty()) user.setPassword(password);
        if (!role.isEmpty()) user.setRole(role);

        // Simpan perubahan
        try {
            service.updateUser(user);
            System.out.println("User berhasil diupdate!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteUser() {
        System.out.println("\n=== Hapus User ===");
        System.out.print("Masukkan ID User yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Hapus user
        try {
            service.deleteUser(id);
            System.out.println("User berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}