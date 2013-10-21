package cn.jsi.exp.outlying.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Vector;

import cn.jsi.exp.outlying.detection.Grid;
import cn.jsi.exp.outlying.detection.OutlierDetector;
import cn.jsi.exp.outlying.detection.OutlierRater;
import cn.jsi.exp.outlying.detection.RaterablePoints;
import cn.jsi.exp.outlying.detection.SpaceDivider;

public class Test {

	public static void main(String[] args) {
		// First we init the divider to divide the space into pieces
		SpaceDivider spaceDivider = new SpaceDivider();
		// Second we input the historical data to count the hitpoints of the girds
		List<RaterablePoints> historicalData = getHistoricalData();
		spaceDivider.inputPoint(historicalData);

		List<Grid> grids = spaceDivider.getCurrentGrids();
		List<RaterablePoints> possibleOutlierPoints = OutlierDetector
				.findOutOutlier(grids);
		//??
		List<RaterablePoints> Outlier = OutlierRater.getOutlierRank(
				possibleOutlierPoints, grids);
		for (RaterablePoints raterablePoints2 : Outlier) {
			System.out.println(raterablePoints2.getScore());
		}
	}

	private static List<RaterablePoints> getHistoricalData() {
		ArrayList<RaterablePoints> pointList = new ArrayList<RaterablePoints>();
		Random r = new Random();
		for (int i = 0; i < 100; i++) {
			ArrayList<Double> p1local = new ArrayList<Double>();
			p1local.add(Double.parseDouble(String.valueOf(r.nextInt(20))));
			p1local.add(Double.parseDouble(String.valueOf(r.nextInt(20))));
			p1local.add(Double.parseDouble(String.valueOf(r.nextInt(20))));
			RaterablePoints p1 = new RaterablePoints(p1local);
			pointList.add(p1);
		}
		for (int i = 0; i < 100; i++) {
			ArrayList<Double> p1local = new ArrayList<Double>();
			p1local.add(Double.parseDouble(String.valueOf(40 + r.nextInt(20))));
			p1local.add(Double.parseDouble(String.valueOf(40 + r.nextInt(20))));
			p1local.add(Double.parseDouble(String.valueOf(40 + r.nextInt(20))));
			RaterablePoints p1 = new RaterablePoints(p1local);
			pointList.add(p1);
		}
		//max to 4 outlying point
		for (int i = 0; i < 4; i++) {
			ArrayList<Double> p1local = new ArrayList<Double>();
			p1local.add(Double.parseDouble(String.valueOf(r.nextInt(100))));
			p1local.add(Double.parseDouble(String.valueOf(r.nextInt(100))));
			p1local.add(Double.parseDouble(String.valueOf(r.nextInt(100))));
			RaterablePoints p1 = new RaterablePoints(p1local);
			pointList.add(p1);
		}

		return pointList;
	}

	// private static void manualDataGenerator(Vector<RaterablePoints>
	// raterablePoints) {
	// double x=0.1,y=0.1;
	// for (int i = 0; i < 10; i++) {
	// RaterablePoints newPoint = new RaterablePoints(x, y);
	// raterablePoints.add(newPoint);
	// x+=0.1;
	// y+=0.1;
	// }
	// raterablePoints.add(new RaterablePoints(2.5, 2.5));
	// raterablePoints.add(new RaterablePoints(3.5, 3.5));
	// }

}
