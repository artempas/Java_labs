package com.miet.pin23.pasechnik;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GraphPanel extends JPanel {



    public GraphPanel (  ArrayList<Integer> xData , ArrayList<Long> yDataA , ArrayList<Long> yDataL ) {

        this.setLayout ( new BorderLayout ( ) );
        this.setPreferredSize ( new Dimension ( 500 , 400 ) );
        this.setBackground ( Color.bg );



        GraphComponent graphComponent = new GraphComponent ( xData,yDataA,yDataL );
        graphComponent.setBorder ( BorderFactory.createLineBorder ( Color.border,3 ));
        this.add (  graphComponent  );


    }
}
