import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import static constant.Constants.NUM_OF_GAMES;
import static constant.Constants.NUM_OF_PLAYERS;

public class Main {

    private static void readProperties() {
        try (InputStream reader = Main.class.getResourceAsStream("/config.properties")) {
            Properties p = new Properties();
            p.load(reader);

            NUM_OF_PLAYERS = Integer.parseInt(p.getProperty("NUMBER_OF_PLAYERS"));
            NUM_OF_GAMES = Integer.parseInt(p.getProperty("NUMBER_OF_GAMES"));
        } catch (IOException e) {
            System.out.println("Could not read properties due to : " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        readProperties();
        CardGameApplication cardGame = new CardGameApplication();
        cardGame.startGame();
    }
}
