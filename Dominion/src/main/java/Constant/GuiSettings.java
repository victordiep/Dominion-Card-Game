package Constant;

import java.util.*;

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

    public static class CardSelectScreen {
        public static final List<String> AVAILABLE_CARD_LIST = new ArrayList<>() {{
            add("Artisan");
            add("Bandit");
            add("Bureaucrat");
            add("Cellar");
            add("Chapel");
            add("CouncilRoom");
            add("Festival");
            add("Gardens");
            add("Harbinger");
            add("Laboratory");
            add("Library");
            add("Market");
            add("Merchant");
            add("Militia");
            add("Mine");
            add("Moat");
            add("Moneylender");
            add("Poacher");
            add("Remodel");
            add("Sentry");
            add("Smithy");
            add("ThroneRoom");
            add("Vassal");
            add("Village");
            add("Witch");
            add("Workshop");
        }};

        public static final Set<String> PRESET_FIRST_GAME = new LinkedHashSet<>() {{
            add("Cellar");
            add("Market");
            add("Merchant");
            add("Militia");
            add("Mine");
            add("Moat");
            add("Remodel");
            add("Smithy");
            add("Village");
            add("Workshop");
        }};

        public static final Set<String> PRESET_SIZE_DISTORTION = new LinkedHashSet<>() {{
            add("Artisan");
            add("Bandit");
            add("Bureaucrat");
            add("Chapel");
            add("Festival");
            add("Gardens");
            add("Sentry");
            add("ThroneRoom");
            add("Witch");
            add("Workshop");
        }};

        public static final Set<String> PRESET_DECK_TOP = new LinkedHashSet<>() {{
            add("Artisan");
            add("Bureaucrat");
            add("CouncilRoom");
            add("Festival");
            add("Harbinger");
            add("Laboratory");
            add("Moneylender");
            add("Sentry");
            add("Vassal");
            add("Village");
        }};

        public static final Set<String> PRESET_IMPROVEMENTS = new LinkedHashSet<>() {{
            add("Artisan");
            add("Cellar");
            add("Market");
            add("Merchant");
            add("Mine");
            add("Moat");
            add("Moneylender");
            add("Poacher");
            add("Remodel");
            add("Witch");
        }};

        public static final Set<String> PRESET_SILVER_AND_GOLD = new LinkedHashSet<>() {{
            add("Bandit");
            add("Bureaucrat");
            add("Chapel");
            add("Harbinger");
            add("Laboratory");
            add("Merchant");
            add("Mine");
            add("Moneylender");
            add("ThroneRoom");
            add("Vassal");
        }};

        public static final int CARDS_PER_ROW = 6;
    }

    public static class GameSettings {
        /*
         * TURN
         */
        public static final int HAND_SIZE = 5;
        public static final int NUMBER_OF_ACTIONS = 1;
        public static final int NUMBER_OF_BUYS = 1;

        /*
         * SUPPLY
         */
        public static final int KINGDOM_CARD_PILE_SIZE = 10;

        // Treasure
        public static final int NUMBER_OF_COPPER = 60;
        public static final int NUMBER_OF_SILVER = 40;
        public static final int NUMBER_OF_GOLD = 30;

        // Victory
        public static final int NUMBER_OF_ESTATE = 24;
        public static final int NUMBER_OF_DUCHY = 12;
        public static final int NUMBER_OF_PROVINCE = 12;

        // Curse
        public static final int NUMBER_OF_CURSE = 30;
    }
}
