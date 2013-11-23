package net.baremodels.device.vaadin;

import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.ui.UI;
import net.baremodels.apps.Nucleus;
import net.baremodels.common.Team;
import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.models.ModelFactory;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;

import java.util.function.Supplier;

/**
 * Spike!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
 * Add tests
 */
@Title("Bare Models")
public final class VaadinDevice
    extends UI
{
    private VaadinRunner runner = new VaadinRunner(this);
    private final Supplier<Model> model;
    private final Intent.Handler handler;
    private final UIComponent.Listener componentListener = new UIComponent.Listener() {

        @Override
        public void onSelected(Model model) {
            System.out.println("---------- component listener : " + model);
            runner.onSelected(model);
        }
    };
    private final SimpleComponentTranslator translator;

    public VaadinDevice() {
        this(createSupplier(), new SimpleComponentTranslator(new VaadinWidgetSupplier()), new DesktopIntentHandler());
    }

    private static Supplier<Model> createSupplier() {
        System.out.println("---------- Supplier : ");
        return new Supplier<Model>() {
            @Override
            public Model get() {
                Nucleus nucleus = new Nucleus();
                nucleus.teams.add(new Team());
                return ModelFactory.DEFAULT.of(nucleus);
            }
        };
    }

    private VaadinDevice(Supplier<Model> supplier) {
        this(supplier,new SimpleComponentTranslator(new VaadinWidgetSupplier()), new DesktopIntentHandler());
    }

    private VaadinDevice(Supplier<Model> model, SimpleComponentTranslator translator, Intent.Handler handler)
    {
        this.model = model;
        this.translator = translator;
        this.handler = handler;
    }

    @Override
    protected void init(VaadinRequest vaadinRequest) {
        System.out.println("--------------- Init : " + vaadinRequest);
        runner.display(model.get());
    }

    public void display(UIComponent ui) {
        System.out.println("---------- Display : " + ui);
        setContent(translator.translate(ui, componentListener));
    }

    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

}
