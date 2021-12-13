// PaintActivity.java
// This file deals with the draw class which outlines basics that a drawing should have
// CPSC 312-02, Fall 2021
// Programming Assignment #6
// Sources: https://www.youtube.com/watch?v=LqJko2Ln86E followed this video series to create this class
//
// Created by Ethan Bao on 12/09/21
package com.example.basic_tbb;

import android.graphics.Path;
public class Draw {

    public int color;
    public int strokeWidth;
    public Path path;

    public Draw (int color, int strokeWidth, Path path) {
        this.color = color;
        this.strokeWidth = strokeWidth;
        this.path = path;
    }
}
