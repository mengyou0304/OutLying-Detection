package cn.jsi.exp.outlying.detection;

import java.awt.Point;
import java.util.List;


public class RaterablePoints implements Comparable<RaterablePoints> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private double score=0;
	private Grid nearestGrid;
	private Grid hitGrid;
	public  List<Double> locals;
	

	public RaterablePoints(List<Double> local) {
		locals=local;
	}

	public Grid getHitGrid() {
		return hitGrid;
	}

	public void setHitGrid(Grid hitGrid) {
		this.hitGrid = hitGrid;
	}

	public double getScore() {
		return score;
	}

	public void setScore(double score) {
		this.score = score;
	}

	public Grid getNearestGrid() {
		return nearestGrid;
	}

	public void setNearestGrid(Grid nearestGrid) {
		this.nearestGrid = nearestGrid;
	}

	public  List<Double> getLocals() {
		return locals;
	}


	@Override
	public int compareTo(RaterablePoints o) {
		// TODO Auto-generated method stub
		if (this.score>o.getScore()) {
			return 1;
		} else if (this.score<o.getScore()) {
			return -1;
		} else {
			return 0;
		}
	}

}
