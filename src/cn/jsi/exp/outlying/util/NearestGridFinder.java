package cn.jsi.exp.outlying.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.detection.DataPoint;
import cn.jsi.exp.outlying.detection.DetectMethods;
import cn.jsi.exp.outlying.detection.Grid;
import cn.jsi.exp.outlying.detection.SpaceDivider;
import cn.jsi.exp.outlying.setting.SystemParameters;

public class NearestGridFinder {
	private static final Logger log = Logger.getLogger(NearestGridFinder.class);

	ArrayList<Grid> nearGridList;
	List<Integer> originLocalX;
	ArrayList<Integer> currentLocalX;
	SpaceDivider spaceDivider;
	Integer allLevel = 0;

	public NearestGridFinder() {
		nearGridList = new ArrayList<Grid>();
		originLocalX = new ArrayList<Integer>();
		currentLocalX = new ArrayList<Integer>();
		spaceDivider = SpaceDivider.getInstance();
	}

	public List<Grid> getNLevelNearestGridList(int computedLevel,DataPoint point) {
		currentLocalX.clear();
		nearGridList.clear();
		originLocalX=SpaceDivider.getInstance().getGridNumbersByLocalValues(point.getLocals());
		log.debug("originalX is"+originLocalX);
		currentLocalX.addAll(originLocalX);
		allLevel=computedLevel;
		recrusion(0);
		return nearGridList;
//		Integer x1 = originLocalX.get(0);
//		for (int i1 = x1 - level; i1 <= x1 + level; i1++) {
//			Integer x2 = originLocalX.get(1);
//			for (int i2 = x2 - level; i2 <= x2 + level; i2++) {
//
//			}
//		}
	}

	private void recrusion(int currentX) {
		if (currentX == originLocalX.size())
			return;
		Integer x = originLocalX.get(currentX);
		for (int i = x - allLevel; i <= x + allLevel; i++) {
			currentLocalX.set(currentX, i);
//			log.debug("trying"+currentLocalX);
			Grid tmpGrid2=spaceDivider.getTheGridAccordingToLocalX(currentLocalX);
			Double distance=DetectMethods.computDistanceBetweenTwoGrid(originLocalX, currentLocalX);
			tmpGrid2.setDistanceWeight(distance);
			log.debug("set distance weight of Gird"+tmpGrid2+" as  "+distance+" between "+originLocalX+" with "+currentLocalX);
			nearGridList.add(tmpGrid2);
			recrusion(currentX+1);
		}

	}
}
