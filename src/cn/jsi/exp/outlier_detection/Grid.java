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
	public static double width,height;
	private double locX,locY;
//	private double density;
	private int numberOfPointsHit=0;
	public Vector<RaterablePoints> hitPoints;
	public boolean isDensity;

	public Grid(double locX, double locY) {
		super();
		this.locX = locX;
		this.locY = locY;
		hitPoints = new Vector<RaterablePoints>();
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
	public static double getWidth() {
		return width;
	}
	public static void setWidth(double width) {
		Grid.width = width;
	}
	public static double getHeight() {
		return height;
	}
	public static void setHeight(double height) {
		Grid.height = height;
	}

/*	public double getDensity() {
		return density;
	}
	public void setDensity(double density) {
		this.density = density;
	}*/
	public int getNumberOfPointsHit() {
		return numberOfPointsHit;
	}
	private void setNumberOfPointsHit(int numberOfPointsHit) {
		this.numberOfPointsHit = numberOfPointsHit;
	}	
	
	public void computeHitPoints(Vector<RaterablePoints> raterablePoints){
		/*
		 * trace the hit points in the vector and assign the number of them to numberofPointsHit
		 */
		for (RaterablePoints raterablePoints2 : raterablePoints) {
			if (isInGrid(this, raterablePoints2)) {
//				System.out.println("hello");
				hitPoints.add(raterablePoints2);
				raterablePoints2.setHitGrid(this);
			}
		}
		setNumberOfPointsHit(hitPoints.size());
	}
	

	public static boolean isInGrid(Grid g, Point p) {
//		System.out.println(p.getX());
//		System.out.println(g.getLocX());
//		System.out.println(width);
		if ((p.getX()>=g.getLocX())&&(p.getX()<=g.getLocX()+width)&&(p.getY()>=g.getLocY())&&(p.getY()<=g.getLocY()+height)) {
			return true;
		} else {
			return false;
		}
	}
}
