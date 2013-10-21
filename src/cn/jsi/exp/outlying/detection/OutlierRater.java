package cn.jsi.exp.outlying.detection;

import java.awt.Point;
import java.util.*;

import cn.jsi.exp.outlying.util.DistanceCalculator;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import java.util.List;

public abstract class OutlierRater {
	
	
	public static List<RaterablePoints> getOutlierRank(List<RaterablePoints> raterablePoints, List<Grid> grids ) {
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
//		System.out.println("nearest is"+nearest);
		for (RaterablePoints hitpoint : nearest.hitPoints) {
			score+=DistanceCalculator.computeDistance(raterablePoints, hitpoint);
		}
		score/=nearest.getNumberOfPointsHit();
		raterablePoints.setScore(score);
	}
	
	
	
	private static void preparer(List<RaterablePoints> raterablePoints, List<Grid> grids) {
		for (RaterablePoints raterablepoint : raterablePoints) {
			DistanceCalculator.findNearest(grids, raterablepoint);
		}
	}
	
}
