package rien.bijl.Scoreboard.r.Board.Implementations;

import org.bukkit.entity.Player;

public class WrapperBoard implements IBoard {

    private static final String DEFAULT = "rien.bijl.Scoreboard.r.Board.Implementations.Drivers.V1.ScoreboardDriverV1";

    private static final String[][] DRIVERS = {
            {"SCOREBOARD_DRIVER_V1", "rien.bijl.Scoreboard.r.Board.Implementations.Drivers.V1.ScoreboardDriverV1"}
    };

    private IBoard child;

    /**
     * Load the WrapperBoard with driver specification
     * @param driver What driver should be loaded
     * @throws ClassNotFoundException This really can't be thrown, but Intelliji wants me to
     * @throws IllegalAccessException This can be thrown tho
     * @throws InstantiationException This really can't be thrown too
     */
    public WrapperBoard(String driver) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        for (String[] drivers : DRIVERS) {
            if (drivers[0].equals(driver)) {
                this.child = (IBoard) Class.forName(drivers[1]).newInstance();
            }
        }
        if (this.child == null) {
            this.child = (IBoard) Class.forName(DEFAULT).newInstance();
        }
    }

    @Override
    public void setPlayer(Player player) {
        child.setPlayer(player);
    }

    @Override
    public void setTitle(String title) {
        child.setTitle(title);
    }

    @Override
    public void setLine(int line, String content) {
        child.setLine(line, content);
    }

    @Override
    public void setLineCount(int lines) {
        child.setLineCount(lines);
    }

    @Override
    public Player getPlayer() {
        return child.getPlayer();
    }

}
