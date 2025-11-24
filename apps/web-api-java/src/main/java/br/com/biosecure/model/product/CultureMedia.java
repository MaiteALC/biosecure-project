package br.com.biosecure.model.product;

import br.com.biosecure.model.product.Sanitizer.PhysicalForm;

import java.time.LocalDate;

public class CultureMedia extends Product {
    private final CultureMediaFinality finality;
    private final StorageConditions storageConditions;
    private final PhysicalForm form;
    private final boolean readyToUse;
    private final boolean protectOfLight;

    public CultureMedia(String name, double price, PhysicalForm form, String manufacturer, String batchNumber, LocalDate expirationDate, PackagingType packagingType, MeasureUnit measureUnit, int qtdPerPackage, CultureMediaFinality finality, boolean isReadyToUse, StorageConditions storageConditions, boolean protectOfLight) {
        super(name, price, manufacturer, batchNumber, expirationDate, packagingType, measureUnit, qtdPerPackage);

        this.finality = finality;
        this.readyToUse = isReadyToUse;
        this.storageConditions = storageConditions;
        this.protectOfLight = protectOfLight;
        this.form = form;
    }

    public enum CultureMediaFinality {
        SELECTIVE,
        DIFFERENTIAL,
        TRANSPORT,
        ENRICHMENT;
    }

    public enum StorageConditions {
        AMBIENT_TEMP(15, 30, "AMB"),
        FROZEN(-20, 0, "FRO"),
        REFRIGERATED(2, 8, "REF"),
        FRESH(8, 15, "FRE"),
        ULTRA_FREEZER(-150, -20, "ULT");

        private final int minTemp;
        private final int maxTemp;
        private final String code;

        StorageConditions(int minTemperature, int maxTemperature, String code) {
            this.minTemp = minTemperature;
            this.maxTemp = maxTemperature;
            this.code = code;
        }

        public int getMinTemperature() {
            return  minTemp;
        }

        public int getMaxTemperature() {
            return maxTemp;
        }

        public String getCode() {
            return code;
        }
    }

    public CultureMediaFinality getFinality() {
        return finality;
    }

    public StorageConditions getStorageConditions() {
        return storageConditions;
    }

    public boolean isReadyToUse() {
        return readyToUse;
    }

    public PhysicalForm getPhysicalForm() {
        return form;
    }

    public  boolean isProtectOfLight() {
        return protectOfLight;
    }
}
