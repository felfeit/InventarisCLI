package org.felfeit.dao;

import org.felfeit.model.Transaction;
import org.felfeit.utils.DatabaseUtil;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TransactionDAO {
    private Connection connection;

    public TransactionDAO() {
        connection = DatabaseUtil.getConnection();
    }

    // Tambah transaksi
    public void addTransaction(Transaction transaction) {
        String sql = "INSERT INTO transaction (product_id, transaction_type, total, date, user_id) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getProductId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setInt(3, transaction.getTotal());
            stmt.setTimestamp(4, new Timestamp(transaction.getDate().getTime()));
            stmt.setInt(5, transaction.getUserId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Ambil semua transaksi
    public List<Transaction> getAllTransactions() {
        List<Transaction> transactions = new ArrayList<>();
        String sql = "SELECT * FROM transaction";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Transaction transaction = new Transaction();
                transaction.setId(rs.getInt("transaction_id"));
                transaction.setProductId(rs.getInt("product_id"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTotal(rs.getInt("total"));
                transaction.setDate(rs.getTimestamp("date"));
                transaction.setUserId(rs.getInt("user_id"));
                transactions.add(transaction);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transactions;
    }

    // Ambil transaksi berdasarkan ID
    public Transaction getTransactionById(int transactionId) {
        Transaction transaction = null;
        String sql = "SELECT * FROM transaction WHERE transaction_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, transactionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                transaction = new Transaction();
                transaction.setId(rs.getInt("transaction_id"));
                transaction.setProductId(rs.getInt("product_id"));
                transaction.setTransactionType(rs.getString("transaction_type"));
                transaction.setTotal(rs.getInt("total"));
                transaction.setDate(rs.getTimestamp("date"));
                transaction.setUserId(rs.getInt("user_id"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return transaction;
    }

    // Update transaksi
    public void updateTransaction(Transaction transaction) {
        String sql = "UPDATE transaction SET transaction_id = ?, transaction_type = ?, total = ?, date = ?, user_id = ? WHERE transaction_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, transaction.getProductId());
            stmt.setString(2, transaction.getTransactionType());
            stmt.setInt(3, transaction.getTotal());
            stmt.setTimestamp(4, new Timestamp(transaction.getDate().getTime()));
            stmt.setInt(5, transaction.getUserId());
            stmt.setInt(6, transaction.getId());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Hapus transaksi
    public void deleteTransaction(int transactionId) {
        String sql = "DELETE FROM transaction WHERE transaction_id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, transactionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
