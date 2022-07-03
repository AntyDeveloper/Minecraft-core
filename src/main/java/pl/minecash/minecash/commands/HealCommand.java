package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class HealCommand implements CommandExecutor {

    private final Main main;

    public HealCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("adm.cmd")) {
                if (args.length == 0) {
                    player.setHealth(20);
                      player.sendMessage("§8» §aZostales uleczony§7!");
                } else if(args[0].equalsIgnoreCase("*")) {
                    for(Player targett : Bukkit.getServer().getOnlinePlayers()) {
                        targett.setHealth(20);
                        targett.setFoodLevel(20);
                        targett.sendTitle("§7[§cBroadcast§7]", "§a§lCaly serwer zostal uleczony§7! §c<3");
                    }
                }
                else {
                    Player target = Bukkit.getServer().getPlayer(args[0]);
                        player.sendMessage("§8» §a§lUleczono gracza §e§l" + target.getName() + "§7.");
                        try {
                            target.setHealth(20);
                            target.sendMessage("§8» §l§aZostales uleczony!");
                        } catch (NullPointerException e) {
                            player.sendMessage("§8» §cNie znaleziono gracza.");
                        }
                }
                } else {
                player.sendMessage("§8» §cNie masz permisji!");
            }
            }
        return true;
    }

}
