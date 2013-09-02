package net.baremodels.ui;

import org.junit.Test;

import static org.junit.Assert.assertSame;

public class UIButtonTest {

    @Test
    public void getName_returns_name_from_constructor() {
        String expected = "Fred";
        String actual = new UIButton(expected).getName();
        assertSame(expected,actual);
    }
}
