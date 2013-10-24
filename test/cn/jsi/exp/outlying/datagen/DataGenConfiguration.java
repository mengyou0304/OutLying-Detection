package cn.jsi.exp.outlying.datagen;

public class DataGenConfiguration {
	public static final String DISTRIBUTION_AVERAGE="AVERAGE";
	/**
	 * 几维数据
	 */
	public static  int dimensionNumber=3;
	/**
	 * 正常数据集合的数量
	 */
	public static  int pointSetNumber=2;
	/**
	 * 每个正常数据集合周围点的数量
	 */
	public static  int pointNumber=1000;
	/**
	 * 每个正常数据集合的半径
	 */
	public static  int pointSetDiameter=10;
	/**
	 * 正常数据集合的数据分布
	 */
	public static  String pointDistribution=DISTRIBUTION_AVERAGE;
	/**
	 * 异常点数量
	 */
	public static  int randomPointNumber=4;
	/**
	 * 生成数据的值域
	 */
	public static int maxvalue=100;
	
	
	
	

}
