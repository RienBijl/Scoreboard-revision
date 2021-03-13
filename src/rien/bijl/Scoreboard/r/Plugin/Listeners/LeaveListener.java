package rien.bijl.Scoreboard.r.Plugin.Listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;
import rien.bijl.Scoreboard.r.Board.BoardPlayer;

public class LeaveListener implements Listener {

    @EventHandler
    public void onLeave(PlayerQuitEvent event)
    {
        BoardPlayer.getBoardPlayer(event.getPlayer()).kill();
    }

}
