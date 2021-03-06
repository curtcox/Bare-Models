package net.baremodels.device.javafx;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import net.baremodels.device.DeviceState;
import net.baremodels.device.SyncDevice;
import net.baremodels.device.desktop.DesktopIntentHandler;
import net.baremodels.intent.Intent;
import net.baremodels.model.Inspectable;
import net.baremodels.model.Model;
import net.baremodels.runner.ContainerTranslator;
import net.baremodels.runner.SimpleComponentConstraintSupplier;
import net.baremodels.runner.SimpleContainerTranslator;
import net.baremodels.runner.WaitingComponentListener;
import net.baremodels.ui.UIContainer;
import net.baremodels.ui.UILayout;

public final class JavaFxSyncDevice
    extends Application
    implements SyncDevice
{

    private final ContainerTranslator translator;
    private final WaitingComponentListener listener;
    private final Intent.Handler handler;
    private final StackPane root = new StackPane();

    private static JavaFxSyncDevice device;

    public JavaFxSyncDevice() {
        this(new SimpleContainerTranslator(new JavaFxWidgetSupplier(),
             new SimpleComponentConstraintSupplier(null)),
             new WaitingComponentListener(),
             new DesktopIntentHandler());
    }

    JavaFxSyncDevice(ContainerTranslator translator, WaitingComponentListener listener, Intent.Handler handler) {
        this.translator = translator;
        this.listener = listener;
        this.handler = handler;
    }

    public static JavaFxSyncDevice newInstance() {
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
    public Inspectable display(final UIContainer container, UILayout layout) {
        Platform.runLater(() -> redisplay(container, layout));
        Inspectable selected = listener.waitForSelectionChange();
        return selected;
    }

    @Override
    public DeviceState getDeviceState() {
        return null;
    }

    public void redisplay(final UIContainer ui, UILayout layout) {
        root.getChildren().add(translator.translate(ui, layout, listener));
    }
}
