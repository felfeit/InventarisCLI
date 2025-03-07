package org.felfeit.service;

import org.felfeit.dao.SupplierDAO;
import org.felfeit.model.Supplier;

import java.util.List;

public class SupplierService {
    private SupplierDAO supplierDAO;

    public SupplierService() {
        supplierDAO = new SupplierDAO();
    }

    // Tambah supplier dengan validasi
    public void addSupplier(Supplier supplier) {
        if (supplier.getName() == null || supplier.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama supplier tidak boleh kosong!");
        }
        supplierDAO.addSupplier(supplier);
    }

    // Ambil semua supplier
    public List<Supplier> getAllSuppliers() {
        return supplierDAO.getAllSuppliers();
    }

    // Ambil supplier berdasarkan ID
    public Supplier getSupplierById(int id) {
        return supplierDAO.getSupplierById(id);
    }

    // Update supplier
    public void updateSupplier(Supplier supplier) {
        if (supplier.getName() == null || supplier.getName().trim().isEmpty()) {
            throw new IllegalArgumentException("Nama supplier tidak boleh kosong!");
        }
        supplierDAO.updateSupplier(supplier);
    }

    // Hapus supplier
    public void deleteSupplier(int id) {
        supplierDAO.deleteSupplier(id);
    }
}