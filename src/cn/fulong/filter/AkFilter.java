package cn.fulong.filter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;

public class AkFilter implements Filter {

	public static final List<String> list = new ArrayList<String>();

	static {
		/*** javascript方面注入的漏洞 ****/
		list.add("<div");
		list.add("<style");
		list.add("link</a>");
		list.add("HTTP-EQUIV");
		list.add("<EMBED");
		list.add("<input");
		list.add("<script");
		list.add("alert");
		list.add("prompt");
		list.add("confirm");
		list.add("iframe");
		list.add("ProcessBuilder");
		list.add("VBScript");
		list.add("ActionScript");
		list.add("eval((.*))");
		list.add("document((.*))");
		list.add("[\"'][\\s]*javascript:(.*)[\"']");
		list.add("xss:expreesion");
		list.add("UserAgent");
		list.add("Referer");
		list.add("AcceptLanguage");
		list.add("redirect");
		list.add("COOKIE");
		list.add("cookie");
		list.add("document");
		list.add("\\43");
		list.add("\\u0023");
		list.add("getRuntime");
		list.add("getRequest");
		list.add("getProperty");

		/** 过滤所有给服务器上传文件的漏洞 */
		list.add("java.io.");
		list.add("java.nio.");
		list.add("GETwriter");
		list.add("FileWriter");
		list.add("BufferedWriter");
		list.add("FileOutputStream");
		list.add(".getRealPath");
		list.add(".replaceAll");
		list.add(".getBytes");
		list.add(".getParameter");
		list.add("response.getWriter");
		list.add("com.opensymphony.xwork2");
		list.add("dispatcher.HttpServletRequest");
	}

	public AkFilter() {
	}

	public void destroy() {
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
			ServletException {

		String attackStr = buildOriginalURL(request);
		HttpServletResponse httpresponse = (HttpServletResponse) response;
		HttpServletRequest httpRequest = (HttpServletRequest) request;

		String url = httpRequest.getScheme() + "://";
		url = url + httpRequest.getHeader("host") + httpRequest.getContextPath();
		Enumeration paramNames = request.getParameterNames();
		while (paramNames.hasMoreElements()) {
			String name = (String) paramNames.nextElement();
			String[] values = request.getParameterValues(name);
			String[] akStrs = new String[values.length + 1];
			System.arraycopy(values, 0, akStrs, 0, values.length);
			akStrs[values.length] = name;
			for (int i = 0; i < list.size(); i++) {
				String akStr = list.get(i);
				for (int j = 0; j < akStrs.length; j++) {
					if (StringUtils.indexOf(akStrs[j].toUpperCase(), akStr.toUpperCase()) != -1) {
						httpresponse.sendRedirect(url + "/303.jsp");
						return;
					}else if(isScriptedAttributeValue(akStrs[j])){
						httpresponse.sendRedirect(url + "/303.jsp");
						return;
					}
				}
			}
		}
		chain.doFilter(request, response);
	}

	public void init(FilterConfig fConfig) throws ServletException {

	}

	private String buildOriginalURL(ServletRequest request) {
		StringBuffer originalURL = new StringBuffer();
		Map parameters = request.getParameterMap();
		if (parameters != null && parameters.size() > 0) {
			originalURL.append("?");
			for (Iterator iter = parameters.keySet().iterator(); iter.hasNext();) {
				String key = (String) iter.next();
				String[] values = (String[]) parameters.get(key);
				for (int i = 0; i < values.length; i++) {
					originalURL.append(key).append("=").append(values[i]).append("&");
				}
			}
		}
		return originalURL.toString();
	}
	private boolean isScriptedAttributeValue( String attrValue ) {  
        attrValue = decode( attrValue );  
        attrValue = attrValue.trim().toLowerCase();  
        if ( attrValue.contains( "javascript:" )) {  
            return true;  
        }  
        if ( attrValue.contains( "mocha:" )) {  
            return true;  
        }  
        if ( attrValue.contains( "eval(" )) {  
            return true;  
        }  
        if ( attrValue.contains( "vbscript:" )) {  
            return true;  
        }  
        if ( attrValue.contains( "livescript:" )) {  
            return true;  
        }  
        if ( attrValue.contains( "expression(" )) { 
            return true;  
        }  
        if ( attrValue.contains( "url(" )) {  
            return true;  
        }  
        if ( attrValue.contains( "&{" )) {  
            return true;  
        }  
        if ( attrValue.contains( "&#" )) {  
            return true;  
        }  
        if ( attrValue.contains( "base64" )) {  
            return true;  
        } 
        return false;  
    }  
	
	private String decode( String value ) {  
        value = value.replace("\u0000", "" );  
        value = value.replace("\u0001", "" );  
        value = value.replace("\u0002", "" );  
        value = value.replace("\u0003", "" );  
        value = value.replace("\u0004", "" );  
        value = value.replace("\u0005", "" );  
        value = value.replace("\u0006", "" );  
        value = value.replace("\u0007", "" );  
        value = value.replace("\u0008", "" );  
        value = value.replace("\u0009", "" );  
        value = value.replace("\n", "" );  
        value = value.replace("\u000B", "" );  
        value = value.replace("\u000C", "" );  
        value = value.replace("\r", "" );  
        value = value.replace("\u000E", "" );  
        value = value.replace("\u000F", "" );  
        value = value.replace("\u0010", "" );  
        value = value.replace("\u0011", "" );  
        value = value.replace("\u0012", "" );  
        value = value.replace("\u0013", "" );  
        value = value.replace("\u0014", "" );  
        value = value.replace("\u0015", "" );  
        value = value.replace("\u0016", "" );  
        value = value.replace("\u0017", "" );  
        value = value.replace("\u0018", "" );  
        value = value.replace("\u0019", "" );  
        value = value.replace("\u001A", "" );  
        value = value.replace("\u001B", "" );  
        value = value.replace("\u001C", "" );  
        value = value.replace("\u001D", "" );  
        value = value.replace("\u001E", "" );  
        value = value.replace("\u001F", "" );  
        return value;  
    }  
}
