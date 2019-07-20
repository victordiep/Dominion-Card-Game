/*
 * Workaround to get Maven MainClass to work correctly
 *  -> Maven was not recognizing Main as a MainClass since
 *     it extended from Application. This class was created
 *     to circumvent that.
 */
public class DominionLauncher {

    public static void main(String[] args) {
        Main.main(args);
    }

}