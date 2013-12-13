package net.baremodels.device.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.baremodels.device.SyncDevice;
import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.intent.Intent;
import net.baremodels.model.Model;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.runner.SimpleComponentTranslator;
import net.baremodels.ui.UIComponent;

import java.util.HashMap;

public final class JavaFxDevice
    extends Application
    implements SyncDevice
{

    private final SimpleComponentTranslator translator;
    private final WaitingComponentListener listener;
    private final Intent.Handler handler;
    private final StackPane root = new StackPane();

    private static JavaFxDevice device;

    public JavaFxDevice() {
        this(new SimpleComponentTranslator(new JavaFxWidgetSupplier(),
             new SimpleComponentConstraintSupplier(null,new HashMap<>())),
             new WaitingComponentListener(),
             new DesktopIntentHandler());
    }

    JavaFxDevice(SimpleComponentTranslator translator, WaitingComponentListener listener, Intent.Handler handler) {
        this.translator = translator;
        this.listener = listener;
        this.handler = handler;
    }

    public static JavaFxDevice newInstance() {
        new Thread(() -> launch()).start();
        waitForStartToComplete();
        return device;
    }

    private static void waitForStartToComplete() {
        while (device == null) {
            try {
                Thread.sleep(100);
                System.out.print(".");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(new Scene(root, 300, 250));
        primaryStage.show();
        device = this;
    }

    @Override
    public void onIntent(Intent intent) {
        handler.onIntent(intent);
    }

    @Override
    public Model display(final UIComponent ui) {
        Platform.runLater(() -> _display(ui));
        Model selected = listener.waitForSelectionChange();
        return selected;
    }

    private void _display(final UIComponent ui) {
        root.getChildren().add(translator.translate(ui, listener));
    }
}
