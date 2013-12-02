package cn.jsi.exp.outlying.detection;

import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.util.DistanceCalculator;

/**
 * A scorcc mechnsm to
 * 
 * @author Yulang Robin
 * 
 */
public  class OutlierDetector {
	private static final Logger log = Logger.getLogger(OutlierDetector.class);

	public static List<DataPoint> getOutlierRank(List<DataPoint> pointList,
			List<Grid> gridList) {
		DetectMethods.computGridWeight(gridList);
//		for(Grid g:gridList){
//			log.debug(g.toString()+":   "+g.getNumberOfPointsHit());
//		}
//		System.out.println("=================");
//		DetectMethods.computPointWeight2(gridList,pointList);
		DetectMethods.computPointWeight3(gridList,pointList);
		Collections.sort(pointList);
		return pointList;
	}

}
