package ohtu;


public class TennisGame {
    
    private int p1_score = 0;
    private int p2_score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals("player1"))
            p1_score += 1;
        else
            p2_score += 1;
    }

    public String getScore() {
        if(deuce()){
            return "Deuce";
        }else if (game())
        {
            return gameString();
        }
        else {
            return scoreString();
        }
    }
    private boolean drawn(){
        return p1_score == p2_score;
    }
    private boolean deuce(){
        return drawn() && p1_score >= 4;
    }
    private  boolean game(){
        return p1_score >=4 || p2_score >= 4;
    }
    private boolean p1Advantages(){
        return (p1_score - p2_score) == 1;
    }
    private boolean p1Wins(){
        return (p1_score - p2_score) >= 2;
    }
    private boolean p2Advantages(){
        return (p1_score - p2_score) == -1;
    }

    private String scoreString(){
        StringBuilder sb = new StringBuilder();
        final String[] scoreConstants = {"Love","Fifteen","Thirty","Forty"};
        if(p1_score < 4){
            sb.append(scoreConstants[p1_score]);
        }
        sb.append("-");
        if(drawn()){
            sb.append("All");
        }else{
            if(p2_score < 4){
                sb.append(scoreConstants[p2_score]);
            }
        }
        return sb.toString();
    }
    private String gameString(){
        if (p1Advantages()) return "Advantage player1";
        else if (p2Advantages()) return "Advantage player2";
        else if (p1Wins()) return  "Win for player1";
        else return "Win for player2";
    }

}