package br.com.biosecure.model;

import br.com.biosecure.utils.NumberUtils;
import lombok.AllArgsConstructor;
import lombok.Getter;
import java.util.ArrayList;

@Getter
public class SampleBag extends SampleContainer {
    private final FilterType filter;
    private final boolean identificationTag;
    private final boolean standUp;
    private final double thicknessMm;
    private final double widthMm;
    private final double heightMm;
    private final double capacityMilliLiters;

    protected SampleBag(SampleBagBuilder builder) {
        super(builder);

        validateSampleBagBioSafetyRules(super.getMaterialType());

        this.filter = builder.filter;
        this.identificationTag = builder.identificationTag;
        this.standUp = builder.standUp;
        this.thicknessMm = builder.thicknessMm;
        this.widthMm = builder.widthMm;
        this.heightMm = builder.heightMm;
        this.capacityMilliLiters = builder.capacityMilliLiters;
    }

    public static SampleBagBuilder builder() {
        return new SampleBagBuilder();
    }

    public static final class SampleBagBuilder extends SampleContainerBuilder<SampleBag, SampleBagBuilder> {
        private FilterType filter;
        private boolean identificationTag;
        private boolean standUp;
        private double thicknessMm;
        private double widthMm;
        private double heightMm;
        private double capacityMilliLiters;

        @Override
        protected SampleBagBuilder self() {
            return this;
        }

        public SampleBagBuilder filter(FilterType filter) {
            this.filter = filter;
            return this;
        }

        public SampleBagBuilder identificationTag(boolean identificationTag) {
            this.identificationTag = identificationTag;
            return this;
        }

        public SampleBagBuilder standUp(boolean standUp) {
            this.standUp = standUp;
            return this;
        }

        public SampleBagBuilder thicknessMm(double thicknessMm) {
            this.thicknessMm = thicknessMm;
            return this;
        }

        public SampleBagBuilder widthMm(double widthMm) {
            this.widthMm = widthMm;
            return this;
        }

        public SampleBagBuilder heightMm(double heightMm) {
            this.heightMm = heightMm;
            return this;
        }

        public SampleBagBuilder capacityMilliLiters(double capacityMilliLiters) {
            this.capacityMilliLiters = capacityMilliLiters;
            return this;
        }

        @Override
        public SampleBag build() {
            if (filter == null) {
                productNotification.addError("filter type", "filter type mustn't be null");
            }

            NumberUtils.validateNumericalAttribute(heightMm, 1, "height (mm)", 999, productNotification);
            NumberUtils.validateNumericalAttribute(widthMm, 1, "width (mm)", 999, productNotification);

            NumberUtils.validateNumericalAttribute(thicknessMm, 1, "thickness (mm)", 99, productNotification);

            NumberUtils.validateNumericalAttribute(capacityMilliLiters, 1, "capacity (mL)", 9999, productNotification);

            if (productNotification.hasErrors()) {
                throw new InvalidProductAttributeException(productNotification.getErrors());
            }

            return new SampleBag(this);
        }
    }
    
    private void validateSampleBagBioSafetyRules(Material material) {
        if (material != Material.PE && material != Material.PP) {
            ArrayList<String> invalids = new ArrayList<>();
            
            invalids.add("Material");
            
            throw new BioSecurityException(
                "Sample bags must be of flexible material (PE, PP). " + getMaterialType().getCommercialName() + " is rigid.", invalids
            );
        }
    }

    @Getter
    @AllArgsConstructor
    public enum FilterType {
        NONE("N"),
        FULL_PAGE("F"),
        LATERAL("L");

        private final String code;
    }
}