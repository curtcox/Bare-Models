package net.baremodels.device.vaadin;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.device.AsyncDevice;
import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.AsyncRunner;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;

import java.util.HashMap;
import java.util.function.Supplier;

@Title("Bare Models")
public final class VaadinDevice
    extends UI
    implements AsyncDevice
{
    private final AsyncRunner runner = new VaadinRunner(this);
    private final Supplier<Model> model;
    private final Intent.Handler handler;
    private final UIComponent.Listener componentListener = model -> runner.onSelected(model);
    private final SimpleComponentTranslator translator;

    public VaadinDevice() {
        this(createSupplier(), new SimpleComponentTranslator(new VaadinWidgetSupplier(), new VaadinLayoutSupplier(new HashMap<>())), new DesktopIntentHandler());
    }

    private static Supplier<Model> createSupplier() {
        return new Supplier<Model>() {
            @Override
            public Model get() {
                Nucleus nucleus = new Nucleus();
                nucleus.teams.add(new Team());
                return ModelFactory.DEFAULT.of(nucleus);
            }
        };
    }

    protected VaadinDevice(Supplier<Model> supplier) {
        this(supplier,new SimpleComponentTranslator(new VaadinWidgetSupplier(), new VaadinLayoutSupplier(new HashMap<>())), new DesktopIntentHandler());
    }

    VaadinDevice(Supplier<Model> model, SimpleComponentTranslator translator, Intent.Handler handler)
    {
        this.model = model;
        this.translator = translator;
        this.handler = handler;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        runner.display(model.get());
    }

    @Override
    public void display(UIComponent ui) {
        setContent(translator.translate(ui, componentListener));
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

}
