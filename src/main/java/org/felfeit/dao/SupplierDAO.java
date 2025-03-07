package org.felfeit.dao;

import org.felfeit.model.Supplier;
import org.felfeit.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierDAO {
    private Connection connection;

    public SupplierDAO() {
        connection = DatabaseUtil.getConnection();
    }

    // Tambah supplier
    public void addSupplier(Supplier supplier) {
        String sql = "INSERT INTO supplier (supplier_name, supplier_address, supplier_phone, supplier_email) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getPhone());
            stmt.setString(4, supplier.getEmail());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ambil semua supplier
    public List<Supplier> getAllSuppliers() {
        List<Supplier> suppliers = new ArrayList<>();
        String sql = "SELECT * FROM supplier";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("supplier_name"));
                supplier.setAddress(rs.getString("supplier_address"));
                supplier.setPhone(rs.getString("supplier_phone"));
                supplier.setEmail(rs.getString("supplier_email"));
                suppliers.add(supplier);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return suppliers;
    }

    // Ambil supplier berdasarkan ID
    public Supplier getSupplierById(int id) {
        Supplier supplier = null;
        String sql = "SELECT * FROM supplier WHERE supplier_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                supplier = new Supplier();
                supplier.setId(rs.getInt("supplier_id"));
                supplier.setName(rs.getString("supplier_name"));
                supplier.setAddress(rs.getString("supplier_address"));
                supplier.setPhone(rs.getString("supplier_phone"));
                supplier.setEmail(rs.getString("supplier_email"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return supplier;
    }

    // Update supplier
    public void updateSupplier(Supplier supplier) {
        String sql = "UPDATE supplier SET supplier_name = ?, supplier_address = ?, supplier_phone = ?, supplier_email = ? WHERE supplier_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, supplier.getName());
            stmt.setString(2, supplier.getAddress());
            stmt.setString(3, supplier.getPhone());
            stmt.setString(4, supplier.getEmail());
            stmt.setInt(5, supplier.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hapus supplier
    public void deleteSupplier(int id) {
        String sql = "DELETE FROM supplier WHERE supplier_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
