package dev.aangepast.fmcquests.commands;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.inventories.questInventory;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class questCommand implements CommandExecutor {

    private Main plugin;

    public questCommand(Main plugin){
        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if(!(sender instanceof Player)){return true;}

        Player player = (Player) sender;

        player.closeInventory();

        User user = Utils.getUser(player.getUniqueId().toString(), plugin);
        if(user == null){player.sendMessage(ChatColor.RED + "Er is iets fout gegaan tijdens het inlog proces. Probeer het later opnieuw.");return true;}

        questInventory questInventory = new questInventory(plugin);
        questInventory.openQuestInventory(player, user);


        return true;
    }
}
