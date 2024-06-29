package org.ezhik.cs2craft;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;

import java.util.*;

public class CsGameplay {
    private static Map<String, Integer> ctplayers = new HashMap<>();
    private static Map<String, Integer> tplayers = new HashMap<>();
    public static boolean sorting = false;
    public static int ctalive = 0;
    public static int talive = 0;
    public static int round = 0;



    public static void addct(Player player, int ballance){
        if (tplayers.containsKey(player.getName())) tplayers.remove(player.getName());
        boolean iscterror = ctplayers.containsKey(player.getName());
        ctplayers.put(player.getName(), ballance);
        if (!iscterror) player.sendMessage("Вы добавлены в комманду контртеррористов");
    }

    public static void addct(String name, int ballance){
        Player player = Bukkit.getPlayer(name);
        if (player != null) addct(player, ballance);
    }

    public static void addt(Player player, int ballance){
        if (ctplayers.containsKey(player.getName())) ctplayers.remove(player.getName());
        boolean isterror = tplayers.containsKey(player.getName());
        tplayers.put(player.getName(), ballance);
        if (!isterror) player.sendMessage("Вы добавлены в комманду террористов");
    }
    
    public static void addt(String name, int ballance){
        Player player = Bukkit.getPlayer(name);
        if (player != null) addt(player, ballance);
    }

    public static void startsort(){
        round = 0;
        sorting = true;
        for (Player player : Bukkit.getOnlinePlayers()) {
            tptolobby(player);
        }
        Bukkit.broadcastMessage("Сортировка началась");
    }

    public static void tptolobby(Player player){
        player.teleport(new Location(player.getWorld(), 102, 170, -92));
        player.getInventory().clear();
    }

    public static String getimbcommand(){
        if (ctplayers.size() > tplayers.size()) return "ct";
        if (ctplayers.size() < tplayers.size()) return "t";
        return "equal";
    }



    public static int getimbtplayers(){
        if (ctplayers.size() > tplayers.size()) return (ctplayers.size() - tplayers.size())/2;
        if (ctplayers.size() < tplayers.size()) return (tplayers.size() - ctplayers.size())/2;
        return 0;
    }

    public static void imbalancewarning(){
        if (getimbcommand().equals("ct")){
            for (Player player : getctplayers()){
                player.sendTitle("Команда контртеррористов больше чем команда террористов", "требуется еще" + getimbtplayers() + "игроков", 10, 200, 10);
            }
        }
        if (getimbcommand().equals("t")){
            for (Player player : gettplayers()){
               player.sendTitle("Команда террористов больше чем команда контртеррористов", "требуется еще" + getimbtplayers() + "игроков", 10, 200, 10);
                }
        }
        if (getimbcommand().equals("equal")){
            for (Player player : Bukkit.getOnlinePlayers()){
                player.sendTitle("","",10,200,10);
            }
        }

    }

    public static ArrayList<Player> getctplayers(){
        ArrayList<Player> players = new ArrayList<>();
        for (String name : ctplayers.keySet()){
            Player player = Bukkit.getPlayer(name);
            if (player != null) players.add(player);
        }
        return players;
    }

    public static ArrayList<Player> gettplayers(){
        ArrayList<Player> players = new ArrayList<>();
        for (String name : tplayers.keySet()){
            Player player = Bukkit.getPlayer(name);
            if (player != null) players.add(player);
        }
        return players;
    }

    public static String getcommand(Player player){
        if (ctplayers.containsKey(player.getName())) return "ct";
        if (tplayers.containsKey(player.getName())) return "t";
        return "none";
    }

    public static String getcommand(String name){
        Player player = Bukkit.getPlayer(name);
        if (player != null) return getcommand(player);
        return "none";
    }

    public static int getbalance(Player player){
        if (ctplayers.containsKey(player.getName())) return ctplayers.get(player.getName());
        if (tplayers.containsKey(player.getName())) return tplayers.get(player.getName());
        return -1;
    }

    public static int getbalance(String name){
        Player player = Bukkit.getPlayer(name);
        if (player != null) return getbalance(player);
        return -1;
    }

    public static void addbalance(Player player, int ballance){
        if (ctplayers.containsKey(player.getName())) ctplayers.put(player.getName(), ctplayers.get(player.getName()) + ballance);
        if (tplayers.containsKey(player.getName())) tplayers.put(player.getName(), tplayers.get(player.getName()) + ballance);
        player.sendMessage("Вы получили " + ballance + " монет. Ваш баланс: " + getbalance(player));
    }

    public static void addbalance(String name, int ballance){
        Player player = Bukkit.getPlayer(name);
        if (player != null) addbalance(player, ballance);
    }

    public static void removebalance(Player player, int ballance){
        if (ctplayers.containsKey(player.getName())) ctplayers.put(player.getName(), ctplayers.get(player.getName()) - ballance);
        if (tplayers.containsKey(player.getName())) tplayers.put(player.getName(), tplayers.get(player.getName()) - ballance);
        player.sendMessage("Вы потратили " + ballance + " монет. Ваш баланс: " + getbalance(player));
    }

    public static void removebalance(String name, int ballance){
        Player player = Bukkit.getPlayer(name);
        if (player != null) removebalance(player, ballance);
    }

    public static void stopsort(){
        sorting = false;
        Bukkit.broadcastMessage("Сортировка завершена");
    }

    public static void forcerandomsort(){
        Random random = new Random();
        if (getimbcommand() == "ct"){
            ArrayList<String> names = new ArrayList<>(ctplayers.keySet());
            for (int i = 0; i < getimbtplayers(); i++){
                addt(names.get(random.nextInt(names.size())), 1000);
            }
        }
        if (getimbcommand() == "t"){
            ArrayList<String> names = new ArrayList<>(tplayers.keySet());
            for (int i = 0; i < getimbtplayers(); i++){
                addct(names.get(random.nextInt(names.size())), 1000);
            }
        }
    }

    public static void runbattle(){
        round++;

        stopsort();
        forcerandomsort();
        for(Player player : getctplayers()){
            player.spigot().respawn();
            player.teleport(new Location(player.getWorld(), 97,163,-90));
        }
        for(Player player : gettplayers()){
            player.spigot().respawn();
            player.teleport(new Location(player.getWorld(), 157,173,-75));
        }

        ctalive = getctplayers().size();
        talive = gettplayers().size();

    }
    public static ItemStack getmenuitem(String title, String lore, Material material){
        ItemStack item = new ItemStack(material);
        ItemMeta itemmeta = item.getItemMeta();
        itemmeta.setDisplayName(title);
        itemmeta.setLore(Arrays.asList(lore));
        item.setItemMeta(itemmeta);
        return item;
    }

    public static void showbuymenu(Player player){
        player.sendMessage("Ваш баланс: " + getbalance(player));
        Inventory ctbuymenu = Bukkit.createInventory(null, 45, "Купить. У вас " + getbalance(player) + " монет");
        ctbuymenu.setItem(0, getmenuitem("USP(200 монет)", "Нажми чтобы купить", Material.DIAMOND));
        ctbuymenu.setItem(1, getmenuitem("M16(2900 монет)", "Нажми чтобы купить", Material.GOLD_INGOT));
        ctbuymenu.setItem(2, getmenuitem("AWP(4000 монет)", "Нажми чтобы купить", Material.IRON_INGOT));
        ItemStack nethershkem = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta nethershkemmeta = nethershkem.getItemMeta();
        nethershkemmeta.setDisplayName("Броня I(150 монет)");
        nethershkemmeta.setLore(Arrays.asList("Нажми чтобы купить"));
        nethershkemmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, true);
        nethershkem.setItemMeta(nethershkemmeta);
        ctbuymenu.setItem(7, nethershkem);
        ItemStack netrnagr2 = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta netrnagrmeta2 = netrnagr2.getItemMeta();
        netrnagrmeta2.setDisplayName("Броня I(150 монет)");
        netrnagrmeta2.setLore(Arrays.asList("Нажми чтобы купить"));
        netrnagrmeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netrnagr2.setItemMeta(netrnagrmeta2);
        ctbuymenu.setItem(16, netrnagr2);
        ItemStack netrleggs = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta netrleggsmeta = netrleggs.getItemMeta();
        netrleggsmeta.setDisplayName("Броня I(150 монет)");
        netrleggsmeta.setLore(Arrays.asList("Нажми чтобы купить"));
        netrleggsmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netrleggs.setItemMeta(netrleggsmeta);
        ctbuymenu.setItem(25, netrleggs);
        player.openInventory(ctbuymenu);
        ItemStack netrbochka = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta netrbochkameta = netrbochka.getItemMeta();
        netrbochkameta.setDisplayName("Броня I(150 монет)");
        netrbochkameta.setLore(Arrays.asList("Нажми чтобы купить"));
        netrbochkameta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netrbochka.setItemMeta(netrbochkameta);
        ctbuymenu.setItem(34, netrbochka);
        ItemStack netrshlem4 = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta netrshlemmeta1 = netrshlem4.getItemMeta();
        netrshlemmeta1.setDisplayName("Броня II(350 монет)");
        netrshlemmeta1.setLore(Arrays.asList("Нажми чтобы купить"));
        netrshlemmeta1.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netrshlem4.setItemMeta(netrshlemmeta1);
        ctbuymenu.setItem(8, netrshlem4);
        ItemStack netrnagr3 = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta netrnagrmeta3 = netrnagr3.getItemMeta();
        netrnagrmeta3.setDisplayName("Броня II(350 монет)");
        netrnagrmeta3.setLore(Arrays.asList("Нажми чтобы купить"));
        netrnagrmeta3.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netrnagr3.setItemMeta(netrnagrmeta3);
        ctbuymenu.setItem(17, netrnagr3);
        ItemStack netrleggs2 = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta netrleggsmeta2 = netrleggs2.getItemMeta();
        netrleggsmeta2.setDisplayName("Броня II(350 монет)");
        netrleggsmeta2.setLore(Arrays.asList("Нажми чтобы купить"));
        netrleggsmeta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netrleggs2.setItemMeta(netrleggsmeta2);
        ctbuymenu.setItem(26, netrleggs2);
        ItemStack netrbochka2 = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta netrbochkameta2 = netrbochka2.getItemMeta();
        netrbochkameta2.setDisplayName("Броня II(350 монет)");
        netrbochkameta2.setLore(Arrays.asList("Нажми чтобы купить"));
        netrbochkameta2.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netrbochka2.setItemMeta(netrbochkameta2);
        ctbuymenu.setItem(35, netrbochka2);
        ctbuymenu.setItem(6, getmenuitem("Граната", "Нажми чтобы купить", Material.WHITE_DYE));
        ctbuymenu.setItem(15, getmenuitem("Граната", "Нажми чтобы купить", Material.RED_DYE));
        ctbuymenu.setItem(24, getmenuitem("Граната", "Нажми чтобы купить", Material.BLUE_DYE));
        ctbuymenu.setItem(33, getmenuitem("Граната", "Нажми чтобы купить", Material.ORANGE_DYE));
        ctbuymenu.setItem(42, getmenuitem("Граната", "Нажми чтобы купить", Material.GREEN_DYE));


        Inventory tbuymenu = Bukkit.createInventory(null, 45, "Купить. У вас " + getbalance(player) + " монет");
        tbuymenu.setItem(0, getmenuitem("GLOCK(200 монет)", "Нажми чтобы купить", Material.DIAMOND));
        tbuymenu.setItem(1, getmenuitem("AK47(2900 монет)", "Нажми чтобы купить", Material.GOLD_INGOT));
        tbuymenu.setItem(2, getmenuitem("AWP(4000 монет)", "Нажми чтобы купить", Material.IRON_INGOT));
        ItemStack netrshlem = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta netrshlemmeta = netrshlem.getItemMeta();
        netrshlemmeta.setDisplayName("Броня I(150 монет)");
        netrshlemmeta.setLore(Arrays.asList("Нажми чтобы купить"));
        netrshlemmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netrshlem.setItemMeta(netrshlemmeta);
        tbuymenu.setItem(7, netrshlem);
        ItemStack netrnagr = new ItemStack(Material.NETHERITE_CHESTPLATE);

        ItemMeta netrnagrmeta = netrnagr.getItemMeta();
        netrnagrmeta.setDisplayName("Броня I(150 монет)");
        netrnagrmeta.setLore(Arrays.asList("Нажми чтобы купить"));
        netrnagrmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netrnagr.setItemMeta(netrnagrmeta);
        tbuymenu.setItem(16, netrnagr);
        ItemStack netherponozh = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta netherponozhmeta = netherponozh.getItemMeta();
        netherponozhmeta.setDisplayName("Броня I(150 монет)");
        netherponozhmeta.setLore(Arrays.asList("Нажми чтобы купить"));
        netherponozhmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netherponozh.setItemMeta(netherponozhmeta);
        tbuymenu.setItem(25, netherponozh);
        ItemStack netherbochka = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta netherbochkameta = netherbochka.getItemMeta();
        netherbochkameta.setDisplayName("Броня I(150 монет)");
        netherbochkameta.setLore(Arrays.asList("Нажми чтобы купить"));
        netherbochkameta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netherbochka.setItemMeta(netherbochkameta);
        tbuymenu.setItem(34, netherbochka);
        ItemStack netrshlem8 = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta netrshlemmeta8 = netrshlem8.getItemMeta();
        netrshlemmeta8.setDisplayName("Броня II(350 монет)");
        netrshlemmeta8.setLore(Arrays.asList("Нажми чтобы купить"));
        netrshlemmeta8.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netrshlem8.setItemMeta(netrshlemmeta8);
        tbuymenu.setItem(8, netrshlem8);
        ItemStack netrnagr8 = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta netrnagrmeta8 = netrnagr8.getItemMeta();
        netrnagrmeta8.setDisplayName("Броня II(350 монет)");
        netrnagrmeta8.setLore(Arrays.asList("Нажми чтобы купить"));
        netrnagrmeta8.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netrnagr8.setItemMeta(netrnagrmeta8);
        tbuymenu.setItem(17, netrnagr8);
        ItemStack netherponozh8 = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta netherponozhmeta8 = netherponozh8.getItemMeta();
        netherponozhmeta8.setDisplayName("Броня II(350 монет)");
        netherponozhmeta8.setLore(Arrays.asList("Нажми чтобы купить"));
        netherponozhmeta8.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netherponozh8.setItemMeta(netherponozhmeta8);
        tbuymenu.setItem(26, netherponozh8);
        ItemStack netherbochka8 = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta netherbochkameta8 = netherbochka8.getItemMeta();
        netherbochkameta8.setDisplayName("Броня II(350 монет)");
        netherbochkameta8.setLore(Arrays.asList("Нажми чтобы купить"));
        netherbochkameta8.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netherbochka8.setItemMeta(netherbochkameta8);
        tbuymenu.setItem(35, netherbochka8);
        tbuymenu.setItem(6, getmenuitem("Граната", "Нажми чтобы купить", Material.WHITE_DYE));
        tbuymenu.setItem(15, getmenuitem("Граната", "Нажми чтобы купить", Material.RED_DYE));
        tbuymenu.setItem(24, getmenuitem("Граната", "Нажми чтобы купить", Material.BLUE_DYE));
        tbuymenu.setItem(33, getmenuitem("Граната", "Нажми чтобы купить", Material.ORANGE_DYE));
        tbuymenu.setItem(42, getmenuitem("Граната", "Нажми чтобы купить", Material.GREEN_DYE));

        if (getcommand(player) == "ct") player.openInventory(ctbuymenu);
        if (getcommand(player) == "t") player.openInventory(tbuymenu);


    }
    public static void giveanetrshlem(Player player){
        ItemStack netrshlem = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta netrshlemmeta = netrshlem.getItemMeta();
        netrshlemmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netrshlem.setItemMeta(netrshlemmeta);
        player.getEquipment().setHelmet(netrshlem);

    }
    public static void giveanetrnagr(Player player){
        ItemStack netrnagr = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta netrnagrmeta = netrnagr.getItemMeta();
        netrnagrmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netrnagr.setItemMeta(netrnagrmeta);
        player.getEquipment().setChestplate(netrnagr);

    }
    public static void giveanetherponozh(Player player){
        ItemStack netherponozh = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta netherponozhmeta = netherponozh.getItemMeta();
        netherponozhmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netherponozh.setItemMeta(netherponozhmeta);
        player.getEquipment().setLeggings(netherponozh);

    }
    public static void giveanetherbochka(Player player){
        ItemStack netherbochka = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta netherbochkameta = netherbochka.getItemMeta();
        netherbochkameta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 2, false);
        netherbochka.setItemMeta(netherbochkameta);
        player.getEquipment().setBoots(netherbochka);
    }
    public static void giveneatrshlem2(Player player){
        ItemStack netrshlem = new ItemStack(Material.NETHERITE_HELMET);
        ItemMeta netrshlemmeta = netrshlem.getItemMeta();
        netrshlemmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netrshlem.setItemMeta(netrshlemmeta);
        player.getEquipment().setHelmet(netrshlem);

    }
    public static void giveneatrnagr2(Player player){
        ItemStack netrnagr = new ItemStack(Material.NETHERITE_CHESTPLATE);
        ItemMeta netrnagrmeta = netrnagr.getItemMeta();
        netrnagrmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netrnagr.setItemMeta(netrnagrmeta);
        player.getEquipment().setChestplate(netrnagr);
    }
    public static void giveneatherponozh2(Player player){
        ItemStack netherponozh = new ItemStack(Material.NETHERITE_LEGGINGS);
        ItemMeta netherponozhmeta = netherponozh.getItemMeta();
        netherponozhmeta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netherponozh.setItemMeta(netherponozhmeta);
        player.getEquipment().setLeggings(netherponozh);
    }
    public static void giveneatherbochka2(Player player){
        ItemStack netherbochka = new ItemStack(Material.NETHERITE_BOOTS);
        ItemMeta netherbochkameta = netherbochka.getItemMeta();
        netherbochkameta.addEnchant(Enchantment.PROTECTION_ENVIRONMENTAL, 4, false);
        netherbochka.setItemMeta(netherbochkameta);
        player.getEquipment().setBoots(netherbochka);
    }
    public void cthidePlayer(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = board.getTeam("Террористы");
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        team.addEntry(player.getName());
    }
    public void thidePlayur(Player player) {
        Scoreboard board = Bukkit.getScoreboardManager().getMainScoreboard();
        Team team = board.getTeam("Контр-террористы");
        team.setOption(Team.Option.NAME_TAG_VISIBILITY, Team.OptionStatus.NEVER);
        team.addEntry(player.getName());
    }

}
