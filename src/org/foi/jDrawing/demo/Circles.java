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
import static java.lang.Thread.sleep;
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
public class Circles extends Applet {

    private Drawing gks;
    private Bodies bodies;
    public double cardioidLimit;
    public double x_c, y_c, phi = 0;

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

                    if (cardioidLimit <= 2 * Math.PI) {
                        cardioidLimit += 0.02;
                    }

                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Fan.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void init() {
        gks = new GKS(getGraphics(), -12, 12, -12, 12, 300, 300);
        bodies = new Bodies2D(gks);
        setBackground(Color.YELLOW);
        cardioidLimit = 0;
        new Animation(30, 20).start();
    }

    @Override
    public void paint(Graphics g) {

        Image picture = createImage(500, 500);
        Graphics gsPicture = picture.getGraphics();
        gsPicture.setColor(Color.RED);

        gks = new GKS(gsPicture, -5, 5, -5, 5, 500, 500);
        bodies = new Bodies2D(gks);

        bodies.drawCoordinateSystem();
        gks.setColor(Color.BLUE);
        bodies.drawCircle(0, 0, 1.5);
        bodies.drawCardioid(0, 0, 1.5, cardioidLimit);

        gks.setColor(Color.BLUE);
        MT2D mt = new MT2D();
        mt.rotate(Math.toDegrees(cardioidLimit));
        mt.move(3, 0);
        gks.trans(mt);
        bodies.drawCircle(0, 0, 1.5);

        g.drawImage(picture, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}