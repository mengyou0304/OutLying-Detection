package cn.jsi.exp.outlier_detection;


import java.util.Vector;

public class SpaceDivider {
	private double divideLengthX, divideLengthY;
	private double xSpan,ySpan;
	
	
	public SpaceDivider(double divideLengthX, double divideLengthY, double xSpan,
			double ySpan) {
		super();
		this.divideLengthX = divideLengthX;
		this.divideLengthY = divideLengthY;
		this.xSpan = xSpan;
		this.ySpan = ySpan;
	}


	public double getxSpan() {
		return xSpan;
	}


	public void setxSpan(double xSpan) {
		this.xSpan = xSpan;
	}


	public double getySpan() {
		return ySpan;
	}


	public void setySpan(double ySpan) {
		this.ySpan = ySpan;
	}


	public Vector<Grid> gridGenerator(){
		Vector<Grid> rstGrids = new Vector<Grid>();
		Grid grid;
		Grid.setHeight(divideLengthY);
		Grid.setWidth(divideLengthX);
		for (double i = 0; i < xSpan; i+=divideLengthX) {
			for (double j = 0; j < ySpan; j+=divideLengthY) {
				grid = new Grid(i, j);
				rstGrids.add(grid);
			}
		}
		return rstGrids;
	}
}
