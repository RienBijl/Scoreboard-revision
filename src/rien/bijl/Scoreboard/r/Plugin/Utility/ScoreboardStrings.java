package rien.bijl.Scoreboard.r.Plugin.Utility;

import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import rien.bijl.Scoreboard.r.Plugin.Session;

public class ScoreboardStrings {

    public static String make(Player player, String content) {
        return colors(placeholders(player, content));
    }

    public static String removeLastCharacter(String str) {
        String result = null;
        if ((str != null) && (str.length() > 0)) {
            result = str.substring(0, str.length() - 1);
        }
        return result;
    }

    public static String colors(String content) {
        return ChatColor.translateAlternateColorCodes('&', content);
    }

    public static String placeholders(Player player, String content) {
        if(Session.getSession().enabled_dependencies.contains(Session.getSession().dependencies[0]) && org.bukkit.Bukkit.getPluginManager().isPluginEnabled("PlaceholderAPI") &&
                PlaceholderAPI.containsPlaceholders(content)) {
            return PlaceholderAPI.setPlaceholders(player, content);
        }

        return content;
    }

}
