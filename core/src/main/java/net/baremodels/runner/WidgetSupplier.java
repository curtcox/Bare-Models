package net.baremodels.runner;

import net.baremodels.ui.*;

import java.util.List;

/**
 * For supplying widgets.
 * A given WidgetSupplier will accept toolkit independent UI components and
 * produce the equivalent widgets for a specific toolkit.
 * <p>
 * There are currently suppliers for AWT, Swing, JavaFX, and Vaadin.
 * Future Java toolkits might include
 *
 * IFC, Apache Pivot,
 * Android, J2ME
 * CodeNameOne, Nuvos
 * Bck2Brwsr, Doppio
 * Enyo, React
 * Dart
 * ADF Mobile,
 * JSF,
 * Grails, Ratpack,
 * IKVM,
 * VoiceXML
 */
public interface WidgetSupplier {

    /**
     * Return the equivalent toolkit-specific label.
     */
    <T> T label(UILabel label);

    /**
     * Return the equivalent toolkit-specific button.
     */
    <T> T button(UIButton button, UIComponent.Listener listener);

    /**
     * Return the equivalent toolkit-specific list.
     */
    <T> T list(UIList list, UIComponent.Listener listener);

    /**
     * Return the equivalent toolkit-specific container.
     */
    <T> T container(UIContainer container, UILayout layout, List components, ComponentConstraintSupplier layoutProvider);
}
