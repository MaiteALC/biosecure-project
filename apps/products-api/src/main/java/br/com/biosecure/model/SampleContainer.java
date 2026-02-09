package br.com.biosecure.model;

import br.com.biosecure.utils.ErrorAggregator;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.util.ArrayList;

@Entity
@Table(name = "sample_containers")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class SampleContainer extends  Product {
    @Enumerated(EnumType.STRING)
    private Material materialType;
    @Enumerated(EnumType.STRING)
    private ClosingMethod closingMethod;
    @Enumerated(EnumType.STRING)
    private SterilizationMethod sterilizationMethod;

    protected SampleContainer(SampleContainerBuilder<?, ?> builder) {
        super(builder);

        validateContainerBioSafetyRules(builder.materialType, builder.sterilizationMethod);

        this.materialType = builder.materialType;
        this.closingMethod = builder.closingMethod;
        this.sterilizationMethod = builder.sterilizationMethod;
    }

    public static abstract class SampleContainerBuilder<P extends SampleContainer, B extends SampleContainerBuilder<P, B>> extends ProductBuilder<P, B> {
        { super.measureUnit = MeasureUnit.U; }

        protected Material materialType;
        protected ClosingMethod closingMethod;
        protected SterilizationMethod sterilizationMethod;

        @Override
        protected abstract B self();

        @Override
        @Deprecated
        public B measureUnit(MeasureUnit measureUnit) {
            throw new UnsupportedOperationException("Measure unit for all sample containers is standardized as 'unit' internally");
        }

        public B materialType(Material materialType) {
            ErrorAggregator.verifyNull(materialType, "Material type", productNotification);

            this.materialType = materialType;
            return self();
        }

        public B closingMethod(ClosingMethod closingMethod) {
            ErrorAggregator.verifyNull(closingMethod, "Closing method", productNotification);

            this.closingMethod = closingMethod;
            return self();
        }

        public B sterilizationMethod(SterilizationMethod sterilizationMethod) {
            ErrorAggregator.verifyNull(sterilizationMethod, "Sterilization method", productNotification);

            this.sterilizationMethod = sterilizationMethod;
            return self();
        }
    }

    protected static void validateContainerBioSafetyRules(Material material, SterilizationMethod sterilization) {
        if (!material.isSupportsAutoclave() && sterilization == SterilizationMethod.AUTOCLAVE) {
            ArrayList<String> invalids = new ArrayList<>();

            invalids.add("Supports autoclave");
            invalids.add("Sterilization method");

            throw new BioSecurityException(
                "Material and sterilization method (autoclave) has incoherent", invalids
            );
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Material {
        PP("Polypropylene", true, "PP"),
        PS("Polystyrene", false, "PS"),
        PE("Polyethylene", false, "PE"),
        PC("Polycarbonate", true, "PC"),
        BOROSILICATE_GLASS("Borosilicate Glass", true, "BG"),
        GLASS("Common glass", false, "GL");

        private final String commercialName;
        private final boolean supportsAutoclave;
        private final String code;
    }

    @Getter
    @AllArgsConstructor
    public enum ClosingMethod {
        SCREW_CAP_SIMPLE(true),
        SCREW_WITH_FILTER(false),
        SCREW_CAP_RING(true),
        SNAP_CAP(true),
        WIRE_TAB(true),
        COTTON_STOPPER(false),
        CELLULOSE_STOPPER(false),
        ZIP_LOCK(true),
        HEAT_SEALABLE(true),
        LID_OVERLAY(false);

        private final boolean hermetic;
    }

    public enum SterilizationMethod {
        GAMMA_RAYS,
        E_BEAM,
        AUTOCLAVE,
        ETHYLENE_OXIDE,
        NO_STERILE
    }

    public boolean isSterile() {
        return this.sterilizationMethod != SterilizationMethod.NO_STERILE;
    }
}
