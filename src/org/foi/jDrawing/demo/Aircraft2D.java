/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.demo;

import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.util.logging.Level;
import java.util.logging.Logger;
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
public final class Aircraft2D extends Applet {

    private Drawing drawing;
    private MT mt;
    private Bodies bodies;
    public int rotation;

    class Animation extends Thread {

        private final long pause;
        private final long end;

        public Animation(double fps, double duration) {
            pause = Math.round(1000.0 / fps);
            end = Math.round(duration * fps);
        }

        @Override
        public void run() {
            long counter = 0;
            while (counter++ < end) {
                try {
                    sleep(pause);
                    rotation += 5;
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Aircraft2D.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void init() {
        drawing = new GKS(getGraphics(), -12, 12, -12, 12, 300, 300);
        mt = new MT2D();
        bodies = new Bodies2D(drawing);
        setBackground(Color.YELLOW);
        rotation = 360;
        new Animation(30, 20).start();
    }

    @Override
    public void paint(Graphics g) {

        Image picture = createImage(500, 500);
        Graphics gsPicture = picture.getGraphics();
        gsPicture.setColor(Color.RED);

        drawing = new GKS(gsPicture, -20, 20, -20, 20, 500, 500);
        bodies = new Bodies2D(drawing);
        mt.identity();
        drawing.trans(mt);
        //bodies.drawCoordinateSystem();

        drawing.setColor(Color.BLUE);
        bodies.drawCircle(0, 0, 5);

        mt.move(0, 5);
        drawing.trans(mt);

        drawing.setTo(-20, 0);
        drawing.lineTo(20, 0);

        drawing.setTo(0, 10);
        drawing.lineTo(0, 0);

        mt.identity();
        mt.move(0, 3);
        drawing.trans(mt);
        drawing.setColor(Color.BLACK);
        bodies.drawCircle(-15, 0, 2);
        bodies.drawCircle(15, 0, 2);
        
        drawing.setColor(Color.RED);
        bodies.drawFan(15, 3, 0.5, 4, rotation);
        bodies.drawFan(-15, 3, 0.5, 4, rotation);

        drawing.setColor(Color.BLUE);
        mt.identity();
        drawing.trans(mt);
        bodies.drawHalfCicleAircraftWindow(0, 0, 4);
        
        g.drawImage(picture, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
