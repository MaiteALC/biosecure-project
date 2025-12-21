package br.com.biosecure.model.product;

import java.util.ArrayList;
import br.com.biosecure.utils.NotificationContext;
import java.time.LocalDate;

public abstract class SampleContainer extends  Product {
    private final ClosingMethod closingMethod;
    private final SterilizationMethod sterilizationMethod;
    private final Material material;

    public SampleContainer(String name, double price, String manufacturer, String batchNumber, LocalDate expirationDate, PackagingType packagingType, int quantityPerPackage, SterilizationMethod sterilizationMethod, ClosingMethod closingMethod, Material materialType) {
        
        super(name, price, manufacturer, batchNumber, expirationDate, packagingType, MeasureUnit.U, (double) quantityPerPackage);

        NotificationContext notification = new NotificationContext();

        if (notification.hasErrors()) {
            throw new InvalidProductAttributeException(notification.getErrors());
        }

        validateBioSafetyRules(materialType, sterilizationMethod);

        this.sterilizationMethod = sterilizationMethod;
        this.closingMethod = closingMethod;
        this.material = materialType;
    }

    private void validateBioSafetyRules(Material material, SterilizationMethod sterilization) {
        if (!material.isSupportsAutoclave() && sterilization == SterilizationMethod.AUTOCLAVE) {
            ArrayList<String> invalids = new ArrayList<>();

            invalids.add("Supports autoclave");
            invalids.add("Sterilization method");

            throw new BioSecurityException(
                "Material and sterilization method (autoclave) has incoherent", invalids
            );
        }
    }

    public enum Material {
        PP("Polypropylene", true, "PP"),
        PS("Polystyrene", false, "PS"),
        PE("Polyethylene", false, "PE"),
        PC("Polycarbonate", true, "PC"),
        BOROSILICATE_GLASS("Borosilicate Glass", true, "BG"),
        GLASS("Common glass", false, "GL");

        private String commercialName;
        private boolean supportsAutoclave;
        private String code;

        Material(String commercialName, boolean supportsAutoclave, String code) {
            this.commercialName = commercialName;
            this.supportsAutoclave = supportsAutoclave;
            this.code = code;
        }

        public String getCommercialName() {
            return commercialName;
        }

        public boolean isSupportsAutoclave() {
            return supportsAutoclave;
        }

        public String getCode() {
            return code;
        }
    }

    public enum ClosingMethod {
        SCREW_CAP_SIMPLE(true),
        SCREW_WITH_FILTER(false),
        SCREW_CAP_ORING(true),
        SNAP_CAP(true),
        WIRE_TAB(true),
        COTTON_STOPPER(false),
        CELLULOSE_STOPPER(false),
        ZIP_LOCK(true),
        HEAT_SEALABLE(true),
        LID_OVERLAY(false);

        private final boolean hermetic;

        ClosingMethod(boolean isHermetic) {
            this.hermetic = isHermetic;
        }

        public boolean isHermetic() {
            return hermetic;
        }
    }

    public enum SterilizationMethod {
        GAMMA_RAYS,
        E_BEAM,
        AUTOCLAVE,
        ETHYLENE_OXIDE,
        NO_STERILE;
    }

    public SterilizationMethod getSterilizationMethod() {
        return sterilizationMethod;
    }

    public ClosingMethod getClosingMethod() {
        return closingMethod;
    }

    public Material getMaterial() {
        return material;
    }

    public boolean isSterile() {
        return this.sterilizationMethod != SterilizationMethod.NO_STERILE;
    }
}
