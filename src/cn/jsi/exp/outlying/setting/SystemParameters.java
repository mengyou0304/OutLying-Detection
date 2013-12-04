package cn.jsi.exp.outlying.setting;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

import cn.jsi.exp.outlying.butil.BeanManager;
import cn.jsi.exp.outlying.butil.FileUtility;
import cn.jsi.exp.outlying.butil.Locator;

public class SystemParameters {
	

	private Integer densityThreshold = 300;

	private Integer nearGridLevel = 1;
	private Integer nearGridDistance = 100;

	private Double divideLength = 50d;
	private Boolean use2LevelOutlying = false;

	// the restricted error event number
	private Integer outlyer_Number_Per_Type = 200;

	private Boolean showPoIntegerAxis = false;

	private Boolean use_PCA_Data = true;
	private Integer dataLinesStart=52;
	private Integer dataLineEnds=61;
	

	private String outputFile = "outlier.csv";

	private Double outputScoreThreshold = 100d;
	
	private Integer typeColumn=41;
	private Integer oldscoreColumn=null;
	private Boolean use100=true;
	private Boolean showGridData=false;
	
	private static SystemParameters[] instanceList = new SystemParameters[10];
	public static SystemParameters currentParameters;

	public static Boolean useConig(Integer i) {
		currentParameters = instanceList[i];
		return true;
	}

	public static void addInstance(String url, Integer i) {
		String baseurl = Locator.getInstance().getBaseLocation() + url;
		String config = FileUtility.readFromFile(url);
		String[] ss = config.split("\n");
		HashMap<String, String> valueMap = new HashMap<String, String>();
		for (String s : ss) {
			if(s.startsWith("\\"))
				continue;
			if (s.trim().length() < 1)
				continue;
			String[] ds = s.split("=");
			valueMap.put(ds[0].trim(), ds[1].trim());
		}
		SystemParameters instance = null;
		try {
			instance = (SystemParameters) BeanManager.fillBean(
					SystemParameters.class, valueMap);
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		instanceList[i]=instance;
	}

	public Integer getDensityThreshold() {
		return densityThreshold;
	}

	public void setDensityThreshold(Integer densityThreshold) {
		this.densityThreshold = densityThreshold;
	}

	public Integer getNearGridLevel() {
		return nearGridLevel;
	}

	public void setNearGridLevel(Integer nearGridLevel) {
		this.nearGridLevel = nearGridLevel;
	}

	public Integer getNearGridDistance() {
		return nearGridDistance;
	}

	public void setNearGridDistance(Integer nearGridDistance) {
		this.nearGridDistance = nearGridDistance;
	}

	public Double getDivideLength() {
		return divideLength;
	}

	public void setDivideLength(Double divideLength) {
		this.divideLength = divideLength;
	}

	public Boolean getUse2LevelOutlying() {
		return use2LevelOutlying;
	}

	public void setUse2LevelOutlying(Boolean use2LevelOutlying) {
		this.use2LevelOutlying = use2LevelOutlying;
	}

	public Integer getOutlyer_Number_Per_Type() {
		return outlyer_Number_Per_Type;
	}

	public void setOutlyer_Number_Per_Type(Integer outlyer_Number_Per_Type) {
		this.outlyer_Number_Per_Type = outlyer_Number_Per_Type;
	}

	public Boolean getShowPoIntegerAxis() {
		return showPoIntegerAxis;
	}

	public void setShowPoIntegerAxis(Boolean showPoIntegerAxis) {
		this.showPoIntegerAxis = showPoIntegerAxis;
	}

	public Boolean getUse_PCA_Data() {
		return use_PCA_Data;
	}

	public void setUse_PCA_Data(Boolean use_PCA_Data) {
		this.use_PCA_Data = use_PCA_Data;
	}

	public Integer getDataLinesStart() {
		return dataLinesStart;
	}

	public void setDataLinesStart(Integer dataLinesStart) {
		this.dataLinesStart = dataLinesStart;
	}

	public Integer getDataLineEnds() {
		return dataLineEnds;
	}

	public void setDataLineEnds(Integer dataLineEnds) {
		this.dataLineEnds = dataLineEnds;
	}

	
	public Double getOutputScoreThreshold() {
		return outputScoreThreshold;
	}

	public void setOutputScoreThreshold(Double outputScoreThreshold) {
		this.outputScoreThreshold = outputScoreThreshold;
	}

	public String getOutputFile() {
		return outputFile;
	}

	public void setOutputFile(String outputFile) {
		this.outputFile = outputFile;
	}

	public Integer getTypeColumn() {
		return typeColumn;
	}

	public void setTypeColumn(Integer typeColumn) {
		this.typeColumn = typeColumn;
	}

	public Boolean getUse100() {
		return use100;
	}

	public void setUse100(Boolean use100) {
		this.use100 = use100;
	}

	public Boolean getShowGridData() {
		return showGridData;
	}

	public void setShowGridData(Boolean showGridData) {
		this.showGridData = showGridData;
	}

	public Integer getOldscoreColumn() {
		return oldscoreColumn;
	}

	public void setOldscoreColumn(Integer oldscoreColumn) {
		this.oldscoreColumn = oldscoreColumn;
	}


}
