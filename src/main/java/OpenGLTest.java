import Config.Config;
import Entity.IEntity;
import Entity.IGeometry;
import Entity.Player;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

import java.util.ArrayList;

import static org.lwjgl.opengl.GL11.*;

public class OpenGLTest {
    ArrayList<IEntity> EntityList;
    ArrayList<IGeometry> GeometryList;

    public static void main(String[] args) {
        new OpenGLTest();
    }

    public OpenGLTest() {
        try {
            Display.setDisplayMode(new DisplayMode(Config.WIDTH, Config.HEIGHT));
            Display.create();

            //init level
            EntityList = new ArrayList<IEntity>();
            EntityList.add(new Player());

            long lastTime = Sys.getTime();
            int nbFrames = 0;
            int real_fps = Config.FPS;

            while(!Display.isCloseRequested()) {
                //init
                setCamera();
                drawBackground();

                for(IEntity e : EntityList) {
                    e.tick();
                }

                for(IEntity e : EntityList) {
                    e.draw();
                }

                Display.update();
                Display.sync(Config.FPS);

                // Measure speed
                nbFrames++;
                if (Sys.getTime() - lastTime >= 1000) {
                    // printf and reset timer
                    System.out.println(nbFrames);
                    real_fps = nbFrames;
                    nbFrames = 0;
                    lastTime += 1000;
                }
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
