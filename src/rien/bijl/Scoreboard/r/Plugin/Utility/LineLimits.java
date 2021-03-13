package rien.bijl.Scoreboard.r.Plugin.Utility;

import rien.bijl.Scoreboard.r.Plugin.Session;

public class LineLimits {

    private static int lineLimit = 0;

    public static int getLineLimit()
    {
        if (lineLimit != 0) {
            return lineLimit;
        }

        String pack = Session.getSession().plugin.getServer().getClass().getPackage().getName();
        String[] version = pack.substring(pack.lastIndexOf('.') + 1).substring(1).split("_");

        int minor = Integer.parseInt(version[1]);

        if (minor >= 13) {
            lineLimit = 64;
        } else {
            lineLimit = 16;
        }

        return lineLimit;
    }

}
