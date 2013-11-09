/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.drawing3D.implementations;

import org.foi.drawing3D.api.Drawing3D;
import org.foi.drawing3D.api.Bodies;
import java.awt.Color;

/**
 *
 * @author ipusic
 */
public class Bodies3D implements Bodies {

    private final Drawing3D drawing3D;

    public Bodies3D(Drawing3D drawing3D) {
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
        drawing3D.setTo(drawing3D.getXmin(), (drawing3D.getYmin() + drawing3D.getYmax()) / 2, 1);
        drawing3D.lineTo(drawing3D.getXmax(), (drawing3D.getYmin() + drawing3D.getYmax()) / 2, 1);

        // drawing Y
        drawing3D.setTo((drawing3D.getXmin() + drawing3D.getXmax()) / 2, drawing3D.getYmin(), 1);
        drawing3D.lineTo((drawing3D.getXmin() + drawing3D.getXmax()) / 2, drawing3D.getYmax(), 1);
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
}
