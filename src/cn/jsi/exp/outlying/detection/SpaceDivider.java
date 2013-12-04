package cn.jsi.exp.outlying.detection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.setting.SystemParameters;
import cn.jsi.exp.outlying.util.DistanceCalculator;

/**
 * This is used to divie the data space into girds.
 * 
 * @author Yulang RobinMeng
 * 
 */
public class SpaceDivider {
	private static final Logger log = Logger.getLogger(SpaceDivider.class);

	private SpaceDivider() {
		gridMap = new HashMap<String, Grid>();
		
	}

	private static SpaceDivider instance;

	public static SpaceDivider getInstance() {
		if (instance == null)
			instance = new SpaceDivider();
		return instance;
	}

	public static SpaceDivider newInstance() {
		instance = new SpaceDivider();
		instance.gridMap = new HashMap<String, Grid>();
		return instance;
	}

	public HashMap<String, Grid> gridMap = new HashMap<String, Grid>();

	public String getGridKey(List<Double> localvalues) {
		List<Integer> gridNumbers = getGridNumbersByLocalValues(localvalues);
		String s = "";
		for (Integer i : gridNumbers) {
			s += i + ",";
		}
		return s;
	}

	public Grid getTheGridAccordingToLocal(List<Double> localvalues) {
		String key = getGridKey(localvalues);
		Grid g = gridMap.get(key);
		if (g == null) {
			log.debug("Creating grid as " + key);
			g = new Grid(localvalues);
			gridMap.put(key, g);
		}
		return g;
	}

	public Grid getTheGridAccordingToLocalX(List<Integer> localx) {
		ArrayList<Double> localvalues = new ArrayList<Double>();

		String key = "";
		for (Integer i : localx) {
			key += i + ",";
			localvalues.add(i * SystemParameters.currentParameters.getDivideLength());
		}
		Grid g = gridMap.get(key);
		if (g == null) {
			log.debug("Creating grid as " + key);
			g = new Grid(localvalues);
			gridMap.put(key, g);
		}
		return g;
	}

	public void inputPoint(List<DataPoint> historicalData) {
		for (DataPoint point : historicalData) {
			inputPoint(point);
		}

	}

	private void inputPoint(DataPoint point) {
		List<Double> locals = point.getLocals();
		Grid g = getTheGridAccordingToLocal(locals);
		g.addPoint(point);
		point.setHitGrid(g);
		// log.debug("after orocesisng point dimension size:"+point.getLocals().size());
	}

	public List<Grid> getCurrentGrids() {
		Iterator<String> it = gridMap.keySet().iterator();
		ArrayList<Grid> gridList = new ArrayList<Grid>();
		while (it.hasNext()) {
			String key = it.next();
			gridList.add(gridMap.get(key));
		}
		return gridList;
	}

	public List<Integer> getGridNumbersByLocalValues(List<Double> localvalues) {
		List<Integer> gridNumber = new ArrayList<Integer>();
		for (int i = 0; i < localvalues.size(); i++) {
			Double j = localvalues.get(i) / SystemParameters.currentParameters.getDivideLength();
			int number = j.intValue();
			gridNumber.add(number);
		}
		return gridNumber;

	}

}
