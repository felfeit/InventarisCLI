package org.felfeit.service;

import org.felfeit.dao.TransactionDAO;
import org.felfeit.model.Transaction;

import java.util.List;

public class TransactionService {
    private TransactionDAO transactionDAO;

    public TransactionService() {
        transactionDAO = new TransactionDAO();
    }

    // Tambah transaksi dengan validasi
    public void addTransaction(Transaction transaction) {
        if (transaction.getTransactionType() == null || transaction.getTransactionType().trim().isEmpty()) {
            throw new IllegalArgumentException("Jenis transaksi tidak boleh kosong!");
        }
        if (transaction.getTotal() <= 0) {
            throw new IllegalArgumentException("Jumlah transaksi harus lebih dari 0!");
        }
        transactionDAO.addTransaction(transaction);
    }

    // Ambil semua transaksi
    public List<Transaction> getAllTransactions() {
        return transactionDAO.getAllTransactions();
    }

    // Ambil transaksi berdasarkan ID
    public Transaction getTransactionById(int id) {
        return transactionDAO.getTransactionById(id);
    }

    // Update transaksi
    public void updateTransaction(Transaction transaction) {
        if (transaction.getTransactionType() == null || transaction.getTransactionType().trim().isEmpty()) {
            throw new IllegalArgumentException("Jenis transaksi tidak boleh kosong!");
        }
        if (transaction.getTotal() <= 0) {
            throw new IllegalArgumentException("Jumlah transaksi harus lebih dari 0!");
        }
        transactionDAO.updateTransaction(transaction);
    }

    // Hapus transaksi
    public void deleteTransaction(int id) {
        transactionDAO.deleteTransaction(id);
    }
}