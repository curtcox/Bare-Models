package net.baremodels.model;

import java.util.List;

/**
 * A model for an object that is a list of things.
 */
public interface ListModel
    extends Model
{
    /**
     * Return the raw objects in this list.
     */
    List getList();
}
