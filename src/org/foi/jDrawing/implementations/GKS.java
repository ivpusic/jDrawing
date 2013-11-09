package org.foi.jDrawing.implementations;

import java.awt.Graphics;
import java.awt.Color;
import org.foi.jDrawing.api.Drawing;
import org.foi.jDrawing.api.MT;

public class GKS implements Drawing {

    public double xmin;
    public double xmax;

    public double ymin;
    public double ymax;

    private double sx;
    private double sy;

    private int xsize;
    private int ysize;

    private double currentPenX;
    private double currentPenY;

    private double px;
    private double py;

    private Graphics g;
    private double matrix[][];
    private MT2D mt2d;

    public GKS(Graphics g, double xmin, double xmax, double ymin, double ymax, int xsize, int ysize) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.xsize = xsize;
        this.ysize = ysize;
        this.g = g;
        this.currentPenX = 0.0;
        this.currentPenY = 0.0;
        this.matrix = new double[][]{{1, 0, 0}, {0, 1, 0}, {0, 0, 1}};
        this.mt2d = new MT2D();
        calculateScaleValues();
        calculateShifts();
    }

    private void calculateScaleValues() {
        sx = xsize / (xmax - xmin);
        sy = ysize / (ymax - ymin);
    }

    private void calculateShifts() {
        px = -sx * xmin;
        py = sy * ymax;
    }

    private double calculateDisplayX(double x) {
        return sx * x + px;
    }

    private double calculateDisplayY(double y) {
        return -sy * y + py;
    }

    public double[][] adjustCoordinatesByMatrix(double x, double y) {
        return mt2d.matrixMultiplication(this.matrix, new double[][]{{x}, {y}, {1}});
    }

    @Override
    public void setTo(double x, double y) {

        double adjusted[][] = this.adjustCoordinatesByMatrix(x, y);
        x = adjusted[0][0];
        y = adjusted[1][0];

        currentPenX = calculateDisplayX(x);
        currentPenY = calculateDisplayY(y);
    }

    @Override
    public void lineTo(double x, double y) {

        double adjusted[][] = this.adjustCoordinatesByMatrix(x, y);
        x = adjusted[0][0];
        y = adjusted[1][0];

        g.drawLine((int) currentPenX, (int) currentPenY, (int) calculateDisplayX(x), (int) calculateDisplayY(y));
        currentPenX = calculateDisplayX(x);
        currentPenY = calculateDisplayY(y);
    }

    @Override
    public void setColor(Color c) {
        g.setColor(c);
    }

    @Override
    public void trans(MT m) {
        double mtMatrix[][] = m.getMatrix();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                matrix[i][j] = mtMatrix[i][j];
            }
        }
    }

    @Override
    public double getXmin() {
        return xmin;
    }

    @Override
    public double getXmax() {
        return xmax;
    }

    @Override
    public double getYmin() {
        return ymin;
    }

    @Override
    public double getYmax() {
        return ymax;
    }

    @Override
    public void KSK(double x0, double y0, double z0, double x1, double y1, double z1, double Vx, double Vy, double Vz) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTo(double x, double y, double z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void lineTo(double x, double y, double z) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
