package net.baremodels.model;

import java.util.List;
import java.util.Map;

/**
 * An object that can be queried at runtime about its properties and operations.
 * <p>
 * The intent is to support user interfaces that focus on one object at a time.
 * A model is queried about its properties and operations.  The resulting information is used to determine what
 * user interface needs to be constructed.  After the user interacts with the interface, this process is repeated.
 * </p>
 * Methods needed to support domain models do not belong on this interface.  Objects that implement this interface
 * are generated from domain models.
 * @author curt
 */
public interface Model {
    Map<String,Property> properties();
    Map<String,Operation> operations();
}
