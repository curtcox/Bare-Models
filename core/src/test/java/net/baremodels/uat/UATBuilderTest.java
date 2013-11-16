package net.baremodels.uat;

import org.junit.Test;
import static org.junit.Assert.*;

public class UATBuilderTest {

    UATBuilder testObject = new UATBuilder();

    @Test
    public void can_create() {
        new UATBuilder();
    }

    @Test
    public void build_create_UAT() {
        assertTrue(testObject.build() instanceof UAT);
    }
}
