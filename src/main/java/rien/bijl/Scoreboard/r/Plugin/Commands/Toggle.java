package rien.bijl.Scoreboard.r.Plugin.Commands;

import org.bukkit.entity.Player;
import rien.bijl.Scoreboard.r.Board.BoardPlayer;
import rien.bijl.Scoreboard.r.Plugin.Utility.i18n;

public class Toggle {

    public Toggle(Player player)
    {
        if (!player.hasPermission("scoreboard.toggle")) {
            player.sendMessage(i18n.get("commands.lackspermission"));
            return;
        }

        if (BoardPlayer.getBoardPlayer(player).worldLock) {
            player.sendMessage(i18n.get("commands.worldlock"));
            return;
        }

        BoardPlayer bp = BoardPlayer.getBoardPlayer(player);

        bp.setEnabled(!bp.isEnabled());

        if (bp.isEnabled()) {
            player.sendMessage(i18n.get("commands.toggleon"));
        } else {
            player.sendMessage(i18n.get("commands.toggleoff"));
        }
    }

}
