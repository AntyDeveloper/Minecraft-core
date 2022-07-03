package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class FlyCommand implements CommandExecutor {
    private final Main main;

    public FlyCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("adm.cmd")) {
                if (args.length == 0) {
                    if (player.getAllowFlight() == false) {
                        player.sendMessage("§8» §cFly zostal wlaczony§7!");
                        player.setAllowFlight(true);
                    } else if (player.getAllowFlight() == true) {
                        player.sendMessage("§8» §cFly zostal wylaczony§7!");
                        player.setAllowFlight(false);
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                    if (player != target) {
                        if (target.isOnline()) {
                            if (target.getAllowFlight() == false) {
                                player.sendMessage("§8» §7Wlaczyles fly dla §e" + target.getName() + "§.");
                                target.setAllowFlight(true);
                            } else if (player.getAllowFlight() == true) {
                                player.sendMessage("§8» §7Wlaczyles fly dla §e" + target.getName() + "§7.");
                                target.sendMessage("§8» §7Wylaczono ci fly!");
                                target.setAllowFlight(false);
                            }
                        } else {
                            player.sendMessage("§8» §cGracz jest offline§7.");
                        }
                    } else {
                        player.sendMessage("§8» §cUzyj po prostu komendy fly ;0");
                    }
                }
            } else {
                player.sendMessage("§8» §cNie masz permisji!");
            }
        }
        return true;
    }

}