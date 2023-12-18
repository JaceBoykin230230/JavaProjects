import java.util.*;

public class Game {
    private String judgePhoneNumber;
    private String playerPhoneNumber;

    private Map<String,ArrayList<String>> gameChatLog = new HashMap<>();

    //stores phone number and role (player or judge)



    public Game(String judgePhoneNumber, String playerPhoneNumber ) {
        this.playerPhoneNumber = playerPhoneNumber;
        this.judgePhoneNumber = judgePhoneNumber;


    }


    public String getJudgePhoneNumber() {
        return judgePhoneNumber;

    }


    public String getPlayerPhoneNumber() {
        return playerPhoneNumber;
    }

    public String setPlayerPhoneNumber(String playerPhoneNumber) {
        return this.playerPhoneNumber = playerPhoneNumber;
    }



    public void setJudgePhoneNumber(String judgePhoneNumber) {
        this.judgePhoneNumber = judgePhoneNumber;
    }

    public void setGameChatLog(Map<String,ArrayList<String>> gameChatLog){
        this.gameChatLog = gameChatLog;
    }

    public boolean getGameChatLog(){

        if(gameChatLog != null) {
            System.out.println("judge entries: ");
            for (String entry : gameChatLog.get("judge")) {
                int x = 0;
                ++x;
                System.out.println(x + ": " + entry);
            }
            System.out.println();
            System.out.println("bot entries: ");
            for (String entry : gameChatLog.get("bot")) {
                int x = 0;
                ++x;
                System.out.println(x + ": " + entry);
            }
            return true;
        }
        return false;
    }




}


