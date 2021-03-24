package rien.bijl.Scoreboard.r.Board.Implementations;

import org.bukkit.entity.Player;

public interface IBoard {

    /**
     * Set the player this board should publish to
     * @param player The player this scoreboard is assigned to
     */
    void setPlayer(Player player);

    /**
     *
     * @param title What should the title display
     */
    void setTitle(String title);
    void setLine(int line, String content);
    void setLineCount(int lines);
    Player getPlayer();

}
