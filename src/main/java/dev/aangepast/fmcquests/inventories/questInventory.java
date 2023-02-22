package dev.aangepast.fmcquests.inventories;

import dev.aangepast.fmcquests.managers.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;

public class questInventory {

    public void openQuestInventory(Player player, User user){

        Inventory inventory = Bukkit.createInventory(player, 27, ChatColor.DARK_GRAY + "Seizoens uitdagingen");



        player.openInventory(inventory);

    }


}
