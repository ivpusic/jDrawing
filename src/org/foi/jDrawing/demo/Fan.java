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
public final class Fan extends Applet {

    private Drawing gks;
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
                    rotation += 1;
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
        mt = new MT2D();
        bodies = new Bodies2D(gks);
        setBackground(Color.YELLOW);
        rotation = 360;
        new Animation(30, 20).start();
    }

    @Override
    public void paint(Graphics g) {

        Image picture = createImage(500, 500);
        Graphics gsPicture = picture.getGraphics();
        gsPicture.setColor(Color.RED);

        gks = new GKS(gsPicture, -20, 20, -20, 20, 500, 500);
        bodies = new Bodies2D(gks);
        mt.identity();
        gks.trans(mt);
        bodies.drawCoordinateSystem();
        bodies.drawCircle(0, 0, 0.2);

        for (int i = 1; i <= 3; i++) {
            mt.identity();
            mt.rotate(rotation + (120 * i));
            gks.trans(mt);
            bodies.drawElipse(5.5, 0, 6, 1);
        }
        g.drawImage(picture, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
