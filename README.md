![Scoreboard](https://i.imgur.com/J5EnzvF.png)

[![Release](https://github.com/RienBijl/Scoreboard-revision/actions/workflows/stable.yml/badge.svg)](https://github.com/RienBijl/Scoreboard-revision/actions/workflows/stable.yml) [![Dev source](https://github.com/RienBijl/Scoreboard-revision/actions/workflows/maven.yml/badge.svg)](https://github.com/RienBijl/Scoreboard-revision/actions/workflows/maven.yml)

## Animated, flickerless Scoreboard

This is the official repo for Scoreboard-revision! Don't know what this is? [Check out our Spigot page!](https://www.spigotmc.org/resources/scoreboard.14754/)  Please adhere to the following commit scheme to keep things organized.

- **`feat(FEATURE): MESSAGE (#issue-id)`**<br/>
  Example: *feat(flicker): Flickering is now gone. #3*<br/>
  For work on particular features. The issue id part is optional, but recommended. Our
  planboard is linked to issues and commits are linked to issues.

- **`fix(FEATURE): MESSAGE (#issue-id)`**<br/>
  Example: *fix(config): Config no longer randomly cleared. #2*<br/>
  If you fixed something in our codebase, use this format. The issue id part is
  also optional, but recommented. Problems in our code should all have issues linked
  to them.

- **`test(FEATURE): MESSAGE (#issue-id)`**<br/>
  Example: *test(flicker): Tested flickering. #2*<br/>
  If you wrote tests or updated a test, use this format. Tests should always have
  an issue linked to them.

- **`chore: MESSAGE`**<br/>
  Example: *chore: updated dependencies*<br/>
  For work like updating composer packages or cleaning up code, this format is used. If
  you're not sure if your commit should have this format

- **`dev: MESSAGE`**<br/>
  Example: *dev: added utility class*<br/>
  For work that improves our development, this format can be used. It shouldn't be 
  used that often though.
