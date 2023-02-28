package dev.aangepast.fmcquests.inventories;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.ItemBuilder;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import java.util.List;

public class uitdagingenInventory {

    public void openUitdagingen(Player player, Main plugin, int pageNmr){

        if(!(plugin.getQuestPages().size() > 0)){return;}

        Inventory inv = Bukkit.createInventory(player, 54, ChatColor.DARK_GRAY + "Uitdagingen - (" + pageNmr + ")");

        User user = Utils.getUser(player.getUniqueId().toString(), plugin);
        if(user == null){return;}

        List<String> activeQuests = Utils.getActiveQuests(player, plugin);

        List<Quest> pagina = plugin.getQuestPages().get(pageNmr-1);

        for(int i = 0; pagina.size() > i;i++) {
            Quest quest = pagina.get(i);

            if (activeQuests.contains(quest.getRawName())) {
                int progression;

                Quest playerQuest = Utils.getQuest(quest.getRawName(), user);

                if(playerQuest == null){
                    progression=0;
                } else {
                    progression= playerQuest.getProgress();
                }

                ItemBuilder itemBuilder = new ItemBuilder(Material.WRITABLE_BOOK, 1).setName(ChatColor.translateAlternateColorCodes('&', quest.getName())).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beschrijving:").addLoreLine(ChatColor.YELLOW + " " + quest.getDescription()).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beloningen:").addLoreLine(ChatColor.DARK_PURPLE + " " + quest.getRewardOrbs() + " Orbs").addLoreLine(ChatColor.LIGHT_PURPLE + " " + quest.getRewardPoints() + " Punten").addLoreLine(ChatColor.AQUA + " " + quest.getRewardXp() + " XP").addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Status: " + ChatColor.GREEN + "Actief").addLoreLine(ChatColor.WHITE + "Progressie: " + ChatColor.GOLD + progression + "/" + quest.getAmount());
                if (quest.isDaily()) {
                    itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Dagelijks");
                } else if (quest.isWeekly()) {
                    itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Wekelijks");
                } else {
                    itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Eenmalig");
                }
                ItemStack item = itemBuilder.addLoreLine(" ").addLoreLine(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Klik om deze uitdaging te annuleren.").toItemStack();
                inv.setItem(i, item);
                continue;
            }

            ItemBuilder itemBuilder = new ItemBuilder(Material.BOOK, 1).setName(ChatColor.translateAlternateColorCodes('&', quest.getName())).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beschrijving:").addLoreLine(ChatColor.YELLOW + " " + quest.getDescription()).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beloningen:").addLoreLine(ChatColor.DARK_PURPLE + " " + quest.getRewardOrbs() + " Orbs").addLoreLine(ChatColor.LIGHT_PURPLE + " " + quest.getRewardPoints() + " Punten").addLoreLine(ChatColor.AQUA + " " + quest.getRewardXp() + " XP").addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Status: " + ChatColor.RED + "Niet actief");
            if (quest.isDaily()) {
                itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Dagelijks");
            } else if (quest.isWeekly()) {
                itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Wekelijks");
            } else {
                itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Eenmalig");
            }
            ItemStack item = itemBuilder.addLoreLine(" ").addLoreLine(ChatColor.GREEN + ChatColor.UNDERLINE.toString() + "Klik om deze uitdaging te starten.").toItemStack();
            inv.setItem(i, item);
        }

        ItemStack currentPage = new ItemBuilder(Material.SPECTRAL_ARROW,1).setName(ChatColor.DARK_PURPLE + "Huidige Pagina: " + ChatColor.LIGHT_PURPLE + pageNmr).addLoreLine(ChatColor.WHITE + "Je zit nu op pagina " + ChatColor.LIGHT_PURPLE + pageNmr + ChatColor.WHITE + " van " + ChatColor.DARK_PURPLE + plugin.getQuestPages().size()).toItemStack();

        ItemStack nextPage = new ItemBuilder(Material.ARROW ,1).setName(ChatColor.GRAY + "Volgende pagina: " + ChatColor.DARK_GRAY + pageNmr+1).addLoreLine(ChatColor.GRAY + "Klik om naar de volgende ")

        inv.setItem(49, currentPage);

        player.openInventory(inv);

    }


}
