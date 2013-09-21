package net.baremodels.text;

import org.junit.Test;

import static org.junit.Assert.fail;

public class TextWidgetSupplierTest {

    TextWidgetSupplier testObject = new TextWidgetSupplier();

    @Test
    public void x() {
        testObject.list(null,null);
        testObject.button(null,null);
        testObject.container(null,null);
        fail();
    }


}
