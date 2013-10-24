package cn.jsi.exp.outlying.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.datagen.DataGenerator;
import cn.jsi.exp.outlying.detection.Grid;
import cn.jsi.exp.outlying.detection.DetectMethods;
import cn.jsi.exp.outlying.detection.OutlierDetector;
import cn.jsi.exp.outlying.detection.DataPoint;
import cn.jsi.exp.outlying.detection.SpaceDivider;
import cn.jsi.exp.outlying.setting.SystemParameters;
import cn.jsi.exp.outlying.util.DistanceCalculator;

/**
 * The basic testcase to run the application
 * 
 * @author Robin_P4
 * 
 */
public class Test {
	private static final Logger log = Logger.getLogger(Test.class);

	public List<DataPoint> initData() {
		// init the input data
		List<DataPoint> historicalData = DataGenerator.getHistoricalData();
		log.info("now we are going to insert testing point of number: "
				+ historicalData.size());
		return historicalData;
	}

	public List<DataPoint> level1(List<DataPoint> historicalData) {
		// First we init the divider to divide the space into pieces
		SpaceDivider spaceDivider = SpaceDivider.getInstance();
		// Second we input the historical data to count the hitpoints of the
		// girds
		spaceDivider.inputPoint(historicalData);
		// After input the points into the grids, we should find which girds are
		// filled with points and find the nomral grids and abnormal grids based
		// on the points in it
		List<Grid> grids = spaceDivider.getCurrentGrids();
		log.info("After inserting the data we get grid number as"
				+ grids.size());

		List<DataPoint> possibleOutlierPoints = DetectMethods
				.findOutOutlierBasedonThreshold(grids);
		String s = "They are\n";
		for (DataPoint p : possibleOutlierPoints) {
			s += p + "\n";
		}
		// log.info(s);

		// It seems to the end, but we further discover that some normal point
		// also be treated as abnomal ones due to the reason that they are
		// placed at the bounder of the grid,so we should find them out
		// and treat them as normal points

		List<DataPoint> Outlier = OutlierDetector.getOutlierRank(
				possibleOutlierPoints, grids);
		ArrayList<DataPoint> l2List = new ArrayList<DataPoint>();
		for (DataPoint point : Outlier) {
			System.out.println(" out lyers score:  "
					+ point.getScore().intValue() + "  " + point);
			Double score = point.getScore();
			List<Double> l2D = new ArrayList<Double>();
			l2D.add(score);
			DataPoint p = new DataPoint(l2D);
			p.setPointID(point.getPointID());
			l2List.add(p);
		}
		log.info("==========================Level1 outlyer finish=================");
		return l2List;

	}

	public static void main(String[] args) {
		Test t = new Test();
		List<DataPoint> datalist = t.initData();
		List<DataPoint> datalist2 = t.level1(datalist);
		if (SystemParameters.use2LevelOutlying)
			t.level2(datalist2);

	}

	private void level2(List<DataPoint> datalist2) {
		SpaceDivider spaceDivide = SpaceDivider.newInstance();
		spaceDivide.inputPoint(datalist2);
		List<Grid> grids = spaceDivide.getCurrentGrids();
		log.info("After inserting the data we get grid number as"
				+ grids.size());

		List<DataPoint> possibleOutlierPoints = DetectMethods
				.findOutOutlierBasedonThreshold(grids);
		datalist2.removeAll(possibleOutlierPoints);
		String s = "They are\n";
		for (DataPoint p : datalist2) {
			s += p + "\n";
		}
		log.info(s);
	}

}
