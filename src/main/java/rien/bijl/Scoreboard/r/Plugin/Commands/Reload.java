package rien.bijl.Scoreboard.r.Plugin.Commands;

import org.bukkit.entity.Player;
import rien.bijl.Scoreboard.r.Board.BoardPlayer;
import rien.bijl.Scoreboard.r.Board.ConfigBoard;
import rien.bijl.Scoreboard.r.Plugin.ConfigControl;
import rien.bijl.Scoreboard.r.Plugin.Session;
import rien.bijl.Scoreboard.r.Plugin.Utility.i18n;
import rien.bijl.Scoreboard.r.Plugin.WorldManager;

import java.util.Collection;

public class Reload {

    public Reload(Player player)
    {
        if (!player.hasPermission("scoreboard.reload")) {
            player.sendMessage(i18n.get("commands.lackspermission"));
            return;
        }

        Session.getSession().defaultBoard.cancel();
        Session.getSession().worldManager.cancel();

        ConfigControl.get().reloadConfigs();
        Collection<BoardPlayer> boardPlayers = BoardPlayer.allBoardPlayers();
        ConfigBoard newBoard = new ConfigBoard("board");

        newBoard.enable();
        newBoard.runTaskTimerAsynchronously(Session.getSession().plugin, 1L, 1L);

        Session.getSession().worldManager = new WorldManager();
        Session.getSession().worldManager.runTaskTimer(Session.getSession().plugin, 1L, 20L);

        Session.getSession().defaultBoard = newBoard;

        for (BoardPlayer bp: boardPlayers) {
            bp.attachConfigBoard(newBoard);
        }

        player.sendMessage(i18n.get("commands.reload"));
    }

}
