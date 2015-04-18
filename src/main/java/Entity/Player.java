package Entity;

import Config.Config;
import org.lwjgl.input.Keyboard;
import org.newdawn.slick.opengl.Texture;
import org.newdawn.slick.opengl.TextureLoader;
import org.newdawn.slick.util.ResourceLoader;

import java.io.IOException;

import static org.lwjgl.opengl.GL11.*;

public class Player implements IEntity {
    double pos_x, pos_y;
    double speed_x, speed_y;
    int ticks_in_air;
    public Texture texture;


    public Player() {
        pos_x = Config.WIDTH/2;
        pos_y = Config.HEIGHT/2;
        ticks_in_air = 0;

        speed_x = 0;
        speed_y = 0;
    }

    @Override
    public double get_x_pos() {
        return pos_x;
    }

    @Override
    public double get_y_pos() {
        return pos_y;
    }

    @Override
    public double get_x_speed() {
        return speed_x;
    }

    @Override
    public double get_y_speed() {
        return speed_y;
    }

    @Override
    public void set_x_pos(double x_pos) {
        pos_x = x_pos;
    }

    @Override
    public void set_y_pos(double y_pos) {
        pos_y = y_pos;
    }

    @Override
    public void set_x_speed(double x_speed) {
        speed_x = x_speed;
    }

    @Override
    public void set_y_speed(double y_speed) {
        speed_y = y_speed;
    }

    @Override
    public boolean is_entity_on_ground() {
        return pos_y <= 0;
    }

    @Override
    public void init() throws IOException {
        texture = TextureLoader.getTexture("PNG", ResourceLoader.getResourceAsStream("src/resources/block.png"));
    }

    @Override
    public void tick(double delta) {
        pos_x += speed_x;
        pos_y += speed_y;

        if (pos_y <= Config.GROUND_LEVEL_Y) {
            pos_y = Config.GROUND_LEVEL_Y;
            speed_y = 0;
            ticks_in_air = 0;
        } else {
            speed_y -= delta;
            ticks_in_air++;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            speed_x -= 0.1*delta;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            speed_x += 0.1*delta;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            //System.out.println(ticks_in_air);
            //speed_y += (delta/(Config.FPS*(ticks_in_air+1)));
            speed_y += delta;
        }

        if (!(Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT))) {
            if (speed_x != 0)
                speed_x *= Math.pow(.998, delta);
        }


        if (speed_x > 0.5*delta)
            speed_x = 0.5*delta;
        if (speed_x < -0.5*delta)
            speed_x = -0.5*delta;
        if (speed_y > 0.5*delta)
            speed_y = 0.5*delta;
        if (speed_y < -0.5*delta)
            speed_y = -0.5*delta;

    }

    @Override
    public void draw() {
        glPushMatrix();

        //Move the frame over
        glTranslated(pos_x, pos_y, 0);


        texture.bind(); // or GL11.glBind(texture.getTextureID());

        glBegin(GL_QUADS);
        glTexCoord2f(0, 0);
        glVertex2f(0, 0);
        glTexCoord2f(1, 0);
        glVertex2f(texture.getTextureWidth(),0);
        glTexCoord2f(1,1);
        glVertex2f(texture.getTextureWidth(), texture.getTextureHeight());
        glTexCoord2f(0,1);
        glVertex2f(0, texture.getTextureHeight());
        glEnd();


        glPopMatrix();
    }
}
