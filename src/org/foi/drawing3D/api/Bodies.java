package org.foi.drawing3D.api;

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

    public void drawSphere(double r, int m, int n, boolean half);
}