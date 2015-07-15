import controllers.AppController;
import views.LifeFrame;

/**
 * Application runnable.
 *
 * Created by Deniz on 7/14/2015.
 */
public class LifeApp {
    public static void main(String[] args) {
        AppController control = new AppController();
        LifeFrame gui = new LifeFrame(control);
    }
}
