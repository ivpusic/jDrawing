/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.implementations;

import java.awt.Color;
import org.foi.jDrawing.api.Bodies;
import org.foi.jDrawing.api.Drawing;
import org.foi.jDrawing.api.MT;

/**
 *
 * @author ipusic
 */
public class Bodies2D implements Bodies {

    private final Drawing drawing;

    public Bodies2D(Drawing drawing) {
        this.drawing = drawing;
    }

    @Override
    public void drawElipse(double x, double y, double width, double height) {
        double phi, step = 0.01, x_c, y_c;

        drawing.setTo(x + width, y);

        for (phi = 0; phi <= 2 * Math.PI; phi += step) {
            x_c = width * Math.cos(phi) + x;
            y_c = height * Math.sin(phi) + y;
            drawing.lineTo(x_c, y_c);
        }
    }

    @Override
    public void drawHalfElipse(double x, double y, double width, double height) {
        double phi, step = 0.01, x_c, y_c;
        drawing.setTo(x, y + height);

        for (phi = Math.PI / 2; phi <= 3 * (Math.PI / 2); phi += step) {
            x_c = width * Math.cos(phi) + x;
            y_c = height * Math.sin(phi) + y;
            drawing.lineTo(x_c, y_c);
        }
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        drawing.setTo(x, y);
        drawing.lineTo(x + width, y);
        drawing.lineTo(x + width, y + height);
        drawing.lineTo(x, y + height);
        drawing.lineTo(x, y);
    }

    @Override
    public void drawCircle(double x, double y, double r) {
        drawElipse(x, y, r, r);
    }

    @Override
    public void drawCoordinateSystem() {
        // drawing coordinate system
        drawing.setColor(Color.BLACK);

        // drawing X
        drawing.setTo(drawing.getXmin(), (drawing.getYmin() + drawing.getYmax()) / 2);
        drawing.lineTo(drawing.getXmax(), (drawing.getYmin() + drawing.getYmax()) / 2);

        // drawing Y
        drawing.setTo((drawing.getXmin() + drawing.getXmax()) / 2, drawing.getYmin());
        drawing.lineTo((drawing.getXmin() + drawing.getXmax()) / 2, drawing.getYmax());
    }

    @Override
    public void drawCubeFromOrigin(double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCube(double x) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCone(double r, double h, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCylinder(double r, double h, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawSphere(double r, int m, int n, boolean half) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCardioid(double x, double y, double r, double cardioidLimit) {

        drawing.setTo(r, 0);
        drawing.setColor(Color.RED);
        double phi, step = 0.01, x_c = 0, y_c = 0;
        for (phi = 0; phi <= cardioidLimit; phi += step) {
            x_c = r * (2 * Math.cos(phi) - Math.cos(2 * phi));
            y_c = r * (2 * Math.sin(phi) - Math.sin(2 * phi));
            drawing.lineTo(x_c, y_c);
        }

        MT mt = new MT2D();
        mt.move(x_c, y_c);
        drawing.trans(mt);
        drawCircle(0, 0, 0.1);
    }

    @Override
    public void draw3DFlatGround(double size) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawTruncatedCone(double r1, double r2, double h, double n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawRectangle(double x, double y, double z, double width, double height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawFan(double x, double y, double rSmall, double width, double rotation) {
        MT mt = new MT2D();
        mt.move(x, y);
        drawing.trans(mt);
        drawCircle(0, 0, rSmall);
        for (int i = 1; i <= 3; i++) {
            mt.identity();
            mt.move(x, y);
            mt.rotate(rotation + (120 * i));
            drawing.trans(mt);
            drawHalfElipse(0, 0, width, rSmall);
        }
    }

    @Override
    public void drawHalfCicleAircraftWindow(double x, double y, double r) {
        double phi, step = 0.5, x_c, y_c;
        drawing.setTo(x, y);

        for (phi = 0 + (Math.PI / 10); phi < Math.PI - (Math.PI / 10); phi += step) {
            x_c = r * Math.cos(phi) + x;
            y_c = r * Math.sin(phi) + y;
            drawing.lineTo(x_c, y_c);
            drawing.lineTo(x, y);
            drawing.setTo(x_c, y_c);
        }
    }

    @Override
    public void drawObliqueCone(double r, double topX, double topY, double h, double n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawFan(double x, double y, double z, double rSmall, double width, double rotation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawEllipsoid(double w, double h, int m, int n) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawHelix(double w, double h, int cicles) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawRectangleWithGrid(double x, double y, double z, double width, double height) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawRectangle(double width, double height, double thickness) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawWheel(double x, double y, double z, double r, double space, double n, double rotation) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void drawCircle(double x, double y, double z, double r) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
