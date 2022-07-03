package pl.minecash.minecash.commands;

import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class SetSpawnCommand implements CommandExecutor {
    private final Main main;

    public SetSpawnCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("staff.cmd")) {
            World world = player.getWorld();
            Main.plugin.getConfig().set("Spawn.World", player.getWorld().getName());
            Main.plugin.getConfig().set("Spawn.X", player.getLocation().getX());
            Main.plugin.getConfig().set("Spawn.Y", player.getLocation().getY() + 1);
            Main.plugin.getConfig().set("Spawn.Z", player.getLocation().getZ());
            Main.plugin.getConfig().set("Spawn.Yaw", player.getLocation().getYaw());
            Main.plugin.getConfig().set("Spawn.Pitch", player.getLocation().getPitch());
            Main.plugin.saveConfig();
            player.sendMessage("§8» §aSpawn zostal ustawiony.");

        }
        return false;
    }
}
