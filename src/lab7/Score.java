package lab7;

import org.jetbrains.annotations.NotNull;

import java.awt.*;

public class Score extends Rectangle {
    public Integer blueScore;
    public Integer redScore;
    private Integer WINDOW_Y;
    private Integer WINDOW_X;



    public Score ( int WINDOW_X , int WINDOW_Y ) {
        this.WINDOW_X = WINDOW_X;
        this.WINDOW_Y = WINDOW_Y;
        this.redScore = 0;
        this.blueScore = 0;
    }

    public void draw ( Graphics g ) {
        //System.out.println ( "Score is drawn" );
        g.setColor ( Color.white );
        g.setFont ( new Font ( "Consolas" , Font.PLAIN , 60 ) );

        g.drawString ( blueScore.toString () , (WINDOW_X / 2) - 85 , 50 );
        g.drawString (redScore.toString () , (WINDOW_X / 2) + 20 , 50 );
    }

    public void scored(boolean blue){
        if (blue) blueScore++;
        else redScore++;
    }

    public void resetScore ( ) {
        blueScore=0;
        redScore=0;
    }
}
