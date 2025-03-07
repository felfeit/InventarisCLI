package org.felfeit;

import org.felfeit.model.Product;
import org.felfeit.service.ProductService;

import java.util.List;
import java.util.Scanner;

public class ProductMenu {
    private ProductService service;
    private Scanner scanner;

    public ProductMenu(ProductService service, Scanner scanner) {
        this.service = service;
        this.scanner = scanner;
    }

    public void showMenu() {
        boolean isRunning = true;
        while (isRunning) {
            System.out.println("\n=== Kelola Produk ===");
            System.out.println("1. Tambah Produk");
            System.out.println("2. Lihat Daftar Produk");
            System.out.println("3. Edit Produk");
            System.out.println("4. Hapus Produk");
            System.out.println("5. Kembali ke Menu Utama");
            System.out.print("Pilih opsi: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (option) {
                case 1:
                    addProduct();
                    break;
                case 2:
                    getAllProducts();
                    break;
                case 3:
                    updateProduct();
                    break;
                case 4:
                    deleteProduct();
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

    private void addProduct() {
        System.out.println("\n=== Tambah Produk ===");
        System.out.print("Nama Produk: ");
        String name = scanner.nextLine();
        System.out.print("Kategori ID: ");
        int categoryId = scanner.nextInt();
        System.out.print("Stok: ");
        int stock = scanner.nextInt();
        System.out.print("Harga Beli: ");
        double purchasePrice = scanner.nextDouble();
        System.out.print("Harga Jual: ");
        double sellingPrice = scanner.nextDouble();
        scanner.nextLine(); // Consume newline
        System.out.print("Deskripsi: ");
        String description = scanner.nextLine();
        System.out.print("Supplier ID: ");
        int supplierId = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Buat objek Product
        Product product = new Product();
        product.setName(name);
        product.setCategoryId(categoryId);
        product.setStock(stock);
        product.setPurchasePrice(purchasePrice);
        product.setSellingPrice(sellingPrice);
        product.setDescription(description);
        product.setSupplierId(supplierId);

        // Tambahkan produk ke database
        try {
            service.addProduct(product);
            System.out.println("Produk berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public void getAllProducts() {
        System.out.println("\n=== Daftar Produk ===");
        List<Product> products = service.getAllProducts();
        if (products.isEmpty()) {
            System.out.println("Tidak ada produk yang tersedia.");
        } else {
            for (Product product : products) {
                System.out.println("ID: " + product.getId());
                System.out.println("Nama: " + product.getName());
                System.out.println("Kategori ID: " + product.getCategoryId());
                System.out.println("Stok: " + product.getStock());
                System.out.println("Harga Beli: " + product.getPurchasePrice());
                System.out.println("Harga Jual: " + product.getSellingPrice());
                System.out.println("Deskripsi: " + product.getDescription());
                System.out.println("Supplier ID: " + product.getSupplierId());
                System.out.println("-----------------------------");
            }
        }
    }

    private void updateProduct() {
        System.out.println("\n=== Edit Produk ===");
        System.out.print("Masukkan ID Produk yang akan diedit: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Ambil produk berdasarkan ID
        Product product = service.getProductById(id);
        if (product == null) {
            System.out.println("Produk dengan ID " + id + " tidak ditemukan.");
            return;
        }

        // Tampilkan data produk saat ini
        System.out.println("ID: " + product.getId());
        System.out.println("Nama: " + product.getName());
        System.out.println("Kategori ID: " + product.getCategoryId());
        System.out.println("Stok: " + product.getStock());
        System.out.println("Harga Beli: " + product.getPurchasePrice());
        System.out.println("Harga Jual: " + product.getSellingPrice());
        System.out.println("Deskripsi: " + product.getDescription());
        System.out.println("Supplier ID: " + product.getSupplierId());

        // Minta input baru
        System.out.print("Nama Produk (kosongkan jika tidak ingin mengubah): ");
        String name = scanner.nextLine();
        System.out.print("Kategori ID (kosongkan jika tidak ingin mengubah): ");
        String inputCategoryId = scanner.nextLine();
        System.out.print("Stok (kosongkan jika tidak ingin mengubah): ");
        String inputStock = scanner.nextLine();
        System.out.print("Harga Beli (kosongkan jika tidak ingin mengubah): ");
        String inputPurchasePrice = scanner.nextLine();
        System.out.print("Harga Jual (kosongkan jika tidak ingin mengubah): ");
        String inputSellingPrice = scanner.nextLine();
        System.out.print("Deskripsi (kosongkan jika tidak ingin mengubah): ");
        String description = scanner.nextLine();
        System.out.print("Supplier ID (kosongkan jika tidak ingin mengubah): ");
        String inputSupplierId = scanner.nextLine();

        // Update data produk jika input tidak kosong
        if (!name.isEmpty()) product.setName(name);
        if (!inputCategoryId.isEmpty()) product.setCategoryId(Integer.parseInt(inputCategoryId));
        if (!inputStock.isEmpty()) product.setStock(Integer.parseInt(inputStock));
        if (!inputPurchasePrice.isEmpty()) product.setPurchasePrice(Double.parseDouble(inputPurchasePrice));
        if (!inputSellingPrice.isEmpty()) product.setSellingPrice(Double.parseDouble(inputSellingPrice));
        if (!description.isEmpty()) product.setDescription(description);
        if (!inputSupplierId.isEmpty()) product.setSupplierId(Integer.parseInt(inputSupplierId));

        // Simpan perubahan
        try {
            service.updateProduct(product);
            System.out.println("Produk berhasil diupdate!");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private void deleteProduct() {
        System.out.println("\n=== Hapus Produk ===");
        System.out.print("Masukkan ID Produk yang akan dihapus: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Hapus produk
        try {
            service.deleteProduct(id);
            System.out.println("Produk berhasil dihapus!");
        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
