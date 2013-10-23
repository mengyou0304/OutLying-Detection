package cn.jsi.exp.outlying.detection;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import cn.jsi.exp.outlying.setting.SystemParameters;
import cn.jsi.exp.outlying.util.DistanceCalculator;

/**
 * the grid that solution space is divided into. loc defines the bottom left
 * point of the grid
 * 
 * 
 * @author yulang RobinMeng
 */
public class Grid {
	private  List<Double> locals;
	private  List<Double> widths;
	private int numberOfPointsHit = 0;
	private List<DataPoint> hitPoints;
	private boolean isDensity;
	private Integer weight = 0;
	private Double distanceWeight = 0d;

	public Grid() {
		locals = new ArrayList<Double>();
		widths = new ArrayList<Double>();
		hitPoints = new ArrayList<DataPoint>();
	}

	public Grid(List<Double> localvalues) {
		locals = new ArrayList<Double>();
		for (int i = 0; i < localvalues.size(); i++) {
			Double tempd = localvalues.get(i) / SystemParameters.divideLength;

			Integer number = tempd.intValue();
			locals.add(number * SystemParameters.divideLength);
		}
		widths = new ArrayList<Double>();
		for (int i = 0; i < locals.size(); i++) {
			widths.add(SystemParameters.divideLength);
		}
		hitPoints = new ArrayList<DataPoint>();
	}

	public  List<Double> getLocals() {
		return locals;
	}

	public  void setLocals(List<Double> locals) {
		this.locals = locals;
	}

	public  List<Double> getWidths() {
		return widths;
	}

	public  void setWidths(List<Double> widths) {
		this.widths = widths;
	}

	private void setNumberOfPointsHit(int numberOfPointsHit) {
		this.numberOfPointsHit = numberOfPointsHit;
	}

	public int getNumberOfPointsHit() {
		return hitPoints.size();
	}

	public void addPoint(DataPoint point) {
		hitPoints.add(point);
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Double getDistanceWeight() {
		return distanceWeight;
	}

	public void setDistanceWeight(Double distanceWeight) {
		this.distanceWeight = distanceWeight;
	}

	public List<DataPoint> getHitPoints() {
		return hitPoints;
	}


	public boolean isDensity() {
		return isDensity;
	}

	public void setDensity(boolean isDensity) {
		this.isDensity = isDensity;
	}
	@Override
	public String toString() {
		String s="";
		for(Double i:getLocals()){
			s+=i.intValue()+",";
		}
		return s;
	}
	

}
