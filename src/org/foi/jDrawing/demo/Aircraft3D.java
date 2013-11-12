/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.demo;

import org.foi.jDrawing.api.Drawing;
import org.foi.jDrawing.api.Bodies;
import java.applet.Applet;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.foi.jDrawing.implementations.Bodies3D;
import org.foi.jDrawing.implementations.MT3D;
import org.foi.jDrawing.implementations.Persp;
import org.foi.jDrawing.api.MT;

/**
 *
 * @author ipusic
 */
public class Aircraft3D extends Applet implements KeyListener {

    int xSize;
    int ySize;
    double degree = 0;
    double rotation = 0;
    double zK = 6;

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == 37) {
            degree += 0.2;
            repaint();
        } else if (key == 39) {
            degree -= 0.2;
            repaint();
        } else if (key == 38) {
            zK += 0.5;
            repaint();
        } else if (key == 40) {
            zK -= 0.5;
            repaint();
        }
    }

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
                    rotation += 25;
                    if (degree < 150) {
                        degree += 1;
                    } else {
                        degree = 0;
                    }
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Aircraft3D.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void init() {
        this.addKeyListener(this);
        setBackground(Color.WHITE);
        (new Animation(30.0, 30.0)).start();
    }

    @Override
    public void paint(Graphics g) {
        xSize = getWidth();
        ySize = getHeight();

        double xMin = -20.0;
        double xMax = 20.0;
        double yMin = -20.0;
        double yMax = 20.0;

        Image slika = createImage(xSize, ySize);
        Graphics gs = slika.getGraphics();

        MT mt = new MT3D();
        Drawing drawing3D = new Persp(gs, xMin, xMax, yMin, yMax, 20, xSize, ySize);
        Bodies bodies = new Bodies3D(drawing3D);

        drawing3D.KSK(-6, -100 + degree, -5, 0, 0, 0, 0, 0, 1);

        mt.rotateX(-90);
        drawing3D.trans(mt);
        drawing3D.setColor(Color.green);
//        bodies.drawCoordinateSystem();

        drawing3D.setColor(Color.BLACK);
        bodies.drawObliqueCone(0.8, 0, 1, 5, 40);

        mt.rotateX(180);
        drawing3D.trans(mt);
        bodies.drawObliqueCone(0.8, 0, -0.2, 2, 40);

        drawing3D.setColor(Color.BLUE);
        mt.move(-2, 0.8, -0.5);
        mt.rotateX(90);
        drawing3D.trans(mt);
        bodies.drawRectangle(0, 0, 0, 4, 1);

        drawing3D.setColor(Color.BLACK);
        mt.identity();
        mt.move(1, -0.5, 0.7);
        mt.rotateX(-90);
        drawing3D.trans(mt);
        bodies.drawCone(0.2, 1, 30);

        mt.move(-2, 0, 0);
        drawing3D.trans(mt);
        bodies.drawCone(0.2, 1, 30);
        drawing3D.setColor(Color.BLACK);

        drawing3D.setColor(Color.RED);
        mt.move(1, 1, 0);
        drawing3D.trans(mt);
        bodies.drawFan(1, 0.8, 0.5, 0.1, 0.5, rotation);
        bodies.drawFan(-1, 0.8, 0.5, 0.1, 0.5, rotation);

        drawing3D.setColor(Color.BLUE);
        mt.identity();
        drawing3D.trans(mt);
        bodies.drawRectangle(-1, 3.5, -0.5, 2, 0.5);

        mt.rotateY(90);
        mt.rotateX(90);
        drawing3D.trans(mt);
        bodies.drawRectangle(0, -0.25, -3.5, 0.5, 0.5);
        g.drawImage(slika, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
