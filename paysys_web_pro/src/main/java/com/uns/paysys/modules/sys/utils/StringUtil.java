/**
 * Copyright ? 2004 Shanghai je01 Co. Ltd.
 * All right reserved
 */
package com.uns.paysys.modules.sys.utils;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author cjh 2006-05-18
 * 
 *         提供对String对象的操作
 */
public class StringUtil {
	/**
	 * 判断str是否为空
	 * 
	 * @author:kevin
	 * @date:2004-12-22
	 * @param str
	 *            String
	 * @return boolean true:空;false:非空
	 */
	public static boolean isStrEmpty(String str) {
		return ((str == null) || (str.trim().equals("")));
	}

	/**
	 * 判断是否是手机号码
	 */
	public static boolean isMobile(String str) {
		Pattern p = null; // 正则表达式

		Matcher m = null; // 操作的字符串

		boolean b;

		p = Pattern.compile("^1(3|5)[0-9]{9}$");

		m = p.matcher(str);

		b = m.matches();

		return b;
	}

	/**
	 * 判断是否是数字
	 */
	public static boolean isNumber(String str) {
		Pattern p = null; // 正则表达式

		Matcher m = null; // 操作的字符串

		boolean b;

		p = Pattern.compile("\\d+");

		m = p.matcher(str);

		b = m.matches();

		return b;
	}

	public static boolean isEmail(String str) {
		if (str == null) {
			return false;
		}
		Pattern p = null; // 正则表达式

		Matcher m = null; // 操作的字符串

		boolean b;

		p = Pattern.compile("@");

		m = p.matcher(str);

		b = m.find();

		return b;
	}

	/**
	 * 判断指定字符串是否匹配指定正则表达式
	 * 
	 * @param s
	 *            字符串
	 * @param pattern
	 *            正则表达式
	 * @return true if 指定字符串匹配指定正则表达式, otherwise false
	 */
	public static boolean matchRegex(String s, String pattern) {
		Matcher matcher = Pattern.compile(pattern).matcher(s);
		return matcher.matches();
	}

	public static boolean isIdcardNo(String idcardNo) {
		if (idcardNo == null || idcardNo.length() == 0)
			return false;
		String pattern = "([0-9]{8}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])[0-9]{3})"
				+ "|"
				+ "([0-9]{6}(1[8-9]|2[0-1])[0-9]{2}(0[1-9]|1[0-2])(0[1-9]|[1-2][0-9]|3[0-1])[0-9]{3}[0-9xX])";
		return matchRegex(idcardNo, pattern);
	}

	public static boolean isBankcardNo(String bankCardNo) {
		if (bankCardNo == null || bankCardNo.length() == 0)
			return false;
		String pattern = "([0-9]{16,32})";
		return matchRegex(bankCardNo, pattern);
	}

	/**
	 * 获取两个字符串相同的开始部分子串。若两个字符串没有相同的开始部分子串，则返回""；若有一个字符串为null，则返回null<br/>
	 * e.g. sameStart("abc","abd") will return "ab"
	 * 
	 * @param s1
	 *            字符串1
	 * @param s2
	 *            字符串2
	 * @return 两个字符串相同的开始部分子串
	 */
	public static String sameStart(String s1, String s2) {
		if (s1 == null || s2 == null)
			return null;
		String s = "";
		int length = s1.length() <= s2.length() ? s1.length() : s2.length();
		for (int i = 0; i < length; i++) {
			String c = s1.substring(i, i + 1);
			if (c.equals(s2.substring(i, i + 1))) {
				s += c;
			}
		}
		return s;
	}

	public static String replace(String parentStr, String ch, String rep) {
		int i = parentStr.indexOf(ch);
		StringBuffer sb = new StringBuffer();
		if (i == -1)
			return parentStr;
		sb.append(parentStr.substring(0, i) + rep);
		if (i + ch.length() < parentStr.length())
			sb.append(replace(
					parentStr.substring(i + ch.length(), parentStr.length()),
					ch, rep));
		return sb.toString();
	}

	public static String replaceFirstBr(String parentStr) {
		if (parentStr.startsWith("<br/>")) {
			parentStr = parentStr.substring(5);
		}
		if (parentStr.startsWith("&nbsp;")) {
			parentStr = parentStr.substring(6);
		}
		if (parentStr.startsWith("<br/>") || parentStr.startsWith("&nbsp;")) {
			replaceFirstBr(parentStr);
		}
		return parentStr;
	}

	/**
	 * 应用正则表达式过滤HTML标签
	 * 
	 * @param parentStr
	 *            需要过滤的字符串
	 * @return 不含html标签的字符串
	 */
	public static String replaceHtml(String parentStr) {
		// 定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
		String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>";
		// 定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
		String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>";
		// 定义HTML标签的正则表达式
		// String regEx_html = "<[^>]+>";//这个正则表达式对中文不支持
		String regEx_html = "<.+?>";

		// 含html标签的字符串
		String htmlStr = parentStr;
		// 不含html标签的字符串
		String textStr = "";
		Pattern pattern;
		Matcher matcher;

		pattern = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(htmlStr);
		htmlStr = matcher.replaceAll("");

		pattern = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(htmlStr);
		htmlStr = matcher.replaceAll("");

		pattern = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		matcher = pattern.matcher(htmlStr);
		htmlStr = matcher.replaceAll("");

		textStr = htmlStr;
		return textStr;
	}

	public static boolean isFixedTel(String telephone) {
		if (telephone == null || telephone.length() == 0)
			return false;
		String pattern = "(010[0-9]{8})|(0[2-9][0-9]{9,10})";
		return matchRegex(telephone, pattern);
	}

	/**
	 * 格式化double型数字为字符串
	 * 
	 * @param d
	 *            数字
	 * @param pattern
	 *            格式，如#0.##
	 * @return 格式化字符串
	 */
	public static String double2String(double d, String pattern) {
		DecimalFormat formator = new DecimalFormat(pattern);
		return formator.format(d);
	}

	/**
	 * 截取身份证号码,后4位显示,前面隐藏
	 * 
	 * @param str
	 * @return
	 */
	public static String hiddenStr(String str) {

		StringBuffer string = new StringBuffer();
		String bb = str.substring(str.length() - 4, str.length());

		for (int i = 1; i <= str.length() - 4; i++) {
			string.append("*");
		}
		string.append(bb);
		return string.toString();

	}

	/**
	 * 将回车换行转换为&lt;BR&gt;
	 * 
	 * @param _strData
	 * @return String
	 */
	public static String convertReturnToBr(String _strData) {
		if (isStrEmpty(_strData)) {
			return "";
		}
		_strData = _strData.trim();

		int intLen = _strData.length();
		boolean bolFlg = false;
		String strTmp = "";
		for (int i = 0; i < intLen; i++) {
			String strChar = "";
			strChar = _strData.substring(i, i + 1);
			if (bolFlg && "\n".equals(strChar)) {
				bolFlg = false;
				strTmp = strTmp + "<BR>";
				continue;
			}
			if ("\r".equals(strChar)) {
				bolFlg = true;
			} else {
				strTmp = strTmp + strChar;
			}

		}
		return strTmp;
	}

	public static String getNotNullValue(String string) {
		return string == null ? "" : string;
	}

	public static String getRandomPwd(int digit) {
		String result = "";
		List letters = new ArrayList();
		Random random = new Random();
		for (int upper = 65; upper <= 90; upper++) { // 大写
			letters.add(String.valueOf((char) upper));
		}
		for (int lower = 97; lower <= 122; lower++) { // 小写
			letters.add(String.valueOf((char) lower));
		}
		for (int i = 0; i < digit; i++) {
			if (random.nextBoolean()) { // 数字
				String rand = String.valueOf(random.nextInt(10));
				result += rand;
			} else { // 字符
				String rand = (String) letters.get(random.nextInt(52));
				result += rand;
			}

		}
		return result;
	}
	
	/**
	 * 将用指定符号分隔的数据解析为List
	 * 
	 * @param _str
	 *            需要拆分的字符串
	 * @param _dot
	 *            分隔符
	 * @return List
	 */
	public static List convertStringToList(String _str, String _dot) {
		if (_str == null)
			return null;
		StringTokenizer st = new StringTokenizer(_str, _dot);

		List result = new ArrayList();

		while (st.hasMoreTokens()) {
			result.add(st.nextToken());
		}
		return result;
	}

	/**
	 * 根据短信内容的长度决定是否附加银生域名(unspay.com) 短信发送最大长度为59(中、英、数字均算作一个长度)
	 * 如短信内容加入银生域名不会超出长度，则在结尾附加域名
	 * 
	 * @param context
	 * @return
	 */
	public static String appendUnspayUrl(String context) {
		if (context == null || context.trim().length() == 0) {
			return context;
		}
		int intMaxLen = 59; // 短信最大长度
		int intContextLen = context.length(); // 发送内容长度
		String strAppendUrl = "unspay.com"; // 附加域名
		int intAppendLen = strAppendUrl.length(); // 附加域名长度

		if (intMaxLen - intContextLen > intAppendLen) {
			// 短信内容长度可以附加域名
			context += strAppendUrl;
		}
		return context;
	}

	/**
	 * 将数组转换成字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static String array2String(String arr[]) {
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < arr.length; i++) {
			sb.append(",").append(arr[i]);
		}
		return sb.substring(1);
	}

	/**
	 * 将数组转换成字符串
	 * 
	 * @param arr
	 * @return
	 */
	public static Boolean arrContainStr(String arrs, String str) {
		Boolean flag = false;
		if (arrs != null) {
			for (String string : arrs.split(",")) {
				if (str.equals(string)) {
					flag = true;
				}
			}
		}
		return flag;
	}
	
	/**
	 * 正则匹配
	 * @param param
	 * @param pattern
	 * @return
	 * @throws Exception
	 */
	public static boolean isBool(String param , String pattern) throws Exception{
		Pattern p = Pattern.compile(pattern);
		Matcher m = p.matcher(param);
		Boolean b = m.matches();
		return b;
	}
	
	/**
	 * 安全显示身份证
	 */
	public static String subIDCard(String str){
		if("null".equals(str)){
			return "";
		}
		if(str != null && str.length()>10){
			return str.substring(0, 6) + "****" + str.substring(str.length()-4, str.length());
		}else{
			return str;
		}
	}
	
	/**
	 * 安全显示取银行卡
	 */
	public static String subBankCard(String str){
		if("null".equals(str)){
			return "";
		}
		if(str != null && str.length()>10){
			return str.substring(0, 6) + "****" + str.substring(str.length()-4, str.length());
		}else{
			return str;
		}
	}
	
	/**
	 * 安全显示姓名
	 */
	public static String subName(String str){
		if("null".equals(str)){
			return "";
		}
		if(str != null && str.length()>1){
			return "*" + str.substring(1);
		}else{
			return str;
		}
	}
	
	/**
	 * 安全显示手机号
	 */
	public static String subTel(String str){
		if("null".equals(str)){
			return "";
		}
		if("null".equals(str)){
			return "";
		}
		if(str != null && str.length()>=6){
			return str.substring(0, 3) + "****" + str.substring(str.length()-3, str.length());
		}else{
			return str;
		}
	}
	
	/**
	 * 安全显示邮箱
	 */
	public static String subEmail(String str){
		if("null".equals(str)){
			return "";
		}
		if(str != null && str.length()>=3){
			int temp = str.indexOf("@");
			if(temp!=-1){
				String a = str.substring(0,temp);
				if(a.length()>3){
					return str.substring(0, 3) + "***" + str.substring(temp, str.length());
				}else{
					return str;
				}
			}else{
				return str;
			}
		}else{
			return str;
		}
	}
	
	/**
	 * 图片格式验证:bmp,jpg,tiff,gif,pcx,tga,exif,fpx,svg,psd,cdr,pcd,dxf,ufo,eps,ai,raw
	 */
	public static boolean valPictrue(String format){
		if(format != null && (
			"bmp".equalsIgnoreCase(format) ||	
			"jpg".equalsIgnoreCase(format) ||	
			"tiff".equalsIgnoreCase(format) ||	
			"gif".equalsIgnoreCase(format) ||	
			"pcx".equalsIgnoreCase(format) ||	
			"tga".equalsIgnoreCase(format) ||	
			"exif".equalsIgnoreCase(format) ||	
			"fpx".equalsIgnoreCase(format) ||	
			"svg".equalsIgnoreCase(format) ||	
			"psd".equalsIgnoreCase(format) ||	
			"cdr".equalsIgnoreCase(format) ||	
			"pcd".equalsIgnoreCase(format) ||	
			"dxf".equalsIgnoreCase(format) ||	
			"ufo".equalsIgnoreCase(format) ||	
			"eps".equalsIgnoreCase(format) ||	
			"ai".equalsIgnoreCase(format) ||	
			"raw".equalsIgnoreCase(format))){
			return true;
		}
		return false;
	}
	
	/**
	 * 验证密码：  必须为8-20位的数字和字母组合
	 */
	public static boolean valPassword(String p){
		String regex1 = "[a-zA-Z0-9]{8,20}";  
		String regex2 = "[a-zA-Z]{8,20}";  
		String regex3 = "[0-9]{8,20}";  
        Pattern pattern1 = Pattern.compile(regex1);  
        Pattern pattern2 = Pattern.compile(regex2);  
        Pattern pattern3 = Pattern.compile(regex3);  
        Matcher matcher1 = pattern1.matcher(p);  
        Matcher matcher2 = pattern2.matcher(p);  
        Matcher matcher3 = pattern3.matcher(p);  
        if(matcher1.matches() && !matcher2.matches() && !matcher3.matches()){
        	return true;
        }
        return false;
	}
}