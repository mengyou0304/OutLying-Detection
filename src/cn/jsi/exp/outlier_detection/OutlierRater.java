package cn.jsi.exp.outlier_detection;

import java.awt.Point;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Vector;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;
import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;
import com.sun.xml.internal.bind.v2.schemagen.xmlschema.List;

public class OutlierRater {
	public Vector<RaterablePoints> raterablePoints;
	
	private void rater(Point point ) {
/* USED FOR DEBUG		
 * java.util.List<RaterablePoints> raterablePoints2 = new ArrayList<RaterablePoints>();
 *
		RaterablePoints rpPoints = new RaterablePoints();
		raterablePoints2.add(rpPoints);
		Collections.sort(raterablePoints2);*/
		
	}
	
	private Grid findNearest(Vector<Grid> grids, RaterablePoints point) {
		double tmpmax=0;
		Grid tmpGrid = new Grid(0, 0);
		for (Grid grid : grids) {
			if (point.getHitGrid()==grid) {
				//TODO == or equals?
				continue;
			} else if (tmpmax==0) {
				tmpGrid=grid;
			}
		}
	}
	
}
