/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.foi.jDrawing.api;

import java.awt.Color;

/**
 *
 * @author ipusic
 */
public interface Drawing {

    public void KSK(double x0, double y0, double z0, double x1, double y1, double z1, double Vx, double Vy, double Vz);

    public void setTo(double x, double y);

    public void setTo(double x, double y, double z);

    public void lineTo(double x, double y);

    public void lineTo(double x, double y, double z);

    public void setColor(Color c);

    public void trans(MT m);

    public double getXmin();

    public double getXmax();

    public double getYmin();

    public double getYmax();
}
