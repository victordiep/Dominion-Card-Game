package Constant;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class CardSettings {

    public static class DominionCards {

        public static final Set<String> CURSE_CARDS = new HashSet<>() {{
            add("Curse");
        }};

        public static final Map<String, Integer> CURSE_CARD_COSTS = new HashMap<>() {{
            put("Curse", 0);
        }};

        public static final Set<String> KINGDOM_CARDS = new HashSet<>() {{
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

        public static final Map<String, Integer> KINGDOM_CARD_COSTS = new HashMap<>() {{
            put("Artisan", 6);
            put("Bandit", 5);
            put("Bureaucrat", 4);
            put("Cellar", 2);
            put("Chapel", 2);
            put("CouncilRoom", 5);
            put("Festival", 5);
            put("Gardens", 4);
            put("Harbinger", 3);
            put("Laboratory", 5);
            put("Library", 5);
            put("Market", 5);
            put("Merchant", 3);
            put("Militia", 4);
            put("Mine", 5);
            put("Moat", 2);
            put("Moneylender", 4);
            put("Poacher", 4);
            put("Remodel", 4);
            put("Sentry", 5);
            put("Smithy", 4);
            put("ThroneRoom", 4);
            put("Vassal", 2);
            put("Village", 3);
            put("Witch", 5);
            put("Workshop", 3);
        }};

        public static final Set<String> TREASURE_CARDS = new HashSet<>() {{
            add("Copper");
            add("Gold");
            add("Silver");
        }};

        public static final Map<String, Integer> TREASURE_CARD_COSTS = new HashMap<>() {{
            put("Copper", 0);
            put("Gold", 6);
            put("Silver", 3);
        }};

        public static final Set<String> VICTORY_CARDS = new HashSet<>() {{
            add("Duchy");
            add("Estate");
            add("Province");
        }};

        public static final Map<String, Integer> VICTORY_CARD_COSTS = new HashMap<>() {{
            put("Duchy", 5);
            put("Estate", 2);
            put("Province", 8);
        }};
    }
}
