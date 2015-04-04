import org.lwjgl.LWJGLException;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

public class OpenGLTest {
    public OpenGLTest() {
        try {
            Display.setDisplayMode(new DisplayMode(1024, 768));
            Display.create();

            while(!Display.isCloseRequested()) {
                Display.update();
            }

            Display.destroy();
        } catch(LWJGLException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new OpenGLTest();
    }
}
