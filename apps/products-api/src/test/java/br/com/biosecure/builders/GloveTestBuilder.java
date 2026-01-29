package br.com.biosecure.builders;

import br.com.biosecure.model.Glove;
import br.com.biosecure.model.Glove.GloveMaterial;

public class GloveTestBuilder extends BasePpeTestBuilder<GloveTestBuilder, Glove> {
    // Specific attributes of Glove
    private boolean powderFree = true;
    private boolean longBarrel = true;
    private GloveMaterial material = GloveMaterial.NITRILE;
    private boolean isTextured = false;
    private double thicknessMils = 4;

    public GloveTestBuilder withPowderFree(boolean powderFree) {
        this.powderFree = powderFree;
        return this;
    }

    public GloveTestBuilder withLongBarrel(boolean longBarrel) {
        this.longBarrel = longBarrel;
        return this;
    }

    public GloveTestBuilder withMaterial(GloveMaterial material) {
        this.material = material;
        return this;
    }

    public GloveTestBuilder withTextured(boolean isTextured) {
        this.isTextured = isTextured;
        return this;
    }

    public GloveTestBuilder withThicknessMils(double thicknessMils) {
        this.thicknessMils = thicknessMils;
        return this;
    }

    @Override
    protected GloveTestBuilder self() {
        return this;
    }

    public static GloveTestBuilder aGlove() {
        return new GloveTestBuilder();
    }

    @Override
    public Glove build() {
        return Glove.builder()
                .powderFree(powderFree)
                .longBarrel(longBarrel)
                .material(material)
                .textured(isTextured)
                .thicknessMils(thicknessMils)
                .size(size)
                .certificateOfApproval(certificateOfApproval)
                .disposable(isDisposable)
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
