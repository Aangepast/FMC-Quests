package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.util.Map;

public class onLeave implements Listener {

    private Main plugin;

    public onLeave(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e){
        for (Map.Entry<User, String> entry : plugin.getUsers().getUsers().entrySet()){
            if(entry.getValue().equals(e.getPlayer().getUniqueId().toString())){

                File file = new File(plugin.getDataFolder() + "/users/" + e.getPlayer().getUniqueId().toString() + ".yml");
                FileConfiguration config = YamlConfiguration.loadConfiguration(file);

                for (Quest quest : entry.getKey().getQuests()){
                    if(config.getStringList("quests").contains(quest.getRawName())){config.set("quests." + quest.getRawName() + "progress", );}
                }


                plugin.getUsers().removeUser(entry.getKey());
            }
        }
    }

}
