package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

import java.io.File;
import java.util.List;

public class onJoin implements Listener {

    private Main plugin;

    public onJoin(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onConnect(PlayerJoinEvent e){
        if(plugin.getUsers().containsUser(e.getPlayer().getUniqueId().toString())){return;}

        Player player = e.getPlayer();

        File file = new File(plugin.getDataFolder() + "/users/" + player.getUniqueId().toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        User user = new User();
        user.setUuid(player.getUniqueId().toString());

        for(String name : config.getKeys(false)){
            if(name.equalsIgnoreCase("stats")){continue;}
            Quest quest = plugin.getQuest(name);

            if(quest != null){
                quest.setProgress(config.getInt(name+".progression"));
                user.addQuest(quest);
                continue;
            }

            Quest quest1 = new Quest();
            quest1.setName(name);
            quest1.setRawName(name);
            quest1.setDescription(name);
            quest1.setLifetime(true);
            quest1.setProgress(config.getInt(name+".progression"));
            user.addQuest(quest1);

        }

        plugin.getUsers().addUser(user, player.getUniqueId().toString());

    }

}
