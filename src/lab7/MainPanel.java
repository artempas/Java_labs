package lab7;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import static java.lang.Thread.sleep;

public class MainPanel extends JPanel implements Runnable {
    private Score score;
    private Racket blue;
    private Racket red;
    private Deque<Highlight> lastScored;
    private Ball ball;
    private Thread blueThread;
    private Thread redThread;
    private Thread ballThread;
    private Queue<MyKeyEvent> blueQ;
    private Queue<MyKeyEvent> redQ;
    static final int WINDOW_WIDTH = 1000;
    static final int WINDOW_HEIGHT = 555;
    static final Dimension SCREEN_SIZE = new Dimension ( WINDOW_WIDTH , WINDOW_HEIGHT );
    public int playUntil;
    public static Thread frameThread;


    public MainPanel (  ) {
        //Score.g = getGraphics ( );
        // Racket.g = getGraphics ( );
        // Ball.g=this.getGraphics ();
        System.out.println ( "MainPanel constructor" );
        score = new Score ( WINDOW_WIDTH , WINDOW_HEIGHT );
        redQ = new LinkedList<MyKeyEvent> ( );
        blueQ = new LinkedList<MyKeyEvent> ( );
        blue = new Racket ( true , WINDOW_WIDTH , WINDOW_HEIGHT , blueQ );
        red = new Racket ( false , WINDOW_WIDTH , WINDOW_HEIGHT , redQ );
        ball = new Ball ( WINDOW_WIDTH , WINDOW_HEIGHT , blue , red );
        System.out.println ( "Elements are created" );
        lastScored = new LinkedList<Highlight> ( );

        this.setFocusable ( true );
        this.setPreferredSize ( SCREEN_SIZE );
        this.addKeyListener ( new KeyListener ( ) {
            @Override
            public void keyTyped ( KeyEvent e ) {

            }

            @Override
            public void keyPressed ( KeyEvent e ) {
                System.out.println ( "KeyPressed " + e.getKeyCode ( ) );
                if ( e.getKeyCode ( ) == KeyEvent.VK_UP || e.getKeyCode ( ) == KeyEvent.VK_DOWN )
                    synchronized (redQ) {
                        redQ.add ( new MyKeyEvent ( e , true ) );
                        System.out.println ( "Action added" );
                    }
                if ( e.getKeyCode ( ) == KeyEvent.VK_W || e.getKeyCode ( ) == KeyEvent.VK_S )
                    synchronized (blueQ) {
                        blueQ.add ( new MyKeyEvent ( e , true ) );
                        System.out.println ( "Action added" );

                    }
            }

            @Override
            public void keyReleased ( KeyEvent e ) {
                System.out.println ( "KeyReleased " + e.getKeyCode ( ) );

                if ( e.getKeyCode ( ) == KeyEvent.VK_UP || e.getKeyCode ( ) == KeyEvent.VK_DOWN )
                    synchronized (redQ) {
                        redQ.add ( new MyKeyEvent ( e , false ) );
                        System.out.println ( "Action added" );
                    }
                if ( e.getKeyCode ( ) == KeyEvent.VK_W || e.getKeyCode ( ) == KeyEvent.VK_S )
                    synchronized (blueQ) {
                        blueQ.add ( new MyKeyEvent ( e , false ) );
                        System.out.println ( "Action added" );

                    }
            }
        } );
        redThread = new Thread ( red );
        blueThread = new Thread ( blue );
        //ballThread = new Thread ( ball );
        System.out.println ( "MainPanel size: " + this.getSize ( ) );

    }

    public void run ( ) {
        if (!redThread.isAlive ())
        redThread.start ( );
        if (!blueThread.isAlive ())
        blueThread.start ( );
        //ballThread.start ( );
        long lasTime = System.nanoTime ( );
        double amountOfTicks = 60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (score.redScore < playUntil && score.blueScore <playUntil) {
            ball.resetPosition ( );
            red.resetPosition ( );
            blue.resetPosition ( );
            delta = 0;
            System.out.println ( ball.ballState );
            while (ball.ballState == Ball.BallState.MOVED) {

                long now = System.nanoTime ( );
                delta += (now - lasTime) / ns;
                lasTime = now;
                if ( delta >= 1 ) {
                    ball.move ( );
                    red.move ( );
                    blue.move ( );
                    repaint ( );
                    delta--;
                }
            }
            score.scored ( ball.ballState == Ball.BallState.RED_SCORE );
            this.rememberScored ( ball.ballState == Ball.BallState.RED_SCORE );
            blue.stop_moving ( );
            red.stop_moving ( );
            repaint ( );

        }
    }

    @Override
    public void paint ( Graphics g ) {
        Image image = createImage ( getWidth ( ) , getHeight ( ) );
        Graphics graphics = image.getGraphics ( );
        draw ( graphics );
        g.drawImage ( image , 0 , 0 , this );
    }

    public void draw ( Graphics g ) {
        //System.out.println ( "Painting MainPanel" );
        ball.draw ( g );
        blue.draw ( g );
        red.draw ( g );
        score.draw ( g );
        Graphics2D g2d = (Graphics2D) g.create ( );

        // Set the stroke of the copy, not the original
        Stroke dashed = new BasicStroke ( 3 , BasicStroke.CAP_BUTT , BasicStroke.JOIN_BEVEL ,
                0 , new float[]{9} , 0 );
        g2d.setStroke ( dashed );

        g2d.setColor ( Color.white.darker ().darker () );
        for (Highlight h :
                lastScored) {
            g2d.draw ( h.racket );
            g2d.drawOval ( h.ball.x,h.ball.y,h.ball.width, h.ball.height );
        }g2d.dispose ();

    }

    private void rememberScored ( boolean blue ) {
        assert lastScored.size ( ) < 4;
        if ( lastScored.size ( ) == 3 ) {
            lastScored.pollLast ( );
        }
        lastScored.addFirst ( new Highlight ( blue ? this.blue.getRect ( ) : red.getRect ( ) , ball.getRect ( ) ) );
    }

    public void resetAll ( ) {
        ball.resetPosition ();
        blue.resetPosition ();
        red.resetPosition ();
        score.resetScore();
        lastScored.clear ();
    }
}

