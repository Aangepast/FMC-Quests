package dev.aangepast.fmcquests.utils;

import dev.aangepast.fmcquests.Main;
import dev.aangepast.fmcquests.managers.User;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.bukkit.ChatColor.COLOR_CHAR;

public class Utils {

    public static User getUser(String uuid, Main plugin) {

        for(User user : plugin.getUsers().getUsers().keySet()){
            plugin.getLogger().info("Checking: " + uuid);
            if(user.getUuid().equals(uuid)){
                return user;
            }
        }
        plugin.getLogger().info("Returning with null");
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


}
