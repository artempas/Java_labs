package lab7;

import javax.swing.*;
import java.awt.*;

public class Frame extends JFrame {
static MainPanel mainPanel = new MainPanel ();
    Frame ( ) {
        //super("Ping Pong");
        this.add ( mainPanel );
        this.setTitle ( "Ping Pong" );
        this.setResizable ( false );
        this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
        this.pack ( );
        this.setVisible ( true );
        this.setBackground ( Color.black );

        //this.setSize ( new Dimension ( mainPanel.WINDOW_WIDTH , mainPanel.WINDOW_HEIGHT ) );
        this.setLocationRelativeTo ( null );
    }

    public static void main ( String[] args ) {
        Frame myFrame = new Frame ( );
        while (true){
            MainPanel.frameThread=new Thread ( mainPanel );
            String inp = JOptionPane.showInputDialog ( myFrame, "До скольких будем играть?", "Max score", JOptionPane.QUESTION_MESSAGE );
            if (inp==null)                    break;
            int maxScore;
            try {
                maxScore = Integer.parseInt ( inp );
            }
            catch (NumberFormatException e){continue;}
            mainPanel.playUntil=maxScore;
            MainPanel.frameThread.start ();
            try {
                mainPanel.frameThread.join ( );
                mainPanel.resetAll();
            }catch (InterruptedException ignored){}
        }
        myFrame.dispose ();

    }
}
