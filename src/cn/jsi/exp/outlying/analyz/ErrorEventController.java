package cn.jsi.exp.outlying.analyz;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorEventController {
	public static HashMap<String, Integer> errorTypeMap_input = new HashMap<String, Integer>();
	public static HashMap<String, Integer> errorTypeMap_outlying=new  HashMap<String, Integer>();
	public static ArrayList<String> newlist=new ArrayList<String>();
	public static void init() {
		errorTypeMap_input = new HashMap<String, Integer>();
		errorTypeMap_outlying=new HashMap<String,Integer> ();
	}

}
