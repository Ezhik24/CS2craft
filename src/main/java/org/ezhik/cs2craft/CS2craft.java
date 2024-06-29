package org.ezhik.cs2craft;

import org.ezhik.cs2craft.events.BuyMenuEvent;
import org.ezhik.cs2craft.events.DeathPlayersEvent;
import org.ezhik.cs2craft.events.JoinGameEvent;
import org.ezhik.cs2craft.events.SortEvent;
import org.bukkit.plugin.java.JavaPlugin;
import org.ezhik.cs2craft.commands.*;

public final class CS2craft extends JavaPlugin {

    @Override
    public void onEnable() {
        getCommand("tolobby").setExecutor(new TolobbyCMD());
        getCommand("sortoff").setExecutor(new SortoffCMD());
        getCommand("runbattle").setExecutor(new StartbattleCMD());
        getCommand("b").setExecutor(new BuymenuCMD());
        getCommand("addbalance").setExecutor(new addBalanceCMD());
        getServer().getPluginManager().registerEvents(new SortEvent(), this);
        getServer().getPluginManager().registerEvents(new BuyMenuEvent(), this);
        getServer().getPluginManager().registerEvents(new DeathPlayersEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinGameEvent(), this);

    }

    @Override
    public void onDisable() {
    }
}
