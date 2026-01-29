package br.com.biosecure.builders;

import br.com.biosecure.model.InvalidProductAttributeException;
import br.com.biosecure.model.PPE;
import java.time.LocalDate;

public class DummyPpe extends PPE {
    private DummyPpe(DummyPpeBuilder builder) {
        super(builder);
    }

    public static DummyPpeBuilder builder() {
        return new DummyPpeBuilder();
    }

    public static class DummyPpeBuilder extends  PpeBuilder<DummyPpe, DummyPpeBuilder>  {
        {
            super.name = "Test Product";
            super.price = 54.9;
            super.manufacturer = "Generic Manufacturer";
            super.batchNumber = "Batch-A1";
            super.expirationDate = LocalDate.of(2027, 6, 12);
            super.packagingType = PackagingType.PACKAGE;
            super.measureUnit = MeasureUnit.U;
            super.quantityPerPackage = 20;
            super.size = Size.UNIVERSAL;
            super.certificateOfApproval = "CA-54967";
            super.disposable = false;
        }

        @Override
        protected DummyPpeBuilder self() {
            return this;
        }

        @Override
        public DummyPpe build() {
            if (productNotification.hasErrors()) {
                throw new InvalidProductAttributeException(productNotification.getErrors());
            }
            return new DummyPpe(this);
        }
    }
}

