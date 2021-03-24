package rien.bijl.Scoreboard.r.Plugin.Commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import rien.bijl.Scoreboard.r.Plugin.Utility.i18n;

public class Manager implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {

        if (!(commandSender instanceof Player)) {
            commandSender.sendMessage(i18n.get("commands.notaplayer"));
            return false;
        }

        Player player = (Player) commandSender;

        if (args.length < 1) {
            new Help(player);
        } else {
            switch (args[0].toLowerCase()) {
                case "on":
                    new On(player);
                    break;
                case "off":
                    new Off(player);
                    break;
                case "toggle":
                    new Toggle(player);
                    break;
                case "reload":
                    new Reload(player);
                    break;
                default:
                    player.sendMessage(i18n.get("commands.notfound"));
                    break;
            }
        }

        return false;
    }
}
