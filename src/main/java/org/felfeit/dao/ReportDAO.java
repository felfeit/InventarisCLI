package org.felfeit.dao;

import org.felfeit.model.Report;
import org.felfeit.utils.DatabaseUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ReportDAO {
    private Connection connection;

    public ReportDAO() {
        connection = DatabaseUtil.getConnection();
    }

    // Generate laporan stok
    public List<Report> generateStockReport() {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT p.product_name, p.product_stock, k.category_name FROM product p JOIN category c ON p.category_id = c.category_id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Report report = new Report();
                report.setProductName(rs.getString("product_name"));
                report.setStock(rs.getInt("product_stock"));
                report.setCategory(rs.getString("category_name"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }

    // Generate laporan transaksi
    public List<Report> generateTransactionReport() {
        List<Report> reports = new ArrayList<>();
        String sql = "SELECT t.transaction_type, t.total, t.date, p.product_name, u.username FROM transaction t " +
                "JOIN product p ON t.product_id = p.product_id " +
                "JOIN users u ON t.user_id = u.user_id";
        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Report report = new Report();
                report.setTransactionType(rs.getString("transaction_type"));
                report.setTotal(rs.getInt("total"));
                report.setDate(rs.getTimestamp("date"));
                report.setProductName(rs.getString("product_name"));
                report.setUsername(rs.getString("username"));
                reports.add(report);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return reports;
    }
}
