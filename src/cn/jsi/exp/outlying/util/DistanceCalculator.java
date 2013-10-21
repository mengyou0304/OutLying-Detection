package cn.jsi.exp.outlying.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.detection.Grid;
import cn.jsi.exp.outlying.detection.RaterablePoints;

/**
 * 
 * A Distance Caclulator to count the distance between two points and the points
 * with the grid
 * 
 * @author Yulang RobinMeng
 * 
 */
// TODO It is only useful for 2D,and is not applicable for 3D or moreD.

public abstract class DistanceCalculator {
	private static final Logger log = Logger
			.getLogger(DistanceCalculator.class);

	/**
	 * Comput the distances between two nodes
	 * 
	 * @param p1
	 *            point1
	 * @param p2
	 *            point2
	 * @return the doubled distance
	 */
	public static double computeDistance(RaterablePoints p1, RaterablePoints p2) {
		double a = 0d;
		List<Double> d1 = p1.getLocals();
		List<Double> d2 = p2.getLocals();
		for (int i = 0; i < d1.size(); i++) {
			a += (d2.get(i) - d1.get(i)) * (d2.get(i) - d1.get(i));
		}
		return Math.sqrt(a);
	}

	/**
	 * Compute the distances between a gird with a point
	 * 
	 * @param the
	 *            point
	 * @param the
	 *            grid
	 * @return a doubled value notify the distance.
	 */
	public static double computeDistance(RaterablePoints p, Grid g) {
		if (isInGrid(g, p)) {
			return 0;
		}
		List<Double> d1 = g.getLocals();
		List<Double> d2 = g.getWidths();
		List<Double> d3 = p.getLocals();
		List<Double> nearestPlace=new ArrayList<Double>();
		Double dvalue1,dvalue2,dvalue3;
		Double distance2=0d;
		for(int i=0;i<d1.size();i++){
			dvalue1=d1.get(i);
			dvalue2=dvalue1+d2.get(i);
			dvalue3=d3.get(i);
			Double option1=(dvalue1-dvalue3)*(dvalue1-dvalue3);
			Double option2=(dvalue2-dvalue3)*(dvalue2-dvalue3);
			if(option1>option2)
				option1=option2;
			distance2+=option1;
		}
		return Math.sqrt(distance2);
	}

	public static boolean isInGrid(Grid g, RaterablePoints p) {
		boolean isInGrid = true;
		List<Double> d1 = g.getLocals();
		List<Double> d2 = g.getWidths();
		List<Double> d4 = p.getLocals();
		if (d1.size() != d2.size())
			log.warn("The grid " + g
					+ " is something wrong in data demension settings");
		Double lowest = 0d;
		Double hightest = 0d;
		Double pointlocal = 0d;
		for (int i = 0; i < d1.size(); i++) {
			lowest = d1.get(i);
			hightest = d2.get(i) + lowest;
			pointlocal = d4.get(i);
			if (pointlocal >= lowest && pointlocal <= hightest) {
			} else {
				isInGrid = false;
				return isInGrid;
			}
		}
		return isInGrid;
	}
	public  static void findNearest(List<Grid> grids, RaterablePoints point) {
		double tmpmin=0,curDistance=0;
		Grid tmpGrid = null;
		for (Grid grid : grids) {
			if ((grid.isDensity==false) || (point.getHitGrid().equals(grid))) {
				//TODO == or equals?
				continue;
			} else if (tmpmin==0) {
				tmpGrid=grid;
				tmpmin = DistanceCalculator.computeDistance(point, grid);
			} else {
				curDistance = DistanceCalculator.computeDistance(point, grid);
				if (tmpmin>curDistance) {
					tmpGrid = grid;
				}
				tmpmin = Math.min(curDistance, tmpmin);
			}
		}
		point.setNearestGrid(tmpGrid);
		//return tmpGrid;
	}
}
