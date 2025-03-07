package org.felfeit;

import org.felfeit.model.Report;
import org.felfeit.service.ReportService;

import java.util.List;
import java.util.Scanner;

public class ReportMenu {
    private ReportService service;
    private Scanner scanner;

    public ReportMenu(ReportService reportService, Scanner scanner) {
        this.service = reportService;
        this.scanner = scanner;
    }

    public void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Generate Laporan ===");
            System.out.println("1. Laporan Stok Barang");
            System.out.println("2. Laporan Transaksi");
            System.out.println("3. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    generateStockReport();
                    break;
                case 2:
                    generateTransactionReport();
                    break;
                case 3:
                    isRunning = false;
                    System.out.println("Kembali ke Menu Utama...");
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
    }

    private void generateStockReport() {
        System.out.println("\n=== Laporan Stok Barang ===");
        List<Report> reports = service.generateStockReport();
        if (reports.isEmpty()) {
            System.out.println("Tidak ada data stok yang tersedia.");
        } else {
            for (Report report : reports) {
                System.out.println("Nama Barang: " + report.getProductName());
                System.out.println("Stok: " + report.getStock());
                System.out.println("Kategori: " + report.getCategory());
                System.out.println("-----------------------------");
            }
        }
    }

    private void generateTransactionReport() {
        System.out.println("\n=== Laporan Transaksi ===");
        List<Report> reports = service.generateTransactionReport();
        if (reports.isEmpty()) {
            System.out.println("Tidak ada data transaksi yang tersedia.");
        } else {
            for (Report report : reports) {
                System.out.println("Jenis Transaksi: " + report.getTransactionType());
                System.out.println("Jumlah: " + report.getTotal());
                System.out.println("Tanggal: " + report.getDate());
                System.out.println("Nama Barang: " + report.getProductName());
                System.out.println("Username: " + report.getUsername());
                System.out.println("-----------------------------");
            }
        }
    }
}
