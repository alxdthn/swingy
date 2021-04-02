package com.nalexand.swingy.utils;

import com.nalexand.swingy.Swingy;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextureProvider {

    public static final String GRASS = "/grass_2.png";
    public static final String VOID = "/void.png";
    public static final String NEVERMORE = "/nevermore.png";
    public static final String TRAXEX = "/traxex.png";
    public static final String URSA = "/ursa.png";
    public static final String BANDIT = "/bandit.png";
    public static final String NECROMANCER = "/necromancer.png";
    public static final String ORK = "/ork.png";
    public static final String WOOD_ELF = "/wood_elf.png";

    public static final String[] OBSTACLE_SPRITES = {
            "/water_2.png",
            "/tree_1.png",
            "/tree_2.png",
            "/tree_3.png",
            "/tree_4.png",
    };

    private static final Map<String, Image> cachedImages = new HashMap<>();

    private TextureProvider() {
    }

    public static String getRandomObstacleSprite() {
        return OBSTACLE_SPRITES[Utils.randomBetween(0, OBSTACLE_SPRITES.length)];
    }

    public static Image getImageWith(String backgroundSource, String foregroundSource) throws IOException {
        String key = backgroundSource + "_" + foregroundSource;
        Image result = cachedImages.get(key);
        if (result == null) {
            Image background = readImage(backgroundSource);
            Image foreground = getImage(foregroundSource);

            int backgroundImageSize = Math.min(
                    background.getHeight(null),
                    background.getWidth(null)
            );
            int foregroundImageSize = backgroundImageSize / 2;
            background.getGraphics().drawImage(
                    foreground,
                    (backgroundImageSize - foregroundImageSize) / 2,
                    (backgroundImageSize - foregroundImageSize) / 2,
                    foregroundImageSize,
                    foregroundImageSize,
                    null
            );
            cachedImages.put(key, background);
            result = background;
        }
        return result;
    }

    public static Image getImage(String source) {
        try {
            Image result = cachedImages.get(source);
            if (result == null) {
                result = readImage(source);
                cachedImages.put(source, result);
            }
            return result;
        } catch (IOException e) {
            //TODO error
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }

    private static Image readImage(String source) throws IOException {
        try {
            return ImageIO.read(TextureProvider.class.getResource(source));
        } catch (RuntimeException e) {
            System.err.println("bad resource " + source);
            if (Swingy.DEBUG) {
                e.printStackTrace();
            }
            System.exit(1);
            return null;
        }
    }
}
