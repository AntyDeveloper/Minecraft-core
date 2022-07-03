package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class FeedCommand implements CommandExecutor {
    private final Main main;

    public FeedCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("adm.cmd")) {
                if (args.length == 0) {
                    player.setFoodLevel(20);
                    player.sendMessage("§8» §aPrzywrocono glod§7!");
                }
                else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (target.isOnline()) {
                        target.setFoodLevel(20);
                        player.sendMessage("§8» §aPrzywrocono glod graczu §e" + target.getName() + "§7.");
                        target.sendMessage("§8» §aPrzywrocono ci caly glod!");
                    } else {
                        player.sendMessage("§8» §cGracz jest offline.");
                    }
                }
            } else {
                player.sendMessage("§8» §cNie masz permisji!");
            }
        }
        return true;
    }

}

