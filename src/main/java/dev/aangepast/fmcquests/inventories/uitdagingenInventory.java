package dev.aangepast.fmcquests.inventories;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.ItemBuilder;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemFlag;
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
            int progression;
            Quest playerQuest = Utils.getPlayerQuest(quest.getRawName(), user);

            if(playerQuest == null){
                progression=0;
            } else {
                progression= playerQuest.getProgress();
            }

            if (activeQuests.contains(quest.getRawName())) {
                if(progression == -1){
                    ItemBuilder itemBuilder = new ItemBuilder(Material.WRITABLE_BOOK, 1).setName(ChatColor.translateAlternateColorCodes('&', quest.getName())).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beschrijving:").addLoreLine(ChatColor.YELLOW + " " + quest.getDescription()).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beloningen:").addLoreLine(ChatColor.DARK_PURPLE + " " + quest.getRewardOrbs() + " Orbs").addLoreLine(ChatColor.LIGHT_PURPLE + " " + quest.getRewardPoints() + " Punten").addLoreLine(ChatColor.AQUA + " " + quest.getRewardXp() + " XP").addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Status: " + ChatColor.GOLD + "Voltooid");
                    ItemStack item = addTeVoltooienLine(itemBuilder, quest).addLoreLine(" ").addLoreLine(ChatColor.LIGHT_PURPLE + "Deze quest heb je voltooid!").addEnchant(Enchantment.DURABILITY, 1).toItemStack();
                    item.getItemMeta().addItemFlags(ItemFlag.HIDE_ENCHANTS);
                    inv.setItem(i, item);
                    continue;
                }
                ItemBuilder itemBuilder = new ItemBuilder(Material.WRITABLE_BOOK, 1).setName(ChatColor.translateAlternateColorCodes('&', quest.getName())).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beschrijving:").addLoreLine(ChatColor.YELLOW + " " + quest.getDescription()).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beloningen:").addLoreLine(ChatColor.DARK_PURPLE + " " + quest.getRewardOrbs() + " Orbs").addLoreLine(ChatColor.LIGHT_PURPLE + " " + quest.getRewardPoints() + " Punten").addLoreLine(ChatColor.AQUA + " " + quest.getRewardXp() + " XP").addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Status: " + ChatColor.GREEN + "Actief").addLoreLine(ChatColor.WHITE + "Progressie: " + ChatColor.GOLD + progression + "/" + quest.getAmount());
                ItemStack item = addTeVoltooienLine(itemBuilder, quest).addLoreLine(" ").addLoreLine(ChatColor.RED + ChatColor.UNDERLINE.toString() + "Klik om deze uitdaging te annuleren.").toItemStack();
                inv.setItem(i, item);
                continue;
            }

            ItemBuilder itemBuilder = new ItemBuilder(Material.BOOK, 1).setName(ChatColor.translateAlternateColorCodes('&', quest.getName())).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beschrijving:").addLoreLine(ChatColor.YELLOW + " " + quest.getDescription()).addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Beloningen:").addLoreLine(ChatColor.DARK_PURPLE + " " + quest.getRewardOrbs() + " Orbs").addLoreLine(ChatColor.LIGHT_PURPLE + " " + quest.getRewardPoints() + " Punten").addLoreLine(ChatColor.AQUA + " " + quest.getRewardXp() + " XP").addLoreLine(" ").addLoreLine(ChatColor.WHITE + "Status: " + ChatColor.RED + "Niet actief");

            ItemStack item = addTeVoltooienLine(itemBuilder, quest).addLoreLine(" ").addLoreLine(ChatColor.GREEN + ChatColor.UNDERLINE.toString() + "Klik om deze uitdaging te starten.").toItemStack();
            inv.setItem(i, item);
        }

        ItemStack currentPage = new ItemBuilder(Material.SPECTRAL_ARROW,1).setName(ChatColor.DARK_PURPLE + "Huidige Pagina: " + ChatColor.LIGHT_PURPLE + pageNmr).addLoreLine(ChatColor.WHITE + "Je zit nu op pagina " + ChatColor.LIGHT_PURPLE + pageNmr + ChatColor.WHITE + " van " + ChatColor.DARK_PURPLE + plugin.getQuestPages().size()).toItemStack();

        ItemStack nextPage = new ItemBuilder(Material.ARROW ,1).setName(ChatColor.DARK_PURPLE + "Volgende pagina: " + ChatColor.LIGHT_PURPLE + (pageNmr = pageNmr + 1)).addLoreLine(ChatColor.WHITE + "Klik om naar de volgende pagina te gaan.").toItemStack();
        ItemStack previousPage = new ItemBuilder(Material.ARROW,1).setName(ChatColor.DARK_PURPLE + "Vorige pagina: " + ChatColor.LIGHT_PURPLE + (pageNmr-1)).addLoreLine(ChatColor.WHITE + "Klik om naar de vorige pagina te gaan.").toItemStack();

        ItemStack blackGlass = new ItemBuilder(Material.BLACK_STAINED_GLASS_PANE,1).setName(" ").toItemStack();

        inv.setItem(53, nextPage);
        inv.setItem(45, previousPage);
        inv.setItem(49, currentPage);

        inv.setItem(46, blackGlass);
        inv.setItem(47, blackGlass);
        inv.setItem(48, blackGlass);

        inv.setItem(50, blackGlass);
        inv.setItem(51, blackGlass);
        inv.setItem(52, blackGlass);

        player.openInventory(inv);

    }

    private ItemBuilder addTeVoltooienLine(ItemBuilder itemBuilder, Quest quest){
        if (quest.isDaily()) {
            itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Dagelijks");
        } else if (quest.isWeekly()) {
            itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Wekelijks");
        } else {
            itemBuilder.addLoreLine(ChatColor.WHITE + "Te voltooien: " + ChatColor.AQUA + "Eenmalig");
        }
        return itemBuilder;
    }


}
