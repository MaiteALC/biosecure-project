package br.com.biosecure.model.product;

import java.time.LocalDate;

public abstract class Product {
    private final String name;
    private double price;
    private SKU sku;
    private final String manufacturer;
    private final String batchNumber;
    private final LocalDate expirationDate;
    private final PackagingType packagingType;
    private final MeasureUnit measureUnit;
    private final double quantityPerPackage;

    public Product(String name, double price, String manufacturer, String batchNumber, LocalDate expirationDate, PackagingType packagingType, MeasureUnit measureUnit, double quantityPerPackage) {
        validateString(name, "name");
        validateString(manufacturer, "manufacturer");
        validateString(batchNumber, "batch number");

        if (quantityPerPackage < 1.0 || quantityPerPackage > 99999) {
            throw new InvalidProductAttributeException("quantity per package");
        }

        if (price < 1.0 || price > 999999.99) {
            throw new InvalidProductAttributeException("price");
        }

        LocalDate todayDate = LocalDate.now();

        if (todayDate.equals(expirationDate) || 
        todayDate.isAfter(expirationDate.minusDays(5)) || 
        expirationDate.isAfter(todayDate.plusYears(15)) ) {       
            throw new InvalidProductAttributeException("expiration date");
        }

        if (packagingType == PackagingType.INDIVIDUAL && (measureUnit != MeasureUnit.UN && measureUnit != MeasureUnit.PAIR)) {
            throw new InvalidProductAttributeException("measure unit");
        }

        this.name = name;
        this.price = price;
        this.manufacturer = manufacturer;
        this.batchNumber = batchNumber;
        this.expirationDate = expirationDate;
        this.packagingType = packagingType;
        this.measureUnit = measureUnit;
        this.quantityPerPackage = packagingType == PackagingType.INDIVIDUAL ? 1 : quantityPerPackage;
    }

    public enum MeasureUnit {
        ML,
        L,
        KG,
        G,
        MG,
        UN,
        MM,
        PAIR
    }

    public enum PackagingType {
        BOX("BX"),
        PACKAGE("P"),
        BOTTLE("BT"),
        GALLON("G"),
        INDIVIDUAL("I");

        private final String code;

        PackagingType(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    protected void validateString(String value, String attributeName) {
        if (value == null || value.isBlank()) {
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

    public LocalDate getExpirationDate() {
        return expirationDate;
    }

    public PackagingType getPackagingType() {
        return packagingType;
    }

    public MeasureUnit getMeasureUnit() {
        return measureUnit;
    }

    public double getQuantityPerPackage() {
        return quantityPerPackage;
    }

    public SKU getSku() {
        // Lazy initialization of SKU
        if (this.sku == null) {
            this.sku = new SKU(this);
        }

        return sku;
    }

    public void setPrice(double newPrice) {
        if (newPrice < 1.0) {
            throw new InvalidProductAttributeException("price");
        }

        this.price = newPrice;
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

        return this.getSku().equals(obj.getSku()) && manufacturer.equals(obj.manufacturer) && name.equals(obj.name);
    }
}