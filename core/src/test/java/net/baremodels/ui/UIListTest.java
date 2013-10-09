package net.baremodels.ui;

import net.baremodels.model.ListModel;
import net.baremodels.models.ObjectModel;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

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

    @Test
    public void list_are_equal_when_names_and_inner_lists_are_equal() {
        assertUIListsEquals(Arrays.asList(), Arrays.asList(), "a", "a");
        assertUIListsEquals(Arrays.asList("one"), Arrays.asList("one"), "b", "b");
    }

    @Test
    public void list_are_not_equal_when_names_or_inner_lists_are_not_equal() {
        assertUIListsNotEquals(Arrays.asList(), Arrays.asList(), "a", "b");
        assertUIListsNotEquals(Arrays.asList("one"), Arrays.asList("two"), "b", "b");
    }

    @Test
    public void toString_contains_name() {
        String name = "agyuageugfw";
        assertTrue(new UIList((ListModel) ObjectModel.of(Arrays.asList()), name).toString().contains(name));
    }

    @Test
    public void toString_contains_list_as_string() {
        List list = Arrays.asList("one","two","shoe");
        assertTrue(new UIList((ListModel) ObjectModel.of(list), "name").toString().contains(list.toString()));
    }

    private void assertUIListsEquals(List list1, List list2, String name1, String name2) {
        UIList uiList1 = new UIList((ListModel) ObjectModel.of(list1), name1);
        UIList uiList2 = new UIList((ListModel) ObjectModel.of(list2), name2);
        assertEquals(uiList1, uiList2);
        assertEquals(uiList2, uiList1);
        assertEquals(uiList1.hashCode(),uiList2.hashCode());
    }

    private void assertUIListsNotEquals(List list1, List list2, String name1, String name2) {
        UIList uiList1 = new UIList((ListModel) ObjectModel.of(list1), name1);
        UIList uiList2 = new UIList((ListModel) ObjectModel.of(list2), name2);
        assertFalse(uiList1.equals(uiList2));
        assertFalse(uiList2.equals(uiList1));
    }
}
