package org.ezhik.cs2craft.events;

import org.ezhik.cs2craft.CsGameplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.PlayerDeathEvent;

public class DeathPlayersEvent implements Listener {

    @EventHandler
    public void onPlayerDeath(PlayerDeathEvent event) {
        if (CsGameplay.getcommand(event.getEntity()) == "ct") CsGameplay.ctalive--;
        if (CsGameplay.getcommand(event.getEntity()) == "t") CsGameplay.talive--;
        Bukkit.broadcastMessage(event.getEntity().getKiller().getName() + " убил " + event.getEntity().getName());
        event.getDrops().clear();
        CsGameplay.addbalance(event.getEntity().getKiller(), 300);
        if (CsGameplay.getcommand(event.getEntity()) == CsGameplay.getcommand(event.getEntity().getKiller())) {
            event.getEntity().getKiller().sendMessage("Вы убили своего союзника");
            CsGameplay.removebalance(event.getEntity().getKiller(), 300);
        }


        for(Player player : Bukkit.getOnlinePlayers()){
            player.sendTitle("Осталось Т:" + CsGameplay.talive + " " + "КТ:" + CsGameplay.ctalive, "", 10, 40, 10);
        }


        if (CsGameplay.round == 5) {
            if (CsGameplay.ctalive == 0) {
                Bukkit.broadcastMessage("Команда террориттов победила!");

                CsGameplay.startsort();
            }
            if (CsGameplay.talive == 0) {
                Bukkit.broadcastMessage("Команда контртеррористов победила!");

                CsGameplay.startsort();
            }
        }else{
            if (CsGameplay.ctalive == 0){
                Bukkit.broadcastMessage("Команда терроритстов выиграла раунд!");
                for (Player player : CsGameplay.getctplayers()) CsGameplay.addbalance(player, 2300);
                for (Player player : CsGameplay.gettplayers()) CsGameplay.addbalance(player, 2700);

                }
                CsGameplay.runbattle();
            }
            if (CsGameplay.talive == 0){
                Bukkit.broadcastMessage("Команда контртеррористов выиграла раунд!");
                for (Player player : CsGameplay.getctplayers()) CsGameplay.addbalance(player, 2700);
                for (Player player : CsGameplay.gettplayers()) CsGameplay.addbalance(player, 2300);
                CsGameplay.runbattle();
            }
        }
    }


