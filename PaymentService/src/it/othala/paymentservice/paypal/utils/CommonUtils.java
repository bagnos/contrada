package it.othala.paymentservice.paypal.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.NumberFormat;
import java.util.Locale;

public class CommonUtils {
	
	private static NumberFormat n = NumberFormat.getCurrencyInstance(Locale.ITALY);

	public static String getImporto(BigDecimal value) {
		// TODO Auto-generated method stub
		
		String s = n.format(value.setScale(2, RoundingMode.HALF_UP).doubleValue());
		return s;
	}
	
	public static final int SIZE_SEO_TITLE = 50;
	public static final int SIZE_SEO_DESCRIPTION = 150;
	public static String getSEODescription(String description) {
		return getSEOTag(description, SIZE_SEO_DESCRIPTION);
	}

	
	public static String getSEOTitle(String description) {
		return getSEOTag(description, SIZE_SEO_TITLE).replaceAll("\\.","").replaceAll(",", "");
	}
	
	private static String getSEOTag(String description, int SEO_SIZE) {
		
		if (description != null) {
			if (description.length() > SEO_SIZE) {
				description= description.substring(0, SEO_SIZE);
			}
			return description.trim();
		}
		else
		{
			return "";
		}
	}
	
	


	public static String getImportoNoCurrency(BigDecimal value) {
		// TODO Auto-generated method stub
		BigDecimal scaleBD = value.setScale(2, RoundingMode.HALF_UP);
		return scaleBD.toString();

	}

	public static String post(String url, String payload) throws IOException

	{
		InputStream is = null;
		Reader reader = null;
		try {
			HttpURLConnection conn = null;
			URL mcUrl = new URL(url);
			conn = ((HttpURLConnection) mcUrl.openConnection());
			conn.setDoOutput(true);
			conn.setConnectTimeout(15000);
			conn.setReadTimeout(15000);
			conn.setRequestMethod("POST");

			byte[] bytes = payload.getBytes("UTF-8");
			conn.addRequestProperty("Content-Type", "application/json; charset=utf-8");
			conn.setRequestProperty("Content-Length", Integer.toString(bytes.length));
			conn.getOutputStream().write(bytes);
			

			is = conn.getResponseCode() == 200 ? conn.getInputStream() : conn.getErrorStream();
			reader = new InputStreamReader(is, "UTF-8");
			StringBuilder sb = new StringBuilder();
			char[] buf = new char[4096];
			int cnt;
			while ((cnt = reader.read(buf)) >= 0) {
				sb.append(buf, 0, cnt);
			}
			return sb.toString();
		} finally {
			try {
				if (reader != null) {
					reader.close();
				}
				if (is != null) {
					is.close();
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}


}
