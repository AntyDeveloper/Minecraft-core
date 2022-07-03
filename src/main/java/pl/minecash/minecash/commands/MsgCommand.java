package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class MsgCommand implements CommandExecutor {
    private final Main main;

    public MsgCommand(Main main) {
        this.main = main;
    }
    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        Player target = Bukkit.getServer().getPlayer(args[0]);
        Player player = (Player) sender;
        if(target == null) {
            player.sendMessage("§8» §cNie znalezionio gracza");
        } else {
            if (args.length > 0) {
                if (target.isOnline()) {
                    if (player != target) {
                        if (target == null) {

                            StringBuilder str = new StringBuilder();
                            for (int i = 1; i < args.length; i++) {
                                str.append(args[i] + " ");
                            }
                            try {
                                target.sendMessage("§7[§c" + player.getName() + "§7] §8» §7[§6Ja§7]§f " + str + " ");
                                player.sendMessage("§7[§6Ja§7] §8» §7[§c" + player.getName() + "§7]§f " + str + "");
                            } catch (NullPointerException e) {
                                player.sendMessage("§8» §cNie znaleziono gracza.");
                            }
                        } else {
                            player.sendMessage("§8» §7Nie mozesz wyslac wiadomosci do samego siebie.");
                        }
                    } else {
                        player.sendMessage("§8» §7Nie mozesz wyslac wiadomosci do samego siebie.");
                    }
                } else {
                    player.sendMessage("§8» §cGracz jest offline.");
                }
            } else {
                player.sendMessage("§8» §7Uzycie: §f/msg [nick] [wiadomosc]");
            }
        }
        return false;
    }
}
