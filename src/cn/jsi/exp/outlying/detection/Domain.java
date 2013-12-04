package cn.jsi.exp.outlying.detection;

import java.util.ArrayList;
import java.util.List;

public class Domain {
	private List<Grid> gridlist = new ArrayList<Grid>();
	private List<DataPoint> pointlist = new ArrayList<DataPoint>();

	public void addGrid(Grid g1, Grid g2) {
		for (Grid g : gridlist) {
			if (g.getId() == g1.getId())
				return;
			if (g.getId() == g2.getId())
				return;
		}
		gridlist.add(g1);
		gridlist.add(g2);
		pointlist.addAll(g1.getHitPoints());
		pointlist.addAll(g2.getHitPoints());
	}

	public List<Grid> getGridlist() {
		return gridlist;
	}

	public List<DataPoint> getPointlist() {
		return pointlist;
	}

}
