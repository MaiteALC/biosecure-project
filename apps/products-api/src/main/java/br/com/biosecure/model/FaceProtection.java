package br.com.biosecure.model;

import br.com.biosecure.utils.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class FaceProtection extends PPE {
    @Enumerated(EnumType.STRING)
    private ProtectionType protectionType;
    private String standardRating;
    private boolean hasValve;
    private boolean antiFog;

    protected FaceProtection(FaceProtectionBuilder builder) {
        super(builder);

        this.protectionType = builder.protectionType;
        this.standardRating = builder.standardRating;
        this.hasValve = builder.protectionType == ProtectionType.MASK_RESPIRATOR && builder.hasValve;
        this.antiFog = builder.antiFog;
    }

    public static FaceProtectionBuilder builder() {
        return new FaceProtectionBuilder();
    }

    public static final class FaceProtectionBuilder extends PpeBuilder<FaceProtection, FaceProtectionBuilder> {
        private ProtectionType protectionType;
        private String standardRating;
        private boolean hasValve;
        private boolean antiFog;

        @Override
        protected FaceProtectionBuilder self() {
            return this;
        }

        public FaceProtectionBuilder protectionType(ProtectionType protectionType) {
            this.protectionType = protectionType;
            return this;
        }

        public FaceProtectionBuilder standardRating(String standardRating) {
            this.standardRating = standardRating;
            return this;
        }

        public FaceProtectionBuilder hasValve(boolean hasValve) {
            this.hasValve = hasValve;
            return this;
        }

        public FaceProtectionBuilder antiFog(boolean antiFog) {
            this.antiFog = antiFog;
            return this;
        }

        @Override
        public FaceProtection build() {
            if (protectionType == null) {
                productNotification.addError("protection type", "protection type mustn't be null");
            }

            StringUtils.validateString(standardRating, 2, "standard rating", 12, true, super.productNotification);

            if (super.productNotification.hasErrors()) {
                throw new InvalidProductAttributeException(super.productNotification.getErrors());
            }

            return new FaceProtection(this);
        }
    }

    @Getter
    @AllArgsConstructor
    public enum ProtectionType {
        MASK_RESPIRATOR("MR"),
        SAFETY_GLASSES("SG"), 
        GOGGLES("GG"),       
        FACE_SHIELD("FS");

        private final String code;
    }
}
