package cn.jsi.exp.outlying.analyz;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.butil.FileUtility;
import cn.jsi.exp.outlying.butil.Locator;
import cn.jsi.exp.outlying.detection.DataPoint;
import cn.jsi.exp.outlying.detection.Domain;
import cn.jsi.exp.outlying.detection.Grid;
import cn.jsi.exp.outlying.setting.SystemParameters;
import cn.jsi.exp.outlying.test.DataProcess1;
import cn.jsi.exp.outlying.util.DistanceCalculator;

public class GridAnalyzer {
	private static final Logger log = Logger.getLogger(GridAnalyzer.class);
	private static Integer anomalyNumber=0;
	
	private static Integer minMumeDomainSize=20;
	
	private static Integer nearestNeighberMaxDistance=100;
	
	public static void analyze(List<Grid> normalGrid) {
		String s="";
		for(Grid g:normalGrid){
			s="";
			HashMap<String,Integer> satasticMap=new HashMap<String,Integer>();
			List<DataPoint> plist=g.getHitPoints();
			Double summedScore=0d;
			for(DataPoint p: plist){
				String type=p.getPointType();
				Integer number=satasticMap.get(type);
				summedScore+=p.getOldScore();
				if(number ==null)
					number=1;
				else 
					number++;
				satasticMap.put(type, number);
//				s+=p.toString()+"\t";
			}
			Iterator<String> it=satasticMap.keySet().iterator();
			s+="Grid weight: "+g.getWeight()+"  score: "+summedScore.intValue()+"\t\t";
			while(it.hasNext()){
				String key=it.next();
				Integer value=satasticMap.get(key);
				s+= key+" : "+value+"\t";
			}
//			log.info(s);
		}
		log.info("Grid anayliz finish...................................");
	}
	public static void summedGrid(List<Grid> normalGrid){
		double comst_distance=nearestNeighberMaxDistance;
		ArrayList<Domain> dlist=new ArrayList<Domain> ();
		for(Grid g1:normalGrid){
			for(Grid g2: normalGrid){
				if(g1.getId()==g2.getId())
					continue;
				double distance=DistanceCalculator.simpleComputeDistance_G(g1,g2);
				if (distance<comst_distance){
					Domain d=null;
					if(g1.getDomain()!=null)
						d=g1.getDomain();
					if(g2.getDomain()!=null)
						d=g2.getDomain();
					if (d==null){
						d=new Domain();
						dlist.add(d);
					}
					d.addGrid(g1,g2);
					g1.setDomain(d);
					g2.setDomain(d);
				}
			}
		}
		String s="";
		ErrorEventController.errorTypeMap_outlying.clear();
		for(Domain d:dlist){
			if(d.getPointlist().size()<minMumeDomainSize)
				continue;
			s="";
			HashMap<String,Integer> satasticMap=new HashMap<String,Integer>();
			List<DataPoint> plist=d.getPointlist();
			Double summedScore=0d;
			for(DataPoint p: plist){
				Integer outnumber = ErrorEventController.errorTypeMap_outlying
						.get(p.getPointType());
				if (outnumber == null)
					ErrorEventController.errorTypeMap_outlying.put(
							p.getPointType(), 1);
				else
					ErrorEventController.errorTypeMap_outlying.put(
							p.getPointType(), outnumber + 1);
				String type=p.getPointType();
				Integer number=satasticMap.get(type);
				summedScore+=p.getOldScore();
				if(number ==null)
					number=1;
				else 
					number++;
				satasticMap.put(type, number);
//				s+=p.toString()+"\t";
			}
			Iterator<String> it=satasticMap.keySet().iterator();
			String ids="";
			for(Grid gg: d.getGridlist()){
				ids+=gg.getId()+",";
			}
			s+="Domain weight: "+d.getPointlist().size()+"  score: "+summedScore.intValue()+"\t\t";
			while(it.hasNext()){
				String key=it.next();
				Integer value=satasticMap.get(key);
				s+= key+" : "+value+"\t";
			}
//			log.info(s+ids);
			log.info(s);
		}
		Iterator<String> it = ErrorEventController.errorTypeMap_input.keySet()
				.iterator();
		String s2="";
		int inallErrorNumber = 0;
		int inallOutLyingNumber = 0;
		while (it.hasNext()) {
			String key = it.next();
			if (ErrorEventController.errorTypeMap_input.get(key) == 0)
				continue;
			Integer outlyingnumber = ErrorEventController.errorTypeMap_outlying
					.get(key);
			if (outlyingnumber == null)
				outlyingnumber = 0;
			s2 += key + " : " + outlyingnumber + "/"
					+ ErrorEventController.errorTypeMap_input.get(key) + "\n";
			
			inallErrorNumber += ErrorEventController.errorTypeMap_input
					.get(key);
			inallOutLyingNumber += outlyingnumber;
		}
		s2 += "\nIn all........" + inallOutLyingNumber + "/" + inallErrorNumber
				+ "  =" + inallOutLyingNumber * 100 / inallErrorNumber + "%";
		log.info(s2);

	}
}
