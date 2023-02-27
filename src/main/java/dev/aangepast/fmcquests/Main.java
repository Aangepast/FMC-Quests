package dev.aangepast.fmcquests;

import dev.aangepast.fmcquests.commands.questCommand;
import dev.aangepast.fmcquests.inventories.uitdagingenInventory;
import dev.aangepast.fmcquests.listener.inventoryClick;
import dev.aangepast.fmcquests.listener.onJoin;
import dev.aangepast.fmcquests.listener.onLeave;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.UserManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public final class Main extends JavaPlugin {

    private UserManager users;
    private List<Quest> questList = new ArrayList<>();
    private HashMap<String, uitdagingenInventory> uitdagingInventories = new HashMap<>();
    private static Main instance;

    List<List<Quest>> questPages = new ArrayList<>();

    @Override
    public void onEnable() {
        users = new UserManager();
        instance = this;

        saveDefaultConfig();

        Bukkit.getPluginManager().registerEvents(new onJoin(this), this);
        Bukkit.getPluginManager().registerEvents(new onLeave(this), this);
        Bukkit.getPluginManager().registerEvents(new inventoryClick(this), this);
        Bukkit.getPluginCommand("quest").setExecutor(new questCommand(this));


        for(String key : getConfig().getKeys(false)){
            if(key.equalsIgnoreCase("settings")){continue;}

            Quest quest = new Quest();

            quest.setName(getConfig().getString(key + ".name"));
            quest.setRawName(key);
            quest.setAmount(getConfig().getInt(key + ".amount"));
            quest.setDescription(getConfig().getString(key + ".description"));
            quest.setDaily(getConfig().getBoolean(key + ".isDaily"));
            quest.setWeekly(getConfig().getBoolean(key + ".isWeekly"));
            quest.setLifetime(getConfig().getBoolean(key + ".isLifetime"));
            quest.setPersonal(getConfig().getBoolean(key + ".isPersonal"));
            quest.setKingdom(getConfig().getBoolean(key + ".isKingdom"));
            quest.setRewardOrbs(getConfig().getInt(key + ".rewardOrbs"));
            quest.setRewardXp(getConfig().getInt(key + ".rewardXp"));
            quest.setRewardPoints(getConfig().getInt(key + ".rewardPoints"));

            getLogger().info("Quest " + quest.getName() + " geladen!");

            questList.add(quest);
        }

        double pages = Math.ceil(questList.size() / 45f);

        getLogger().info("Pages to load: " + pages);

        List<Quest> tempQuests = questList;
        List<Quest> questsToRemove = new ArrayList<>();

        getLogger().info("Loaded temp quests.");

        for(int i = 0;pages>i;i++){
            List<Quest> page = new ArrayList<>();

            getLogger().info("Created new page for page " + i);

            int x = 0;
            for (Quest quest : tempQuests){
                if (x > 44) {break;}
                getLogger().info("Adding quest to page");
                x++;
                getLogger().info("Adding: " + quest.getRawName());
                page.add(quest);
                getLogger().info("Added! Removing from temp quests...");
                tempQuests.remove(quest); /// ConcurrentModificationException, kan niet uit lijst verwijderen terwijl je de lijst looped
            }

            // remove hier de quests van de lijst

            getLogger().info("Adding page to questPages");
            questPages.add(page);
        }

        getLogger().info("Created " + pages + " pages with " + questPages.size() + " questPages");

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

    public HashMap<String, uitdagingenInventory> getUitdagingInventories() {
        return uitdagingInventories;
    }

    public void setUitdagingInventories(HashMap<String, uitdagingenInventory> uitdagingInventories) {
        uitdagingInventories = uitdagingInventories;
    }

    public void removeFromUitdagingenInventories(String uuid){
        this.uitdagingInventories.remove(uuid);
    }

    public void addUitdagingenInventories(String uuid, uitdagingenInventory inv){
        if(this.uitdagingInventories.containsKey(uuid)){
            uitdagingInventories.remove(uuid);
        }
        this.uitdagingInventories.put(uuid, inv);
    }
}
