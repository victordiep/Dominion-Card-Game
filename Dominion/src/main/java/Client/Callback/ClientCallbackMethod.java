package Client.Callback;

public enum ClientCallbackMethod {
    JOINED_TABLE("joinedTable"),
    START_GAME("startGame"),
    SHOW_GAME_END_DIALOG("showGameEndDialog"),
    SERVER_MESSAGE("serverMessage"),
    GAME_INIT("gameInit"),
    GAME_OVER("gameOver"),
    GAME_INFORM("gameInform"),
    GAME_INFORM_PERSONAL("gameInformPersonal"),
    GAME_ERROR("gameError"),
    GAME_UPDATE("gameUpdate"),
    USER_REQUEST_DIALOG("userRequestDialog"),
    END_GAME_INFO("endGameInfo"),
    GAME_TARGET("gameTarget"),
    GAME_CHOOSE_ABILITY("gameChooseAbility"),
    GAME_CHOOSE_PILE("gameChoosePile"),
    GAME_CHOOSE_CHOICE("gameChooseChoice"),
    GAME_ASK("gameAsk"),
    GAME_SELECT("gameSelect"),
    GAME_PLAY_MANA("gamePlayMana"),
    GAME_PLAY_XMANA("gamePlayXMana"),
    GAME_GET_AMOUNT("gameSelectAmount");

    String value;

    ClientCallbackMethod(String value) {
        this.value = value;
    }
}
