package net.baremodels.runner;

import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.uat.Times;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class RunnerTest {

    ModelFactory modelFactory = ModelFactory.DEFAULT;
    Model start = modelFactory.of("start");
    Model end = modelFactory.of("end");

    @Test(timeout=100)
    public void setModel_does_not_display_if_until_is_true() {
        Runner runner = new Runner() {
            @Override
            public Model display(Model current) {
                fail();
                return null;
            }
        };

        runner.setModel(null,new Times(0));
    }

    @Test(timeout=100)
    public void setModel_does_display_if_until_is_false() {
        Runner runner = new Runner() {
            @Override
            public Model display(Model current) {
                return end;
            }
        };

        Model actual = runner.setModel(start,new Times(1));

        assertSame(end,actual);
    }

    @Test(timeout=100)
    public void setModel_does_display_while_until_is_false() {
        List list = new ArrayList<>();
        Runner runner = new Runner() {
            @Override
            public Model display(Model current) {
                list.add("");
                return end;
            }
        };

        runner.setModel(start,new Times(42));

        assertEquals(42, list.size());
    }

}
