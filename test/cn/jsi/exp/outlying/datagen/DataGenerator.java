package cn.jsi.exp.outlying.datagen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.detection.DataPoint;
import cn.jsi.exp.outlying.test.Test;

public class DataGenerator {
	private static final Logger log = Logger.getLogger(DataGenerator.class);

	public static List<DataPoint> getHistoricalData() {
		ArrayList<DataPoint> pointList = new ArrayList<DataPoint>();
		Random r = new Random();
		//generate normal data
		int baseValue=0;
		int baseStep=DataGenConfiguration.maxvalue/DataGenConfiguration.pointSetNumber;
		for (int setNumber = 0; setNumber < DataGenConfiguration.pointSetNumber; setNumber++){
			for (int pointNumber = 0; pointNumber < DataGenConfiguration.pointNumber; pointNumber++) {
				ArrayList<Double> p1local = new ArrayList<Double>();
				for (int demensionNumber = 0; demensionNumber < DataGenConfiguration.dimensionNumber; demensionNumber++) {
					p1local.add(Double.parseDouble(String.valueOf(baseValue+r.nextInt(DataGenConfiguration.pointSetDiameter))));
				}
				DataPoint p1 = new DataPoint(p1local);
				p1.setPointID("Set"+baseValue+"_"+pointNumber);
				log.debug("add point normal: " + p1);
				pointList.add(p1);
			}
			baseValue+=baseStep;
		}
		
		//generate outlying points
		for (int i = 0; i < DataGenConfiguration.randomPointNumber; i++) {
			ArrayList<Double> p1local = new ArrayList<Double>();
			for (int demensionNumber = 0; demensionNumber < DataGenConfiguration.dimensionNumber; demensionNumber++) {
				p1local.add(Double.parseDouble(String.valueOf(r
						.nextInt(DataGenConfiguration.maxvalue))));
			}
			DataPoint p1 = new DataPoint(p1local);
			p1.setPointID("Outs"+i);
			pointList.add(p1);
			log.info("add point abnormal: " + p1);
		}

		return pointList;
	}

}
