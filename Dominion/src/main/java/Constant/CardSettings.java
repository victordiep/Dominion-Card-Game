package Constant;

import java.util.*;

public class CardSettings {

    public static class DominionCards {
        public static final Map<String, Integer> CARD_COSTS = new LinkedHashMap<>() {{
            // Kingdom Cards
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

            // Treasure Cards
            put("Copper", 0);
            put("Silver", 3);
            put("Gold", 6);

            // Victory Cards
            put("Estate", 2);
            put("Duchy", 5);
            put("Province", 8);

            // Curse Cards
            put("Curse", 0);
        }};

        public static final Set<String> CURSE_CARDS = new LinkedHashSet<>() {{
            add("Curse");
        }};

        public static final Set<String> KINGDOM_CARDS = new LinkedHashSet<>() {{
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

        public static final Set<String> TREASURE_CARDS = new LinkedHashSet<>() {{
            add("Copper");
            add("Silver");
            add("Gold");
        }};

        public static final Set<String> VICTORY_CARDS = new LinkedHashSet<>() {{
            add("Estate");
            add("Duchy");
            add("Province");
        }};
    }
}
