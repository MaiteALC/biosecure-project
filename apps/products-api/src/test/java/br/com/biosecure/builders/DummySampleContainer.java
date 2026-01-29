package br.com.biosecure.builders;

import br.com.biosecure.model.InvalidProductAttributeException;
import br.com.biosecure.model.SampleContainer;
import java.time.LocalDate;

public class DummySampleContainer extends SampleContainer {
    private DummySampleContainer(DummySampleContainerBuilder builder) {
        super(builder);
    }

    public static DummySampleContainerBuilder builder() {
        return new DummySampleContainerBuilder();
    }

    public static final class DummySampleContainerBuilder extends SampleContainerBuilder<DummySampleContainer, DummySampleContainerBuilder> {
        {
            super.name = "Dummy Sample Container Builder";
            super.price = 54.9;
            super.manufacturer = "Generic Manufacturer";
            super.batchNumber = "Batch-A1";
            super.expirationDate = LocalDate.of(2027, 6, 12);
            super.packagingType = PackagingType.PACKAGE;
            super.quantityPerPackage = 20;
            super.closingMethod = ClosingMethod.CELLULOSE_STOPPER;
            super.sterilizationMethod = SterilizationMethod.ETHYLENE_OXIDE;
            super.materialType = Material.BOROSILICATE_GLASS;
        }

        @Override
        protected DummySampleContainerBuilder self() {
            return this;
        }

        @Override
        public DummySampleContainer build() {
            if (productNotification.hasErrors()) {
                throw new InvalidProductAttributeException(productNotification.getErrors());
            }
            return new DummySampleContainer(this);
        }
    }
}
