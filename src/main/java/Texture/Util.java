package Texture;

import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

public class Util {
    public static Texture texture;

    public static void init() throws IOException {
        texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/resources/block.png"));
    }
}
