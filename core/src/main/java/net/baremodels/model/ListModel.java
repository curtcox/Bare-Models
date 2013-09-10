package net.baremodels.model;

import java.util.List;

public interface ListModel
    extends Model
{
    /**
     * Return the raw objects in this list.
     */
    List getList();
}
