package br.com.biosecure.model;

import br.com.biosecure.utils.StringUtils;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ppes")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public abstract class PPE extends Product {
    @Enumerated(EnumType.STRING)
    private Size size;
    private String certificateOfApproval;
    private boolean disposable;

    protected PPE(PpeBuilder<?, ?> builder) {
        super(builder);

        this.size = builder.size;
        this.certificateOfApproval = builder.certificateOfApproval;
        this.disposable = builder.disposable;
    }

    public static abstract class PpeBuilder<P extends PPE, B extends PpeBuilder<P, B>> extends ProductBuilder<P, B> {
        { super.measureUnit = MeasureUnit.U; }

        protected Size size;
        protected String certificateOfApproval;
        protected boolean disposable;

        @Override
        protected abstract B self();

        @Override
        public abstract P build();

        @Override
        @Deprecated
        public B measureUnit(MeasureUnit measureUnit) {
            throw  new UnsupportedOperationException("Measure unit for all PPEs is standardized as 'unit' internally");
        }

        public B size(Size size) {
            if (size == null) {
                productNotification.addError("size", "size mustn't be null");
            }

            this.size = size;
            return self();
        }

        public B certificateOfApproval(String certificateOfApproval) {
            StringUtils.validateString(certificateOfApproval, 5, "certificate of approval", 10, true, super.productNotification);

            this.certificateOfApproval = certificateOfApproval;
            return self();
        }

        public B disposable(boolean disposable) {
            this.disposable = disposable;
            return self();
        }
    }

    @Getter
    @AllArgsConstructor
    public enum Size {
        EXTRA_SMALL("XS"),
        SMALL("S"),
        MEDIUM("M"),
        LARGE("L"),
        EXTRA_LARGE("XL"),
        UNIVERSAL("U");

        private final String code;
    }
}
