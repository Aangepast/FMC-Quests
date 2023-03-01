package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.Utils;
import io.lumine.mythic.bukkit.events.MythicMobDeathEvent;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import java.io.IOException;

public class onMMKill implements Listener {

    private Main plugin;

    public onMMKill(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onMythicMobKill(MythicMobDeathEvent e) throws IOException {
        if(e.getKiller() instanceof Player){

            Player player = (Player) e.getKiller();
            User user = Utils.getUser(player.getUniqueId().toString(), plugin);

            if(user == null){return;}

            if(user.getQuests().size() > 0){

                for(Quest quest : user.getQuests()){
                    if(quest.getProgress() > -1){
                        if(quest.getActivity().equalsIgnoreCase("MMKill")){
                            if(quest.getType().equalsIgnoreCase(e.getMob().getMobType())){
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
