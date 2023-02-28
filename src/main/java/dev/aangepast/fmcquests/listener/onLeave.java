package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.IOException;

public class onLeave implements Listener {

    private Main plugin;

    public onLeave(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) throws IOException {

        Utils.savePlayer(e.getPlayer(), plugin);
        plugin.getUsers().removeUser(Utils.getUser(e.getPlayer().getUniqueId().toString(), plugin));

    }

}
