package com.uns.paysys.modules.sys.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.nio.charset.CodingErrorAction;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.Security;
import java.security.cert.CertificateException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.ParseException;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.config.ConnectionConfig;
import org.apache.http.config.MessageConstraints;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.config.SocketConfig;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLContexts;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.params.CoreConnectionPNames;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.uns.paysys.modules.merc.form.InputStreamB;


public class HttpClientUtils {

	private static final Logger log = Logger.getLogger(HttpClientUtils.class);
	
	private static final Logger logger = Logger.getLogger(HttpClientUtils.class);
	

	private static PoolingHttpClientConnectionManager connManager = null;
	private static CloseableHttpClient httpclient = null;
	
	
	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 200;
	/**
	 * 每个路由最大连接数
	 */
	public final static int MAX_ROUTE_CONNECTIONS = 20;
	
	public final static int WAIT_TIMEOUT = 30000;
	/**
	 * 连接超时时间
	 */
	public final static int CONNECT_TIMEOUT = 60000;
	/**
	 * 读取超时时间
	 */
	public final static int READ_TIMEOUT = 45000;

//	public static String get(String url) {
//		return send("get", url);
//	}
//
//	public static String post(String url) {
//		return send("post", url);
//	}




	@SuppressWarnings("deprecation")
	public static <T> T sendJson(String url, Map<String,Object> parameters,InputStream fileInputStream, Class<T> clazz) throws Exception {
		org.apache.http.client.HttpClient httpclient = new DefaultHttpClient();
		try{
			org.apache.http.client.methods.HttpPost httppost = new HttpPost(url);
			
			httppost.getParams().setIntParameter(
					CoreConnectionPNames.SO_TIMEOUT, 10000);
			httppost.getParams().setIntParameter(
					CoreConnectionPNames.CONNECTION_TIMEOUT, 10000);
			
			String fileName = (String) parameters.get("fileName");
			String lsId = (String) parameters.get("lsid");
			StringBody fileType = new StringBody((String) parameters.get("fileType"));
			StringBody lsid = new StringBody(lsId);
			StringBody bodyFileName = new StringBody(fileName,Charset.forName("UTF-8"));
			MultipartEntity reqEntity = new MultipartEntity();
			InputStreamB bin = new InputStreamB(fileInputStream, URLEncoder.encode(fileName, "UTF-8"));
			
			reqEntity.addPart("bin", bin);
			reqEntity.addPart("fileType", fileType);
			reqEntity.addPart("fileName",bodyFileName);
			reqEntity.addPart("lsId", lsid);
			
			httppost.setEntity(reqEntity);
			HttpResponse response = httpclient.execute(httppost);
			HttpEntity resEntity = response.getEntity();
			StatusLine status = response.getStatusLine();
			if (status != null && status.getStatusCode() == HttpStatus.SC_OK) {
				String ret = new String(EntityUtils.toByteArray(resEntity));
				JSONArray array = JSONArray.fromObject(ret);
				
				for (Object json : array) {
					JSONObject b = JSONObject.fromObject(json);
					Iterator keyIter = b.keys();
					while( keyIter.hasNext())
	
					 {
					 String key = (String)keyIter.next();
					 String value =String.valueOf(b.get(key));
	
					 System.out.println(key+"="+value);
	
					 }
				}
				return (T) ret;
			}
		}catch (IOException e) {
			
		}catch(Exception ee){
			ee.printStackTrace();
		}
		finally {
			try {
				httpclient.getConnectionManager().shutdown();
			} catch (Exception ignore) {
			}
		}
		return null;
		
	}
	
	

}