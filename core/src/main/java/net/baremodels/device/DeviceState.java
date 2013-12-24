package net.baremodels.device;

/**
 * The immutable state of a device at an instant.
 */
public final class DeviceState {

    public final int width;
    public final int height;

    public DeviceState(int width, int height) {
        this.width = width;
        this.height = height;
    }

    /**
     * For listening to device state changes.
     */
    public interface Listener {
        void onChange(DeviceState state);
    }

}
