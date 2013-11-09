package org.foi.drawing3D.implementations;

import org.foi.drawing3D.api.Drawing3D;
import java.awt.Graphics;
import java.awt.Color;
import org.foi.drawing3D.api.MT;

public class Persp implements Drawing3D {

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

    private final double d;

    private final Graphics g;
    private final double matrix[][];
    private double matrix_camera[][];
    private final MT3D mt;

    public Persp(Graphics g, double xmin, double xmax, double ymin, double ymax, double d, int xsize, int ysize) {
        this.xmin = xmin;
        this.xmax = xmax;
        this.ymin = ymin;
        this.ymax = ymax;
        this.xsize = xsize;
        this.ysize = ysize;
        this.d = d;
        this.g = g;
        this.currentPenX = 0.0;
        this.currentPenY = 0.0;
        this.matrix = new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        this.matrix_camera = new double[][]{{1, 0, 0, 0}, {0, 1, 0, 0}, {0, 0, 1, 0}, {0, 0, 0, 1}};
        this.mt = new MT3D();
        calculateScaleValues();
        calculateShifts();
    }

    public double[][] calculateUnitVector(double v[][]) {
        double result[][] = new double[3][1];
        double N_size = calculateVectorSize(v);
        result[0][0] = v[0][0] / N_size;
        result[1][0] = v[1][0] / N_size;
        result[2][0] = v[2][0] / N_size;

        return result;
    }

    public double calculateVectorSize(double v[][]) {
        return Math.sqrt(Math.pow(v[0][0], 2) + Math.pow(v[1][0], 2) + Math.pow(v[2][0], 2));
    }

    public double[][] vectorMultiplication(double a[][], double b[][]) {
        double result[][] = new double[3][1];
        result[0][0] = a[1][0] * b[2][0] - a[2][0] * b[1][0];
        result[1][0] = -(a[0][0] * b[2][0] - a[2][0] * b[0][0]);
        result[2][0] = a[0][0] * b[1][0] - a[1][0] * b[0][0];
        return result;
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

    public double[][] adjustCoordinatesByMatrix(double x, double y, double z) {
        double res[][] = mt.matrixMultiplication(matrix, new double[][]{{x}, {y}, {z}, {1}});
        res = mt.matrixMultiplication(matrix_camera, res);
        return res;
    }

    @Override
    public void KSK(double x0, double y0, double z0, double x1, double y1, double z1, double Vx, double Vy, double Vz) {

        double n[][] = {{x0 - x1}, {y0 - y1}, {z0 - z1}};
        n = calculateUnitVector(n);
        double V[][] = {{Vx}, {Vy}, {Vz}};

        double u[][];
        u = vectorMultiplication(V, n);
        u = calculateUnitVector(u);

        double v[][] = vectorMultiplication(n, u);

        double mat1[][] = {{u[0][0], u[1][0], u[2][0], 0}, {v[0][0], v[1][0], v[2][0], 0}, {n[0][0], n[1][0], n[2][0], 0}, {0, 0, 0, 1}};
        double mat2[][] = {{1, 0, 0, -x0}, {0, 1, 0, -y0}, {0, 0, 1, -z0}, {0, 0, 0, 1}};
        matrix_camera = mt.matrixMultiplication(mat1, mat2);
    }

    @Override
    public void setTo(double x, double y, double z) {

        double adjusted[][] = adjustCoordinatesByMatrix(x, y, z);
        x = -(d / adjusted[2][0]) * adjusted[0][0];
        y = -(d / adjusted[2][0]) * adjusted[1][0];

        currentPenX = calculateDisplayX(x);
        currentPenY = calculateDisplayY(y);
    }

    @Override
    public void lineTo(double x, double y, double z) {

        double adjusted[][] = adjustCoordinatesByMatrix(x, y, z);
        x = -(d / adjusted[2][0]) * adjusted[0][0];
        y = -(d / adjusted[2][0]) * adjusted[1][0];

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
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
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
}
