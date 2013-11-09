/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.api;

/**
 *
 * @author ipusic
 */
public interface MT {

    public void move(double px, double py);
    
    public void move(double px, double py, double pz);

    public void scale(double sx, double sy);
    
    public void scale(double sx, double sy, double sz);

    public void mirrorX();
    
    public void mirrorY();
    
    public void rotateX(double kut);

    public void rotateY(double kut);

    public void rotateZ(double kut);
    
    public void rotate(double angle);

    public void rotate(double x1, double y1, double z1, double x2, double y2, double z2, double kut);

    public void identity();

    public double[][] matrixMultiplication(double matrix_one[][], double matrix_two[][]);
    
    public double[][] getMatrix();
}
