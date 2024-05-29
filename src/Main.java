import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException, ClassNotFoundException {

        //--------------------------THE MENU--------------------------//
        Canvas.getInstance().loadShapes();
        Application.runApp();
    }
}