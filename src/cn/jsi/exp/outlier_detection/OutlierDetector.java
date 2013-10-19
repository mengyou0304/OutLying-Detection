package cn.jsi.exp.outlier_detection;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Vector;

public abstract class OutlierDetector {
//	private Vector<Point> possibleOutlier;
	
	/**
	 * @param g
	 * @param densityThreshold
	 * @return
	 */
	public static Vector<Point> findOutOutlier(Vector<Grid> g, int densityThreshold){
		Vector<Point> possibleOutliers = new Vector<Point>();
		for (Iterator<Grid> iter = g.iterator(); iter.hasNext();) {
			Grid tmpGrid = iter.next();
			if (tmpGrid.getNumberOfPointsHit()>=densityThreshold) {
				//if the number of hit points in the grid is more than threshold, the grid is regarded as density grid
				continue;
			} else {
				//TODO cannot ensure this syntax
				for (Point point : tmpGrid.hitPoints) {
					possibleOutliers.add(point);
				}
			}
		}
		return possibleOutliers;
	}
}
