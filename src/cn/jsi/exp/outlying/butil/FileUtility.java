package cn.jsi.exp.outlying.butil;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import cn.jsi.exp.outlying.datagen.DataGenerator;
import cn.jsi.exp.outlying.detection.DataPoint;
import cn.jsi.exp.outlying.detection.SpaceDivider;

/**
 * 
 * @author Robin
 * @date 2009-10-20 03:53:32
 * 
 */
public class FileUtility {
	private static final Logger log = Logger.getLogger(FileUtility.class);

	public static String readFromFile(String url) {
		File f = new File(url);
		BufferedReader br = null;
		FileInputStream fis = null;
		String outputfile = "";
		try {
			// System.out.println("url= "+url);
			fis = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			// br = new BufferedReader(new UnicodeReader(fis,
			// Charset.defaultCharset().name()));
			while (br.ready()) {
				String s = br.readLine();
				outputfile += s + "\n";
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (br != null)
					br.close();
				if (fis != null)
					fis.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return outputfile;
	}

	public static boolean writeToFile(String url, String xml) {
		try {
			File file = new File(url);
			if (file.exists())
				file.delete();
			File file2 = file.getParentFile();
			file2.mkdirs();
			FileOutputStream fos = new FileOutputStream(url);
			OutputStreamWriter out = new OutputStreamWriter(fos, "UTF-8");
			out.write(xml);
			out.close();
			fos.close();

		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	public static void writeLineToEnd(String insertString, String url) {
		RandomAccessFile rf = null;
		try {
			rf = new RandomAccessFile(url, "rw");
			long len = rf.length();
			rf.seek(len);
			rf.writeBytes(insertString);
			rf.close();

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (rf != null)
					;
				rf.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static ArrayList<String> detectFiles(String url, final String parten) {
		ArrayList<String> filelist = new ArrayList<String>();
		File file = new File(url);
		File[] files = file.listFiles();
		for (File tempfile : files) {
			if (tempfile.getName().endsWith("jpg")
					|| tempfile.getName().endsWith("bmp")
					|| tempfile.getName().endsWith("JPG"))
				filelist.add(tempfile.getName());
		}
		return filelist;
	}

	public static String readFromFileStream(String url) {
		File f = new File(url);
		BufferedReader br = null;
		FileInputStream fis = null;
		int i=0;
		boolean firstline=true;
		List<DataPoint> dlist = new ArrayList<DataPoint>();
		try {
			// System.out.println("url= "+url);
			fis = new FileInputStream(f);
			br = new BufferedReader(new InputStreamReader(fis, "UTF-8"));
			// br = new BufferedReader(new UnicodeReader(fis,
			// Charset.defaultCharset().name()));
			while (br.ready()) {
				String s = br.readLine();
				if(firstline){
					firstline=false;
					continue;
				}
				DataPoint data = DataGenerator.toData(s);
				if(data!=null)
					dlist.add(data);
				if (dlist.size() > 1000) {
					log.info(" finish processing "+i+++"000 data");
					SpaceDivider spaceDivider = SpaceDivider.getInstance();
					spaceDivider.inputPoint(dlist);
					dlist.clear();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			try {
				if (br != null)
					br.close();
				if (fis != null)
					fis.close();
			} catch (Exception ex) {
				ex.printStackTrace();
			}
		}
		return null;
	}

	public static void main(String[] args) {
		String imagePath = Locator.getInstance().getBaseLocation() + "images"
				+ File.separator + "food" + File.separator;
		ArrayList<String> list = detectFiles(imagePath, "\bw*\b");
		for (String s : list)
			System.out.println(s);
	}
}
