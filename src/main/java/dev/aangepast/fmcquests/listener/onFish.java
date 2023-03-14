package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerFishEvent;

import java.io.IOException;

public class onFish implements Listener {

    private Main plugin;

    public onFish(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void onFishing(PlayerFishEvent e) throws IOException {
        Player player = e.getPlayer();
        User user = Utils.getUser(player.getUniqueId().toString(), plugin);
        if(user == null){return;}

        for (Quest quest : user.getQuests()){
            if(quest.getProgress() > -1){
                if(quest.getActivity().equalsIgnoreCase("fish")){
                    if(e.getState().equals(PlayerFishEvent.State.CAUGHT_FISH)){
                        if(quest.getType().toLowerCase().contains(e.getCaught().getName().toLowerCase())){
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
