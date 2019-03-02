/*
 *     Copyright (C) 2017-Present 25
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 *
 */

package org.pixeltime.enchantmentsenhance.mysql;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.scheduler.BukkitRunnable;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.MaterialManager;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;

public class DataStorage {

    private static DataStorage instance;

    public static DataStorage get() {
        if (DataStorage.instance == null) {
            DataStorage.instance = new DataStorage();
        }
        return DataStorage.instance;
    }

    public void saveStats(final PlayerStat pData) {
        boolean sqlEnabled = Main.getMain().getConfig().getBoolean("mysql.enabled");
        if (!sqlEnabled) {
            try {
                File dataDirectory = Main.getMain().getDataFolder();
                File playerDataDirectory = new File(dataDirectory, "player_data");

                if (!playerDataDirectory.exists() && !playerDataDirectory.mkdirs()) {
                    return;
                }

                File playerFile = new File(playerDataDirectory, pData.getPlayername() + ".yml");
                if (!playerFile.exists()) {
                    Main.getMain().getLogger().info("File doesn't exist!");
                    return;
                }
                copyDefaults(playerFile);
                FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
                // Query
                fc.set("failstack", pData.getFailstack());
                fc.set("items", Arrays.toString(pData.getItems()));
                fc.set("valks", pData.getValks().toString());
                fc.set("grind", pData.getGrind());
                fc.save(playerFile);

            } catch (IOException ioException) {
                System.out.println("Failed to load faction " + pData.getPlayername() + ": " + ioException.getMessage());
            }
        } else {
            Database database = Main.getDatabase();

            if (!database.checkConnection()) {
                return;
            }

            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("UPDATE `enchantmentsenhance` SET ");
                queryBuilder.append("`failstack` = ?, `items` = ?, `valks` = ?, `grind` = ?");
                queryBuilder.append("WHERE `playername` = ?;");

                preparedStatement = connection.prepareStatement(queryBuilder.toString());

                // Query
                preparedStatement.setInt(1, pData.getFailstack());
                preparedStatement.setString(2, Arrays.toString(pData.getItems()));
                preparedStatement.setString(3, pData.getValks().toString());
                preparedStatement.setInt(4, pData.getGrind());
                preparedStatement.setString(5, pData.getPlayername());

                preparedStatement.executeUpdate();

            } catch (final SQLException sqlException) {
                sqlException.printStackTrace();

            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (final SQLException ignored) {
                    }
                }
            }
        }
    }


    public void loadStats(final PlayerStat pData) {
        new BukkitRunnable() {
            @Override
            public void run() {
                boolean sqlEnabled = Main.getMain().getConfig().getBoolean("mysql.enabled");
                if (sqlEnabled) {
                    Database database = Main.getDatabase();

                    if (!database.checkConnection()) {
                        return;
                    }

                    if (!database.doesPlayerExist(pData.getPlayername())) {
                        database.createNewPlayer(pData.getPlayername());
                        pData.setFailstack(0);
                        pData.setItems(new int[MaterialManager.stoneTypes.size()]);
                        pData.setValks(new ArrayList<>());
                        pData.setGrind(2);
                    } else {
                        Connection connection = database.getConnection();
                        PreparedStatement preparedStatement = null;
                        ResultSet resultSet = null;

                        try {
                            StringBuilder queryBuilder = new StringBuilder();
                            // Query
                            queryBuilder.append("SELECT `failstack`, `items`, `valks`, `grind` ");
                            queryBuilder.append("FROM `enchantmentsenhance` ");
                            queryBuilder.append("WHERE `playername` = ? ");
                            queryBuilder.append("LIMIT 1;");

                            preparedStatement = connection.prepareStatement(queryBuilder.toString());
                            preparedStatement.setString(1, pData.getPlayername());
                            resultSet = preparedStatement.executeQuery();

                            if (resultSet != null && resultSet.next()) {
                                pData.setFailstack(resultSet.getInt("failstack"));

                                int[] items = Arrays.copyOf(Arrays.stream(resultSet.getString("items").replace("[", "").replace("]", "").split(", ")).mapToInt(Integer::parseInt).toArray(), MaterialManager.stoneTypes.size());
                                ArrayList<Integer> valks = new ArrayList<>();
                                for (String s : resultSet.getString("valks").replace("[", "").replace("]", "").split(", ")) {
                                    if (!s.isEmpty()) {
                                        valks.add(Integer.parseInt(s));
                                    }
                                }
                                pData.setItems(items);
                                pData.setValks(valks);
                                pData.setGrind(resultSet.getInt("grind"));
                            }

                        } catch (final SQLException sqlException) {
                            sqlException.printStackTrace();

                        } finally {
                            if (resultSet != null) {
                                try {
                                    resultSet.close();
                                } catch (final SQLException ignored) {
                                }
                            }

                            if (preparedStatement != null) {
                                try {
                                    preparedStatement.close();
                                } catch (final SQLException ignored) {
                                }
                            }
                        }
                    }
                } else {
                    try {
                        File dataDirectory = Main.getMain().getDataFolder();
                        File playerDataDirectory = new File(dataDirectory, "player_data");

                        if (!playerDataDirectory.exists() && !playerDataDirectory.mkdirs()) {
                        }

                        File playerFile = new File(playerDataDirectory, pData.getPlayername() + ".yml");

                        if (!playerFile.exists() && !playerFile.createNewFile()) {
                            Main.getMain().getLogger().info("Something strange is happening while saving!");
                        }
                        copyDefaults(playerFile);
                        FileConfiguration fc = YamlConfiguration.loadConfiguration(playerFile);
                        pData.setFailstack(fc.getInt("failstack"));
                        int[] items = Arrays.copyOf(Arrays.stream(fc.getString("items").replace("[", "").replace("]", "").split(", ")).mapToInt(Integer::parseInt).toArray(), MaterialManager.stoneTypes.size());
                        ArrayList<Integer> valks = new ArrayList<>();
                        for (String s : fc.getString("valks").replace("[", "").replace("]", "").split(", ")) {
                            if (!s.isEmpty()) {
                                valks.add(Integer.parseInt(s));
                            }
                        }
                        pData.setItems(items);
                        pData.setValks(valks);
                        pData.setGrind(fc.getInt("grind"));

                    } catch (IOException ioException) {
                        System.out.println("Failed to load player " + pData.getPlayername() + ": " + ioException.getMessage());
                    }
                }
            }
        }.runTaskAsynchronously(Main.getMain());
    }

    public void removePlayerData(String playername) {
        boolean sqlEnabled = Main.getMain().getConfig().getBoolean("mysql.enabled");
        if (!sqlEnabled) {
            try {
                File dataDirectory = Main.getMain().getDataFolder();
                File playerDataDirectory = new File(dataDirectory, "player_data");

                if (!playerDataDirectory.exists() && !playerDataDirectory.mkdirs()) {
                }

                File playerFile = new File(playerDataDirectory, playername + ".yml");

                if (!playerFile.exists() && !playerFile.createNewFile()) {
                }
                playerFile.delete();
            } catch (IOException ioException) {
                System.out.println("Failed to load faction " + playername + ": " + ioException.getMessage());
            }
        } else {
            Database database = Main.getDatabase();

            if (!database.checkConnection()) {
                return;
            }

            Connection connection = database.getConnection();
            PreparedStatement preparedStatement = null;

            try {
                StringBuilder queryBuilder = new StringBuilder();
                queryBuilder.append("DELETE FROM `enchantmentsenhance` ");
                queryBuilder.append("WHERE `playername` = ?;");

                preparedStatement = connection.prepareStatement(queryBuilder.toString());
                preparedStatement.setString(1, playername);
                preparedStatement.executeUpdate();

            } catch (final SQLException sqlException) {
                sqlException.printStackTrace();

            } finally {
                if (preparedStatement != null) {
                    try {
                        preparedStatement.close();
                    } catch (final SQLException ignored) {
                    }
                }
            }
        }
    }

    private void copyDefaults(File playerFile) {
        try {
            FileConfiguration playerConfig = YamlConfiguration.loadConfiguration(playerFile);
            Reader defConfigStream = new InputStreamReader(Main.getMain().getResource("playerdata.yml"));
            YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
            playerConfig.options().copyDefaults(true);
            playerConfig.setDefaults(defConfig);
            playerConfig.save(playerFile);
        } catch (Exception e) {
        }
    }
}