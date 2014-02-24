package net.baremodels.device.swing;

import net.baremodels.ui.UILayout.Constraints;
import net.miginfocom.swing.MigLayout;
import org.junit.Test;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

import static net.baremodels.device.swing.SwingComponentConstraintSupplier.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.*;

public class SwingComponentConstraintSupplierTest {

    Map<String,String> map = new HashMap<>();
    SwingComponentConstraintSupplier testObject = new SwingComponentConstraintSupplier(map);

    @Test
    public void getLayoutManager_returns_MigLayout_when_map_layout_is_MigLayout() {
        map.put(LAYOUT_MANAGER,"MigLayout");
        assertTrue(testObject.getLayoutManager() instanceof MigLayout);
    }

    @Test
    public void getLayoutManager_sets_MigLayout_cache_layout_constraints_from_map() {
        map.put(LAYOUT_MANAGER,"MigLayout");
        map.put(LAYOUT_CONSTRAINTS,"nocache");
        MigLayout layout = (MigLayout) testObject.getLayoutManager();
        assertEquals("nocache",layout.getLayoutConstraints());
    }

    @Test
    public void getLayoutManager_sets_MigLayout_flowy_layout_constraints_from_map() {
        map.put(LAYOUT_MANAGER,"MigLayout");
        map.put(LAYOUT_CONSTRAINTS,"flowy");
        MigLayout layout = (MigLayout) testObject.getLayoutManager();
        assertEquals("flowy", layout.getLayoutConstraints());
    }

    @Test
    public void getLayoutManager_sets_MigLayout_fill_row_constraints_from_map() {
        map.put(LAYOUT_MANAGER,"MigLayout");
        map.put(ROW_CONSTRAINTS,"fill");
        MigLayout layout = (MigLayout) testObject.getLayoutManager();
        assertEquals("fill",layout.getRowConstraints());
    }

    @Test
    public void getLayoutManager_sets_MigLayout_fill_row_constraints_from_map_when_MigLayout_by_default() {
        map.put(ROW_CONSTRAINTS,"fill");
        MigLayout layout = (MigLayout) testObject.getLayoutManager();
        assertEquals("fill",layout.getRowConstraints());
    }

    @Test
    public void getLayoutManager_sets_MigLayout_nogrid_row_constraints_from_map() {
        map.put(LAYOUT_MANAGER,"MigLayout");
        map.put(ROW_CONSTRAINTS,"nogrid");
        MigLayout layout = (MigLayout) testObject.getLayoutManager();
        assertEquals("nogrid",layout.getRowConstraints());
    }

    @Test
    public void getLayoutManager_sets_MigLayout_fill_column_constraints_from_map() {
        map.put(LAYOUT_MANAGER,"MigLayout");
        map.put(COLUMN_CONSTRAINTS,"fill");
        MigLayout layout = (MigLayout) testObject.getLayoutManager();
        assertEquals("fill",layout.getColumnConstraints());
    }

    @Test
    public void getLayoutManager_sets_MigLayout_nogrid_column_constraints_from_map() {
        map.put(LAYOUT_MANAGER,"MigLayout");
        map.put(COLUMN_CONSTRAINTS,"nogrid");
        MigLayout layout = (MigLayout) testObject.getLayoutManager();
        assertEquals("nogrid",layout.getColumnConstraints());
    }

    @Test
    public void getLayoutManager_returns_MigLayout_when_map_layout_is_full_MigLayout_class_name() {
        map.put(LAYOUT_MANAGER,MigLayout.class.getName());
        assertTrue(testObject.getLayoutManager() instanceof MigLayout);
    }

    @Test
    public void getLayoutManager_returns_GridLayout_when_map_layout_is_GridLayout() {
        map.put(LAYOUT_MANAGER,"GridLayout");
        assertTrue(testObject.getLayoutManager() instanceof GridLayout);
    }

    @Test
    public void getLayoutManager_returns_GridLayout_when_map_layout_is_full_GridLayout_class_name() {
        map.put(LAYOUT_MANAGER,GridLayout.class.getName());
        GridLayout layout = (GridLayout) testObject.getLayoutManager();
        assertEquals(1,layout.getRows());
        assertEquals(0,layout.getColumns());
        assertEquals(0,layout.getHgap());
        assertEquals(0,layout.getVgap());
    }

    @Test
    public void getLayoutManager_returns_FlowLayout_when_map_layout_is_FlowLayout() {
        map.put(LAYOUT_MANAGER,"FlowLayout");
        FlowLayout layout = (FlowLayout) testObject.getLayoutManager();
        assertEquals(FlowLayout.CENTER,layout.getAlignment());
        assertEquals(5,layout.getHgap());
        assertEquals(5,layout.getVgap());
    }

    @Test
    public void getLayoutManager_returns_FlowLayout_when_map_layout_is_full_FlowLayout_class_name() {
        map.put(LAYOUT_MANAGER,FlowLayout.class.getName());
        assertTrue(testObject.getLayoutManager() instanceof FlowLayout);
    }

    @Test
    public void getLayoutManager_returns_GridBagLayout_when_map_layout_is_FlowLayout() {
        map.put(LAYOUT_MANAGER,"GridBagLayout");
        assertTrue(testObject.getLayoutManager() instanceof GridBagLayout);
    }

    @Test
    public void getLayoutManager_returns_GridBagLayout_when_map_layout_is_full_GridBagLayout_class_name() {
        map.put(LAYOUT_MANAGER,GridBagLayout.class.getName());
        assertTrue(testObject.getLayoutManager() instanceof GridBagLayout);
    }

    @Test
    public void getLayoutManager_sets_Grid_constraints_from_map() {
        map.put(LAYOUT_MANAGER,"GridLayout");
        int rows = randomInt();
        int cols = randomInt();
        int hgap = randomInt();
        int vgap = randomInt();
        map.put(ROWS,Integer.toString(rows));
        map.put(COLS,Integer.toString(cols));
        map.put(HGAP,Integer.toString(hgap));
        map.put(VGAP,Integer.toString(vgap));
        GridLayout layout = (GridLayout) testObject.getLayoutManager();
        assertEquals(rows,layout.getRows());
        assertEquals(cols,layout.getColumns());
        assertEquals(hgap,layout.getHgap());
        assertEquals(vgap,layout.getVgap());
    }

    @Test
    public void getLayoutManager_sets_Flow_constraints_from_map() {
        map.put(LAYOUT_MANAGER,"FlowLayout");
        int align = FlowLayout.LEFT;
        int hgap = randomInt();
        int vgap = randomInt();
        map.put(ALIGN,"left");
        map.put(HGAP,Integer.toString(hgap));
        map.put(VGAP,Integer.toString(vgap));
        FlowLayout layout = (FlowLayout) testObject.getLayoutManager();
        assertEquals(align,layout.getAlignment());
        assertEquals(hgap,layout.getHgap());
        assertEquals(vgap,layout.getVgap());
    }

    @Test
    public void getLayoutManager_sets_Flow_constraints_from_map_for_right() {
        map.put(LAYOUT_MANAGER,"FlowLayout");
        int align = FlowLayout.RIGHT;
        map.put(ALIGN,"right");
        FlowLayout layout = (FlowLayout) testObject.getLayoutManager();
        assertEquals(align,layout.getAlignment());
    }

    @Test
    public void getLayoutManager_sets_Flow_constraints_from_map_for_center() {
        map.put(LAYOUT_MANAGER,"FlowLayout");
        int align = FlowLayout.CENTER;
        map.put(ALIGN,"center");
        FlowLayout layout = (FlowLayout) testObject.getLayoutManager();
        assertEquals(align,layout.getAlignment());
    }

    int random;
    private int randomInt() {
        random+=hashCode();
        return random;
    }

    @Test
    public void getLayoutManager_returns_MigLayout_when_map_layout_is_not_specified() {
        map.remove(LAYOUT_MANAGER);
        assertTrue(testObject.getLayoutManager() instanceof MigLayout);
    }

    @Test
    public void getComponentConstraints_uses_constraints_value() {
        String expected = toString();
        Constraints component = new Constraints(expected);
        assertSame(expected, testObject.getComponentConstraints(component));
    }

}
