package io.lessons.database;

import io.lessons.database.database.Database;
import org.cloudburstmc.server.plugin.PluginBase;

public class Loader extends PluginBase {

    @Override
    public void onLoad() {
        Database.connect();
    }

    @Override
    public void onDisable() {
        Database.disconnect();
    }
}
