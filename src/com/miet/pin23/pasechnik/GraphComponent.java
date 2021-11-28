package com.miet.pin23.pasechnik;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class GraphComponent extends JComponent {
    private final ArrayList<Integer> xData;
    private final ArrayList<Long> yDataA;
    private final ArrayList<Long> yDataL;
    int padX = 50;
    int padY = 30;
    double width = 400;
    double height = 300;
    private static Font customFont = null;


    public GraphComponent ( ArrayList<Integer> xData , ArrayList<Long> yDataA , ArrayList<Long> yDataL ) {
        try {
                customFont = Font.createFont ( Font.TRUETYPE_FONT , new File ( getClass ().getResource ( "Roboto-Light.ttf" ).getPath ()) );
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment ( );
                ge.registerFont ( customFont );
            } catch (IOException | FontFormatException e) {
                e.printStackTrace ( );
            }
        this.xData = xData;
        this.yDataA = yDataA;
        this.yDataL = yDataL;
    }

    @Override
    public void paintComponent ( Graphics g ) {
        Graphics2D g2 = (Graphics2D) g;
        float scaleX = (float) (Math.log10( Collections.max(xData)) / width);
        float scaleY = (float) (Math.max(Collections.max(yDataA),Collections.max ( yDataL )) / height);
        g2.setStroke ( new BasicStroke ( 2 ) );

        g2.setColor ( Color.txt );
        Rectangle2D rect = new Rectangle2D.Double( padX , padY , width, height);
        g2.draw(rect);
        g2.drawString("Elements number", (int) ( width-15), (int) (padY + height + 35));
        g2.drawString("Time, ms", (int) (padX), (int) (padY-10 ));
        g2.setFont ( customFont.deriveFont ( 10f ) );

        for (int i = 0; i < xData.size() - 1; i++) {
            double FirstX;
            double SecondX;

            if (xData.get(i) != 0)
                FirstX = Math.round(Math.log10(xData.get(i)) / scaleX) + padX;
            else
                FirstX = padX;
            SecondX = Math.round(Math.log10(xData.get(i + 1)) / scaleX + padX );

            double FirstY1 = Math.round(height - yDataA.get(i) / scaleY + padY );
            double SecondY1 = Math.round(height - yDataA.get(i + 1) / scaleY + padY );
            double FirstY2 = Math.round(height - yDataL.get(i) / scaleY + padY );
            double SecondY2 = Math.round(height - yDataL.get(i + 1) / scaleY + padY );

            //Рисуем засечки на осях
            //g2.draw(new Line2D.Double(FirstX, height + padY - 10, FirstX, height + padY + 10));
            if (Math.abs ( FirstY1-FirstY2)<10)
                g2.draw(new Line2D.Double( padX - 5, Math.round((FirstY1+FirstY2)/2), padX + 5, Math.round((FirstY1+FirstY2)/2)));
            else {
                g2.draw ( new Line2D.Double ( padX - 5 , FirstY1 , padX + 5 , FirstY1 ) );
                g2.draw ( new Line2D.Double ( padX - 5 , FirstY2 , padX + 5 , FirstY2 ) );
            }
            //Пишем значения
            String messageX = xData.get(i).toString();
            g2.drawString(messageX, (int) FirstX - 5, (int) ((int) height + padY + 20));
            if (Math.abs ( FirstY1-FirstY2)<10)
            g2.drawString ( String.format ( "%d", Math.round((float) (yDataA.get(i)+yDataL.get(i))/2) ) ,padX-30,(int)Math.round((FirstY1+FirstY2)/2));
            else{
            String messageY1 = yDataA.get(i).toString();
            g2.drawString(messageY1, padX - 30, (int) FirstY1);
            String messageY2 = yDataL.get(i).toString();
            g2.drawString(messageY2, padX - 30, (int) FirstY2);}

            //Устанавливаем цвет.
            //Будет применяться для всего содержимого компонента: линий, текстов, заливки фигур
            g2.setColor(Color.graph1 );

            //Рисуем квадраты, обозначающие точки
            g2.fill(new Rectangle2D.Double(FirstX - 3, FirstY1 - 3, 6, 6));

            //Соединяем точки линиями
            g2.draw(new Line2D.Double(FirstX, FirstY1, SecondX, SecondY1));

            //Устанавливаем цвет.
            //Будет применяться для всего содержимого компонента: линий, текстов, заливки фигур
            g2.setColor( Color.graph2);

            //Рисуем квадраты, обозначающие точки
            Rectangle2D rec2 = new Rectangle2D.Double(FirstX - 3, FirstY2 - 3, 6, 6);
            g2.draw(rec2);
            g2.fill(rec2);

            //Соединяем точки линиями
            g2.draw(new Line2D.Double(FirstX, FirstY2, SecondX, SecondY2));

            //Особо обрабатываем последнюю точку
            if (i == xData.size() - 2) {
                g2.setColor( Color.graph1);
                g2.draw(new Rectangle2D.Double(SecondX - 3, SecondY1 - 3, 6, 6));
                g2.fill(new Rectangle2D.Double(SecondX - 3, SecondY1 - 3, 6, 6));
                g2.setColor( Color.graph2);
                rec2 = new Rectangle2D.Double(SecondX - 3, SecondY2 - 3, 6, 6);
                g2.draw(rec2);
                g2.fill(rec2);
                //Возвращаем цвет к значению по умолчанию
                g2.setColor(Color.txt);
//                g2.draw(new Line2D.Double(SecondX, height + padY - 5, SecondX, height + padY + 5));
//                g2.draw(new Line2D.Double( padX - 5, SecondY1, padX + 5, SecondY1));
//                g2.draw(new Line2D.Double( padX - 5, SecondY2, padX + 5, SecondY2));
                messageX = xData.get(i + 1).toString();
                g2.drawString(messageX, (int) SecondX - 15, (int) ((int) height + padY + 20));
                g2.drawString(yDataA.get(i + 1).toString(), (int) ((int) padX - 30), (int) SecondY1);
                g2.drawString(yDataL.get(i + 1).toString(), (int) ((int) padX - 30), (int) SecondY2);
            }

            //Возвращаем цвет к значению по умолчанию
            g2.setColor(Color.txt);

        }
    }
}
