package org.pixeltime.enchantmentsenhance.mysql;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.sql.*;
import java.util.UUID;

public class MysqlMain implements Listener {
    public String host, database, username, password, table;
    public int port;
    private Connection connection;

    public MysqlMain() {
        this.setUp();
    }

    public void setUp() {
        host = SettingsManager.config.getString("mysql.host");
        port = SettingsManager.config.getInt("mysql.port");
        database = SettingsManager.config.getString("mysql.database");
        username = SettingsManager.config.getString("mysql.user");
        password = SettingsManager.config.getString("mysql.password");
        table = SettingsManager.config.getString("mysql.table");

        try {

            synchronized (this) {
                if (getConnection() != null && !getConnection().isClosed()) {
                    return;
                }

                Class.forName("com.mysql.jdbc.Driver");
                setConnection(
                        DriverManager.getConnection("jdbc:mysql://" + this.host + ":" + this.port + "/" + this.database,
                                this.username, this.password));

                Bukkit.getConsoleSender().sendMessage("[EnchantmentsEnhance] " + ChatColor.GREEN + "MYSQL CONNECTED");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    public void createTable() {
        try {

            Statement statement = connection.createStatement();

            statement.executeUpdate("CREATE TABLE IF NOT EXISTS this.table ( "
                    + "id integer PRIMARY KEY AUTO_INCREMENT, "
                    + "player varchar(16) NOT NULL, "
                    + "failstack integer NOT NULL, "
                    + "deaths integer NOT NULL, "
                    + "mobkills integer NOT NULL, "
                    + "killstreak integer NOT NULL, "
                    + "last_online timestamp NOT NULL )");

            ResultSet res = statement.executeQuery("");
            res.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean playerExists(UUID uuid) {
        try {
            PreparedStatement statement = this.getConnection()
                    .prepareStatement("SELECT * FROM " + this.table + " WHERE player=?");
            statement.setString(1, "healpot");

            ResultSet results = statement.executeQuery();
            if (results.next()) {
                Main.getMain().getServer().broadcastMessage(net.md_5.bungee.api.ChatColor.YELLOW + "Player Found");
                return true;
            }
            Main.getMain().getServer().broadcastMessage(net.md_5.bungee.api.ChatColor.RED + "Player NOT Found");

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void createPlayer(final UUID uuid, Player player) {
        try {
            PreparedStatement statement = this.getConnection()
                    .prepareStatement("SELECT * FROM " + this.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();
            System.out.print(1);
            if (playerExists(uuid) != true) {
                PreparedStatement insert = this.getConnection()
                        .prepareStatement("INSERT INTO " + this.table + " (UUID,NAME,COINS) VALUES (?,?,?)");
                insert.setString(1, uuid.toString());
                insert.setString(2, player.getName());
                insert.setInt(3, 500);
                insert.executeUpdate();

                Main.getMain().getServer().broadcastMessage(net.md_5.bungee.api.ChatColor.GREEN + "Player Inserted");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void updateCoins(UUID uuid) {
        try {
            PreparedStatement statement = this.getConnection()
                    .prepareStatement("UPDATE " + this.table + " SET COINS=? WHERE UUID=?");
            statement.setInt(1, 1000);
            statement.setString(2, uuid.toString());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void getCoins(UUID uuid) {
        try {
            PreparedStatement statement = this.getConnection()
                    .prepareStatement("SELECT * FROM " + this.table + " WHERE UUID=?");
            statement.setString(1, uuid.toString());
            ResultSet results = statement.executeQuery();
            results.next();

            System.out.print(results.getInt("COINS"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}