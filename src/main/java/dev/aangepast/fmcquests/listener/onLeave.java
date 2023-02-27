package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import java.io.File;
import java.io.IOException;
import java.util.Map;

public class onLeave implements Listener {

    private Main plugin;

    public onLeave(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onPlayerLeave(PlayerQuitEvent e) throws IOException {

        File file = new File(plugin.getDataFolder() + "/users/" + e.getPlayer().getUniqueId().toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        User user = Utils.getUser(e.getPlayer().getUniqueId().toString(), plugin);

        if(user == null){return;}

        for (Quest quest : user.getQuests()){
            if(config.getStringList("quests").contains(quest.getRawName())){config.set("quests." + quest.getRawName() + ".progress", quest.getProgress());}
        }
        config.save(file);
        plugin.getUsers().removeUser(user);
    }

}
