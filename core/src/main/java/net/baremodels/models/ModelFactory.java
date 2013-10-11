package net.baremodels.models;

import net.baremodels.model.Model;

/**
 * Something that creates ModelS.
 */
public interface ModelFactory {
    ModelFactory DEFAULT = new ObjectModelFactory();

    Model of(Object object);
    Model of(Object object, String name);

}