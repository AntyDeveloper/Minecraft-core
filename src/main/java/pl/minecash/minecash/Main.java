package pl.minecash.minecash;

import dev.jcsoftware.jscoreboards.JPerPlayerScoreboard;
import me.clip.placeholderapi.PlaceholderAPI;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;
import pl.minecash.minecash.commands.*;
import pl.minecash.minecash.events.*;


import javax.imageio.IIOException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.*;
import java.util.Arrays;
import java.util.UUID;

public final class Main extends JavaPlugin implements Listener {
    public static Main plugin;
    private static Main instance;

    private JPerPlayerScoreboard scoreboard = new JPerPlayerScoreboard(
            (Player player) -> {
                return "§dMine&bCash§7.§fonline";
            },
            (Player player) -> {
                String format = PlaceholderAPI.setPlaceholders(player, "%luckperms_prefix%");
                int gc = getCoins(player.getUniqueId());
                return Arrays.asList(
                        "§7=-=-=-=-=-=-=-=-=-=",
                        "§7Ranga: " + format +"",
                        "§7Twoje wpln  ",
                        " ",
                        "§7Dc: §9dc.minecash.online",
                        "§7=-=-=-=-=-=-=-=-=-="
                );
            }
    );
    private Connection connection;
    public String host, database,username,password, table;
    public int port;
    @Override
    public void onEnable() {
        mysqlSetup();


        plugin = this;

        createFiles();

        if (getServer().getPluginManager().getPlugin("PlaceholderAPI") != null) {
        } else {
            getServer().getPluginManager().disablePlugin(this);
        }

        if(!Bukkit.getOnlinePlayers().isEmpty())
            for(Player online : Bukkit.getOnlinePlayers())
                scoreboard.addPlayer(online);
                scoreboard.updateScoreboard();


        instance = this;
        // Plugin startup logic
        this.getServer().getPluginManager().registerEvents(this, this);

        getServer().getConsoleSender().sendMessage(ChatColor.GREEN + "MineCashCore zostal zaladowany!");
        getServer().getPluginCommand("pomoc").setExecutor(new PomocCommand(this));
        getServer().getPluginCommand("bc").setExecutor(new BCCommand(this));
        getServer().getPluginCommand("chat").setExecutor(new ChatCommand(this));
        getServer().getPluginCommand("msg").setExecutor(new MsgCommand(this));
        getServer().getPluginCommand("heal").setExecutor(new HealCommand(this));
        getServer().getPluginCommand("gm").setExecutor(new GameModeCommand(this));
        getServer().getPluginCommand("setspawn").setExecutor(new SetSpawnCommand(this));
        getServer().getPluginCommand("spawn").setExecutor(new SpawnCommand(this));
        getServer().getPluginCommand("kill").setExecutor(new KillCommand(     this));
        getServer().getPluginCommand("feed").setExecutor(new FeedCommand(this));
        getServer().getPluginCommand("fly").setExecutor(new FlyCommand(this));
        getServer().getPluginCommand("sun").setExecutor(new SunCommand(this));
        getServer().getPluginCommand("core").setExecutor(new ReloadCommand(this));
        getServer().getPluginCommand("day").setExecutor(new DayCommand(this));
        getServer().getPluginCommand("night").setExecutor(new NightCommand(this));
        getServer().getPluginManager().registerEvents(new ChatEvent(), this);
        getServer().getPluginManager().registerEvents(new FormatEvent(), this);
        getServer().getPluginManager().registerEvents(new JoinEvent(), this);
        getServer().getPluginManager().registerEvents(new LeaveEvent(), this);
        getServer().getPluginManager().registerEvents(new ItemEnchantDisable(), this);
        getServer().getPluginManager().registerEvents(new DropEvent(), this);
        getServer().getPluginManager().registerEvents(new BlockEvent(), this);
        getServer().getPluginManager().registerEvents(new MotddEvent(), this);

    }

public void mysqlSetup() {
        host = "localhost";
                port = 3306;
        database = "mc";
        username = "root";
        password = "9g*vnh(7WZ[+&tzL";
        table = "cash-users";
        try {
            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }
            }
            Class.forName("com.mysql.jdbc.Driver");
            setConnection(DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database, this.username, this.password));
            getServer().getConsoleSender().sendMessage("Polaczono z baza danych.");
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

}

    public static Main getInstance() {return instance;}


    private File configf;
    private FileConfiguration config = getConfig();

    private void createFiles() {
        configf = new File(getDataFolder(), "config.yml");

        if(!configf.exists() ) {
            configf.getParentFile().mkdirs();
            saveResource("config.yml", false);
        }
        config = new YamlConfiguration();

        try {
            config.load(configf);

        } catch (IIOException | InvalidConfigurationException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
@EventHandler
public void onJoin(PlayerJoinEvent event) {
    scoreboard.addPlayer(event.getPlayer());
    scoreboard.updateScoreboard();

}
    public void addToScoreboard(Player player) {
        scoreboard.addPlayer(player);
        scoreboard.updateScoreboard();
    }

    public int getCoins(UUID uuid) {
        try {
            PreparedStatement statement = plugin.getConnection()
                    .prepareStatement("SELECT hasCash FROM cash-users WHERE uuid=?");
            statement.setString(2, uuid.toString() );
            ResultSet results = statement.executeQuery();
            int hasCash = 0;
            while(results.next()) {
                Bukkit.broadcastMessage(results.getString("hasCash"));
            }
            if (results.next()) {
                hasCash = results.getInt("hasCash");
                return hasCash;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return 0;
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        Main.plugin.saveConfig();
        getServer().getConsoleSender().sendMessage("Zapisano config...");
        Bukkit.getServer().dispatchCommand(Bukkit.getConsoleSender(), "save-all");
    }


    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
