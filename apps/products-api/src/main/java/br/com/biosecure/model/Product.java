package br.com.biosecure.model;

import br.com.biosecure.utils.*;
import br.com.biosecure.utils.NumberUtils;
import br.com.biosecure.utils.StringUtils;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;
import java.util.Objects;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class Product {
    private String name;
    private double price;
    @Embedded
    private SKU sku;
    private String manufacturer;
    private String batchNumber;
    private LocalDate expirationDate;
    @Enumerated(EnumType.STRING)
    private PackagingType packagingType;
    @Enumerated(EnumType.STRING)
    private MeasureUnit measureUnit;
    private double quantityPerPackage;
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    protected static final int MIN_NAMES_LENGTH = 2;
    protected static final int MAX_NAMES_LENGTH = 70;
    protected static final double MAX_PRICE = 99999.99;
    protected static final double MAX_QUANTITY = 99999;

    protected Product(ProductBuilder<?, ?> builder) {
        this.name = builder.name;
        this.price = builder.price;
        this.manufacturer = builder.manufacturer;
        this.batchNumber = builder.batchNumber;
        this.expirationDate = builder.expirationDate;
        this.packagingType = builder.packagingType;
        this.measureUnit = packagingType == PackagingType.INDIVIDUAL ? MeasureUnit.U : builder.measureUnit;
        this.quantityPerPackage = builder.packagingType == PackagingType.INDIVIDUAL ? 1 : builder.quantityPerPackage;
    }

    public static abstract class ProductBuilder<P extends Product, B extends ProductBuilder<P, B>> {
        protected String name;
        protected double price;
        protected String manufacturer;
        protected String batchNumber;
        protected LocalDate expirationDate;
        protected PackagingType packagingType;
        protected MeasureUnit measureUnit;
        protected double quantityPerPackage;

        protected final NotificationContext productNotification = new NotificationContext();

        protected abstract B self();

        public abstract P build();

        public B name(String name) {
            StringUtils.validateString(name, MIN_NAMES_LENGTH, "name", MAX_NAMES_LENGTH, true, productNotification);

            this.name = name;
            return self();
        }

        public B price(double price) {
            NumberUtils.validateNumericalAttribute(price, 0.01, "price", MAX_PRICE, productNotification);

            this.price = price;
            return self();
        }

        public B manufacturer(String manufacturer) {
            StringUtils.validateString(manufacturer, MIN_NAMES_LENGTH, "manufacturer", MAX_NAMES_LENGTH, true, productNotification);

            this.manufacturer = manufacturer;
            return self();
        }

        public B batchNumber(String batchNumber) {
            StringUtils.validateString(batchNumber, "batch number", true, productNotification);

            this.batchNumber = batchNumber;
            return self();
        }

        public B expirationDate(LocalDate expirationDate) {
            NumberUtils.validateExpirationDate(expirationDate, "expiration date", productNotification);

            this.expirationDate = expirationDate;
            return self();
        }

        public B packagingType(PackagingType packagingType) {
            ErrorAggregator.verifyNull(packagingType, "packaging type", productNotification);

            this.packagingType = packagingType;
            return self();
        }

        public B measureUnit(MeasureUnit measureUnit) {
            if (measureUnit == null) {
                productNotification.addError("measure unit", "measure unit mustn't be null");
            }

            this.measureUnit = measureUnit;
            return self();
        }

        public B quantityPerPackage(double quantityPerPackage) {
            NumberUtils.validateNumericalAttribute(quantityPerPackage, 1, "quantity per package", MAX_QUANTITY, productNotification);

            this.quantityPerPackage = quantityPerPackage;
            return self();
        }
    }

    public enum MeasureUnit {
        ML,
        L,
        KG,
        G,
        MG,
        U,
        MM
    }

    @Getter
    @AllArgsConstructor
    public enum PackagingType {
        BOX("BX"),
        PACKAGE("P"),
        BOTTLE("BT"),
        GALLON("G"),
        INDIVIDUAL("INDV");

        private final String code;
    }

    public SKU getSku() {
        if (this.sku == null) {
            this.sku = new SKU(this);  // Lazy initialization of SKU
        }
        return sku;
    }

    public void changePrice(double newPrice) {
        NotificationContext productNotification = new NotificationContext();

        NumberUtils.validateNumericalAttribute(price, 0.01, "price", MAX_PRICE, productNotification);

        if (productNotification.hasErrors()) {
            throw new InvalidProductAttributeException(productNotification.getErrors());
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

        return Objects.equals(this.id, obj.id);
    }
}
