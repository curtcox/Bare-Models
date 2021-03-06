package net.baremodels.runner;

import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import org.junit.Test;

import static org.junit.Assert.assertSame;

public class WaitingComponentListenerTest {

    Model a = ModelFactory.DEFAULT.of("a");
    Model b = ModelFactory.DEFAULT.of("b");
    WaitingComponentListener testObject = new WaitingComponentListener();

    @Test
    public void can_create() {
        new WaitingComponentListener();
    }

    @Test
    public void waitForSelectionChange_returns_selected_immediately_if_already_set() {
        testObject.onSelected(a);
        Inspectable actual = testObject.waitForSelectionChange();

        assertSame(a,actual);
    }

    @Test
    public void waitForSelectionChange_returns_selected_after_change() {
        new Thread(()->{testObject.onSelected(a);}).start();
        Inspectable actual = testObject.waitForSelectionChange();

        assertSame(a,actual);
    }

    @Test
    public void waitForSelectionChange_returns_selected_after_two_changes() {
        testObject.onSelected(a);
        assertSame(a,testObject.waitForSelectionChange());

        new Thread(()->{testObject.onSelected(b);}).start();

        assertSame(b,testObject.waitForSelectionChange());
    }

}
