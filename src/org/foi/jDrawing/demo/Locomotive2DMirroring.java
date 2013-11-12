package org.foi.jDrawing.demo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import org.foi.jDrawing.api.Bodies;
import org.foi.jDrawing.api.Drawing;
import org.foi.jDrawing.api.MT;
import org.foi.jDrawing.implementations.Bodies2D;
import org.foi.jDrawing.implementations.GKS;
import org.foi.jDrawing.implementations.MT2D;

/**
 *
 * @author ipusic
 */
public final class Locomotive2DMirroring extends Applet {

    private Drawing gks;
    private MT mt;
    private Bodies bodies;

    @Override
    public void init() {
        gks = new GKS(getGraphics(), -12, 12, -12, 12, 300, 300);
        mt = new MT2D();
        bodies = new Bodies2D(gks);
        setBackground(Color.YELLOW);
    }

    @Override
    public void paint(Graphics g) {

        mt.identity();
        gks.trans(mt);

        this.drawCircle(2, 0, 1);
        this.drawCircle(6, 0, 1);
        this.drawRectangle(0, 0, 4, 3);
        this.drawRectangle(4, 0, 4, 6);
        this.drawRectangle(5, 2.5, 2, 2.5);

        this.gks.setColor(Color.RED);
        this.gks.setTo(-5, -9);
        this.gks.lineTo(5, 21);

        mt.move(0, 6);
        mt.rotate(Math.toDegrees(Math.atan(3)));
        mt.mirrorX();
        mt.rotate(-Math.toDegrees(Math.atan(3)));
        mt.move(0, -6);

        gks.trans(mt);
        gks.setColor(Color.BLUE);
        
        bodies.drawCircle(2, 0, 1);
        bodies.drawCircle(6, 0, 1);
        bodies.drawRectangle(0, 0, 4, 3);
        bodies.drawRectangle(4, 0, 4, 6);
        bodies.drawRectangle(5, 2.5, 2, 2.5);
    }

    public void drawRectangle(double x, double y, double width, double height) {
        gks.setColor(Color.BLACK);
        gks.setTo(x, y);
        gks.lineTo(x + width, y);
        gks.lineTo(x + width, y + height);
        gks.lineTo(x, y + height);
        gks.lineTo(x, y);

    }

    public void drawCircle(double x, double y, double r) {
        double phi, step = 0.01, x_c, y_c;
        gks.setColor(Color.BLACK);
        gks.setTo(r * Math.cos(0), r * Math.sin(0));
        for (phi = 0; phi <= 2.0 * Math.PI + step; phi += step) {
            x_c = r * Math.cos(phi) + x;
            y_c = r * Math.sin(phi) + y;
            gks.lineTo(x_c, y_c);
        }
    }
}