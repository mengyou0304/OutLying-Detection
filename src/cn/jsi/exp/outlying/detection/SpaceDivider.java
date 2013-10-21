package cn.jsi.exp.outlying.detection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

import cn.jsi.exp.outlying.setting.SystemConst;

/**
 * This is used to divie the data space into girds.
 * 
 * @author Yulang RobinMeng
 * 
 */
public class SpaceDivider {

	public HashMap<String, Grid> gridMap = new HashMap<String, Grid>();

	public String getGridKey(List<Double> localvalues) {
		String s = "";
		for (int i = 0; i < localvalues.size(); i++) {
			Double j = localvalues.get(i) / SystemConst.divideLength;
			int number = j.intValue();
			s += number + ",";
		}
		return s;
	}

	public Grid getNewGrid(List<Double> localvalues) {
		String key = getGridKey(localvalues);
		Grid g = gridMap.get(key);
		if (g == null) {
			g = new Grid(localvalues);
			gridMap.put(key, g);
		}
		return g;
	}

	public void inputPoint(List<RaterablePoints> historicalData) {
		for (RaterablePoints point : historicalData) {
			inputPoint(point);
		}

	}

	private void inputPoint(RaterablePoints point) {
		List<Double> locals = point.getLocals();

		Grid g = getNewGrid(locals);
		g.addPoint(point);

	}

	public List<Grid> getCurrentGrids() {
		Iterator<String> it=gridMap.keySet().iterator();
		ArrayList<Grid> gridList=new ArrayList<Grid>();
		while(it.hasNext()){
			String key=it.next();
			gridList.add(gridMap.get(key));
		}
		return gridList;
	}

}
