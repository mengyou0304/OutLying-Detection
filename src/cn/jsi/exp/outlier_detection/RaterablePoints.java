package cn.jsi.exp.outlier_detection;

import java.awt.Point;

public class RaterablePoints extends Point implements Comparable<RaterablePoints> {

	private double score;
	private Grid nearestGrid;
	
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
