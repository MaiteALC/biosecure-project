package br.com.biosecure.model.product;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import br.com.biosecure.builders.GloveBuilder;
import br.com.biosecure.model.product.Glove.GloveMaterial;
import br.com.biosecure.model.product.PPE.Size;

public class GloveTest {
    
    @Test
    public void shouldBuildValidGlove() {
        Glove aGlove = GloveBuilder.aGlove()
            .withMaterial(GloveMaterial.LATEX)
            .withDisposable(true)
            .withPowderFree(true)
            .withSize(Size.SMALL)
            .build();

        Glove defaultGlove = GloveBuilder.aGlove().build();

        assertNotNull(aGlove);
        assertNotNull(defaultGlove);
    }

    @Test
    public void shouldThrowException_WhenThicknessIsInvalid() {
        InvalidProductAttributeException exception = assertThrows(InvalidProductAttributeException.class, () -> {
            GloveBuilder.aGlove().withThicknessMils(2).build();
        });

        InvalidProductAttributeException exception2 = assertThrows(InvalidProductAttributeException.class, () -> {
            GloveBuilder.aGlove().withThicknessMils(11).build();
        });

        assertEquals("thickness (mils)", exception.getInvalidAttribute());
        assertEquals(exception.getInvalidAttribute(), exception2.getInvalidAttribute());

        assertTrue(exception.getMessage().contains("These attributes are invalids:\n\t - thickness (mils) |"));
    }

    @Test
    public void shouldInferIfIsDisposableCorrectly() {
        Glove latexGlove = GloveBuilder.aGlove()
            .withMaterial(GloveMaterial.LATEX)
            .build();

        Glove vinylGlove = GloveBuilder.aGlove()
            .withMaterial(GloveMaterial.VINYL)
            .build();

        Glove nitrileGlove = GloveBuilder.aGlove()
            .withMaterial(GloveMaterial.NITRILE)
            .withDisposable(false)
            .build();

        Glove neopreneGlove = GloveBuilder.aGlove()
            .withMaterial(GloveMaterial.NEOPRENE)
            .withDisposable(true)
            .build(); 

        assertTrue(latexGlove.isDisposable()); // always true
        assertTrue(vinylGlove.isDisposable()); // always true

        assertTrue(neopreneGlove.isDisposable());

        assertFalse(nitrileGlove.isDisposable());
    }
}
