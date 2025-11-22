package br.com.biosecure.model.product;

import java.time.LocalDate;

public class PetriDishes extends SampleContainer {
    private final int divNum;
    private final boolean grid;
    private final boolean ventilated;
    private final double diameter;
    private final double height;

    public PetriDishes(String name, double price, String manufacturer, SterilizationMethod sterilizationMethod, String batchNumber, LocalDate expirationDate, PackagingType packagingType, MeasureUnity measureUnity, int qtdPerPackage, ClosingMethod closingMethod, Material materialType, int divisionsNumber, boolean hasGrid, boolean isVentilated, double diameter, double height) {
        super(name, price, manufacturer, sterilizationMethod, batchNumber, expirationDate, packagingType, measureUnity, qtdPerPackage, closingMethod, materialType);

        if (divisionsNumber < 1) {
            throw new InvalidProductAttributeException("divisions number");
        }

        this.divNum = divisionsNumber;
        this.grid = hasGrid;
        this.ventilated = isVentilated;
        this.diameter = diameter;
        this.height = height;
    }

    public int getDivisionsNumber() {
        return divNum;
    }

    public boolean isGrid() {
        return grid;
    }

    public boolean isVentilated() {
        return ventilated;
    }

    public double getDiameter() {
        return diameter;
    }

    public double getHeight() {
        return height;
    }
}
