package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class SunCommand implements CommandExecutor {
    private final Main main;

    public SunCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        World world = Bukkit.getWorlds().get(0);
        if(sender instanceof Player) {
            Player player = (Player) sender;
            if(player.hasPermission("adm.cmd")) {
                world.setStorm(false);
                world.setThundering(false);
                player.sendMessage("§8» §7Pogoda zostala zmieniona!");
            } else {
                player.sendMessage("§8» §cNie masz permisji!");
            }
        }
        return false;
    }
}
