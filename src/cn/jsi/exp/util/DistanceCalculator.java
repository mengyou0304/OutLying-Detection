package cn.jsi.exp.util;

import java.awt.Point;

import cn.jsi.exp.outlier_detection.Grid;
import cn.jsi.exp.outlier_detection.RaterablePoints;

public abstract class DistanceCalculator {
	public static double computeDistance(RaterablePoints p1, RaterablePoints p2) {
		double a = Math.pow(p1.getX() - p2.getX(), 2) + Math.pow(p1.getY() - p2.getY(), 2);
		return Math.sqrt(a);
	}
	
	public static double computeDistance(RaterablePoints p, Grid g) {
		if (Grid.isInGrid(g, p)) {
			return 0;
		}
		double x = p.getX();
		double y = p.getY();
		double distance=0;
		double leftBound,rightBound,upBound,buttomBound;
		leftBound = g.getLocX();
		rightBound = g.getLocX() + Grid.width;
		buttomBound = g.getLocY();
		upBound = g.getLocY() + Grid.height;
		if (x<leftBound) {
			if (y<buttomBound) {
				distance = computeDistance(p, new RaterablePoints(g.getLocX(),g.getLocY()));
			} else if (y>upBound) {
				distance = computeDistance(p, new RaterablePoints(g.getLocX(),g.getLocY()+Grid.height));
			} else {
				distance = leftBound - x;
			}
		} else if (x>rightBound) {
			if (y<buttomBound) {
				distance = computeDistance(p, new RaterablePoints(g.getLocX()+Grid.width,g.getLocY()));
			} else if (y>upBound) {
				distance = computeDistance(p, new RaterablePoints(g.getLocX()+Grid.width,g.getLocY()+Grid.height));
			} else {
				distance = x - rightBound;
			}
		} else {
			//point is in the range of grid's x span
			if (y<buttomBound) {
				distance = buttomBound - y;
			} else if (y>upBound) {
				distance = y - upBound;
			} else {
				distance = 0;
			}
		}
		return distance;
	}
}
