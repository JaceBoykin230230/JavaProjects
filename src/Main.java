import jdk.jfr.Event;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) throws IOException {
        //System.out.println(OpenAI.chatGPT("What was the first crusade about?"));
        HashMap<String,Game> games = new HashMap<String,Game>();


        System.out.println("Testing Queue: ");

        EventHandler.handleEvent(games, "+1111111111", "play");
        EventHandler.handleEvent(games, "+1111111111", "judge");

        EventHandler.handleEvent(games, "+2222222222", "play");
        EventHandler.handleEvent(games, "+2222222222", "player");

        EventHandler.handleEvent(games, "+3333333333", "play");
        EventHandler.handleEvent(games, "+3333333333", "judge");

        EventHandler.handleEvent(games,"+4444444444", "play");
        EventHandler.handleEvent(games,"+4444444444", "player");


        EventHandler.handleEvent(games, "+5555555555", "play");
        EventHandler.handleEvent(games, "+5555555555", "judge");

        EventHandler.handleEvent(games, "+6666666666", "play");
        EventHandler.handleEvent(games, "+6666666666", "player");

        EventHandler.handleEvent(games, "+7777777777", "play");
        EventHandler.handleEvent(games, "+7777777777", "judge");

        EventHandler.handleEvent(games, "+8888888888", "play");
        EventHandler.handleEvent(games, "+8888888888", "player");
        EventHandler.makeGames(games);
        EventHandler.makeGames(games);
        EventHandler.makeGames(games);
        EventHandler.makeGames(games);


        System.out.println();
        System.out.println("Test Game Sessions: ");
        for (Map.Entry<String, Game> key : games.entrySet()) {
            System.out.println(key.getKey() + " " + "Player: " +  key.getValue().getPlayerPhoneNumber() + " " + "Judge: " +  key.getValue().getJudgePhoneNumber());
        }



        System.out.println();

        EventHandler.handleEvent(games,"+7777777777","B");
        System.out.println();


        EventHandler.handleEvent(games,"+5555555555","B");

        EventHandler.handleEvent(games,"+7777777777","B");
        System.out.println();

        //Testing chat log for each game session
        for (Map.Entry<String, Game> key : games.entrySet()) {
            if (games.get(key.getKey()).getGameChatLog()) {
                System.out.println(games.get(key.getKey()).getGameChatLog());
            }

        }



    }
}
