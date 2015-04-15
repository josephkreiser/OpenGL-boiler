package Entity;

import Config.Config;
import org.lwjgl.input.Keyboard;

import static org.lwjgl.opengl.GL11.*;

public class Player implements IEntity {
    double pos_x, pos_y;
    double speed_x, speed_y;
    boolean on_ground;
    int ticks_in_air;

    public Player() {
        pos_x = Config.WIDTH/2;
        pos_y = Config.HEIGHT/2;
        on_ground = false;
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
        return on_ground;
    }

    @Override
    public void init() {
        //Load a texture?
    }

    @Override
    public void tick() {
        pos_x += speed_x;
        pos_y += speed_y;

        if (pos_y < 200) {
            pos_y = 200;
            speed_y = 0;
            ticks_in_air = 0;
            //on_ground = true;
        } else {
            speed_y -= 60.0/Config.FPS;
            ticks_in_air++;
            //on_ground = false;
        }

        if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
            speed_x -= 360.0/Config.FPS;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
            speed_x += 360.0/Config.FPS;
        }
        if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
            //System.out.println(ticks_in_air);
            speed_y += (480.0/(Config.FPS*(ticks_in_air+1)));
        }

        if (!(Keyboard.isKeyDown(Keyboard.KEY_LEFT) || Keyboard.isKeyDown(Keyboard.KEY_RIGHT))) {
            if (speed_x != 0)
                speed_x += speed_x > 0 ? -180.0/Config.FPS : 180.0/Config.FPS;
        }

        if (speed_x > 360.0/Config.FPS)
            speed_x = 360.0/Config.FPS;
        if (speed_x < -360.0/Config.FPS)
            speed_x = -360.0/Config.FPS;
        if (speed_y > 720.0/Config.FPS)
            speed_y = 720.0/Config.FPS;
        if (speed_y < -720.0/Config.FPS)
            speed_y = -720.0/Config.FPS;
    }

    @Override
    public void draw() {
        glPushMatrix();

        //Move the frame over
        glTranslated(pos_x, pos_y, 0);

        glBegin(GL_QUADS);
        glColor3d(1.0, 0, 1.0);
        glVertex2d(-8, 0);
        glVertex2d(8, 0);
        glVertex2d(8, 16);
        glVertex2d(-8, 16);
        glEnd();

        glPopMatrix();
    }
}
