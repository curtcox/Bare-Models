package net.baremodels.device.swing;

import net.baremodels.runner.ComponentConstraintSupplier;
import net.baremodels.ui.UILayout;
import net.miginfocom.swing.MigLayout;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

final class SwingComponentConstraintSupplier
    implements ComponentConstraintSupplier
{
    private final Map<String, String> map;
    public static final String LAYOUT_MANAGER     = "layout_manager";
    public static final String LAYOUT_CONSTRAINTS = "layout_constraints";
    public static final String COLUMN_CONSTRAINTS = "column_constraints";
    public static final String ROW_CONSTRAINTS    = "row_constraints";
    public static final String ROWS               = "rows";
    public static final String COLS               = "cols";
    public static final String HGAP               = "hgap";
    public static final String VGAP               = "vgap";
    public static final String ALIGN              = "align";

    public SwingComponentConstraintSupplier() {
        this(new HashMap<>());
    }

    public SwingComponentConstraintSupplier(Map<String, String> map) {
        this.map = map;
    }

    @Override
    public LayoutManager getLayoutManager() {
        if (!map.containsKey(LAYOUT_MANAGER)) {
            return newMigLayout();
        }
        String manager = managerName();
        if (isGridBagLayout(manager)) {
            return new GridBagLayout();
        }
        if (isGridLayout(manager)) {
            return newGridLayout();
        }
        if (isFlowLayout(manager)) {
            return newFlowLayout();
        }
        return newMigLayout();
    }

    private String managerName() {
        return map.get(LAYOUT_MANAGER);
    }

    private boolean isFlowLayout(String manager) {
        return "FlowLayout".equals(manager) || FlowLayout.class.getName().equals(manager);
    }

    private boolean isGridLayout(String manager) {
        return "GridLayout".equals(manager) || GridLayout.class.getName().equals(manager);
    }

    private boolean isGridBagLayout(String manager) {
        return "GridBagLayout".equals(manager) || GridBagLayout.class.getName().equals(manager);
    }

    private LayoutManager newGridLayout() {
        return new GridLayout(getInt(ROWS,1),getInt(COLS,0),getInt(HGAP,0),getInt(VGAP,0));
    }

    private LayoutManager newFlowLayout() {
        Map<String,Integer> stringToInt = new HashMap() {{
            put("left",FlowLayout.LEFT);
            put("right",FlowLayout.RIGHT);
            put("center",FlowLayout.CENTER);
        }};
        int align = getInt(ALIGN,stringToInt,FlowLayout.CENTER);
        int hgap = getInt(HGAP, 5);
        int vgap = getInt(VGAP, 5);
        return new FlowLayout(align, hgap, vgap);
    }

    private LayoutManager newMigLayout() {
        return new MigLayout(map.get(LAYOUT_CONSTRAINTS),map.get(COLUMN_CONSTRAINTS),map.get(ROW_CONSTRAINTS));
    }

    private int getInt(String key, int defaultValue) {
        return map.containsKey(key) ? Integer.parseInt(map.get(key)) : defaultValue;
    }

    private int getInt(String key, Map<String,Integer> stringToInt, int defaultValue) {
        return map.containsKey(key) ? stringToInt.get(map.get(key)) : defaultValue;
    }

    @Override
    final public <T> T getComponentConstraints(UILayout.Constraints constraints) {
        return isGridBagLayout(managerName())
            ? (T) new GridBagConstraints()
            : (T) constraints.value;
    }

}
