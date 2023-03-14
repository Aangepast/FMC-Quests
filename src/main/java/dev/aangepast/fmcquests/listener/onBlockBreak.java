package dev.aangepast.fmcquests.listener;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import dev.aangepast.fmcquests.utils.Utils;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;

import java.io.IOException;

public class onBlockBreak implements Listener {

    private Main plugin;

    public onBlockBreak(Main plugin){
        this.plugin = plugin;
    }

    @EventHandler
    public void blockBreak(BlockBreakEvent e) throws IOException {
        Player player = e.getPlayer();
        User user = Utils.getUser(player.getUniqueId().toString(), plugin);
        if(user == null){return;}

        for (Quest quest : user.getQuests()){
            if (quest.getProgress() > -1) {
                if(quest.getActivity().equalsIgnoreCase("mine")){
                    if(e.getBlock().getType().toString().toLowerCase().equals(quest.getType().toLowerCase())){
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
