/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.implementations;

import org.foi.jDrawing.api.MT;

/**
 *
 * @author ipusic
 */
public class MT2D implements MT {

    public double matrix[][];

    public MT2D() {
        matrix = new double[3][3];
        this.identity();
    }

    @Override
    public void move(double px, double py) {
        double mat[][] = {{1, 0, px}, {0, 1, py}, {0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public void scale(double sx, double sy) {
        double mat[][] = {{sx, 0, 0}, {0, sy, 0}, {0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public void mirrorX() {
        double mat[][] = {{1, 0, 0}, {0, -1, 0}, {0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public void mirrorY() {
        double mat[][] = {{-1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public void rotate(double angle) {
        angle = Math.toRadians(angle);
        double mat[][] = {{Math.cos(angle), -Math.sin(angle), 0}, {Math.sin(angle), Math.cos(angle), 0}, {0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public final void identity() {
        double mat[][] = {{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        this.matrix = mat;
    }

    @Override
    public double[][] matrixMultiplication(double matrix_one[][], double matrix_two[][]) {

        int r1 = matrix_one.length, c1 = matrix_one[0].length, c2 = matrix_two[0].length;
        double result[][] = new double[r1][c2];

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < c1; k++) {
                    result[i][j] += matrix_one[i][k] * matrix_two[k][j];
                }
            }
        }
        return result;
    }

    @Override
    public double[][] getMatrix() {
        return matrix;
    }

    private void mult(MT2D m) {
        this.matrix = this.matrixMultiplication(this.matrix, m.matrix);
    }

    @Override
    public void move(double px, double py, double pz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void scale(double sx, double sy, double sz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateX(double kut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateY(double kut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotateZ(double kut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotate(double x1, double y1, double z1, double x2, double y2, double z2, double kut) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
