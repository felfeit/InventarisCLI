package org.felfeit.service;

import org.felfeit.dao.ReportDAO;
import org.felfeit.model.Report;

import java.util.List;

public class ReportService {
    private ReportDAO reportDAO;

    public ReportService() {
        reportDAO = new ReportDAO();
    }

    // Generate laporan stok
    public List<Report> generateStockReport() {
        return reportDAO.generateStockReport();
    }

    // Generate laporan transaksi
    public List<Report> generateTransactionReport() {
        return reportDAO.generateTransactionReport();
    }
}