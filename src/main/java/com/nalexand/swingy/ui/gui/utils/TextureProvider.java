package com.nalexand.swingy.ui.gui.utils;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;

public class TextureProvider {

    private static Image grassTexture = null;

    private static Image grassWithHeroTexture = null;

    private static Image grassWithMobTexture = null;

    private static Image grassWithLootTexture = null;

    private static Image waterTexture = null;

    private static Image mobTexture = null;

    private static Image traxexMapSprite = null;

    private TextureProvider() {
    }

    public static Image getGrassTexture() throws IOException {
        if (grassTexture == null) {
            grassTexture = readGrassTexture();
        }
        return grassTexture;
    }

    public static Image getGrassWithHeroTexture() throws IOException {
        if (grassWithHeroTexture == null) {
            grassWithHeroTexture = readGrassTexture();
            grassWithHeroTexture.getGraphics().drawImage(
                    getTraxexMapSprite(),
                    0,
                    0,
                    null
            );
        }
        return grassWithHeroTexture;
    }

    public static Image getGrassWithMobTexture() throws IOException {
        if (grassWithMobTexture == null) {
            grassWithMobTexture = readGrassTexture();
            grassWithMobTexture.getGraphics().drawOval(0, 0, 10, 10);
        }
        return grassWithMobTexture;
    }

    public static Image getGrassWithLootTexture() throws IOException {
        if (grassWithLootTexture == null) {
            grassWithLootTexture = readGrassTexture();
            grassWithLootTexture.getGraphics().drawOval(0, 0, 10, 10);
        }
        return grassWithLootTexture;
    }

    public static Image getWaterTexture() throws IOException {
        if (waterTexture == null) {
            waterTexture = readImage("/water.png");
        }
        return waterTexture;
    }

    private static Image getTraxexMapSprite() throws IOException {
        if (traxexMapSprite == null) {
            traxexMapSprite = readImage("/traxex_map.gif");
        }
        return traxexMapSprite;
    }

    private static Image readGrassTexture() throws IOException {
        return readImage("/grass.png");
    }

    private static Image readImage(String source) throws IOException {
        return ImageIO.read(TextureProvider.class.getResource(source));
    }
}
