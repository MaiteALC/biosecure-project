package br.com.biosecure.builders;

import br.com.biosecure.model.PetriDish;

public class PetriDishTestBuilder extends BaseSampleContainerTestBuilder<PetriDishTestBuilder, PetriDish> {
    // Specific attributes of Petri Dish
    private int divNum = 2;
    private boolean grid = false;
    private boolean ventilated = true;
    private double diameterMm = 90;
    private double heightMm = 15;

    public PetriDishTestBuilder withDivNum(int divNum) {
        this.divNum = divNum;
        return this;
    }

    public PetriDishTestBuilder withGrid(boolean grid) {
        this.grid = grid;
        return this;
    }

    public PetriDishTestBuilder withVentilated(boolean ventilated) {
        this.ventilated = ventilated;
        return this;
    }

    public PetriDishTestBuilder withDiameterMm(int diameter) {
        this.diameterMm = diameter;
        return this;
    }

    public PetriDishTestBuilder withHeightMm(int height) {
        this.heightMm = height;
        return this;
    }
    
    public PetriDishTestBuilder withDiameterMm(double diameter) {
        this.diameterMm = diameter;
        return this;
    }

    public PetriDishTestBuilder withHeightMm(double height) {
        this.heightMm = height;
        return this;
    }

    @Override
    protected PetriDishTestBuilder self() {
        return this;
    }

    public static PetriDishTestBuilder aPetriDish() {
        return new PetriDishTestBuilder();
    }

    @Override
    public PetriDish build() {
        return PetriDish.builder()
                .divisionsNumber(divNum)
                .grid(grid)
                .ventilated(ventilated)
                .diameterMm(diameterMm)
                .heightMm(heightMm)
                .materialType(material)
                .closingMethod(closingMethod)
                .sterilizationMethod(sterilizationMethod)
                .name(name)
                .price(price)
                .manufacturer(manufacturer)
                .batchNumber(batchNumber)
                .expirationDate(expirationDate)
                .packagingType(packagingType)
                .quantityPerPackage(quantityPerPackage)
                .build();
    }
}
