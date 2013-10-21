package cn.jsi.exp.outlier_detection;

import java.awt.Point;
import java.util.*;

import cn.jsi.exp.util.DistanceCalculator;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public abstract class OutlierRater {
	
	
	public static Vector<RaterablePoints> getOutlierRank(Vector<RaterablePoints> raterablePoints, Vector<Grid> grids ) {
		/* USED FOR DEBUG		
		 * java.util.List<RaterablePoints> raterablePoints2 = new ArrayList<RaterablePoints>();
		 *
		RaterablePoints rpPoints = new RaterablePoints();
		raterablePoints2.add(rpPoints);
		Collections.sort(raterablePoints2);*/
		preparer(raterablePoints, grids);
		for (RaterablePoints raterablepoint : raterablePoints) {
			scorer(raterablepoint);
		}
		Collections.sort(raterablePoints);
		return raterablePoints;
	}
	
	public static void scorer(RaterablePoints raterablePoints) {
		Grid nearest = raterablePoints.getNearestGrid();
		double score=0;
		for (RaterablePoints hitpoint : nearest.hitPoints) {
			score+=DistanceCalculator.computeDistance(raterablePoints, hitpoint);
		}
		score/=nearest.getNumberOfPointsHit();
		raterablePoints.setScore(score);
	}
	
	private static void findNearest(Vector<Grid> grids, RaterablePoints point) {
		double tmpmin=0,curDistance=0;
		Grid tmpGrid = null;
		for (Grid grid : grids) {
			if ((grid.isDensity==false) || (point.getHitGrid()==grid)) {
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
	
	private static void preparer(Vector<RaterablePoints> raterablePoints, Vector<Grid> grids) {
		for (RaterablePoints raterablepoint : raterablePoints) {
			findNearest(grids, raterablepoint);
		}
	}
	
}
