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
	public static Vector<RaterablePoints> findOutOutlier(Vector<Grid> g, int densityThreshold){
		Vector<RaterablePoints> possibleOutliers = new Vector<RaterablePoints>();
		for (Iterator<Grid> iter = g.iterator(); iter.hasNext();) {
			Grid tmpGrid = iter.next();
			if (tmpGrid.getNumberOfPointsHit()>=densityThreshold) {
				//if the number of hit points in the grid is more than threshold, the grid is regarded as density grid
				tmpGrid.isDensity=true;
				continue;
			} else {
				//TODO cannot ensure this syntax
				tmpGrid.isDensity=false;
				for (RaterablePoints point : tmpGrid.hitPoints) {
					possibleOutliers.add(point);
				}
			}
		}
		return possibleOutliers;
	}
}
