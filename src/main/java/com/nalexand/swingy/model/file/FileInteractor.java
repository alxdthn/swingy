package com.nalexand.swingy.model.file;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.JsonReader;
import com.nalexand.swingy.Swingy;
import com.nalexand.swingy.model.GameState;
import com.nalexand.swingy.utils.exceptions.FileInteractionException;

import java.io.*;

public class FileInteractor {

    private final Gson gson = new Gson();

    public void saveGameState(GameState gameState) {
        try {
            File gameStateFile = new File(Swingy.GAME_DATA_FILE);
            PrintWriter writer = new PrintWriter(gameStateFile);
            writer.write(gson.toJson(gameState));
            writer.close();
        } catch (FileNotFoundException e) {
            throw new FileInteractionException();
        }
    }

    public GameState loadGameState() throws FileInteractionException {

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
            return (result == null) ? new GameState() : result;
        } catch (FileNotFoundException e) {
            throw new FileInteractionException();
        } catch (JsonIOException | JsonSyntaxException e) {
            return new GameState();
        }
    }
}
