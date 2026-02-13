package listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import players.IPlayerList;

import java.util.logging.Logger;

public class PlayerEventListener implements Listener {
    private final IPlayerList playerList;

    public PlayerEventListener(IPlayerList playerList) {
        this.playerList = playerList;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        playerList.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        playerList.removePlayer(event.getPlayer());
    }
}
