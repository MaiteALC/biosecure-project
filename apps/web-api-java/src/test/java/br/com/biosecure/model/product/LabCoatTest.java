package br.com.biosecure.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import br.com.biosecure.builders.LabCoatBuilder;
import br.com.biosecure.model.product.LabCoat.CollarType;
import br.com.biosecure.model.product.LabCoat.FabricType;

public class LabCoatTest {
    
    @Test
    public void shouldBuildValidLabCoat() {
        LabCoat labCoat = LabCoatBuilder.aLabCoat()
            .withCollarType(CollarType.HIGH_NECK)
            .withGrammage(30)
            .withFabricType(FabricType.POLYPROPYLEN)
            .build();

        LabCoat defaultLabCoat = LabCoatBuilder.aLabCoat().build();

        assertNotNull(labCoat);
        assertNotNull(defaultLabCoat);

        assertFalse(defaultLabCoat.isDisposable());
        assertTrue(labCoat.isDisposable());
    }

    @Test
    public void shouldThrowException_WhenGrammageIsInvalid() {
        InvalidProductAttributeException exception = assertThrows(InvalidProductAttributeException.class, () -> {
            LabCoatBuilder.aLabCoat().withGrammage(19).build();
        });

        InvalidProductAttributeException exception2 = assertThrows(InvalidProductAttributeException.class, () -> {
            LabCoatBuilder.aLabCoat().withGrammage(351).build();
        });

        assertEquals(exception.getInvalidAttribute(), exception2.getInvalidAttribute());

        assertEquals("grammage (g/cm²)", exception.getInvalidAttribute());

        assertTrue(exception.getMessage().contains("Invalid product attributes:\n\t - grammage (g/cm²) |"));
    }
}
