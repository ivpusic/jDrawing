/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.implementations;

import org.foi.jDrawing.api.Drawing;
import org.foi.jDrawing.api.Bodies;
import java.awt.Color;

/**
 *
 * @author ipusic
 */
public class Bodies3D implements Bodies {

    private final Drawing drawing3D;

    public Bodies3D(Drawing drawing3D) {
        this.drawing3D = drawing3D;
    }

    @Override
    public void drawCubeFromOrigin(double x) {
        double i;

        // left : right
        for (i = 0; i <= x; i += x) {
            drawing3D.setTo(i, 0, 0);
            drawing3D.lineTo(i, 0, x);
            drawing3D.lineTo(i, x, x);
            drawing3D.lineTo(i, x, 0);
            drawing3D.lineTo(i, 0, 0);
        }

        // botton : top
        for (i = 0; i <= x; i += x) {
            drawing3D.setTo(0, i, 0);
            drawing3D.lineTo(0, i, x);
            drawing3D.lineTo(x, i, x);
            drawing3D.lineTo(x, i, 0);
            drawing3D.lineTo(0, i, 0);
        }

        // front : rear
        for (i = 0; i <= x; i += x) {
            drawing3D.setTo(0, 0, i);
            drawing3D.lineTo(0, x, i);
            drawing3D.lineTo(x, x, i);
            drawing3D.lineTo(x, 0, i);
            drawing3D.lineTo(0, 0, i);
        }
    }

    @Override
    public void drawCube(double x) {

        double xmin = -(x / 2);
        double xmax = x / 2;
        double i;

        // left : right
        for (i = xmin; i <= xmax; i += x) {
            drawing3D.setTo(i, xmin, xmin);
            drawing3D.lineTo(i, xmin, xmax);
            drawing3D.lineTo(i, xmax, xmax);
            drawing3D.lineTo(i, xmax, xmin);
            drawing3D.lineTo(i, xmin, xmin);
        }

        // botton : top
        for (i = xmin; i <= xmax; i += x) {
            drawing3D.setTo(xmin, i, xmin);
            drawing3D.lineTo(xmin, i, xmax);
            drawing3D.lineTo(xmax, i, xmax);
            drawing3D.lineTo(xmax, i, xmin);
            drawing3D.lineTo(xmin, i, xmin);
        }

        // front : rear
        for (i = xmin; i <= xmax; i += x) {
            drawing3D.setTo(xmin, xmin, i);
            drawing3D.lineTo(xmin, xmax, i);
            drawing3D.lineTo(xmax, xmax, i);
            drawing3D.lineTo(xmax, xmin, i);
            drawing3D.lineTo(xmin, xmin, i);
        }
    }

    @Override
    public void drawCoordinateSystem() {
        // drawing coordinate system
        drawing3D.setColor(Color.BLACK);

        // drawing X
        System.out.println((drawing3D.getXmin() + drawing3D.getXmax()) / 2);
        drawing3D.setColor(Color.RED);
        drawing3D.setTo(drawing3D.getXmin() / 2, 0, 0);
        drawing3D.lineTo(drawing3D.getXmax() / 2, 0, 0);

        // drawing Y
        drawing3D.setColor(Color.GREEN);
        drawing3D.setTo(0, drawing3D.getYmin() / 2, 0);
        drawing3D.lineTo(0, drawing3D.getYmax() / 2, 0);

        // drawing Z
        drawing3D.setColor(Color.BLUE);
        drawing3D.setTo(0, 0, drawing3D.getYmin() / 2);
        drawing3D.lineTo(0, 0, drawing3D.getYmax() / 2);
    }

    @Override
    public void drawCone(double r, double h, int n) {
        double phi;
        double step = (2.0 * Math.PI) / n;
        double x;
        double y;

        drawing3D.setTo(r * Math.cos(0), r * Math.sin(0), 0);
        for (phi = 0; phi <= 2.0 * Math.PI + step; phi += step) {
            x = r * Math.cos(phi);
            y = r * Math.sin(phi);
            drawing3D.lineTo(x, y, 0);
            drawing3D.lineTo(0, 0, h);
            drawing3D.setTo(x, y, 0);
        }
    }

    @Override
    public void drawCylinder(double r, double h, int n) {
        double phi;
        double step = (2.0 * Math.PI) / n;
        double x;
        double y;

        drawing3D.setTo(r * Math.cos(0), r * Math.sin(0), 0);
        for (phi = 0; phi <= 2.0 * Math.PI + step; phi += step) {
            x = r * Math.cos(phi);
            y = r * Math.sin(phi);
            drawing3D.lineTo(x, y, 0);
            drawing3D.setTo(x, y, h);
            drawing3D.lineTo(x, y, h);
            drawing3D.lineTo(x, y, 0);
        }

        drawing3D.setTo(r * Math.cos(0), r * Math.sin(0), h);
        for (phi = 0; phi <= 2.0 * Math.PI + step; phi += step) {
            x = r * Math.cos(phi);
            y = r * Math.sin(phi);
            drawing3D.lineTo(x, y, h);
        }
    }

    @Override
    public void drawSphere(double r, int m, int n, boolean half) {
        double phi;
        double step = 0.1;
        double x;
        double y;
        double z;
        double Rho;
        double boundMultiplier = 2;
        if (half) {
            boundMultiplier = 1;
        }

        for (double Theta = 0; Theta <= Math.PI; Theta += Math.PI / n) {
            z = r * Math.cos(Theta);
            Rho = r * Math.sin(Theta);
            drawing3D.setTo(Rho * Math.cos(0), Rho * Math.sin(0), z);
            for (phi = 0; phi <= boundMultiplier * Math.PI + step; phi += step) {
                x = Rho * Math.cos(phi);
                y = Rho * Math.sin(phi);
                drawing3D.lineTo(x, y, z);
            }
        }
        for (phi = 0; phi <= Math.PI; phi += Math.PI / m) {

            drawing3D.setTo(0, 0, r);
            for (double Theta = 0; Theta <= boundMultiplier * Math.PI + step; Theta
                    += step) {
                x = r * Math.sin(Theta) * Math.cos(phi);
                y = r * Math.sin(Theta) * Math.sin(phi);
                z = r * Math.cos(Theta);
                drawing3D.lineTo(x, y, z);
            }
        }
    }

    @Override
    public void drawCircle(double x, double y, double r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawRectangle(double x, double y, double z, double width, double height) {
        for (double i = x; i < width; i += width / 10) {
            drawing3D.setTo(i, y, z);
            drawing3D.lineTo(i, y + height, z);
            drawing3D.lineTo(i, y, z);
        }
        for (double i = y; i < height; i += height / 5) {
            drawing3D.setTo(x, i, z);
            drawing3D.lineTo(x + width, i, z);
            drawing3D.lineTo(x, i, z);
        }

    }

    @Override
    public void drawElipse(double x, double y, double width, double height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCardioid(double x, double y, double r, double cardioidLimit) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void draw3DFlatGround(double size) {
        for (double i = -size / 2; i <= size / 2; i = i + 0.5) {
            drawing3D.setTo(size / 2, i, 0);
            drawing3D.lineTo(-size / 2, i, 0);

            drawing3D.setTo(i, size / 2, 0);
            drawing3D.lineTo(i, -size / 2, 0);
        }
    }

    @Override
    public void drawTruncatedCone(double r1, double r2, double h, double n) {
        double phi;
        double step = (2.0 * Math.PI) / n;
        double x, x1;
        double y, y1;

        drawing3D.setTo(r1 * Math.cos(0), r1 * Math.sin(0), 0);
        for (phi = 0; phi <= 2.0 * Math.PI + step; phi += step) {
            x = r1 * Math.cos(phi);
            y = r1 * Math.sin(phi);

            x1 = r2 * Math.cos(phi);
            y1 = r2 * Math.sin(phi);
            drawing3D.lineTo(x, y, 0);
            drawing3D.setTo(x1, y1, h);
            drawing3D.lineTo(x, y, 0);
        }

        drawing3D.setTo(r2 * Math.cos(0), r2 * Math.sin(0), h);
        for (phi = 0; phi <= 2.0 * Math.PI + step; phi += step) {
            x = r2 * Math.cos(phi);
            y = r2 * Math.sin(phi);
            drawing3D.lineTo(x, y, h);
        }
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
