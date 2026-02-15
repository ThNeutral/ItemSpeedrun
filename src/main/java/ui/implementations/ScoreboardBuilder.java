package ui.implementations;

import org.bukkit.Bukkit;
import org.bukkit.scoreboard.DisplaySlot;
import org.bukkit.scoreboard.Objective;
import org.bukkit.scoreboard.Scoreboard;

class ScoreboardBuilder {
    private Scoreboard _scoreboard;
    private Objective _objective;

    private int _size;

    public ScoreboardBuilder(
            int size,
            String displayName,
            DisplaySlot displaySlot
    ) {
        _size = size;

        _scoreboard = Bukkit.getScoreboardManager().getNewScoreboard();
        _objective = _scoreboard.registerNewObjective("test", "dummy");

        _objective.setDisplayName(displayName);
        _objective.setDisplaySlot(displaySlot);
    }

    public ScoreboardBuilder addLine() {
        return addLine(" ");
    }
    public ScoreboardBuilder addLine(String line) {
        var score = _objective.getScore(line);
        score.setScore(_size--);

        return this;
    }

    public ScoreboardBuilder addTeam(String name, String entry, String suffix) {
        var team = _scoreboard.registerNewTeam(name);
        team.addEntry(entry);
        team.setSuffix(suffix);

        var score = _objective.getScore(entry);
        score.setScore(_size--);

        return this;
    }

    public Scoreboard build() {
        return _scoreboard;
    }
}
