package dev.aangepast.fmcquests.utils;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.inventories.uitdagingenInventory;
import dev.aangepast.fmcquests.managers.Quest;
import dev.aangepast.fmcquests.managers.User;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.ChatColor.COLOR_CHAR;

public class Utils {

    public static User getUser(String uuid, Main plugin) {

        for(User user : plugin.getUsers().getUsers().keySet()){
            if(user.getUuid().equals(uuid)){
                return user;
            }
        }
        return null;

    }

    public static String translateHexColorCodes(String startTag, String endTag, String message)
    {
        final Pattern hexPattern = Pattern.compile(startTag + "([A-Fa-f0-9]{6})" + endTag);
        Matcher matcher = hexPattern.matcher(message);
        StringBuffer buffer = new StringBuffer(message.length() + 4 * 8);
        while (matcher.find())
        {
            String group = matcher.group(1);
            matcher.appendReplacement(buffer, COLOR_CHAR + "x"
                    + COLOR_CHAR + group.charAt(0) + COLOR_CHAR + group.charAt(1)
                    + COLOR_CHAR + group.charAt(2) + COLOR_CHAR + group.charAt(3)
                    + COLOR_CHAR + group.charAt(4) + COLOR_CHAR + group.charAt(5)
            );
        }
        return matcher.appendTail(buffer).toString();
    }

    public static List<String> getActiveQuests(Player player, Main plugin){

        User user = getUser(player.getUniqueId().toString(), plugin);
        if(user == null){return null;}

        List<String> activeQuests = new ArrayList<>();

        for(Quest quest : user.getQuests()){
            activeQuests.add(quest.getRawName());
        }

        return activeQuests;

    }

    public static boolean startQuest(Quest quest, Player player, Main plugin) throws IOException {

        User user = Utils.getUser(player.getUniqueId().toString(), plugin);
        if (user == null) {return false;}

        List<String> activeQuests = getActiveQuests(player, plugin);

        if (activeQuests.contains(quest.getRawName())) {return false;}

        quest.setProgress(0);

        user.addQuest(quest);

        player.playSound(player.getLocation(), "block.note_block.bit", 1, 1);
        uitdagingenInventory inv = new uitdagingenInventory();
        inv.openUitdagingen(player, plugin, 1);
        player.sendMessage(ChatColor.GREEN + "Je hebt de quest " + ChatColor.translateAlternateColorCodes('&', quest.getName()) + ChatColor.GREEN + " gestart!");
        return savePlayer(player, plugin);

    }

    public static boolean stopQuest(Quest quest, Player player, Main plugin) throws IOException {
        File file = new File(plugin.getDataFolder() + "/users/" + player.getUniqueId().toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        User user = Utils.getUser(player.getUniqueId().toString(), plugin);
        if(user==null){return false;}

        List<String> activeQuests = getActiveQuests(player, plugin);

        if(!(activeQuests.contains(quest.getRawName()))){return false;}

        config.set(quest.getRawName() + ".progression", null);
        config.set(quest.getRawName() + ".rawName", null);
        config.set(quest.getRawName() + "", null);

        quest.setProgress(0);
        user.getQuests().remove(quest);
        user.removeQuest(quest);

        player.playSound(player.getLocation(), "block.note_block.bit",1,1);
        uitdagingenInventory inv = new uitdagingenInventory();
        inv.openUitdagingen(player, plugin, 1);

        player.sendMessage(ChatColor.RED + "Je hebt de quest " + ChatColor.translateAlternateColorCodes('&', quest.getName()) + ChatColor.RED + " gestopt.");

        return savePlayer(player, plugin);

    }

    public static boolean savePlayer(Player player, Main plugin) throws IOException {
        File file = new File(plugin.getDataFolder() + "/users/" + player.getUniqueId().toString() + ".yml");
        FileConfiguration config = YamlConfiguration.loadConfiguration(file);

        User user = Utils.getUser(player.getUniqueId().toString(), plugin);

        if(user == null){return false;}

        for (Quest quest : user.getQuests()){
            config.set(quest.getRawName()+".progression", quest.getProgress());
            config.set(quest.getRawName()+".rawName", quest.getRawName());
        }
        config.save(file);
        plugin.getUsers().removeUser(user);
        plugin.getUsers().addUser(user, player.getUniqueId().toString());
        player.sendMessage(ChatColor.GRAY + ChatColor.ITALIC.toString() + "Je profiel is opgeslagen.");
        return true;
    }

    public static Quest getQuest(String rawName, User user){
        for (Quest quest : user.getQuests()) {
            if(quest.getRawName().equals(rawName)){
                return quest;
            }
        }
        return null;
    }


}
