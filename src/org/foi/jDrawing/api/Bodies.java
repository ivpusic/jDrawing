package org.foi.jDrawing.api;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author ipusic
 */
public interface Bodies {

    public void drawCubeFromOrigin(double x);

    public void drawCube(double x);

    public void drawCoordinateSystem();

    public void drawCone(double r, double h, int n);

    public void drawCylinder(double r, double h, int n);

    public void drawCircle(double x, double y, double r);
    
    public void drawRectangle(double x, double y, double width, double height);
    
    public void drawElipse(double x, double y, double width, double height);

    public void drawSphere(double r, int m, int n, boolean half);
}