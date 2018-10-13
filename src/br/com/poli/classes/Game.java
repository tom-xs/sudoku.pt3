package br.com.poli.classes;

import java.time.LocalDateTime;
import static java.time.LocalDateTime.now;

public class Game {

    private LocalDateTime startTime;
    private  LocalDateTime endTime;
    private Player player;
    private Enum difficulty;

    public class game{
    }

    public LocalDateTime getStartTime(){
        return startTime;
    }

    public LocalDateTime getEndTime(){
        return endTime;
    }

    public Player getPlayer(){
        return player;
    }

    public void startGame(Player player){
        startTime = now();
        this.player = player;
    }

    public void endGame(){
        endTime = now();
    }


}
