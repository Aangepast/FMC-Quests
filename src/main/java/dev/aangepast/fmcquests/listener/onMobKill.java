package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDeathEvent;

import java.io.IOException;

public class onMobKill implements Listener {

    private Main plugin;

    public onMobKill(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMobDeath(EntityDeathEvent e) throws IOException {
        if(e.getEntity().getKiller() != null){
            Player player = e.getEntity().getKiller();
            User user = Utils.getUser(player.getUniqueId().toString(), plugin);

            if(user == null){return;}

            if(user.getQuests().size() > 0){

                for(Quest quest : user.getQuests()){
                    if(quest.getProgress() > -1){
                        if(quest.getActivity().equalsIgnoreCase("kill")){
                            if(quest.getType().toLowerCase().contains(e.getEntity().getType().toString().toLowerCase())){
                                quest.setProgress(quest.getProgress() + 1);
                                if(quest.getProgress() >= quest.getAmount()){
                                    Utils.completeQuest(player, quest, plugin);
                                }
                            }
                        }
                    }
                }

            }
        }
    }


}
