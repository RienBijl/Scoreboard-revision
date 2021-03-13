package rien.bijl.Scoreboard.r;

import org.bukkit.plugin.java.JavaPlugin;

public class Main extends JavaPlugin {

    public void onEnable()
    {
        new ScoreboardRevision(this, "board")
            .enable();
    }


}
