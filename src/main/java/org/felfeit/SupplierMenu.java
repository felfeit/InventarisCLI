package org.felfeit;

import org.felfeit.model.Supplier;
import org.felfeit.service.SupplierService;

import java.util.List;
import java.util.Scanner;

public class SupplierMenu {
    private SupplierService service;
    private Scanner scanner;

    public SupplierMenu(SupplierService supplierService, Scanner scanner) {
        this.service = supplierService;
        this.scanner = scanner;
    }

    public void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Kelola Supplier ===");
            System.out.println("1. Tambah Supplier");
            System.out.println("2. Lihat Daftar Supplier");
            System.out.println("3. Edit Supplier");
            System.out.println("4. Hapus Supplier");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addSupplier();
                    break;
                case 2:
                    getAllSuppliers();
                    break;
                case 3:
                    updateSupplier();
                    break;
                case 4:
                    deleteSupplier();
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

    private void addSupplier() {
        System.out.println("\n=== Tambah Supplier ===");
        System.out.print("Nama Supplier: ");
        String supplierName = scanner.nextLine();
        System.out.print("Alamat: ");
        String supplierAddress = scanner.nextLine();
        System.out.print("Telepon: ");
        String supplierPhone = scanner.nextLine();
        System.out.print("Email: ");
        String supplierEmail = scanner.nextLine();

        // Buat objek Supplier
        Supplier supplier = new Supplier();
        supplier.setName(supplierName);
        supplier.setAddress(supplierAddress);
        supplier.setPhone(supplierPhone);
        supplier.setEmail(supplierEmail);

        // Tambahkan supplier ke database
        try {
            service.addSupplier(supplier);
            System.out.println("Supplier berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void getAllSuppliers() {
        System.out.println("\n=== Daftar Supplier ===");
        List<Supplier> suppliers = service.getAllSuppliers();
        if (suppliers.isEmpty()) {
            System.out.println("Tidak ada supplier yang tersedia.");
        } else {
            for (Supplier supplier : suppliers) {
                System.out.println("ID: " + supplier.getId());
                System.out.println("Nama Supplier: " + supplier.getName());
                System.out.println("Alamat: " + supplier.getAddress());
                System.out.println("Telepon: " + supplier.getPhone());
                System.out.println("Email: " + supplier.getEmail());
                System.out.println("-----------------------------");
            }
        }
    }

    private void updateSupplier() {
        System.out.println("\n=== Edit Supplier ===");
        System.out.print("Masukkan ID Supplier yang akan diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Ambil supplier berdasarkan ID
        Supplier supplier = service.getSupplierById(id);
        if (supplier == null) {
            System.out.println("Supplier dengan ID " + id + " tidak ditemukan.");
            return;
        }

        // Tampilkan data supplier saat ini
        System.out.println("Data saat ini:");
        System.out.println("Nama Supplier: " + supplier.getName());
        System.out.println("Alamat: " + supplier.getAddress());
        System.out.println("Telepon: " + supplier.getPhone());
        System.out.println("Email: " + supplier.getEmail());

        // Minta input baru
        System.out.print("Nama Supplier (kosongkan jika tidak ingin mengubah): ");
        String supplierName = scanner.nextLine();
        System.out.print("Alamat: ");
        String supplierAddress = scanner.nextLine();
        System.out.print("Telepon: ");
        String supplierPhone = scanner.nextLine();
        System.out.print("Email: ");
        String supplierEmail = scanner.nextLine();

        // Update data supplier jika input tidak kosong
        if (!supplierName.isEmpty()) supplier.setName(supplierName);
        if (!supplierAddress.isEmpty()) supplier.setAddress(supplierAddress);
        if (!supplierPhone.isEmpty()) supplier.setPhone(supplierPhone);
        if (!supplierEmail.isEmpty()) supplier.setEmail(supplierEmail);

        // Simpan perubahan
        try {
            service.updateSupplier(supplier);
            System.out.println("Supplier berhasil diupdate!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteSupplier() {
        System.out.println("\n=== Hapus Supplier ===");
        System.out.print("Masukkan ID Supplier yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Hapus supplier
        try {
            service.deleteSupplier(id);
            System.out.println("Supplier berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
