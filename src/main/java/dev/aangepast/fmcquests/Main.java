package dev.aangepast.fmcquests;

import dev.aangepast.fmcquests.listener.onJoin;
import dev.aangepast.fmcquests.listener.onLeave;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class Main extends JavaPlugin {

    private UserManager users;
    private List<Quest> questList = new ArrayList<>();
    private static Main instance;

    @Override
    public void onEnable() {
        users = new UserManager();
        instance = this;

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new onJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new onLeave(this), this);


        for(String key : getConfig().getKeys(false)){
            if(key.equalsIgnoreCase("settings")){continue;}

            Quest quest = new Quest();

            quest.setName(getConfig().getString("name"));
            quest.setRawName(getConfig().getString("rawName"));
            quest.setAmount(getConfig().getInt("amount"));
            quest.setDescription(getConfig().getString("description"));
            quest.setDaily(getConfig().getBoolean("isDaily"));
            quest.setWeekly(getConfig().getBoolean("isWeekly"));
            quest.setLifetime(getConfig().getBoolean("isLifetime"));
            quest.setPersonal(getConfig().getBoolean("isPersonal"));
            quest.setKingdom(getConfig().getBoolean("isKingdom"));
            quest.setRewardOrbs(getConfig().getInt("rewardOrbs"));
            quest.setRewardXp(getConfig().getInt("rewardXp"));
            quest.setRewardPoints(getConfig().getInt("rewardPoints"));

            getLogger().info("Quest " + quest.getName() + " geladen!");

            questList.add(quest);
        }

    }



    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public UserManager getUsers() {
        return users;
    }

    public void setUsers(UserManager users) {
        this.users = users;
    }

    public static Main getInstance() {
        return instance;
    }

    public static void setInstance(Main instance) {
        Main.instance = instance;
    }

    public List<Quest> getQuestList() {
        return questList;
    }

    public boolean containsQuest(String rawName){
        for(Quest quest : questList){
            if(quest.getRawName().equals(rawName)){
                return true;
            }
        }
        return false;
    }

    public Quest getQuest(String rawName){
        for(Quest quest : questList){
            if(quest.getRawName().equals(rawName)){
                return quest;
            }
        }
        return null;
    }
}
