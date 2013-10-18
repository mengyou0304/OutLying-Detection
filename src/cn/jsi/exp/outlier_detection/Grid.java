package cn.jsi.exp.outlier_detection;

import java.awt.Point;
import java.util.Vector;

/**
 * the grid that solution space is divided into.
 * loc defines the bottom left point of the grid
 * 
 * 
 * @author yulang
 */
public class Grid {
	private static int width,height;
	private double locX,locY;
//	private double density;
	private int numberOfPointsHit;
	private Vector<Point> hitPoints;

	public Grid(double locX, double locY) {
		super();
		this.locX = locX;
		this.locY = locY;
		hitPoints = new Vector<Point>();
	}
	public double getLocX() {
		return locX;
	}
	public void setLocX(double locX) {
		this.locX = locX;
	}
	public double getLocY() {
		return locY;
	}
	public void setLocY(double locY) {
		this.locY = locY;
	}
	public static int getWidth() {
		return width;
	}
	public static void setWidth(int width) {
		Grid.width = width;
	}
	public static int getHeight() {
		return height;
	}
	public static void setHeight(int height) {
		Grid.height = height;
	}

/*	public double getDensity() {
		return density;
	}
	public void setDensity(double density) {
		this.density = density;
	}*/
	private int getNumberOfPointsHit() {
		return numberOfPointsHit;
	}
	private void setNumberOfPointsHit(int numberOfPointsHit) {
		this.numberOfPointsHit = numberOfPointsHit;
	}	
	
	private void computeHitPoints(Point[] pArray){
		/*
		 * trace the hit points in the vector and assign the number of them to numberofPointsHit
		 */
		for (int i = 0; i < pArray.length; i++) {
			if (isInGrid(this, pArray[i])) {
				hitPoints.add(pArray[i]);
			}
		}
		setNumberOfPointsHit(hitPoints.size());
	}
	

	private static boolean isInGrid(Grid g, Point p) {
		if ((p.x>=g.getLocX())&&(p.x<=g.getLocX()+width)&&(p.y>=g.getLocY())&&(p.x<=g.getLocY()+height)) {
			return true;
		} else {
			return false;
		}
	}
}