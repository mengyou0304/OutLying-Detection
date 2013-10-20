package cn.jsi.exp.outlier_detection;

import java.util.Vector;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		System.out.println("hello");
		SpaceDivider spaceDivider = new SpaceDivider(1, 1, 4, 4);
		Vector<Grid> grids = spaceDivider.gridGenerator();
/*		for (Grid grid : grids) {
			System.out.println(grid.getLocX());
		}*/
		Vector<RaterablePoints> raterablePoints= new Vector<RaterablePoints>();
		manualDataGenerator(raterablePoints);
/*		System.out.println(raterablePoints.size());
		for (RaterablePoints raterablePoints2 : raterablePoints) {
			System.out.println(raterablePoints2.getX());
		}*/
		for (Grid grid : grids) {
			grid.computeHitPoints(raterablePoints);
			System.out.println(grid.getLocX());
			System.out.println(grid.getNumberOfPointsHit());
		}
		Vector<RaterablePoints> possibleOutlierPoints = OutlierDetector.findOutOutlier(grids, 8);
		Vector<RaterablePoints> Outlier = OutlierRater.getOutlierRank(possibleOutlierPoints, grids);
//		System.out.println("hello");
		for (RaterablePoints raterablePoints2 : Outlier) {
			System.out.println(raterablePoints2.getX());
			System.out.println(raterablePoints2.getScore());
			
		}
	}
	
	private static void manualDataGenerator(Vector<RaterablePoints> raterablePoints) {
		double x=0.1,y=0.1;
		for (int i = 0; i < 10; i++) {
			RaterablePoints newPoint = new RaterablePoints(x, y);
			raterablePoints.add(newPoint);
			x+=0.1;
			y+=0.1;
		}
		raterablePoints.add(new RaterablePoints(2.5, 2.5));
		raterablePoints.add(new RaterablePoints(3.5, 3.5));
	}

}
