import java.util.*;
import java.io.IOException;

public class EventHandler {
    private static Queue<String> playerQueue = new LinkedList<String>();
    private static Queue<String> judgeQueue = new LinkedList<String>();
    private static Map<String, String> playerRole = new HashMap<String, String>();

    // store judge questions and AI bot responses in order to feed back to chatGpt
    private static Map<String,ArrayList<String>> chatLog;
    private static ArrayList<String> judgeQuestions;
    private static ArrayList<String> botResponses;

    public static HashMap<String,Game> games = new HashMap<String,Game>();
    public static void handleEvent(Map<String, Game> games, String phoneNumber, String message) {

        //if phone number is known to us
        if (games.containsKey(phoneNumber)) {

            Game game = games.get(phoneNumber);

            String judgePhoneNumber = game.getJudgePhoneNumber();
            String playerPhoneNumber = game.getPlayerPhoneNumber();
            Map<String,ArrayList<String>> chatLog = new HashMap<>();
            ArrayList<String> judgeQuestions = new ArrayList<>();
            ArrayList<String> botResponses = new ArrayList<>();

            //set up chat log
            judgeSetChatLog(chatLog,judgeQuestions);
            botSetChatLog(chatLog,botResponses);

            // actually save the chat log per game session.
            game.setGameChatLog(chatLog);

            if (phoneNumber.equals(judgePhoneNumber)) {

                // create proper loop so judge can switch between asking player 'a' and 'b' questions until they are done.
                //judge is prompted to keep questioning when a response is given back from either player 'a' or 'b'.
                    SMSSender.sendSMS(judgePhoneNumber,"Judge, who would you like to question? ");
                    SMSSender.sendSMS(judgePhoneNumber, "Judge... select 'A' to question player A or select 'B' to question player B.");

                    //send message to player
                    if (message.equals("A")) {
                        SMSSender.sendSMS(judgePhoneNumber, "Ok... what question would you like to ask player A?");

                        //fix this logic to capture next judge message

                        //needs to send message to player phone number from Twilio API
                        SMSSender.sendSMS(game.getPlayerPhoneNumber(), message);

                        //log question
                        judgeQuestions.add(message);
                    }

                    //Send message to chatgpt
                    else if (message.equals("B")) {
                        SMSSender.sendSMS(judgePhoneNumber, "Ok... what question would you like to ask player B?");

                        // fix logic to capture judges next message
                        judgeQuestions.add(message);
                        try {

                            System.out.println(OpenAI.chatGPT(message));
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }

                    } else {
                        System.out.println("Judge... select player 'A' or 'B'.");
                    }

                    // wait for response to come back then prompt the judge to ask again

            } else {
                //Player is sending a message
                System.out.println("Player is sending a message" + " " + message);
                SMSSender.testSendSMS(phoneNumber, message);
            }

            getJudgeQuestionLog(chatLog);
            System.out.println();
            getBotResponseLog(chatLog);

            //find a way to actually store chat per game session
        } else {
            // create a queue for phone numbers of players

            // phone number not know to us so create new game
            // Start game
            if (message.equals("play")) {
                SMSSender.sendSMS(phoneNumber, "Welcome to Turing Text!");
                SMSSender.sendSMS(phoneNumber, "Would you like to be a player or a judge?");
                SMSSender.sendSMS(phoneNumber, "Type 'help' for the conditions of the game.");
            }

            // Assign player
            if (message.equals("player")) {

                // Set player role
                playerRole.put(phoneNumber, "player");
                SMSSender.sendSMS(phoneNumber, "You are now the player.");
                EventHandler.addPlayerQueuePhoneNumber(phoneNumber);

                // Assign judge
            } else if (message.equals("judge")) {

                // Set judge role
                playerRole.put(phoneNumber, "judge");
                SMSSender.sendSMS(phoneNumber, "You are the judge.");
                EventHandler.addJudgeQueuePhoneNumber(phoneNumber);
            }
            else if (!message.equals("play")) {
                SMSSender.sendSMS(phoneNumber, "Type 'player' or 'judge' to select your role.");
            }
        }
    }
    public static  void addPlayerQueuePhoneNumber(String phoneNumber) {

        playerQueue.add(phoneNumber);
    }
    public static void addJudgeQueuePhoneNumber(String phoneNumber) {

        judgeQueue.add(phoneNumber);
    }

    //only matches 2 pair at a time
    public static void makeGames(HashMap<String, Game> games) {
        //check if queue has players waiting for game
        if (!playerQueue.isEmpty() && !judgeQueue.isEmpty()) {
            //create game
            String playerPhoneNumber = playerQueue.poll();
            String judgePhoneNumber = judgeQueue.poll();
            Game game = new Game(judgePhoneNumber, playerPhoneNumber);
            games.put(playerPhoneNumber, game);
            games.put(judgePhoneNumber, game);

            System.out.println("Game created!");
            SMSSender.testSendSMS(judgePhoneNumber, "Select which player you would like to ask a question: text 'A' for player A, or 'B' for player B.");
            System.out.println();
        }
    }

    public static void judgeSetChatLog(Map<String,ArrayList<String>> chatLog,ArrayList<String> judgeQuestions){
        chatLog.put("judge",judgeQuestions);
    }

    public static void botSetChatLog(Map<String,ArrayList<String>> chatLog, ArrayList<String> botResponses ){
        chatLog.put("bot", botResponses);
    }

    public static void botAddChatResponse(ArrayList<String> botResponses, String response){
        botResponses.add(response);
    }

    public static void getBotResponseLog(Map<String,ArrayList<String>> chatLog){
        for (String response : chatLog.get("bot")){
            System.out.println(response);
        }

    }
    public static void getJudgeQuestionLog(Map<String,ArrayList<String>> chatLog){
        for (String question : chatLog.get("judge")){
            System.out.println(question);
        }

    }




}




