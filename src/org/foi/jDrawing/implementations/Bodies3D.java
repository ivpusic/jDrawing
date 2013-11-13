/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.implementations;

import org.foi.jDrawing.api.Drawing;
import org.foi.jDrawing.api.Bodies;
import java.awt.Color;
import org.foi.jDrawing.api.MT;

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
        drawRectangle(x, x, x);
    }

    @Override
    public void drawCoordinateSystem() {
        // drawing coordinate system
        drawing3D.setColor(Color.BLACK);

        // drawing X
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
        drawObliqueCone(r, 0, 0, h, n);
    }

    @Override
    public void drawObliqueCone(double r, double topX, double topY, double h, double n) {
        double phi;
        double step = (2.0 * Math.PI) / n;
        double x;
        double y;

        drawing3D.setTo(r * Math.cos(0), r * Math.sin(0), 0);
        for (phi = 0; phi <= 2.0 * Math.PI + step; phi += step) {
            x = r * Math.cos(phi);
            y = r * Math.sin(phi);
            drawing3D.lineTo(x, y, 0);
            drawing3D.lineTo(topX, topY, h);
            drawing3D.setTo(x, y, 0);
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
            drawing3D.lineTo(0, 0, h);
            drawing3D.setTo(x, y, h);
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
    public void drawElipse(double x, double y, double width, double height) {
        double phi, step = 0.01, x_c, y_c;

        //drawing.setTo(width * Math.cos(0), height * Math.sin(0));
        drawing3D.setTo(x + width, y, 0);

        for (phi = 0; phi <= 2 * Math.PI; phi += step) {
            x_c = width * Math.cos(phi) + x;
            y_c = height * Math.sin(phi) + y;
            drawing3D.lineTo(x_c, y_c, 0);
        }
    }

    @Override
    public void drawRectangleWithGrid(double x, double y, double z, double width, double height) {
        for (double i = x; i < x + width; i += width / 10) {
            drawing3D.setTo(i, y, z);
            drawing3D.lineTo(i, y + height, z);
            drawing3D.lineTo(i, y, z);
        }
        for (double i = y; i < y + height; i += height / 5) {
            drawing3D.setTo(x, i, z);
            drawing3D.lineTo(x + width, i, z);
            drawing3D.lineTo(x, i, z);
        }
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
    public void drawHalfElipse(double x, double y, double width, double height) {
        double phi, step = 0.01, x_c, y_c;
        drawing3D.setTo(x, y + height, 0);

        for (phi = Math.PI / 2; phi <= 3 * (Math.PI / 2); phi += step) {
            x_c = width * Math.cos(phi) + x;
            y_c = height * Math.sin(phi) + y;
            drawing3D.lineTo(x_c, y_c, 0);
        }
    }

    @Override
    public void drawFan(double x, double y, double z, double rSmall, double width, double rotation) {
        MT mt = new MT3D();
        mt.rotateX(90);
        mt.move(x, y, z);
        drawing3D.trans(mt);
        drawElipse(0, 0, rSmall, rSmall);
        for (int i = 1; i <= 3; i++) {
            mt.identity();
            mt.rotateX(90);
            mt.move(x, y, z);
            mt.rotateZ(rotation + (120 * i));
            drawing3D.trans(mt);
            drawHalfElipse(0, 0, width, rSmall);
        }
    }

    @Override
    public void drawHalfCicleAircraftWindow(double x, double y, double r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCircle(double x, double y, double z, double r) {
        double phi, step = 0.01, x_c, y_c;

        drawing3D.setTo(x + r, y, z);

        for (phi = 0; phi <= 2 * Math.PI; phi += step) {
            x_c = r * Math.cos(phi) + x;
            y_c = r * Math.sin(phi) + y;
            drawing3D.lineTo(x_c, y_c, z);
        }
    }

    @Override
    public void drawFan(double x, double y, double rSmall, double width, double rotation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCylinder(double r, double h, int n) {
        drawTruncatedCone(r, r, h, n);
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawEllipsoid(double w, double h, int m, int n) {
        double phi;
        double step = 0.1;
        double x;
        double y;
        double z;
        double Rho;
        double boundMultiplier = 2;

        // pararels
        for (double Theta = 0; Theta <= Math.PI; Theta += Math.PI / n) {
            z = h * Math.cos(Theta);
            Rho = w * Math.sin(Theta);
            drawing3D.setTo(Rho * Math.cos(0), Rho * Math.sin(0), z);
            for (phi = 0; phi <= boundMultiplier * Math.PI + step; phi += step) {
                x = Rho * Math.cos(phi);
                y = Rho * Math.sin(phi);
                drawing3D.lineTo(x, y, z);
            }
        }

        // meridians
        for (phi = 0; phi <= Math.PI; phi += Math.PI / m) {

            drawing3D.setTo(0, 0, h);
            for (double Theta = 0; Theta <= boundMultiplier * Math.PI + step; Theta
                    += step) {
                x = w * Math.sin(Theta) * Math.cos(phi);
                y = w * Math.sin(Theta) * Math.sin(phi);
                z = h * Math.cos(Theta);
                drawing3D.lineTo(x, y, z);
            }
        }
    }

    @Override
    public void drawHelix(double w, double h, int cicles) {
        double x;
        double y;
        double z;
        drawing3D.setTo(0, 0, 0);
        for (double i = 0; i <= 2 * h * cicles; i += 0.01) {
            x = w * Math.cos(i);
            y = w * Math.sin(i);
            z = i / cicles;
            drawing3D.lineTo(x, y, z);
        }
    }

    @Override
    public void drawRectangle(double width, double height, double thickness) {
        double xmin = -(width / 2);
        double xmax = width / 2;

        double ymin = -(height / 2);
        double ymax = height / 2;

        double zmin = -(thickness / 2);
        double zmax = thickness / 2;

        double i;

        // front : rear
        for (i = zmin; i <= zmax; i += thickness) {
            drawing3D.setTo(xmin, ymin, i);
            drawing3D.lineTo(xmax, ymin, i);
            drawing3D.lineTo(xmax, ymax, i);
            drawing3D.lineTo(xmin, ymax, i);
            drawing3D.lineTo(xmin, ymin, i);
        }

        // left : right
        for (i = xmin; i <= xmax; i += width) {
            drawing3D.setTo(i, ymin, zmin);
            drawing3D.lineTo(i, ymax, zmin);
            drawing3D.lineTo(i, ymax, zmax);
            drawing3D.lineTo(i, ymin, zmax);
            drawing3D.lineTo(i, ymin, zmin);
        }
    }

    @Override
    public void drawWheel(double x, double y, double z, double r, double space, double n, double rotation) {

        MT mt = new MT3D();
        mt.move(rotation / 50, 0, 0);
        mt.move(x, y, z);
        mt.rotateX(90);
        drawing3D.trans(mt);
        drawCircle(0, 0, 0, r);
        drawCircle(0, 0, space, r);

        for (double i = rotation; i < 2 * Math.PI + rotation; i += Math.PI / n) {
            mt.identity();
            mt.move(rotation / 50, 0, 0);
            mt.move(x, y, z);
            mt.rotateX(90);
            mt.rotateZ(Math.toDegrees(i));
            mt.rotateX(90);
            mt.move(-r, 0, 0);
            drawing3D.trans(mt);
            drawRectangle(0, 0, 0, r, space);
        }
    }

    @Override
    public void drawCircle(double x, double y, double r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawRectangle(double x, double y, double z, double width, double height) {
        drawing3D.setTo(x, y, z);
        drawing3D.lineTo(x + width, y, z);
        drawing3D.lineTo(x + width, y + height, z);
        drawing3D.lineTo(x, y + height, z);
        drawing3D.lineTo(x, y, z);
    }
}
