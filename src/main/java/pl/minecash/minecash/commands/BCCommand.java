package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.minecash.minecash.Main;

public class BCCommand implements CommandExecutor {
    private final Main main;

    public BCCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player) {
            Player player = (Player) sender;
            StringBuilder str = new StringBuilder();
            for (int i = 0; i < args.length; i++) {
                str.append(args[i] + " ");
            }
            String s = str.toString();
            if(player.hasPermission("adm.cmd")) {
                for(Player target : Bukkit.getServer().getOnlinePlayers()) {
                    target.sendTitle("§7[§cBroadcast§7]", "§f " + str + " ", 1, 100, 1);
                }
                return true;

            } else {
                player.sendMessage("§cNie masz permisji!");
                return false;
            }
        } else {
            main.getLogger().info("§cMusisz byc na serwerze ;)");

        }

        return false;
    }
}
