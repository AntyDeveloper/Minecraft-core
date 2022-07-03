package pl.minecash.minecash.commands;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;


public class ReloadCommand implements CommandExecutor {
    private final Main main;

    public ReloadCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player player = (Player) sender;
        if(player.hasPermission("staff.cmd") || player.isOp()) {
            if(args.length == 0) {
            if(args[0].equalsIgnoreCase("config")) {
                Main.plugin.reloadConfig();
                player.sendMessage("Przeladowoano config");
            }
            else {
                player.sendMessage("§8» §7Poprawne uzycie: /core config");
            }
            }
        }
        return false;
    }
}
