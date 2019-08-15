package Constant;

import static Constant.GuiSettings.WindowDimensions.*;

public class GuiSettings {

    public static class WindowDimensions {
        public static final int WINDOW_WIDTH = 1080;
        public static final int WINDOW_HEIGHT = 600;
    }

    public static class StartScreen {
        // LOGO
        public static final double LOGO_WIDTH = 500;
        public static final double LOGO_HEIGHT = 176;
        public static final double LOGO_X = WINDOW_WIDTH / 2;
        public static final double LOGO_Y = WINDOW_HEIGHT * 0.0125;

        // MENU BACKGROUND
        public static final double MENU_RECT_WIDTH = 300;
        public static final double MENU_RECT_HEIGHT = 340;

        // START MENU
        public static final double START_RECT_X = (WINDOW_WIDTH * 0.33 - 20) - (MENU_RECT_WIDTH / 2);
        public static final double START_RECT_Y = LOGO_Y + LOGO_HEIGHT + 15;

        // LOBBY MENU
        public static final double LOBBY_RECT_X = (WINDOW_WIDTH * 0.66 - 20) - (MENU_RECT_WIDTH / 2);
        public static final double LOBBY_RECT_Y = LOGO_Y + LOGO_HEIGHT + 15;
    }


}
