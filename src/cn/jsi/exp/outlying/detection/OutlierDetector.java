package cn.jsi.exp.outlying.detection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import cn.jsi.exp.outlying.setting.SystemParameters;

public abstract class OutlierDetector {
//	private Vector<Point> possibleOutlier;
	
	/**
	 * @param g
	 * @param densityThreshold
	 * @return
	 */
	public static List<RaterablePoints> findOutOutlier(List<Grid> g){
		List<RaterablePoints> possibleOutliers = new ArrayList<RaterablePoints>();
		for (Iterator<Grid> iter = g.iterator(); iter.hasNext();) {
			Grid tmpGrid = iter.next();
			if (tmpGrid.getNumberOfPointsHit()>=SystemParameters.densityThreshold) {
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
