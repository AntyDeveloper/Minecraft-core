package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class DayCommand implements CommandExecutor {
    private final Main main;

    public DayCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            int value = 0;
            if (player.hasPermission("adm.cmd")) {
                for (World world : Bukkit.getWorlds()) {
                    world.setFullTime(value);
                }
                player.sendMessage("§8» §7Pomyslnie ustawiono dzien!");
            } else {
                player.sendMessage("§8» §cNie masz permisji!");
            }
        }
        return false;
    }
}