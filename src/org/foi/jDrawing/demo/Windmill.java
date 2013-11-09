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
public class Windmill extends Applet implements KeyListener {

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
                    //stupanj += 0.03;
                    rotation += 5;
                    if (degree > Math.PI * 2) {
                        //zK = zK + 4;
                        //stupanj = 0;
                    }
                    repaint();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Windmill.class.getName()).log(Level.SEVERE, null, ex);
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

        drawing3D.KSK(xK * Math.cos(degree), yK * Math.sin(degree), zK, 0, 0, 0, 0, 0, 5);

        drawing3D.setColor(Color.green);
        for (double i = -10; i <= 10; i = i + 0.5) {
            drawing3D.setTo(10, i, 0);
            drawing3D.lineTo(-10, i, 0);

            drawing3D.setTo(i, 10, 0);
            drawing3D.lineTo(i, -10, 0);
        }

        drawing3D.setColor(Color.BLACK);

        bodies.drawCone(2, 5, 20);
        mt.move(0, 0, 3.5);
        mt.rotateZ(-rotation);
        drawing3D.trans(mt);
        bodies.drawCylinder(0.5, 1.5, 10);

        mt.identity();
        mt.move(0, 0, 4.5);
        mt.rotateX(-90);

        for (int i = 1; i <= 3; i++) {
            drawing3D.setColor(Color.red);
            mt.rotateY(((120 * i) + rotation));
            if (i > 1) {
                mt.rotateY(-((120 * (i - 1)) + rotation));
            }
            drawing3D.trans(mt);
            bodies.drawCylinder(0.2, 3, 5);
            mt.move(0, 0, 4);
            mt.rotateZ(-90);
            drawing3D.trans(mt);
            bodies.drawSphere(1, 5, 10, true);
            mt.move(0, 0, -4);
            mt.rotateZ(90);
            drawing3D.trans(mt);
        }
        g.drawImage(slika, 0, 0, null);
    }

    @Override
    public void update(Graphics g) {
        paint(g);
    }
}