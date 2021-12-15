package lab7;

import java.awt.*;
import java.util.Random;


public class Ball extends Rectangle {
    public Rectangle getRect ( ) {
        return new Rectangle ( x,y,DIAMETER,DIAMETER );
    } //implements Runnable {
    public enum BallState {
        MOVED,
        BLUE_SCORE,
        RED_SCORE
    }

    private enum Collision {
        NO,
        LEFT_RACKET,
        LEFT_SCORE,
        TOP,
        BOTTOM,
        RIGHT_RACKET,
        RIGHT_SCORE,
    }

    public BallState ballState;
    private static final int DIAMETER = 20;
    private float speed;
    private double angle;
    private int WINDOW_X;
    private int WINDOW_Y;
    private Random random;
    private final Racket blue;
    private final Racket red;


    Ball ( int windowX , int windowY , Racket Blue , Racket Red ) {
        super ( windowX / 2 , windowY / 2 , DIAMETER , DIAMETER );
        random = new Random ( );
        angle = random.nextDouble ( -Math.PI , Math.PI );
        WINDOW_X=windowX;
        WINDOW_Y=windowY;
        System.out.println ( "Init angle " + angle );
        this.red = Red;
        this.blue = Blue;
        ballState = BallState.MOVED;
        speed = 7;
    }

    public void draw ( Graphics g ) {
        //System.out.println ( "Ball drawn" );
       // System.out.println ( "Ball x = " + x + " y= " + y );
        g.setColor ( Color.white );
        g.fillOval ( x , y , DIAMETER , DIAMETER );
    }

    //@Override
//    public void run ( ) {
//        System.out.println ( "Ball run" );
//        long lasTime = System.nanoTime ( );
//        double amountOfTicks = 120.0;
//        double ns = 1000000000 / amountOfTicks;
//        double delta = 0;
//
//        while (ballState == BallState.MOVED) {
//            long now = System.nanoTime ( );
//            delta += (now - lasTime) / ns;
//            lasTime = now;
//            if ( delta >= 1 ) {
//                move ( );
//            }
//        }
//        synchronized (this){
//        this.notify();}
//
//    }


    public Collision check_colission ( int newX , int newY ) {
      // System.out.println ( "Checking collision for x="+newX+" y="+newY );
        if ( newY > (float) DIAMETER ) { //no top collision
            if ( newY < WINDOW_Y - (float) DIAMETER )//no bottom collision
            {
                if ( newX > Racket.X_SIZE ) {// no left collision
                    if ( newX + DIAMETER < WINDOW_X - Racket.X_SIZE ) //no right collision
                        return Collision.NO;
                    else { //right collision happened
                        if ( newY -DIAMETER > red.getYPosition ( ) + Racket.Y_SIZE || newY+DIAMETER < red.getYPosition ( ) ) //right score
                            return Collision.RIGHT_SCORE;
                        else return Collision.RIGHT_RACKET;
                    }
                } else { //left collision happened
                    if ( newY-DIAMETER > blue.getYPosition ( ) + Racket.Y_SIZE || newY+DIAMETER < blue.getYPosition ( ) ) // left score
                        return Collision.LEFT_SCORE;
                    else  //left racket
                        return Collision.LEFT_RACKET;
                }
            } else return Collision.BOTTOM;
        } else return Collision.TOP;
    }

    private void updateCoordinates ( ){
        //System.out.println ( "Coordinates updated" );
        x += speed * Math.cos ( angle );
        y += speed * Math.sin ( angle );
    }

    private void updateAngle ( Collision collidedInto , int newY ) {
        assert collidedInto != Collision.NO;
        switch (collidedInto) {
            case TOP -> angle = -angle;
            case LEFT_RACKET -> angle = Math.PI * ((Math.max ( newY - blue.getYPosition ( ) , 0.1 )) / (float) Racket.Y_SIZE) - Math.PI / 2;
            case RIGHT_RACKET -> angle = -Math.PI * (Math.max(newY - red.getYPosition ( ),0.1) / (float) Racket.Y_SIZE) - Math.PI/2;
            case BOTTOM -> angle = -angle;
        }
        while (angle > Math.PI || angle < -Math.PI) {
            System.out.println ( " insufficient angle " +angle);
            if ( angle < -Math.PI ) angle =angle+ 2 * Math.PI;
            if (angle>Math.PI) angle =angle- 2 * Math.PI;
        }
        assert (angle < Math.PI || angle > -Math.PI);
        if( Math.abs ( Math.PI/2-angle )<0.2||Math.abs ( -Math.PI/2-angle )<0.2){
            if (Math.PI/2-angle<0||-Math.PI/2-angle<0) angle+=0.2;
            else angle-=0.2;
        }
        System.out.println ( "x speed = "+speed * Math.cos ( angle )+"angle"+angle );

    }


    public void move ( ) {
        //System.out.println ( "move ball" );
        int oldX=x;
        int oldY=y;
        updateCoordinates ();
        Collision collision = check_colission ( x,y );
        while (!(collision==Collision.NO || collision==Collision.LEFT_SCORE||collision==Collision.RIGHT_SCORE)){
            //System.out.println ( "move cycle, collision."+collision );
            updateAngle ( collision, y);
            x=oldX;
            y=oldY;
            updateCoordinates ();
            collision=check_colission ( x,y );
        }
        switch (collision){
            case LEFT_SCORE -> ballState= BallState.RED_SCORE;
            case RIGHT_SCORE -> ballState= BallState.BLUE_SCORE;
            case NO -> ballState=BallState.MOVED;
        }
        //System.out.println ( "Ball moved" );
    }

    public void resetPosition ( ) {
        speed = 7;
        angle = random.nextDouble ( -Math.PI , Math.PI );
        ballState = BallState.MOVED;
        x = WINDOW_X / 2;
        y = WINDOW_Y / 2;

    }
}
