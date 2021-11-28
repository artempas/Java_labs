package com.miet.pin23.pasechnik;

import java.awt.color.ColorSpace;

public class Color extends java.awt.Color {
    public static final java.awt.Color bg=new java.awt.Color ( 44, 62, 80);
    public static final java.awt.Color txt=new java.awt.Color ( 236, 240, 241);
    public static final java.awt.Color border=new java.awt.Color (41, 128, 185);
    public static final java.awt.Color graph1=new java.awt.Color (192, 57, 43);
    public static final java.awt.Color graph2=new java.awt.Color (39, 174, 96);


    public Color ( int r , int g , int b ) {
        super ( r , g , b );
    }

    public Color ( int r , int g , int b , int a ) {
        super ( r , g , b , a );
    }

    public Color ( int rgb ) {
        super ( rgb );
    }

    public Color ( int rgba , boolean hasalpha ) {
        super ( rgba , hasalpha );
    }

    public Color ( float r , float g , float b ) {
        super ( r , g , b );
    }

    public Color ( float r , float g , float b , float a ) {
        super ( r , g , b , a );
    }

    public Color ( ColorSpace cspace , float[] components , float alpha ) {
        super ( cspace , components , alpha );
    }
}
