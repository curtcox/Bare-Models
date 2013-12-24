package net.baremodels.device.vaadin;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.UI;
import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.device.AsyncDevice;
import net.baremodels.device.DeviceState;
import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.*;
import net.baremodels.ui.UIComponent;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;

import java.util.HashMap;
import java.util.function.Supplier;

@Title("Bare Models")
public final class VaadinDevice
    extends UI
    implements AsyncDevice
{
    private final AsyncRunner runner = new VaadinRunner(new SimpleAppContext(),this);
    private final Supplier<Model> model;
    private final Intent.Handler handler;
    private final UIComponent.Listener componentListener = model -> runner.onSelected(model);
    private final ContainerTranslator translator;

    public VaadinDevice() {
        this(createSupplier(),
             new SimpleContainerTranslator(new VaadinWidgetSupplier(), new SimpleComponentConstraintSupplier(new FormLayout(),new HashMap<>())),
             new DesktopIntentHandler());
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
        this(supplier,
             new SimpleContainerTranslator(new VaadinWidgetSupplier(), new SimpleComponentConstraintSupplier(new FormLayout(),new HashMap<>())),
             new DesktopIntentHandler());
    }

    VaadinDevice(Supplier<Model> model, ContainerTranslator translator, Intent.Handler handler)
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
    public void display(UIContainer ui, UILayout layout) {
        setContent(translator.translate(ui, layout, componentListener));
    }

    @Override
    public DeviceState getDeviceState() {
        return null;
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

}
