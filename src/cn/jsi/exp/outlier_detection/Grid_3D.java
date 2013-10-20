package cn.jsi.exp.outlier_detection;

import java.awt.Point;

import javax.vecmath.Point3d;

public class Grid_3D extends Grid{
	private double locZ;
	public static double depth;

	public Grid_3D(double locX, double locY, double locZ) {
		super(locX, locY);
		// TODO Auto-generated constructor stub
		this.locZ=locZ;
	}

	public double getLocZ() {
		return locZ;
	}

	public void setLocZ(double locZ) {
		this.locZ = locZ;
	}

	public static double getDepth() {
		return depth;
	}

	public static void setDepth(double depth) {
		Grid_3D.depth = depth;
	}
	
/*	public static boolean isInGrid(Grid g, Point3d p) {
		if (Grid.isInGrid(g, p)&& Poin) {
			return true;
		} else {
			return false;
		}
	}*/
}
