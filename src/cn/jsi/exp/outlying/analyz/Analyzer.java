package cn.jsi.exp.outlying.analyz;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.butil.FileUtility;
import cn.jsi.exp.outlying.butil.Locator;
import cn.jsi.exp.outlying.detection.DataPoint;
import cn.jsi.exp.outlying.setting.SystemParameters;
import cn.jsi.exp.outlying.test.DataProcess1;

public class Analyzer {
	private static final Logger log = Logger.getLogger(Analyzer.class);

	public static void analyze(List<DataPoint> possibleOutlierPoints) {
		List<DataPoint> clusteringList=new ArrayList<DataPoint>();
		
		String s = "They are\n";

		Integer[] normalValues = new Integer[100001];
		Integer[] anomalyValues = new Integer[10000001];
		for (int i = 0; i < normalValues.length; i++){
			normalValues[i] = 0;
		}
		for (int i = 0; i < anomalyValues.length; i++){
			anomalyValues[i] = 0;
		}
		for (DataPoint p : possibleOutlierPoints) {
			if(p.getScore()<=SystemParameters.outputScoreThreshold)
				clusteringList.add(p);
			if ("normal.".equals(p.getPointType())) {
				int pointvalue = p.getScore().intValue();
				normalValues[pointvalue]++;
			} else {
				s += p.getScore().intValue() + "\t" + p + "\n";
				anomalyValues[p.getScore().intValue()]++;
				Integer outnumber = ErrorEventController.errorTypeMap_outlying
						.get(p.getPointType());
				if (outnumber == null)
					ErrorEventController.errorTypeMap_outlying.put(
							p.getPointType(), 1);
				else
					ErrorEventController.errorTypeMap_outlying.put(
							p.getPointType(), outnumber + 1);
			}
		}

		s += "\n\n\nNormal values( UP TO)\n";
		int currentNormalValue = 0;
		int currentAnomalyValue = 0;
		for (int i = 0; i < 42000; i++) {
			currentNormalValue += normalValues[i];
			currentAnomalyValue+=anomalyValues[i];
			if (i < 10) {
				s += i + ":\t" +currentAnomalyValue+" / "+ currentNormalValue + "("+currentAnomalyValue*100/currentNormalValue+"%)\n";
				continue;
			}
			if (i < 100 && i % 10 == 0) {
				s += i + ":\t" +currentAnomalyValue+" / "+ currentNormalValue + "("+currentAnomalyValue*100/currentNormalValue+"%)\n";
					continue;
			}
			if (i < 1000 && i % 100 == 0) {
				s += i + ":\t" +currentAnomalyValue+" / "+ currentNormalValue + "("+currentAnomalyValue*100/currentNormalValue+"%)\n";
					continue;
			}
			if ( i % 1000 == 0) {
				s += i + ":\t" +currentAnomalyValue+" / "+ currentNormalValue + "("+currentAnomalyValue*100/currentNormalValue+"%)\n";
					continue;
			}
		}
		log.info(s);
		String s2 = "For anomalies:\n";
		int inallErrorNumber = 0;
		int inallOutLyingNumber = 0;
		Iterator<String> it = ErrorEventController.errorTypeMap_input.keySet()
				.iterator();
		while (it.hasNext()) {
			String key = it.next();
			if (ErrorEventController.errorTypeMap_input.get(key) == 0)
				continue;
			Integer outlyingnumber = ErrorEventController.errorTypeMap_outlying
					.get(key);
			if (outlyingnumber == null)
				outlyingnumber = 0;
			s2 += key + " : " + outlyingnumber + "/"
					+ ErrorEventController.errorTypeMap_input.get(key) + "\n";
			
			inallErrorNumber += ErrorEventController.errorTypeMap_input
					.get(key);
			inallOutLyingNumber += outlyingnumber;
		}
		s2 += "\nIn all........" + inallOutLyingNumber + "/" + inallErrorNumber
				+ "  =" + inallOutLyingNumber * 100 / inallErrorNumber + "%";

		log.info(s2);
		if(SystemParameters.outputFile)
			outPutOutliers(clusteringList);
	}
	public static void outPutOutliers(List<DataPoint> possibleOutlierPoints){
		String s="";
		for(DataPoint p:possibleOutlierPoints){
			s+=p.toFileData()+"\n";
		}
		log.info("Output to file...."+Locator.getInstance().getBaseLocation()+"outliers.csv");
		FileUtility.writeToFile(Locator.getInstance().getBaseLocation()+"outliers.csv", s);
	}
}
