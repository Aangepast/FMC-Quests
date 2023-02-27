package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.inventories.uitdagingenInventory;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class inventoryClick implements Listener {

    private Main plugin;

    public inventoryClick(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent e){
        if(e.getView().getTitle().contains("Seizoens Uitdagingen")){
            e.setCancelled(true);

            switch(e.getRawSlot()){
                case 43:
                    uitdagingenInventory inv = new uitdagingenInventory();
                    inv.openUitdagingen((Player) e.getWhoClicked(), plugin);
            }


        }
    }

}
