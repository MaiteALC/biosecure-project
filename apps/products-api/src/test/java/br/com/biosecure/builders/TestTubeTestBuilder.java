package br.com.biosecure.builders;

import br.com.biosecure.model.TestTube;
import br.com.biosecure.model.TestTube.*;

public class TestTubeTestBuilder extends BaseSampleContainerTestBuilder<TestTubeTestBuilder, TestTube> {
    // Specific attributes of Test Tubes
    private int maxRCF = 4000;
    private BottomType bottomType = BottomType.ROUND;
    private boolean graduated = true;
    private CapColor capColor = CapColor.WHITE;
    private double diameterMm = 12;
    private double heightMm = 75;

    public TestTubeTestBuilder withMaxRCF(int maxRCF) {
        this.maxRCF = maxRCF;
        return this;
    }

    public TestTubeTestBuilder withBottomType(BottomType bottomType) {
        this.bottomType = bottomType;
        return this;
    }

    public TestTubeTestBuilder withGraduated(boolean graduated) {
        this.graduated = graduated;
        return this;
    }

    public TestTubeTestBuilder withCapColor(CapColor capColor) {
        this.capColor = capColor;
        return this;
    }

    public TestTubeTestBuilder withDiameterMm(double diameterMm) {
        this.diameterMm = diameterMm;
        return this;
    }
    
    public TestTubeTestBuilder withDiameterMm(int diameterMm) {
        this.diameterMm = diameterMm;
        return this;
    }

    public TestTubeTestBuilder withHeightMm(double heightMm) {
        this.heightMm = heightMm;
        return this;
    }
    
    public TestTubeTestBuilder withHeightMm(int heightMm) {
        this.heightMm = heightMm;
        return this;
    }

    @Override
    protected TestTubeTestBuilder self() {
        return this;
    }

    public static TestTubeTestBuilder aTestTube() {
        return new TestTubeTestBuilder();
    }

    @Override
    public TestTube build() {
        return TestTube.builder()
                .maxRCF(maxRCF)
                .bottomType(bottomType)
                .graduated(graduated)
                .capColor(capColor)
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
