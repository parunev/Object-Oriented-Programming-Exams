package viceCity.models.players;

public class MainPlayer extends BasePlayer{
    private static final String MAIN_PLAYER_NAME = "Tommy Vercetti";
    private static final int LIFE_POINTS = 100;

    public MainPlayer() {
        super(MAIN_PLAYER_NAME, LIFE_POINTS);
    }

}
