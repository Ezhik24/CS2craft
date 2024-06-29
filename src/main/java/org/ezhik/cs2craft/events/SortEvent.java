package org.ezhik.cs2craft.events;

import org.ezhik.cs2craft.CsGameplay;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;

public class SortEvent implements Listener {
    @EventHandler
    public void onMove(PlayerMoveEvent event){
        if (CsGameplay.sorting) {
            Player player = event.getPlayer();
            if (player.getLocation().getX() > 102) CsGameplay.addct(player, 1000);
            else CsGameplay.addt(player, 1000);
            //CsGameplay.imbalancewarning();
        }
    }
}

