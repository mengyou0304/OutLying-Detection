package cn.jsi.exp.outlying.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.analyz.OutAnalyzer;
import cn.jsi.exp.outlying.analyz.ErrorEventController;
import cn.jsi.exp.outlying.butil.FileUtility;
import cn.jsi.exp.outlying.butil.Locator;
import cn.jsi.exp.outlying.detection.DataPoint;
import cn.jsi.exp.outlying.detection.DetectMethods;
import cn.jsi.exp.outlying.detection.Grid;
import cn.jsi.exp.outlying.detection.OutlierDetector;
import cn.jsi.exp.outlying.detection.SpaceDivider;
import cn.jsi.exp.outlying.setting.SystemParameters;
import cn.jsi.exp.outlying.util.DataGenerator;

public class DataProcess1 {
	private static final Logger log = Logger.getLogger(DataProcess1.class);
	private static SpaceDivider spaceDivider = SpaceDivider.getInstance();

	public void initData() {
		SystemParameters.addInstance("config"+File.separator+"conf1.cfg", 0);
		SystemParameters.useConig(0);
		ErrorEventController.init();
		String baseUrl = Locator.getInstance().getBaseLocation();
		FileUtility.readFromFileStream(baseUrl + "d1.csv");
		System.out.println("finish reading");
	}

	public List<DataPoint> level1Process() {

		// After input the points into the grids, we should find which girds are
		// filled with points and find the nomral grids and abnormal grids based
		// on the points in it
		List<Grid> grids = spaceDivider.getCurrentGrids();
		log.info("After inserting the data we get grid number as"
				+ grids.size());

		List<DataPoint> possibleOutlierPoints = DetectMethods
				.findOutOutlierBasedonThreshold(grids);
		List<Grid> normalGrid=DetectMethods.getNormalGrids(grids);
		
		// log.info(s);

		// It seems to the end, but we further discover that some normal point
		// also be treated as abnomal ones due to the reason that they are
		// placed at the bounder of the grid,so we should find them out
		// and treat them as normal points

		List<DataPoint> Outlier = OutlierDetector.getOutlierRank(
				possibleOutlierPoints, normalGrid);
		OutAnalyzer.analyze(Outlier);
		System.out.println("=================Level1 analyz finish=====================");
		ArrayList<DataPoint> l2List = new ArrayList<DataPoint>();
//		for (DataPoint point : Outlier) {
//			System.out.println(" out lyers score:  "
//					+ point.getScore().intValue() + "  " + point);
//			Double score = point.getScore();
//			List<Double> l2D = new ArrayList<Double>();
//			l2D.add(score);
//			DataPoint p = new DataPoint(l2D);
//			p.setPointID(point.getPointID());
//			l2List.add(p);
//		}
		log.info("==========================Level1 outlyer finish=================");
		return l2List;

	}

	public static void main(String[] args) {
		DataProcess1 t = new DataProcess1();
		t.initData();
		List<DataPoint> datalist2 = t.level1Process();
		for(String s: ErrorEventController.newlist){
			System.out.println(s);
		}

	}

	
	
}
