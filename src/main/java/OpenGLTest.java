import Config.Config;
import Entity.IEntity;
import Entity.IGeometry;
import Entity.Player;
import Texture.Util;

import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.io.IOException;
import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLTest {
    ArrayList<IEntity> EntityList;
    ArrayList<IGeometry> GeometryList;

    public static void main(String[] args) throws IOException {
        new OpenGLTest();
    }

    public OpenGLTest() throws IOException {
        try {
            Display.setDisplayMode(new DisplayMode(Config.WIDTH, Config.HEIGHT));
            Display.create();

            // This was copy pasted from here: http://ninjacave.com/slickutil1
            glEnable(GL_TEXTURE_2D);
            //glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
            glEnable(GL_BLEND);
            glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

            //init level
            EntityList = new ArrayList<IEntity>();
            EntityList.add(new Player());

            long checkIn = System.nanoTime();
            long lastTime = System.nanoTime();
            long delta;

            final long ONE_SECOND = 1000000000;
            final long DELTA_SCALE = 1000000;

            Util.init();



            while(!Display.isCloseRequested()) {
                // Obtain Delta
                delta = System.nanoTime() - lastTime;
                lastTime = System.nanoTime();
                if (delta < 0) {
                    delta += Long.MAX_VALUE;
                }

                if (System.nanoTime() > checkIn) {
                    System.out.println("FPS: " + ONE_SECOND/delta + " - Scaled Delta: " + (float)delta/DELTA_SCALE);
                    checkIn += ONE_SECOND;
                }

                //init
                setCamera();
                drawBackground();

                for(IEntity e : EntityList) {
                    e.tick((float)delta/DELTA_SCALE);
                }

                for(IEntity e : EntityList) {
                    e.draw();
                }

                Display.update();
                Display.sync(240);
            }

            Display.destroy();
        } catch(LWJGLException e) {
            e.printStackTrace();
        }
    }

    private void setCamera() {


        // Clear the screen
        glClear(GL_COLOR_BUFFER_BIT);

        // Modify the projection matric
        glMatrixMode(GL_PROJECTION);
        glLoadIdentity();
        glOrtho(0, Config.WIDTH, 0, Config.HEIGHT, -1, 1);

        // Set matrix mode to the Model View for drawing sprites
        glMatrixMode(GL_MODELVIEW);
        glLoadIdentity();
    }

    private void drawBackground() {
        glBegin(GL_QUADS);

        glColor3d(1.0, 1.0, 1.0);
        glVertex2d(0, 0);
        glVertex2d(Config.WIDTH, 0);
        glVertex2d(Config.WIDTH, Config.HEIGHT);
        glVertex2d(0, Config.HEIGHT);

        glEnd();
    }
}
