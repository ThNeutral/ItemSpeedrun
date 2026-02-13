package Roll;

import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import java.util.Random;

public class Roll implements CommandExecutor {
    private static Material[] materials = Material.values(); 
    private Random random = new Random();

    public Material getRandomMaterial() {
        Material randomMaterial = materials[random.nextInt(materials.length)];
        return randomMaterial;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        Material randomMaterial = getRandomMaterial();
        sender.sendMessage("§6Random Item: §e" + randomMaterial.name());
        return true;
    }
}