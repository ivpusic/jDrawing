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
public class Windmill_2 extends Applet implements KeyListener {

    int xSize;
    int ySize;
    double degree = 0;
    double rotation = 0;
    double xK = 20;
    double zK = 0;

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
                    rotation += 5;
                    if (xK >= 0) {
                        degree += 0.01;
                        zK += 0.09;
                        xK -= 0.1;
                    }

                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Windmill_2.class.getName()).log(Level.SEVERE, null, ex);
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

        Image picture = createImage(xSize, ySize);
        Graphics gs = picture.getGraphics();

        MT mt = new MT3D();
        Drawing drawing3D = new Persp(gs, xMin, xMax, yMin, yMax, 20, xSize, ySize);
        Bodies bodies = new Bodies3D(drawing3D);
        drawing3D.KSK(xK, 0, zK, 0, 0, 0, 0, degree, 1);

        //bodies.drawCoordinateSystem();

        drawing3D.setColor(Color.GREEN);
        bodies.drawTruncatedCone(5, 3, 1, 50);

        drawing3D.setColor(Color.BLACK);
        mt.move(0, 0, 1);
        drawing3D.trans(mt);
        bodies.drawCylinder(3, 5, 40);

        drawing3D.setColor(Color.RED);
        mt.move(0, 0, 5);
        drawing3D.trans(mt);
        bodies.drawCone(3, 2, 40);

        double rectHeight = 3;
        double rectWidth = 0.5;
        drawing3D.setColor(Color.BLUE);

        mt.identity();
        mt.move(3, 0, 4);
        mt.rotateY(90);
        mt.rotateZ(rotation);
        drawing3D.trans(mt);
        bodies.drawRectangle(0, 0, 0, rectHeight, rectWidth);

        mt.rotateZ(180);
        drawing3D.trans(mt);
        bodies.drawRectangle(0, 0, 0, rectHeight, rectWidth);

        mt.rotateZ(90);
        drawing3D.trans(mt);
        bodies.drawRectangle(0, 0, 0, rectHeight, rectWidth);

        mt.rotateZ(180);
        drawing3D.trans(mt);
        bodies.drawRectangle(0, 0, 0, rectHeight, rectWidth);

        g.drawImage(picture, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}
