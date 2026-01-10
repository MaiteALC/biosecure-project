package br.com.biosecure.builders;

import java.time.LocalDate;
import br.com.biosecure.model.PPE;

public class PpeBuilder extends BasePpeBuilder<PpeBuilder, PPE> {
    private static class PpeDummy extends PPE {
        public PpeDummy(String name, double price, String manufacturer, String batchNumber, LocalDate expirationDate, PackagingType packagingType, int quantityPerPackage, Size size, String certificateOfApproval, boolean isDisposable) {

            super(name, price, manufacturer, batchNumber, expirationDate, packagingType, quantityPerPackage, size, certificateOfApproval, isDisposable);
        }
    }

    @Override
    protected PpeBuilder self() {
        return this;
    }

    public static PpeBuilder aPPE() {
        return new PpeBuilder();
    }

    @Override
    public PPE build() {
        return new PpeDummy(name, price, manufacturer, batchNumber, expirationDate, packagingType, (int) quantityPerPackage, size, certificateOfApproval, isDisposable);
    }
}
