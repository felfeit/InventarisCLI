package org.felfeit.model;

import java.util.Date;

public class Transaction {
    private int id;
    private int productId;
    private int userId;
    private String transactionType;
    private int total;
    private Date date;

    public Transaction() {
    }

    public Transaction(int id, int productId, int userId, String transactionType, int total, Date date) {
        this.id = id;
        this.productId = productId;
        this.userId = userId;
        this.transactionType = transactionType;
        this.total = total;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int stock) {
        this.total = stock;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
