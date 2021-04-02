package com.nalexand.swingy.model.database;

import com.google.gson.Gson;
import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.model.GameState;
import com.nalexand.swingy.model.models.Hero;

import java.sql.*;

public class DataBaseInteractor {

    private Connection connection = null;

    private final Gson gson = new Gson();

    public void saveGameState(GameState gameState, Hero.Type heroType) {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("REPLACE INTO GAME_STATE (hero, state) VALUES(?, ?)");
            preparedStatement.setString(1, heroType.toString());
            preparedStatement.setString(2, gson.toJson(gameState.heroes.get(gameState.selectedHeroType)));
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            handleError(e);
        }
    }

    public GameState loadGameState() {
        try {
            Connection connection = getConnection();
            PreparedStatement preparedStatement = connection.prepareStatement
                    ("SELECT * FROM GAME_STATE");
            ResultSet resultSet = preparedStatement.executeQuery();
            GameState result = new GameState();
            while (resultSet.next()) {
                Hero.Type heroType = gson.fromJson(resultSet.getString("hero"), Hero.Type.class);
                Hero hero = gson.fromJson(resultSet.getString("state"), Hero.class);
                if (hero == null) {
                    hero = new Hero(heroType);
                }
                if (hero.selected) {
                    result.selectedHeroType = heroType;
                }
                result.heroes.put(heroType, hero);
            }
            return result;
        } catch (Exception e) {
            handleError(e);
            return null;
        }
    }

    private Connection getConnection() {
        if (connection == null) {
            try {
                Class.forName("org.sqlite.JDBC");
                connection = DriverManager.getConnection("jdbc:sqlite:swingy.db");
                String sql = "CREATE TABLE IF NOT EXISTS GAME_STATE (\n"
                        + "	hero string PRIMARY KEY,\n"
                        + "	state strgin\n"
                        + ");";
                Statement stmt = connection.createStatement();
                stmt.execute(sql);
            } catch (Exception e) {
                handleError(e);
            }
        }
        return connection;
    }

    private static void handleError(Exception e) {
        System.err.println("DB error");
        if (Swingy.DEBUG) {
            e.printStackTrace();
        }
        System.exit(1);
    }
}