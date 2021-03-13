package rien.bijl.Scoreboard.r.Plugin.Utility;

import org.bukkit.ChatColor;
import org.bukkit.configuration.ConfigurationSection;
import rien.bijl.Scoreboard.r.Plugin.ConfigControl;

import java.util.Objects;

public class i18n {

    public static String get(String key)
    {
        ConfigurationSection section = ConfigControl.get().gc("language");

        String[] keys = key.split("\\.");

        for (int i = 0; i < keys.length; i++) {
            if (i == keys.length-1) {
                String data = section.getString(keys[i]);
                if (data == null) {
                    return  parse("&cWeird, translation failed!");
                }
                return parse(data);
            }
            section = section.getConfigurationSection(keys[i]);
            if (section == null) {
                return parse("&cWeird, translation failed!");
            }
        }
        return parse("&cWeird, translation failed!");
    }

    public static String parse(String content) {
        return ChatColor.translateAlternateColorCodes('&', content).replaceAll("%prefix%", getPrefix());
    }

    private static String getPrefix()
    {
        return ChatColor.translateAlternateColorCodes('&', Objects.requireNonNull(ConfigControl.get().gc("language").getConfigurationSection("general").getString("prefix")));
    }

}
