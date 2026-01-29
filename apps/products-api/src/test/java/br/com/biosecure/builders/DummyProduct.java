package br.com.biosecure.builders;

import br.com.biosecure.model.InvalidProductAttributeException;
import br.com.biosecure.model.Product;
import java.time.LocalDate;

public class DummyProduct extends Product {
    private DummyProduct(DummyProductBuilder builder) {
        super(builder);
    }

    public static DummyProductBuilder builder() {
        return new DummyProductBuilder();
    }

    public static final class DummyProductBuilder extends ProductBuilder<DummyProduct, DummyProductBuilder> {
        {
            super.name = "Dummy Product Builder";
            super.price = 54.9;
            super.manufacturer = "Generic Manufacturer";
            super.batchNumber = "Batch-A1";
            super.expirationDate = LocalDate.of(2027, 6, 12);
            super.packagingType = PackagingType.PACKAGE;
            super.measureUnit = MeasureUnit.KG;
            super.quantityPerPackage = 20;
        }

        @Override
        protected DummyProductBuilder self() {
            return this;
        }

        @Override
        public DummyProduct build() {
            if (productNotification.hasErrors()) {
                throw new InvalidProductAttributeException(productNotification.getErrors());
            }
            return new DummyProduct(this);
        }
    }
}
