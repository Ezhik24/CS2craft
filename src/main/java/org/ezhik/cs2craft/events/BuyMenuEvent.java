package org.ezhik.cs2craft.events;

import org.ezhik.cs2craft.CsGameplay;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class BuyMenuEvent implements Listener {

    @EventHandler
    public void onInvClick(InventoryClickEvent event) {
        if (event.getView().getTitle().contains("Купить.")) {
            event.setCancelled(true);
            Player player = (Player) event.getWhoClicked();
            if (CsGameplay.getcommand(player) == "ct") {
                if (event.getSlot() == 0) {
                    if (CsGameplay.getbalance(player) < 200) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 200);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "qa give p30silenced " + player.getName());
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "qa give 9mm " + player.getName() + " 36");
                        player.sendMessage("Вы купили USP");
                    }
                }
                if (event.getSlot() == 1) {
                    if (CsGameplay.getbalance(player) < 2700) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 2700);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "qa give m16 " + player.getName() );
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "qa give 556 " + player.getName() + " 90");
                        player.sendMessage("Вы купили M16");
                    }
                }
                if (event.getSlot() == 7) {
                    if (CsGameplay.getbalance(player) < 150) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 150);
                        CsGameplay.giveanetrshlem(player);
                        player.sendMessage("Вы купили Броня I");
                    }
                }
                if (event.getSlot() == 16) {
                    if (CsGameplay.getbalance(player) < 150) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 150);
                        CsGameplay.giveanetrnagr(player);
                        player.sendMessage("Вы купили Броня I");
                    }
                }
                if (event.getSlot() == 25) {
                    if (CsGameplay.getbalance(player) < 150) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 150);
                        CsGameplay.giveanetherponozh(player);
                        player.sendMessage("Вы купили Броня I");
                    }
                }
                if (event.getSlot() == 34) {
                    if (CsGameplay.getbalance(player) < 150) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 150);
                        CsGameplay.giveanetherbochka(player);
                        player.sendMessage("Вы купили Броня I");
                    }
                }
                if (event.getSlot() == 8) {
                    if (CsGameplay.getbalance(player) < 350) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 350);
                        CsGameplay.giveneatrshlem2(player);
                        player.sendMessage("Вы купили Броня II");
                    }
                }
                if (event.getSlot() == 17) {
                    if (CsGameplay.getbalance(player) < 350) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 350);
                        CsGameplay.giveneatrnagr2(player);
                        player.sendMessage("Вы купили Броня II");
                    }
                }
                if (event.getSlot() == 26) {
                    if (CsGameplay.getbalance(player) < 350) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 350);
                        CsGameplay.giveneatherponozh2(player);
                        player.sendMessage("Вы купили Броня II");
                    }
                }
                if (event.getSlot() == 35) {
                    if (CsGameplay.getbalance(player) < 350) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 350);
                        CsGameplay.giveneatherbochka2(player);
                        player.sendMessage("Вы купили Броня II");
                    }
                }

            }
            if (CsGameplay.getcommand(player) == "t") {
                if (event.getSlot() == 0) {
                    if (CsGameplay.getbalance(player) < 200) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 200);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "qa give glock " + player.getName());
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "qa give 9mm " + player.getName() + " 36");
                        player.sendMessage("Вы купили GLOCK");

                    }
                }
                if (event.getSlot() == 1) {
                    if (CsGameplay.getbalance(player) < 2700) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 2700);
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "qa give ak47 " + player.getName() );
                        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "qa give 762 " + player.getName() + " 120");
                        player.sendMessage("Вы купили AK47");
                    }
                }
                if (event.getSlot() == 7) {
                    if (CsGameplay.getbalance(player) < 150) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 150);
                        CsGameplay.giveanetrshlem(player);
                        player.sendMessage("Вы купили Броня I");
                    }
                }
                if (event.getSlot() == 16) {
                    if (CsGameplay.getbalance(player) < 150) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 150);
                        CsGameplay.giveanetrnagr(player);
                        player.sendMessage("Вы купили Броня I");
                    }
                }
                if (event.getSlot() == 25) {
                    if (CsGameplay.getbalance(player) < 150) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 150);
                        CsGameplay.giveanetherponozh(player);
                        player.sendMessage("Вы купили Броня I");
                    }
                }
                if (event.getSlot() == 34) {
                    if (CsGameplay.getbalance(player) < 150) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 150);
                        CsGameplay.giveanetherbochka(player);
                        player.sendMessage("Вы купили Броня I");
                    }
                }
                if (event.getSlot() == 8) {
                    if (CsGameplay.getbalance(player) < 350) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 350);
                        CsGameplay.giveneatrshlem2(player);
                        player.sendMessage("Вы купили Броня II");
                    }
                }
                if (event.getSlot() == 17) {
                    if (CsGameplay.getbalance(player) < 350) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 350);
                        CsGameplay.giveneatrnagr2(player);
                        player.sendMessage("Вы купили Броня II");
                    }
                }
                if (event.getSlot() == 26) {
                    if (CsGameplay.getbalance(player) < 350) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 350);
                        CsGameplay.giveneatherponozh2(player);
                        player.sendMessage("Вы купили Броня II");
                    }
                }
                if (event.getSlot() == 35) {
                    if (CsGameplay.getbalance(player) < 350) player.sendMessage("У вас недостаточно монет");
                    else {
                        CsGameplay.removebalance(player, 350);
                        CsGameplay.giveneatherbochka2(player);
                        player.sendMessage("Вы купили Броня II");
                    }
                }
                event.getView().close();
                CsGameplay.showbuymenu(player);
            }
        }
    }
}
