package cn.jsi.exp.outlying.analyz;

import java.util.ArrayList;
import java.util.HashMap;

public class ErrorEventController {
	public static HashMap<String, Integer> errorTypeMap_input = new HashMap<String, Integer>();
	public static HashMap<String, Integer> errorTypeMap_outlying=new  HashMap<String, Integer>();
	public static ArrayList<String> newlist=new ArrayList<String>();
	public static void init() {
		errorTypeMap_input = new HashMap<String, Integer>();
		String[] errors = new String[] { "snmpgetattack.","named.","xlock.","smurf.","ipsweep.","multihop.","xsnoop.","sendmail.","guess_passwd.","saint.","buffer_overflo","portsweep.","pod.","apache2.","phf.","udpstorm.","warezmaster.","perl.","satan.","xterm.","mscan.","processtable.","ps.","nmap.","rootkit.","neptune.","loadmodule.","imap.","back.","httptunnel.","worm.","mailbomb.","teardrop.","snmpguess.","land.","teardrop.","ftp_write.","sqlattack."};
		for (String tmps : errors) {
			errorTypeMap_input.put(tmps, 0);
		}
	}

}
