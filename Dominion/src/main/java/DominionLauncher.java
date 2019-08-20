import Client.DominionManager;

/*
 * Workaround to get Maven MainClass to work correctly
 *  -> Maven does not recognize subclasses (e.g. extends Application) as a MainClass.
 *     This class was created to circumvent that issue.
 */
public class DominionLauncher {

    public static void main(String[] args) {
        DominionManager.main(args);
    }

}