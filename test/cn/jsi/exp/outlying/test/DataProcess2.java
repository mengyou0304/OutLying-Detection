package cn.jsi.exp.outlying.test;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.analyz.GridAnalyzer;
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

public class DataProcess2 {
	private static final Logger log = Logger.getLogger(DataProcess2.class);
	private SpaceDivider spaceDivider;

	public void initData(String configfile, String datafile) {
		SystemParameters.addInstance("config" + File.separator + configfile, 0);
		SystemParameters.useConig(0);
		ErrorEventController.init();
		String baseUrl = Locator.getInstance().getBaseLocation();
		FileUtility.readFromFileStream(baseUrl + datafile);
		System.out.println("finish reading");
	}

	public void level1Process(String configfile, String datafile) {
		spaceDivider = SpaceDivider.newInstance();
		initData(configfile, datafile);
		List<Grid> grids = spaceDivider.getCurrentGrids();
		log.info("After inserting the data we get grid number as"
				+ grids.size());
		List<DataPoint> possibleOutlierPoints = DetectMethods
				.findOutOutlierBasedonThreshold(grids);
		List<Grid> normalGrid = DetectMethods.getNormalGrids(grids);
		List<DataPoint> Outlier = OutlierDetector.getOutlierRank(
				possibleOutlierPoints, normalGrid);
		OutAnalyzer.analyze(Outlier);
		if (SystemParameters.currentParameters.getShowGridData()){
			GridAnalyzer.analyze(normalGrid);
			GridAnalyzer.summedGrid(normalGrid);
			
		}
		System.out
				.println("=================Level1 analyz finish=====================");
	}

	public static void main(String[] args) {
		DataProcess2 t = new DataProcess2();
		t.level1Process("conf1.cfg", "d1.csv");
		t.level1Process("conf2.cfg", "o1.csv");
	}

}
