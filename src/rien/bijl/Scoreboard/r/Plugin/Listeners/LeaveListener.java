package rien.bijl.Scoreboard.r.Plugin.Listeners;

import com.sun.istack.internal.NotNull;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import rien.bijl.Scoreboard.r.Board.BoardPlayer;
import rien.bijl.Scoreboard.r.Board.ConfigBoard;
import rien.bijl.Scoreboard.r.Plugin.Session;

import java.util.Objects;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event)
    {
        BoardPlayer.getBoardPlayer(event.getPlayer()).kill();
    }

}
