package cn.jsi.exp.outlying.detection;

import java.awt.Point;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.jsi.exp.outlying.setting.SystemConst;
import cn.jsi.exp.outlying.util.DistanceCalculator;

/**
 * the grid that solution space is divided into. loc defines the bottom left
 * point of the grid
 * 
 * 
 * @author yulang RobinMeng
 */
public class Grid {
	public static List<Double> locals;
	public static List<Double> widths;
	private int numberOfPointsHit = 0;
	public List<RaterablePoints> hitPoints;
	public boolean isDensity;

	public Grid() {
		locals = new ArrayList<Double>();
		widths = new ArrayList<Double>();
		hitPoints = new ArrayList<RaterablePoints>();
	}

	public Grid(List<Double> localvalues) {
		locals=localvalues;
		widths = new ArrayList<Double>();
		for(int i=0;i<locals.size();i++){
			widths.add(SystemConst.divideLength);
		}
		hitPoints = new ArrayList<RaterablePoints>();
	}

	public static List<Double> getLocals() {
		return locals;
	}

	public static void setLocals(List<Double> locals) {
		Grid.locals = locals;
	}

	public static List<Double> getWidths() {
		return widths;
	}

	public static void setWidths(List<Double> widths) {
		Grid.widths = widths;
	}

	private void setNumberOfPointsHit(int numberOfPointsHit) {
		this.numberOfPointsHit = numberOfPointsHit;
	}
	@Deprecated 
	/**
	 * TOO SLOW
	 * @param raterablePoints
	 */
	public void computeHitPoints(Vector<RaterablePoints> raterablePoints) {
		/*
		 * trace the hit points in the vector and assign the number of them to
		 * numberofPointsHit
		 */
		for (RaterablePoints raterablePoints2 : raterablePoints) {
			if (DistanceCalculator.isInGrid(this, raterablePoints2)) {
				// System.out.println("hello");
				hitPoints.add(raterablePoints2);
				raterablePoints2.setHitGrid(this);
			}
		}
		setNumberOfPointsHit(hitPoints.size());
	}
	public int getNumberOfPointsHit(){
		return hitPoints.size();
	}

	public void addPoint(RaterablePoints point) {
		hitPoints.add(point);
		
	}
}
