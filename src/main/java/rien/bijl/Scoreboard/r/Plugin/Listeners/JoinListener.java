package rien.bijl.Scoreboard.r.Plugin.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import rien.bijl.Scoreboard.r.Board.BoardPlayer;
import rien.bijl.Scoreboard.r.Plugin.Session;
import rien.bijl.Scoreboard.r.Plugin.Utility.i18n;

public class JoinListener implements Listener {


    @EventHandler
    public void onJoin(PlayerJoinEvent event)
    {
        if (event.getPlayer().isOp() && Session.getSession().alertOutOfDate) {
            event.getPlayer().sendMessage(i18n.get("misc.outofdate"));
        }

        if (event.getPlayer().isOp() && Session.getSession().alertBrokenConfig) {
            event.getPlayer().sendMessage(i18n.get("misc.configUpdated"));
        }

        BoardPlayer.getBoardPlayer(event.getPlayer()).attachConfigBoard(Session.getSession().defaultBoard);
    }

}
