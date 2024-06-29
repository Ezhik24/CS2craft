package org.ezhik.cs2craft.events;

import org.ezhik.cs2craft.CsGameplay;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinGameEvent implements Listener {
    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        event.getPlayer().sendMessage("Добро пожаловать в CS2Craft");
        if (CsGameplay.sorting)  {
            CsGameplay.tptolobby(event.getPlayer());
        }
    }
}
