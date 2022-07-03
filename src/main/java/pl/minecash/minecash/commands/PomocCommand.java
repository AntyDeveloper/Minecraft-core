package pl.minecash.minecash.commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import pl.minecash.minecash.Main;

public class PomocCommand implements CommandExecutor {
    private final Main main;

    public PomocCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if(sender instanceof Player){
            Player player = (Player) sender;
                String kolor = "§8» §9";
                if (player.hasPermission("gracz.cmd")) {
                    player.sendMessage("§7------------------------------------------------");
                    player.sendMessage(kolor + "/kit §7- §fdostepne zestawy.");
                    player.sendMessage(kolor + "/rangi §7- §fdostepne rangi na serwerze.");
                    player.sendMessage(kolor + "/drop §7- §fdrop ze stone" );
                    player.sendMessage("§7------------------------------------------------");
                    return true;
                } else {
                    player.sendMessage("§8» §cNie masz permisji");
                    return true;
                }
        } else {
            main.getLogger().info(ChatColor.RED + "§8» §cMusisz byc na serwerze ;)");
        }

        return true;
    }
}
