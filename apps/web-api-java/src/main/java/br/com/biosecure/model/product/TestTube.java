package br.com.biosecure.model.product;

import java.time.LocalDate;

public class TestTube extends SampleContainer {
    private final int maxRcf;
    private final BottomType bottomType;
    private final boolean graduated;

    public TestTube(String name, double price, String manufacturer, SterilizationMethod sterilizationMethod, String batchNumber, LocalDate expirationDate, PackagingType packagingType, MeasureUnity measureUnity, int qtdPerPackage, ClosingMethod closingMethod, Material materialType, int maxRcf, BottomType bottomType, boolean isGraduated) {
        super(name, price, manufacturer, sterilizationMethod, batchNumber, expirationDate, packagingType, measureUnity, qtdPerPackage, closingMethod, materialType);

        this.maxRcf = maxRcf;
        this.bottomType = bottomType;
        this.graduated = isGraduated;
    }

    public enum BottomType {
        CONIC,
        PLAN,
        ROUND,
        SKIRTED
    }

    public enum CoverColor {
        BLUE("Sodium citrate"),
        GRAY("Potassium fluoride and EDTA"),
        GREEN("Heparin"),
        PURPLE("EDTA"),
        RED("Clot activator"),
        WHITE("None"),
        YELLOW("Clot activator and separator gel");

        private final String chemicalAdditive;

        CoverColor(String chemicalAdditive) {
            this.chemicalAdditive = chemicalAdditive;
        }

        public String getChemicalAdditive() {
            return chemicalAdditive;
        }
    }

    public int getMaxRcf() {
        return maxRcf;
    }

    public BottomType getBottomType() {
        return bottomType;
    }

    public boolean isGraduated() {
        return graduated;
    }
}
