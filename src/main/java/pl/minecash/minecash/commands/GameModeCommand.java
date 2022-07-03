package pl.minecash.minecash.commands;

import org.bukkit.Bukkit;
import org.bukkit.GameMode;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;
import pl.minecash.minecash.Main;

public class GameModeCommand implements CommandExecutor {
    private final Main main;

    public GameModeCommand(Main main) {
        this.main = main;
    }

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if(sender instanceof Player) {

            Player player = (Player) sender;

            if (player.hasPermission("gm.cmd")) {
                if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("1")) {
                        player.setGameMode(GameMode.CREATIVE);
                        player.sendMessage("§8» §7Pomyslnie ustawiono tryb §9kreatywny§7.");
                    } else if (args[0].equalsIgnoreCase("0")) {
                        player.setGameMode(GameMode.SURVIVAL);
                        player.sendMessage("§8» §7Pomyslnie ustawiono tryb §9survival§7.");
                    } else if (args[0].equalsIgnoreCase("3")) {
                        player.setGameMode(GameMode.SPECTATOR);
                        player.sendMessage("§8» §7Pomyslnie ustawiono tryb §9spectator§7.");
                    } else if (args[0].equalsIgnoreCase("2")) {
                        player.setGameMode(GameMode.ADVENTURE);
                        player.sendMessage("§8» §7Pomyslnie ustawiono tryb §9przygodowy§7.");
                    }
                } else {
                    Player target = Bukkit.getServer().getPlayer(args[1]);
                    if (target.isOnline()) {
                        if (args[0].equalsIgnoreCase("1")) {
                            target.setGameMode(GameMode.CREATIVE);
                            player.sendMessage("§8» §7Pomyslnie ustawiono tryb §9kreatywny §7dla §e" + target.getName() + ".");
                        } else if (args[0].equalsIgnoreCase("0")) {
                            target.setGameMode(GameMode.SURVIVAL);
                            target.sendMessage("§8» §7Pomyslnie ustawiono tryb §9survival §7dla §7e" + target.getName() + ".");
                        } else if (args[0].equalsIgnoreCase("3")) {
                            target.setGameMode(GameMode.SPECTATOR);
                            target.sendMessage("§8» §7Pomyslnie ustawiono tryb §9spectator §7dla §7e" + target.getName() + ".");
                        } else if (args[0].equalsIgnoreCase("2")) {
                            target.setGameMode(GameMode.ADVENTURE);
                            target.sendMessage("§8» §7Pomyslnie ustawiono tryb §9przygodowy §7dla §7e" + target.getName() + ".");
                        }
                    } else {
                        player.sendMessage("§8» §cGracz jest offline.");
                    }
                }
            } else {
                player.sendMessage("§8» §cNie masz permisji!");
            }
            } else {
            main.getLogger().info("Musisz byc na serwerze");
        }

        return true;
    }
}
