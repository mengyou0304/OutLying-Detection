package cn.jsi.exp.outlying.detection;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.setting.SystemParameters;
import cn.jsi.exp.outlying.util.DistanceCalculator;
import cn.jsi.exp.outlying.util.NearestGridFinder;

/**
 * Methods should be or can be changed in the future.
 * 
 * @author Yulang Robin_P4
 * 
 */
public class DetectMethods {
	private static final Logger log = Logger.getLogger(DetectMethods.class);

	/**
	 * This is the basic method to detect the outlying data it only compare the
	 * point a grid hold with the threshold
	 * 
	 * @param gridlist
	 * @param densityThreshold
	 * @return
	 */
	public static List<DataPoint> findOutOutlierBasedonThreshold(
			List<Grid> gridlist) {
		List<DataPoint> possibleOutliers = new ArrayList<DataPoint>();
		for (Grid g : gridlist) {
			int hitNumber = g.getNumberOfPointsHit();
			if (hitNumber >= SystemParameters.densityThreshold)
				g.setDensity(true);
			else {
				g.setDensity(false);
				for (DataPoint point : g.getHitPoints()) {
					possibleOutliers.add(point);
					log.debug("point.size=" + point.getLocals().size());
				}
			}
		}
		return possibleOutliers;
	}

	/**
	 * Judge the weight of a grid according to the points in it.
	 * 
	 * @param gridList
	 */
	public static void computGridWeight(List<Grid> gridList) {
		for (Grid grid : gridList) {
			grid.setWeight(grid.getNumberOfPointsHit());
		}
	}

	/**
	 * Judge the weight of a point according the nearest grid and other grids
	 * near it. I think there are at least two points should be considered. The
	 * weight of the nearest grid and other top-k nearest grids.
	 * 
	 * @param gridList
	 * @param pointList
	 */
	public static void computPointWeight(List<DataPoint> pointList) {
		Double score = 0d;
		for (DataPoint point : pointList) {
			NearestGridFinder gridfinder = new NearestGridFinder();
			List<Grid> gridList = gridfinder.getNLevelNearestGridList(
					SystemParameters.nearGridLevel, point);
			for (Grid grid : gridList) {
				score += grid.getNumberOfPointsHit() / grid.getDistanceWeight();
			}
			point.setScore(score);
			score = 0d;
		}
	}

	public static void computPointWeight2(List<Grid> possibleGridList,
			List<DataPoint> pointList) {
		Double score = 0d;
		for (DataPoint point : pointList) {
			NearestGridFinder gridfinder = new NearestGridFinder();
			log.info("start finding grids for point: "+point);
			List<Grid> gridList = gridfinder.getNLevelNearestGridList(
					SystemParameters.nearGridLevel, point);
			int usedGrid=0;
			for (Grid grid : gridList) {
				if(grid.getDistanceWeight()==0)
					continue;
				score += grid.getNumberOfPointsHit() / grid.getDistanceWeight();
				log.debug("score+="+grid.getNumberOfPointsHit()+"/"+grid.getDistanceWeight()+"="+score);
				usedGrid++;
			}
			log.info("usedGrid is "+usedGrid);
			point.setScore(score);
			score = 0d;
		}
	}

	public static Double computDistanceBetweenTwoGrid(
			List<Integer> gridlocalX1, List<Integer> gridlocalX2) {
		Double distance2 = 0d;
		for (int i = 0; i < gridlocalX1.size(); i++) {
			distance2 =distance2+ (gridlocalX1.get(i) - gridlocalX2.get(i))
					* (gridlocalX1.get(i) - gridlocalX2.get(i)) * 1d;
		}
		return (distance2);

	}

}
