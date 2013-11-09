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
public final class MT3D implements MT {

    public double matrix[][];

    public MT3D() {
        matrix = new double[4][4];
        this.identity();
    }

    @Override
    public void move(double px, double py, double pz) {
        double mat[][] = {{1, 0, 0, px}, {0, 1, 0, py}, {0, 0, 1, pz}, {0, 0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public void scale(double sx, double sy, double sz) {
        double mat[][] = {{sx, 0, 0, 0}, {0, sy, 0, 0}, {0, 0, sz, 0}, {0, 0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public void rotateX(double kut) {
        kut = Math.toRadians(kut);
        double mat[][] = {{1, 0, 0, 0}, {0, Math.cos(kut), -Math.sin(kut), 0}, {0, Math.sin(kut), Math.cos(kut), 0}, {0, 0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public void rotateY(double kut) {
        kut = Math.toRadians(kut);
        double mat[][] = {{Math.cos(kut), 0, Math.sin(kut), 0}, {0, 1, 0, 0}, {-Math.sin(kut), 0, Math.cos(kut), 0}, {0, 0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    @Override
    public void rotateZ(double kut) {
        kut = Math.toRadians(kut);
        double mat[][] = {{Math.cos(kut), -Math.sin(kut), 0, 0}, {Math.sin(kut), Math.cos(kut), 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        this.matrix = matrixMultiplication(matrix, mat);
    }

    public double calculateA(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (x2 - x1) / Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }

    public double calculateB(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (y2 - y1) / Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }

    public double calculateC(double x1, double y1, double z1, double x2, double y2, double z2) {
        return (z2 - z1) / Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2) + Math.pow(z2 - z1, 2));
    }
    
    public double calculateD(double b, double c) {
        return Math.sqrt(Math.pow(b, 2) + Math.pow(c, 2));
    }

    @Override
    public void rotate(double x1, double y1, double z1, double x2, double y2, double z2, double kut) {
        double a = calculateA(x1, y1, z1, x2, y2, z2);
        double b = calculateB(x1, y1, z1, x2, y2, z2);
        double c = calculateC(x1, y1, z1, x2, y2, z2);
        double d = calculateD(b, c);
        double sinAlfa = b / d;
        double sinBeta = a;
        
        double alfa = Math.toDegrees(Math.asin(sinAlfa));
        double beta = Math.toDegrees(Math.asin(sinBeta));
        
        double mat;
        move(x1, y1, z1);
        rotateX(-alfa);
        rotateY(beta);
        rotateZ(kut);
        rotateY(-beta);
        rotateX(alfa);
        move(-x1, -y1, -z1);
    }

    @Override
    public void identity() {
        double mat[][] = {{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
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

    public void mult(MT3D m) {
        this.matrix = this.matrixMultiplication(this.matrix, m.matrix);
    }
    
    @Override
    public double[][] getMatrix() {
        return matrix;
    }

    @Override
    public void move(double px, double py) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void scale(double sx, double sy) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mirrorX() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void mirrorY() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void rotate(double angle) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}