package org.felfeit.model;

public class Product {
    private int id;
    private String name;
    private String description;
    private int stock;
    private double purchasePrice;
    private double sellingPrice;
    private int categoryId;
    private int supplierId;

    public Product() {
    }

    public Product(int id, String name, String description, int stock, double purchasePrice, double sellingPrice, int categoryId, int supplierId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.purchasePrice = purchasePrice;
        this.sellingPrice = sellingPrice;
        this.categoryId = categoryId;
        this.supplierId = supplierId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getSellingPrice() {
        return sellingPrice;
    }

    public void setSellingPrice(double sellingPrice) {
        this.sellingPrice = sellingPrice;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }
}
