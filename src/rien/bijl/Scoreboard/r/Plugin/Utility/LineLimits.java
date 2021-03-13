package rien.bijl.Scoreboard.r.Plugin.Utility;

import rien.bijl.Scoreboard.r.Plugin.Session;

public class LineLimits {

    private static int lineLimit = 0;

    public static int getLineLimit()
    {
        if (lineLimit != 0) {
            return lineLimit;
        }

        int minor = ServerVersion.minor();

        if (minor >= 13) {
            lineLimit = 64;
        } else {
            lineLimit = 16;
        }

        return lineLimit;
    }

}
