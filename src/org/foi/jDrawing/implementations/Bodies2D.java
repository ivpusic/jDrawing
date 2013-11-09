/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.implementations;

import java.awt.Color;
import org.foi.jDrawing.api.Bodies;
import org.foi.jDrawing.api.Drawing;

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
        drawing.setColor(Color.red);
        drawing.setTo(width * Math.cos(0), height * Math.sin(0));
        for (phi = 0; phi <= 2.0 * Math.PI + step; phi += step) {
            x_c = width * Math.cos(phi) + x;
            y_c = height * Math.sin(phi) + y;
            drawing.lineTo(x_c, y_c);
        }
    }

    @Override
    public void drawRectangle(double x, double y, double width, double height) {
        drawing.setColor(Color.BLACK);
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
}