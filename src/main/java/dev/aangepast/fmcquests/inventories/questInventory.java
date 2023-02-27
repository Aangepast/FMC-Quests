package dev.aangepast.fmcquests.inventories;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.ItemBuilder;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class questInventory {

    private Main plugin;

    public void openQuestInventory(Player player, User user){

        Inventory inventory = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Seizoens Uitdagingen");

        ItemStack purpleGlass = new ItemBuilder(Material.PURPLE_STAINED_GLASS_PANE, 1).setName(ChatColor.RESET + " ").toItemStack();
        ItemStack pinkGlass = new ItemBuilder(Material.PINK_STAINED_GLASS_PANE, 1).setName(ChatColor.RESET + " ").toItemStack();
        ItemStack blazePowder = new ItemBuilder(Material.BLAZE_POWDER, 1).setName(Utils.translateHexColorCodes("&#", "", ChatColor.translateAlternateColorCodes('&', "&#ff9800&lU&#fda001&li&#fca901&lt&#fab102&ld&#f9b903&la&#f7c104&lg&#f6ca04&li&#f4d205&ln&#f3da06&lg&#f1e207&le&#f0eb07&ln &#eef308&lL&#f0eb07&le&#f1e207&la&#f3da06&ld&#f4d205&le&#f6ca04&lr&#f7c104&lb&#f9b903&lo&#fab102&la&#fca901&lr&#fda001&ld&#ff9800&ls"))).addLoreLine("").addLoreLine(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "Klik om naar de leaderboard te gaan.").toItemStack();
        ItemStack logo = new ItemBuilder(Material.PAPER, 1).setName("LOGO HIER").toItemStack();
        ItemStack uitdagingen = new ItemBuilder(Material.WRITABLE_BOOK, 1).setName(Utils.translateHexColorCodes("&#", "", ChatColor.translateAlternateColorCodes('&', "&#3b3d8f&lU&#34368f&li&#2d2f8f&lt&#25298f&ld&#1e228f&la&#171b8f&lg&#1e228f&li&#25298f&ln&#2d2f8f&lg&#34368f&le&#3b3d8f&ln"))).addLoreLine("").addLoreLine(ChatColor.AQUA + ChatColor.UNDERLINE.toString() + "Klik om naar de uitdagingen te gaan.").toItemStack();

        inventory.setItem(4, purpleGlass);
        inventory.setItem(13, purpleGlass);
        inventory.setItem(19, purpleGlass);
        inventory.setItem(20, purpleGlass);
        inventory.setItem(21, purpleGlass);
        inventory.setItem(23, purpleGlass);
        inventory.setItem(24, purpleGlass);
        inventory.setItem(25, purpleGlass);
        inventory.setItem(28, pinkGlass);
        inventory.setItem(34, pinkGlass);
        inventory.setItem(37, blazePowder);
        inventory.setItem(40, logo);
        inventory.setItem(43, uitdagingen);


        player.openInventory(inventory);


    }

    public questInventory(Main plugin) {
        this.plugin = plugin;
    }


}
