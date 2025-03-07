package org.felfeit.dao;

import org.felfeit.model.Product;
import org.felfeit.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductDAO {
    private Connection connection;

    public ProductDAO() {
        connection = DatabaseUtil.getConnection();
    }

    public void addProduct(Product product) {
        String sql = "INSERT INTO product (product_name, product_description, product_stock, product_purchase_price, product_selling_price, category_id, supplier_id) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setString(2, product.getDescription());
            stmt.setInt(3, product.getStock());
            stmt.setDouble(4, product.getPurchasePrice());
            stmt.setDouble(5, product.getSellingPrice());
            stmt.setInt(6, product.getCategoryId());
            stmt.setInt(7, product.getSupplierId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();

        String sql = "SELECT * FROM product";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql);
        ) {
            while (rs.next()) {
                Product product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setDescription(rs.getString("product_description"));
                product.setStock(rs.getInt("product_stock"));
                product.setPurchasePrice(rs.getDouble("product_purchase_price"));
                product.setSellingPrice(rs.getDouble("product_selling_price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setSupplierId(rs.getInt("supplier_id"));
                products.add(product);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return products;
    }

    public Product getProductById(int productId) {
        Product product = null;
        String sql = "SELECT * FROM product WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                product = new Product();
                product.setId(rs.getInt("product_id"));
                product.setName(rs.getString("product_name"));
                product.setDescription(rs.getString("product_description"));
                product.setStock(rs.getInt("product_stock"));
                product.setPurchasePrice(rs.getDouble("product_purchase_price"));
                product.setSellingPrice(rs.getDouble("product_selling_price"));
                product.setCategoryId(rs.getInt("category_id"));
                product.setSupplierId(rs.getInt("supplier_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

    public void updateProduct(Product product) {
        String sql = "UPDATE product SET product_name = ?, category_id = ?, product_stock = ?, product_purchase_price = ?, product_selling_price = ?, product_description = ?, supplier_id = ? WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, product.getName());
            stmt.setInt(6, product.getCategoryId());
            stmt.setInt(3, product.getStock());
            stmt.setDouble(4, product.getPurchasePrice());
            stmt.setDouble(5, product.getSellingPrice());
            stmt.setString(2, product.getDescription());
            stmt.setInt(7, product.getSupplierId());
            stmt.setInt(8, product.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void deleteProduct(int productId) {
        String sql = "DELETE FROM product WHERE product_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, productId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
