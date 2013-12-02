package cn.jsi.exp.outlying.detection;

import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.setting.SystemParameters;

public class DataPoint implements Comparable<DataPoint> {
	private static final Logger log = Logger.getLogger(DataPoint.class);

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Double score = 0d;
	private String pointID = "";
	private String pointType = "";
	private Grid nearestGrid;
	private Grid hitGrid;
	private List<Double> locals;

	public DataPoint() {

	}

	public DataPoint(List<Double> local) {
		locals = local;
	}

	public Grid getHitGrid() {
		return hitGrid;
	}

	public void setHitGrid(Grid hitGrid) {
		this.hitGrid = hitGrid;
	}

	public Double getScore() {
		return score;
	}

	public void setScore(Double score) {
		this.score = score;
	}

	public Grid getNearestGrid() {
		return nearestGrid;
	}

	public void setNearestGrid(Grid nearestGrid) {
		this.nearestGrid = nearestGrid;
	}

	public List<Double> getLocals() {
		return locals;
	}

	@Override
	public int compareTo(DataPoint o) {
		if (this.score > o.getScore()) {
			return 1;
		} else if (this.score < o.getScore()) {
			return -1;
		} else {
			return 0;
		}
	}

	public String getPointID() {
		return pointID;
	}

	public void setPointID(String pointID) {
		this.pointID = pointID;
	}

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	@Override
	public String toString() {
		String s = pointID + "\t";
		if (SystemParameters.showPointAxis) {
			for (Double d : locals) {
				s += d.intValue() + ",";
			}
			s = s.substring(0, s.length() - 1);
		}
		return s;
	}

	public String toFileData() {
		String s=pointID+",";
		for (Double d : locals) {
			s += d.intValue() + ",";
		}
		s+=getScore().intValue();
		return s;
	}

}
