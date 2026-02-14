package constants;

import org.bukkit.Material;
import state.game.ChallengeDifficulty;

import java.util.List;
import java.util.Map;

public class Items {
    private Map<ChallengeDifficulty, List<Material>> itemsByDifficulty;

    public Items() {
        itemsByDifficulty = Map.of(
                ChallengeDifficulty.Fast, List.of(Material.WOODEN_SWORD, Material.WOODEN_AXE, Material.WOODEN_PICKAXE),
                ChallengeDifficulty.Medium, List.of(Material.STONE_SWORD, Material.STONE_AXE, Material.STONE_PICKAXE),
                ChallengeDifficulty.Long, List.of(Material.IRON_SWORD, Material.IRON_AXE, Material.IRON_PICKAXE)
        );
    }

    public Material getRandomItem(ChallengeDifficulty difficulty) {
        List<Material> items = itemsByDifficulty.get(difficulty);
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("No items found for the given difficulty: " + difficulty);
        }
        int randomIndex = (int) (Math.random() * items.size());
        return items.get(randomIndex);
    }
}
