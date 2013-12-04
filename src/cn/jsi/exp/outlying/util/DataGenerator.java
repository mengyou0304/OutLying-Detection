package cn.jsi.exp.outlying.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.analyz.ErrorEventController;
import cn.jsi.exp.outlying.datagen.DataGenConfiguration;
import cn.jsi.exp.outlying.detection.DataPoint;
import cn.jsi.exp.outlying.setting.SystemParameters;

public class DataGenerator {
	private static final Logger log = Logger.getLogger(DataGenerator.class);

	public static List<DataPoint> getHistoricalData() {
		ArrayList<DataPoint> pointList = new ArrayList<DataPoint>();
		Random r = new Random();
		// generate normal data
		int baseValue = 0;
		int baseStep = DataGenConfiguration.maxvalue
				/ DataGenConfiguration.pointSetNumber;
		for (int setNumber = 0; setNumber < DataGenConfiguration.pointSetNumber; setNumber++) {
			for (int pointNumber = 0; pointNumber < DataGenConfiguration.pointNumber; pointNumber++) {
				ArrayList<Double> p1local = new ArrayList<Double>();
				for (int demensionNumber = 0; demensionNumber < DataGenConfiguration.dimensionNumber; demensionNumber++) {
					p1local.add(Double.parseDouble(String.valueOf(baseValue
							+ r.nextInt(DataGenConfiguration.pointSetDiameter))));
				}
				DataPoint p1 = new DataPoint(p1local);
				p1.setPointID("Set" + baseValue + "_" + pointNumber);
				log.debug("add point normal: " + p1);
				pointList.add(p1);
			}
			baseValue += baseStep;
		}

		// generate outlying points
		for (int i = 0; i < DataGenConfiguration.randomPointNumber; i++) {
			ArrayList<Double> p1local = new ArrayList<Double>();
			for (int demensionNumber = 0; demensionNumber < DataGenConfiguration.dimensionNumber; demensionNumber++) {
				p1local.add(Double.parseDouble(String.valueOf(r
						.nextInt(DataGenConfiguration.maxvalue))));
			}
			DataPoint p1 = new DataPoint(p1local);
			p1.setPointID("Outs" + i);
			pointList.add(p1);
			log.info("add point abnormal: " + p1);
		}

		return pointList;
	}

	public static DataPoint toData(String s) {
		String[] ss = s.split(",");
		List<Double> locals = new ArrayList<Double>();
		for (int i = SystemParameters.currentParameters.getDataLinesStart(); i <= SystemParameters.currentParameters
				.getDataLineEnds(); i++) {
			if (SystemParameters.currentParameters.getUse100())
				locals.add(100 * Double.parseDouble(ss[i]));
			else
				locals.add(Double.parseDouble(ss[i]));

		}
		DataPoint tdata = new DataPoint(locals);
		String type = ss[SystemParameters.currentParameters.getTypeColumn()];
		tdata.setPointType(type);
		if (SystemParameters.currentParameters.getOldscoreColumn() != null) {
			Integer oldscore = Integer
					.parseInt(ss[SystemParameters.currentParameters
							.getOldscoreColumn()]);
			tdata.setOldScore(oldscore);
		}
		tdata.setPointID(type
				+ ErrorEventController.errorTypeMap_input.get(type));
		if (type.equals("normal."))
			return tdata;
		// if(!ErrorEventController.errorTypeMap_input.keySet().contains(type))
		// ErrorEventController.newlist.add(type);
		Integer times = ErrorEventController.errorTypeMap_input.get(type);
		if (times == null) {
			ErrorEventController.errorTypeMap_input.put(type, 1);
			return tdata;
		}
		if (times > SystemParameters.currentParameters
				.getOutlyer_Number_Per_Type())
			return null;
		ErrorEventController.errorTypeMap_input.put(type, times + 1);
		return tdata;
	}

}
