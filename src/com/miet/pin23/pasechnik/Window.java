package com.miet.pin23.pasechnik;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Window extends JFrame {

    private static Font customFont = null;


    public Window ( windowType type , ArrayList<Integer> xData , ArrayList<Long> yDataA , ArrayList<Long> yDataL ) {
        try {
                customFont = Font.createFont ( Font.TRUETYPE_FONT , new File ( getClass ().getResource ( "Montserrat-Medium.ttf" ).getPath ()) );
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ( );
                ge.registerFont ( customFont );
            } catch (IOException | FontFormatException e) {
                e.printStackTrace ( );
            }



        this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
        this.setVisible ( true );
        this.setResizable ( false );
        this.setLayout ( new BoxLayout (this.getContentPane (),BoxLayout.Y_AXIS ) );

        JPanel topPanel = new JPanel ( );
        topPanel.setPreferredSize ( new Dimension ( 500 , 40 ) );
        topPanel.setBackground ( Color.bg);

        JLabel topLabel = new JLabel ( );
        topLabel.setVerticalTextPosition ( JLabel.CENTER );
        topLabel.setFont ( customFont.deriveFont ( 24f ) );
        topLabel.setForeground ( Color.txt);
        topLabel.setText ( ((type == windowType.ADD) ? "Add Graph" : "Remove graph") );

        Border border = BorderFactory.createLineBorder ( Color.border, 3 );
        topPanel.setBorder ( border );
        topPanel.add ( topLabel );
        this.add ( topPanel);

        JPanel lowPanel = new JPanel();
        lowPanel.setPreferredSize(new Dimension(500, 40));
        JLabel lowLabel = new JLabel();
        lowLabel.setFont(customFont.deriveFont ( 15f ));
        lowLabel.setForeground( Color.txt);
        lowPanel.setBackground ( Color.bg );
        lowLabel.setText("Array List's graphic is red, Linked List's - green");
        lowPanel.add(lowLabel);



        this.add ( new GraphPanel ( xData , yDataA , yDataL ));
        this.add ( lowPanel);
        this.setTitle ( (type == windowType.ADD ? "Add Graph" : "Remove graph") );
        this.pack ( );
    }

    public Window ( windowType type ) {

        try {
                customFont = Font.createFont ( Font.TRUETYPE_FONT , new File ( getClass ().getResource ( "Montserrat-Medium.ttf" ).getPath ()) );
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ( );
                ge.registerFont ( customFont );
            } catch (IOException | FontFormatException e) {
                e.printStackTrace ( );
            }


        if ( type == windowType.LOADING ) {
            this.setDefaultCloseOperation ( EXIT_ON_CLOSE );
            this.setSize ( 500 , 500 );
            this.setVisible ( true );
            this.setResizable ( false );
            JPanel panel = new JPanel ( );
            panel.setBackground ( Color.bg );
            JLabel topLabel = new JLabel ( );
            topLabel.setVerticalTextPosition ( JLabel.CENTER );
            



            topLabel.setFont ( customFont.deriveFont ( 24f ) );
            topLabel.setForeground ( Color.txt );
            topLabel.setText ( "Loading, please wait" );
            panel.add ( topLabel );
            this.add ( panel );
            this.pack ( );
        }
    }
}
