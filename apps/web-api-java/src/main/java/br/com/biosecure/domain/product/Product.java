package br.com.biosecure.domain.product;

import java.util.Date;
import br.com.biosecure.domain.product.InvalidProductAttributeException;

public abstract class Product {
    private String name;
    private double price;
    private SKU sku;
    private String manufacturer;
    private String batchNumber;
    private Date expirationDate;
    private PackagingType packagingType;
    private MeasureUnity measureUnity;
    private int qtdPerPackage;

    public Product(String name, double price, String manufacturer, String batchNumber, Date expirationDate, PackagingType packagingType, MeasureUnity measureUnity, int qtdPerPackage) {
        validateString(name, "name");
        validateString(manufacturer, "manufacturer");
        validateString(batchNumber, "batch number");

        if (qtdPerPackage < 1) {
            throw new InvalidProductAttributeException("quantity per package");
        }

        if (price < 1.0) {
            throw new InvalidProductAttributeException("price");
        }

        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.expirationDate = expirationDate;
        this.packagingType = packagingType;
        this.measureUnity = measureUnity;
        this.qtdPerPackage = qtdPerPackage;
    }

    public enum MeasureUnity {
        ML,
        L,
        KG,
        G,
        UN, 
        PAIR
    }

    public enum PackagingType {
        BOX,
        PACKAGE,
        BOTTLE,
        GALLON,
        INDIVIDUAL
    }

    private void validateString(String value, String attributeName) {
        if (value == null || value.trim().isEmpty()) {
            throw new InvalidProductAttributeException(attributeName);
        }
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

    public PackagingType getPackagingType() {
        return packagingType;
    }

    public MeasureUnity getMeasureUnity() {
        return measureUnity;
    }

    public int getQtdPerPackage() {
        return qtdPerPackage;
    }

    public SKU getSku() {
        // Lazy initialization of SKU
        if (this.sku == null) {
            this.sku = SKU.generateFor(this); 
        }

        return this.sku;
    }

    public void setPrice(double newPrice) {
        if (newPrice < 1.0) {
            throw new InvalidProductAttributeException("price");
        }

        this.price = newPrice;
    }

    public void setQtdPerPackage(int newQtd) {
        if (newQtd < 1 || newQtd >= 9999) {
            throw new InvalidProductAttributeException("quantity per package");
        }
 
        this.qtdPerPackage = newQtd;
    }

    @Override
    public String toString() {
        return "Product [name:" + name + ", price:" + price + ", sku:" + sku + ", manufacturer:" + manufacturer + ", batchNumber:" + batchNumber + ", expirationDate:" + expirationDate + ", qtdPerPackage:" + qtdPerPackage + "]";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Product obj = (Product) o;

        if (!sku.equals(obj.sku) || manufacturer != obj.manufacturer || name != obj.name) {
            return false;
        }

        return true;
    }
}