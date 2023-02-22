package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
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
        List<String> quests = config.getStringList("quests");

        User user = new User();

        for(String rawName : quests){
            if(plugin.containsQuest(rawName)){
                Quest quest = plugin.getQuest(rawName);
                quest.setProgress(config.getInt("quests." + rawName + ".progress"));
                user.addQuest(quest);
                continue;
            }
            Quest quest = new Quest();
            quest.setRawName(rawName);
            quest.setName(rawName);
            quest.setProgress(config.getInt("quests." + rawName + ".progress"));
            user.addQuest(quest);
        }
        plugin.getUsers().addUser(user, player.getUniqueId().toString());



    }

}
