package br.com.biosecure.domain.product;

import java.util.Date;

public abstract class Product {
    private String name;
    private double price;
    private SKU sku;
    private String manufacturer;
    private String batchNumber;
    private Date expirationDate;
    private int qtdPerPackage;

    public Product(String name, double price, String manufacturer, String batchNumber, Date expirationDate, int qtdPerPackage) {
        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.expirationDate = expirationDate;
        this.qtdPerPackage = qtdPerPackage;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public Date getExpirationDate() {
        return expirationDate;
    }

    public int getQtdPerPackage() {
        return qtdPerPackage;
    }

    public SKU getSku() {
        if (this.sku == null) {
            // Lazy initialization of SKU
            this.sku = SKU.generateFor(this); 
        }

        return this.sku;
    }

    public void setPrice(double newPrice) {
        if (newPrice < 1.0) {
            throw new IllegalArgumentException();
        }

        this.price = newPrice;
    }

    public void setQtdPerPackage(int newQtd) {
        if (newQtd < 1) {
            throw new IllegalArgumentException();
        }

        this.qtdPerPackage = newQtd;
    }

    @Override
    public String toString() {
        return "Product [name:" + name + ", price:" + price + ", sku:" + sku + ", manufacturer:" + manufacturer + ", batchNumber:" + batchNumber + ", expirationDate:" + expirationDate + ", qtdPerPackage:" + qtdPerPackage + "]";
    }
}