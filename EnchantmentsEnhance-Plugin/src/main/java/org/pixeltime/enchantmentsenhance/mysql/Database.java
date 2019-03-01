/*
 *     Copyright (C) 2017-Present HealPot
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

import com.google.common.base.Charsets;
import com.google.common.io.Resources;
import org.pixeltime.enchantmentsenhance.Main;
import org.pixeltime.enchantmentsenhance.manager.MaterialManager;
import org.pixeltime.enchantmentsenhance.manager.SettingsManager;

import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Database {

    private final String connectionUri;
    private final String username;
    private final String password;
    private Connection connection;

    public Database() throws ClassNotFoundException, SQLException {
        final String hostname = SettingsManager.config.getString("mysql.host");
        final int port = SettingsManager.config.getInt("mysql.port");
        final String database = SettingsManager.config.getString("mysql.database");

        connectionUri = String.format("jdbc:mysql://%s:%d/%s", hostname, port, database);
        username = SettingsManager.config.getString("mysql.user");
        password = SettingsManager.config.getString("mysql.password");

        try {
            Class.forName("com.mysql.jdbc.Driver");
            connect();

        } catch (SQLException sqlException) {
            close();
            throw sqlException;
        }
    }

    private void connect() throws SQLException {
        if (connection != null) {
            try {
                connection.createStatement().execute("SELECT 1;");

            } catch (SQLException sqlException) {
                if (sqlException.getSQLState().equals("08S01")) {
                    try {
                        connection.close();

                    } catch (SQLException ignored) {
                    }
                }
            }
        }

        if (connection == null || connection.isClosed()) {
            connection = DriverManager.getConnection(connectionUri, username, password);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    private void close() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }

        } catch (SQLException ignored) {

        }

        connection = null;
    }

    public boolean checkConnection() {
        try {
            connect();
        } catch (SQLException sqlException) {
            close();
            sqlException.printStackTrace();
            return false;
        }
        return true;
    }

    public void createTables() throws IOException, SQLException {
        URL resource = Resources.getResource(Main.class, "/tables.sql");
        String[] databaseStructure = Resources.toString(resource, Charsets.UTF_8).split(";");

        if (databaseStructure.length == 0) {
            return;
        }

        Statement statement = null;

        try {
            connection.setAutoCommit(false);
            statement = connection.createStatement();

            for (String query : databaseStructure) {
                query = query.trim();

                if (query.isEmpty()) {
                    continue;
                }

                statement.execute(query);
            }

            connection.commit();

        } finally {
            connection.setAutoCommit(true);

            if (statement != null && !statement.isClosed()) {
                statement.close();
            }
        }
    }

    public boolean doesPlayerExist(String playername) {
        if (!checkConnection()) {
            return false;
        }

        int count = 0;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("SELECT Count(`playername`) ");
            queryBuilder.append("FROM `enchantmentsenhance` ");
            queryBuilder.append("WHERE `playername` = ? ");
            queryBuilder.append("LIMIT 1;");

            preparedStatement = connection.prepareStatement(queryBuilder.toString());
            preparedStatement.setString(1, playername);
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                count = resultSet.getInt(1);
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

        return count > 0;
    }

    public void createNewPlayer(String name) {
        if (!checkConnection()) {
            return;
        }

        PreparedStatement preparedStatement = null;

        try {
            StringBuilder queryBuilder = new StringBuilder();
            queryBuilder.append("INSERT INTO `enchantmentsenhance` ");
            // Query
            queryBuilder.append("(`id`, `playername`, `failstack`, `items`, `valks`, `grind`) ");
            queryBuilder.append("VALUES ");
            queryBuilder.append("(NULL, ?, 0, ?, ?, ?);");
            preparedStatement = connection.prepareStatement(queryBuilder.toString());
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, Arrays.toString(new int[MaterialManager.stoneTypes.size()]));
            preparedStatement.setString(3, new ArrayList<Integer>().toString());
            preparedStatement.setInt(4, 2);
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