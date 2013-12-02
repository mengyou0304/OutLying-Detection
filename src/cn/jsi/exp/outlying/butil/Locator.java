package cn.jsi.exp.outlying.butil;





import java.io.File;

/**
 * @author Robin
 *
 * @date Aug 14, 2007
 */
/**
 * 鎻愪緵锟�锟斤拷杩愯鐜鐨勮矾寰勪俊锟�杩欓噷鎻愪緵褰撳墠绫讳富瑕佺敤浜庡涓嬪満锟�
 * 鍚姩浠ュ悗,锟�锟斤拷鍔犺浇hibernate鎸佷箙鍖栧眰,鑰宧ibernate鍚姩杩囩▼涓渶瑕佽鍙栭厤缃枃浠剁瓑淇℃伅,杩欎簺淇℃伅鏀惧湪绯荤粺鐩綍conf/锟�
 * 锟�锟斤拷鍦╤ibernate鍒濆鍖栨椂锟�锟斤拷鎻愪緵锟�锟斤拷鏂规硶鑳藉璁块棶褰撳墠绯荤粺鐨勬牴鐩綍,Locator鍙互鎻愪緵杩欎簺淇℃伅鐨勮锟�
 * 
 */
public class Locator {
	public static final String conf_location = "conf";

	/**
	 * 鍗曚緥妯″紡鐨勫疄锟�
	 */
	private static Locator instance = new Locator();

	/**
	 * 涓嶆彁渚涘叕鍏辨瀯閫犲嚱锟�閬垮厤閲嶅瀹炰緥鍖栫殑鎯呭喌鍑虹幇
	 * this.getClass()鏄痗n.jsi.procmg.config.Locator
	 * this.getClass().getResource("/cn")鏄粷瀵硅祫婧愬悕/jsi/procmg/config/Locator鐨勭粷瀵硅矾锟�
	 * this.getClass().getResource("/")鏄粷瀵硅祫婧愬悕cn/jsi/procmg/config/Locator鐨勭粷瀵硅矾寰剈rl,file:/D:/eclipse/myeclipse/PMConfig/bin/
	 * this.getClass().getResource("/").getPath()鑾峰緱url鐨勮矾锟�/D:/eclipse/myeclipse/PMConfig/bin/
	 */
	private Locator() {
		String appConf = this.getClass().getResource("/").getPath();
//		System.out.println(this.getClass().getResource("/").getPath());
//		System.out.println("appConf="+appConf);
		if (appConf.endsWith("bin/")){
			appConf = new File(appConf).getParentFile() + File.separator;
//			System.out.println("appConf="+appConf);
		}
		else if (appConf.endsWith("classes/"))
			appConf = new File(appConf).getParentFile().getParent() + File.separator;
		else if (appConf.endsWith("src/"))
			appConf = new File(appConf).getParent()+File.separator+"WebRoot"+File.separator;
		else
			appConf = new File(appConf).getParentFile().getParent()
					+ File.separator;
		if (null != appConf)
			this.baseLocation = appConf;
	}

	public static synchronized Locator getInstance() {
		return instance;
	}

	/**
	 * 鎻愪緵绯荤粺杩愯鐨勬牴璺緞淇℃伅,閲囩敤File.seperator缁撳熬
	 * 
	 * 榛樿涓轰笌src鐩綍鍚岀骇 modify by kewang 11:40 2005-8-31
	 */
	private String baseLocation = System.getProperty("user.dir")
			+ File.separator;

	public void setBaseLocation(String location) {
		this.baseLocation = location;
	}

	public String getBaseLocation() {
		return this.baseLocation;
	}
	public static void main(String[] args) {

		System.out.println(Locator.getInstance().getBaseLocation());
	}
}
