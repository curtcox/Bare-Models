package net.baremodels.ui;

import net.baremodels.model.ListModel;
import net.baremodels.models.ObjectModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class UIListTest {

    String name = "random name";
    List list = new ArrayList();
    ListModel listModel = (ListModel) ObjectModel.of(list);
    UIList testObject = new UIList(listModel,name);

    @Test
    public void is_a_UIComponent() {
        assertTrue(testObject instanceof UIComponent);
    }

    @Test
    public void getModel_uses_model_from_constructor() {
        assertSame(listModel,testObject.getModel());
    }

    @Test
    public void getName_uses_name_from_constructor() {
        assertSame(name,testObject.getName());
    }
}
