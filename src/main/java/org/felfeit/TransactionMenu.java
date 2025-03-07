package org.felfeit;

import org.felfeit.model.Transaction;
import org.felfeit.service.TransactionService;

import java.sql.Connection;
import java.util.List;
import java.util.Scanner;

public class TransactionMenu {
    private TransactionService service;
    private Scanner scanner;

    public TransactionMenu(TransactionService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Kelola Transaksi ===");
            System.out.println("1. Catat Transaksi Masuk");
            System.out.println("2. Catat Transaksi Keluar");
            System.out.println("3. Lihat Riwayat Transaksi");
            System.out.println("4. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    recordTransactionEntry();
                    break;
                case 2:
                    recordTransactionOut();
                    break;
                case 3:
                    viewTransactionHistory();
                    break;
                case 4:
                    isRunning = false;
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void recordTransactionEntry() {
        System.out.println("\n=== Catat Transaksi Masuk ===");
        System.out.print("ID Barang: ");
        int productId = scanner.nextInt();
        System.out.print("Jumlah: ");
        int total = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("ID User: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Buat objek Transaction
        Transaction transaction = new Transaction();
        transaction.setProductId(productId);
        transaction.setTransactionType("masuk");
        transaction.setTotal(total);
        transaction.setUserId(userId);

        // Catat transaksi
        try {
            service.addTransaction(transaction);
            System.out.println("Transaksi masuk berhasil dicatat!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void recordTransactionOut() {
        System.out.println("\n=== Catat Transaksi Keluar ===");
        System.out.print("ID Barang: ");
        int productId = scanner.nextInt();
        System.out.print("Jumlah: ");
        int total = scanner.nextInt();
        scanner.nextLine(); // Consume newline
        System.out.print("ID User: ");
        int userId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Buat objek Transaction
        Transaction transaction = new Transaction();
        transaction.setProductId(productId);
        transaction.setTransactionType("keluar");
        transaction.setTotal(total);
        transaction.setUserId(userId);

        // Catat transaksi
        try {
            service.addTransaction(transaction);
            System.out.println("Transaksi keluar berhasil dicatat!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void viewTransactionHistory() {
        System.out.println("\n=== Riwayat Transaksi ===");
        List<Transaction> transactions = service.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("Tidak ada transaksi yang tersedia.");
        } else {
            for (Transaction transaction : transactions) {
                System.out.println("ID: " + transaction.getId());
                System.out.println("Barang ID: " + transaction.getProductId());
                System.out.println("Jenis Transaksi: " + transaction.getTransactionType());
                System.out.println("Jumlah: " + transaction.getTotal());
                System.out.println("Tanggal: " + transaction.getDate());
                System.out.println("User ID: " + transaction.getUserId());
                System.out.println("-----------------------------");
            }
        }
    }
}
