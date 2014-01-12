package net.baremodels.runner;

import net.baremodels.device.DeviceState;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.uat.Times;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SyncRunnerTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model start = modelFactory.of("start");
    Model end = modelFactory.of("end");

    @Test(timeout=100)
    public void setModel_does_not_display_if_until_is_true() {
        SyncRunner runner = new SyncRunner() {
            @Override public Model generateNextModel(Model current, Model selected) { return null; }
            @Override public Model display(Model current) { fail(); return null; }
            @Override public void onChange(DeviceState state) {}
            @Override public void onChange(AppContext context) {}
        };

        runner.setModel(null,new Times(0));
    }

    @Test(timeout=100)
    public void setModel_does_display_if_until_is_false() {
        SyncRunner runner = new SyncRunner() {
            @Override public Model generateNextModel(Model current, Model selected) { return selected; }
            @Override public Model display(Model current) { return end; }
            @Override public void onChange(DeviceState state) {}
            @Override public void onChange(AppContext context) {}
        };

        Model actual = runner.setModel(start,new Times(1));

        assertSame(end,actual);
    }

    @Test(timeout=100)
    public void setModel_does_display_while_until_is_false() {
        List list = new ArrayList<>();
        SyncRunner runner = new SyncRunner() {
            @Override public Model generateNextModel(Model current, Model selected) { return null; }
            @Override public Model display(Model current) { list.add(""); return end; }
            @Override public void onChange(DeviceState state) {}
            @Override public void onChange(AppContext context) {}
        };

        runner.setModel(start,new Times(42));

        assertEquals(42, list.size());
    }

}
