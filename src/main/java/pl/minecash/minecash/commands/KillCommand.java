package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class KillCommand implements CommandExecutor {
    private final Main main;

    public KillCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("adm.cmd")) {
                if (args.length == 0) {
                    player.setHealth(0);
                    player.sendMessage("§8» §cZabiles sie§7!");
                }
                else {

                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if(target == null) {
                        player.sendMessage("§8» §cNie znaleziono gracza.");
                    } else {

                        target.setHealth(0);
                        player.sendMessage("§8» §cZabito gracza §e" + target.getName() + "§7.");
                        target.sendMessage("§8» §cZabito cie komenda!");
                    }
                }
            } else {
                player.sendMessage("§8» §cNie masz permisji!");
            }
        }
        return true;
    }

}