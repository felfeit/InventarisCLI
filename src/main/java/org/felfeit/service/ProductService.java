package org.felfeit.service;

import org.felfeit.dao.ProductDAO;
import org.felfeit.model.Product;

import java.util.List;

public class ProductService {
    private ProductDAO productDAO;

    public ProductService() {
        productDAO = new ProductDAO();
    }

    // Tambah produk dengan validasi
    public void addProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong!");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif!");
        }
        productDAO.addProduct(product);
    }

    // Ambil semua produk
    public List<Product> getAllProducts() {
        return productDAO.getAllProducts();
    }

    // Ambil produk berdasarkan ID
    public Product getProductById(int id) {
        return productDAO.getProductById(id);
    }

    // Update produk
    public void updateProduct(Product product) {
        if (product.getName() == null || product.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama produk tidak boleh kosong!");
        }
        if (product.getStock() < 0) {
            throw new IllegalArgumentException("Stok tidak boleh negatif!");
        }
        productDAO.updateProduct(product);
    }

    // Hapus produk
    public void deleteProduct(int id) {
        productDAO.deleteProduct(id);
    }
}
