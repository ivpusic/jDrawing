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
public class Tractor3D extends Applet implements KeyListener {

    int xSize;
    int ySize;
    double degree = Math.toDegrees(90);
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
        System.out.println(key);
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
                    if (rotation > -340) {
                        rotation -= 5;
                    }
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Tractor3D.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void init() {
        this.addKeyListener(this);
        setBackground(Color.yellow);
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

        double xK = 15;
        double yK = 15;

        drawing3D.KSK(xK * Math.cos(degree), yK * Math.sin(degree), zK, 0, 0, 0, 0, 0, 1);
        //bodies.drawCoordinateSystem();
        drawing3D.setColor(Color.GREEN);
        mt.move(0, 0, -2);
        drawing3D.trans(mt);
        bodies.draw3DFlatGround(20);
        drawing3D.setColor(Color.green);

        mt.identity();
        mt.move(rotation / 50, 0, 0);
        drawing3D.trans(mt);

        drawing3D.setColor(Color.RED);
        bodies.drawRectangle(5, 2, 2);
        mt.identity();
        mt.move(rotation / 50, 0, 0);
        mt.move(2, 0, 0.5);
        drawing3D.trans(mt);
        bodies.drawRectangle(2.5, 4, 3);

        drawing3D.setColor(Color.BLUE);
        mt.identity();
        drawing3D.trans(mt);
        bodies.drawWheel(1.5, 2.5, -1, 1, 0.5, 10, rotation);
        bodies.drawWheel(1.5, -2, -1, 1, 0.5, 10, rotation);

        bodies.drawWheel(-1, 1.5, -1, 1, 0.5, 10, rotation);
        bodies.drawWheel(-1, -1, -1, 1, 0.5, 10, rotation);

        drawing3D.setColor(Color.BLACK);
        mt.identity();
        mt.move(rotation / 50, 0, 0);
        mt.move(-1, 2, -1);
        mt.rotateX(90);
        drawing3D.trans(mt);
        bodies.drawCylinder(0.1, 4, 5);

        mt.move(2.5, 0, 0);
        drawing3D.trans(mt);
        bodies.drawCylinder(0.1, 4, 5);

        g.drawImage(slika, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
