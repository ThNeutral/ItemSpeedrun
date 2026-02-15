package listeners;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerRespawnEvent;
import state.players.IInventoryManager;
import state.players.IPlayerList;

public class PlayerEventListener implements Listener {
    private final IPlayerList _playerList;
    private final IInventoryManager _inventoryManager;

    public PlayerEventListener(IPlayerList playerList, IInventoryManager inventoryManager) {
        this._playerList = playerList;
        this._inventoryManager = inventoryManager;
    }

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        _playerList.addPlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerQuit(PlayerQuitEvent event) {
        _playerList.removePlayer(event.getPlayer());
    }

    @EventHandler
    public void onPlayerRespawn(PlayerRespawnEvent event)
    {
        _inventoryManager.setDefaultInventory(event.getPlayer());
    }
}
