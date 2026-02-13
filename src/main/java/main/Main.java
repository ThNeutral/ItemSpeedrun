package main;

import game.Game;
import setupWorld.Setup;
import start.Start;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import org.bukkit.entity.Player;
import winCond.WinConditionChecker;

public class Main extends JavaPlugin {
    private Set<UUID> readyPlayers = new HashSet<>();
    private boolean gameStarted = false;
    private static Main instance;

    @Override
    public void onEnable() {
        var setup = new Setup(this);
        var start = new Start(this);
        var game = new Game();
        var winChecker = new WinConditionChecker(this, game);

        getServer().getPluginManager().registerEvents(setup, this);
        getServer().getPluginManager().registerEvents(winChecker, this);
        this.getCommand("play").setExecutor(setup);
        this.getCommand("ready").setExecutor(start);

        instance = this;
    }

    public static Main getInstance() {
        return instance;
    }

    public void resetGame() {
        readyPlayers.clear();
        gameStarted = false;
    }
    public void setPlayerReady(Player player) {
        readyPlayers.add(player.getUniqueId());
    }

    public boolean isPlayerReady(Player player) {
        return readyPlayers.contains(player.getUniqueId());
    }

    public int getReadyCount() {
        return readyPlayers.size();
    }

    public boolean isGameStarted() {
        return gameStarted;
    }

    public void setGameStarted(boolean started) {
        this.gameStarted = started;
    }

    @Override
    public void onDisable() {
    }
}
