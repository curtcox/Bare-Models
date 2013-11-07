package net.baremodels.intents;

import net.baremodels.common.StreetAddress;
import net.baremodels.intent.Intent;

public final class MapAddressIntent
    extends Intent
{
    public final StreetAddress address;

    private MapAddressIntent(StreetAddress address) {
        this.address = address;
    }

    public static MapAddressIntent of(StreetAddress address) {
        return new MapAddressIntent(address);
    }
}
