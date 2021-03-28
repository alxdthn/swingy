package com.nalexand.swingy.ui.gui.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TextureProvider {

    public static final String GRASS = "/grass.png";
    public static final String WATER = "/water.png";
    public static final String VOID = "/void.png";
    public static final String NEVERMORE = "/nevermore.png";
    public static final String TRAXEX = "/traxex.png";
    public static final String URSA = "/ursa.png";
    public static final String BANDIT = "/bandit.png";
    public static final String NECROMANCER = "/necromancer.png";
    public static final String ORK = "/ork.png";
    public static final String WOOD_ELF = "/wood_elf.png";
    public static final String DESOLATOR = "/desolator.png";
    public static final String BUTTERFLY = "/butterfly.png";
    public static final String SHIVA = "/shiva.png";
    public static final String DOMINATOR = "/dominator.png";
    public static final String BLADE_MAIL = "/blade_mail.png";
    public static final String RAPIER = "/rapier.png";
    public static final String INVENTORY = "/inventory.png";

    private static final Map<String, Image> cachedImages = new HashMap<>();

    private TextureProvider() {
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
        } catch (IllegalArgumentException e) {
            //TODO ERROR
            System.err.println("bad resource "+ source);
            e.printStackTrace();
            System.exit(1);
            return null;
        }
    }
}
