package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.inventories.uitdagingenInventory;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.io.IOException;
import java.util.List;

public class inventoryClick implements Listener {

    private Main plugin;

    public inventoryClick(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e) throws IOException {
        if(e.getView().getTitle().contains("Seizoens Uitdagingen")){
            e.setCancelled(true);

            switch(e.getRawSlot()){
                case 43:
                    uitdagingenInventory inv = new uitdagingenInventory();
                    inv.openUitdagingen((Player) e.getWhoClicked(), plugin, 1);
            }


        } else if (e.getView().getTitle().contains("Uitdagingen - (")){
            e.setCancelled(true);

            int clickedSlot = e.getRawSlot();
            int currentPage = Integer.parseInt((e.getView().getTitle().replace("Uitdagingen - (", "").replace(")", "").replace("§8", "")));

            if(e.getCurrentItem() == null){return;}
            if(e.getCurrentItem().getType().equals(Material.AIR)){return;}

            if(clickedSlot > -1 && clickedSlot < 45){
                List<Quest> pageQuests = plugin.getQuestPages().get(currentPage-1);
                Quest quest = pageQuests.get(clickedSlot);

                if(e.getClickedInventory().getItem(e.getRawSlot()).getType().equals(Material.WRITABLE_BOOK)){
                    Utils.stopQuest(quest, (Player) e.getWhoClicked(), plugin, currentPage);
                    return;
                }

                Utils.startQuest(quest, (Player) e.getWhoClicked(), plugin);

            } else if (clickedSlot > 44){

                switch(clickedSlot){
                    case 45:
                        if(currentPage > 1){
                            uitdagingenInventory inv = new uitdagingenInventory();
                            inv.openUitdagingen((Player) e.getWhoClicked(), plugin, currentPage-1);
                            break;
                        } else {
                            Player player = (Player) e.getWhoClicked();
                            player.sendMessage(ChatColor.RED + "Je bent momenteel al op de eerste pagina.");
                            player.playSound(player.getLocation(), "itemsadder:item.revolver.no_ammo",1,1);
                        }
                        break;
                    case 53:
                        if(!(plugin.getQuestPages().size() == currentPage)){
                            uitdagingenInventory inv = new uitdagingenInventory();
                            inv.openUitdagingen((Player) e.getWhoClicked(), plugin, (currentPage = currentPage + 1));
                            break;
                    } else {
                            Player player = (Player) e.getWhoClicked();
                            player.sendMessage(ChatColor.RED + "Je bent momenteel op de laatste pagina.");
                            player.playSound(player.getLocation(), "itemsadder:item.revolver.no_ammo",1,1);
                        }
                        break;
                }


            }



        }
    }

}
