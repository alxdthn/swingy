package com.nalexand.swingy.utils;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.model.GameState;
import com.nalexand.swingy.utils.exceptions.FileInteractionException;

import java.io.*;

public class Utils {

    private static Gson gson = new Gson();

    private Utils() {}

    public static void println(String line) {
        System.out.println(line);
    }

    public static int printFormat(String format, Object... args) {
        String formatted = String.format(format, args);
        System.out.print(formatted);
        return formatted.length();
    }

    public static void saveGameState(GameState gameState) {

        try {
            File gameStateFile = new File(Swingy.GAME_DATA_FILE);
            PrintWriter writer = new PrintWriter(gameStateFile);

            System.out.println("save game state");
            System.out.println(gson.toJson(gameState));

            writer.write(gson.toJson(gameState));

            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileInteractionException();
        }
    }

    public static GameState loadGameState() throws FileInteractionException {

        if (Swingy.IGNORE_SAVED) {
            return new GameState();
        }

        File gameStateFile = new File(Swingy.GAME_DATA_FILE);

        if (!gameStateFile.exists()) {
            try {
                if (!gameStateFile.createNewFile()) {
                    throw new FileInteractionException();
                }
            } catch (IOException e) {
                throw new FileInteractionException();
            }
        }

        try {
            InputStreamReader fileReader = new FileReader(gameStateFile);
            GameState result = gson.fromJson(new JsonReader(fileReader), GameState.class);
            if (result == null) {
                return new GameState();
            } else {
                return result;
            }
        } catch (FileNotFoundException e) {
            throw new FileInteractionException();
        } catch (JsonIOException | JsonSyntaxException e) {
            return new GameState();
        }
    }
}
