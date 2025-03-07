package org.felfeit;


import org.felfeit.model.Users;
import org.felfeit.service.*;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Inisialisasi service dan scanner
        ProductService productService = new ProductService();
        SupplierService supplierService = new SupplierService();
        TransactionService transactionService = new TransactionService();
        UsersService usersService = new UsersService();
        ReportService reportService = new ReportService();
        Scanner scanner = new Scanner(System.in);

        // Menu Login
        Users loggedInUser = null;
        while (loggedInUser == null) {
            System.out.println("=== Login ===");
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            // Validasi login
            loggedInUser = usersService.getUserByUsername(username);
            if (loggedInUser != null && loggedInUser.getPassword().equals(password)) {
                System.out.println("Login berhasil! Selamat datang, " + loggedInUser.getUsername() + ".");
            } else {
                System.out.println("Username atau password salah. Silakan coba lagi.");
                loggedInUser = null;
            }
        }

        // Menu Utama berdasarkan role
        boolean isRunning = true;
        while (isRunning) {
            if (loggedInUser.getRole().equals("admin")) {
                // Menu Admin
                System.out.println("\n=== Sistem Manajemen Inventaris (Admin) ===");
                System.out.println("1. Kelola Produk");
                System.out.println("2. Kelola Supplier");
                System.out.println("3. Kelola Transaksi");
                System.out.println("4. Kelola User");
                System.out.println("5. Generate Laporan");
                System.out.println("6. Logout");
                System.out.print("Pilih opsi: ");
                int pilihan = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (pilihan) {
                    case 1:
                        ProductMenu productMenu = new ProductMenu(productService, scanner);
                        productMenu.showMenu();
                        break;
                    case 2:
                        SupplierMenu supplierMenu = new SupplierMenu(supplierService, scanner);
                        supplierMenu.showMenu();
                        break;
                    case 3:
                        TransactionMenu transactionMenu = new TransactionMenu(transactionService, scanner);
                        transactionMenu.showMenu();
                        break;
                    case 4:
                        UsersMenu userMenu = new UsersMenu(usersService, scanner);
                        userMenu.showMenu();
                        break;
                    case 5:
                        ReportMenu reportMenu = new ReportMenu(reportService, scanner);
                        reportMenu.showMenu();
                        break;
                    case 6:
                        isRunning = false;
                        System.out.println("Logout berhasil. Sampai jumpa!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            } else if (loggedInUser.getRole().equals("employee")) {
                // Menu Employee
                System.out.println("\n=== Sistem Manajemen Inventaris (Employee) ===");
                System.out.println("1. Lihat Stok Barang");
                System.out.println("2. Catat Transaksi");
                System.out.println("3. Logout");
                System.out.print("Pilih opsi: ");
                int choice = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                switch (choice) {
                    case 1:
                        // Lihat stok barang
                        ProductMenu productMenu = new ProductMenu(productService, scanner);
                        productMenu.getAllProducts();
                        break;
                    case 2:
                        // Catat transaksi
                        TransactionMenu transactionMenu = new TransactionMenu(transactionService, scanner);
                        transactionMenu.showMenu();
                        break;
                    case 3:
                        isRunning = false;
                        System.out.println("Logout berhasil. Sampai jumpa!");
                        break;
                    default:
                        System.out.println("Pilihan tidak valid!");
                }
            }
        }
    }
}