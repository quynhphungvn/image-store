package quynh.java.webapp.imagestore.support.util;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

public class RequestUtil {
	public static int getParameterInt(HttpServletRequest request, String paramName) {
		String paramValue = request.getParameter(paramName);
		if (StringChecker.isInteger(paramValue))
			return Integer.parseInt(paramValue);
		return 0;
	}
	
	public static String convertPartToString(Part part) throws IOException {
		BufferedInputStream bis = new BufferedInputStream(part.getInputStream());
		ByteArrayOutputStream buf = new ByteArrayOutputStream();
		for (int result = bis.read(); result != -1; result = bis.read()) {
		    buf.write((byte) result);
		}
		// StandardCharsets.UTF_8.name() > JDK 7
		return buf.toString("UTF-8");
	}
}
