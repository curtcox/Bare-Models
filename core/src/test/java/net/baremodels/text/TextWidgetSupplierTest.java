package net.baremodels.text;

import org.junit.Test;

import static org.junit.Assert.fail;

public class TextWidgetSupplierTest {


    TextWidgetSupplier testObject = new TextWidgetSupplier();

    @Test
    public void list() {
        testObject.list(null,null);
        fail();
    }

    @Test
    public void button() {
        testObject.button(null,null);
        fail();
    }

    @Test
    public void container() {
        testObject.container(null,null);
        fail();
    }

}
