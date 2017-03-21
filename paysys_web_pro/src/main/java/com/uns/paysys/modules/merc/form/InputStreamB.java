package com.uns.paysys.modules.merc.form;
import java.io.InputStream;

import org.apache.http.entity.mime.content.InputStreamBody;


public class InputStreamB extends InputStreamBody {

	public InputStreamB(InputStream in, String filename) {
		super(in, filename);
		// TODO Auto-generated constructor stub
	}

	public InputStreamB(InputStream in, String mimeType, String filename) {
		super(in, mimeType, filename);
		// TODO Auto-generated constructor stub
	}

	/** 
	 * @Description: TODO(这里用一句话描述这个方法的作用)
	 * @author duchunsheng 
	 * @date 2014-10-29 下午2:25:15  
	 * @param args
	 * @return void    返回类型 
	 * @throws 
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	@Override
	public String getCharset() {
		// TODO Auto-generated method stub
		return "utf-8";
	}

}
