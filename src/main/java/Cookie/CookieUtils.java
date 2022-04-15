package Cookie;

import java.util.Iterator;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieUtils {
	public static Cookie add(String name,String value,int hours,HttpServletResponse response) {
		Cookie cookie=new Cookie(name, value);
		cookie.setMaxAge(hours*60*60);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
	}
	
	public static Cookie add1(String name,int hours,HttpServletResponse response) {
		Cookie cookie=new Cookie(name,"");
		cookie.setMaxAge(hours*60*1);
		cookie.setPath("/");
		response.addCookie(cookie);
		return cookie;
		
	}
	
	public static Cookie delete(String name, String value, int hours,
			HttpServletResponse resp) {
			Cookie cookie = new Cookie(name,value);
			cookie.setMaxAge(0);
			resp.addCookie(cookie);
			return null;
	}
	
	public static Cookie eraseCookie(String strCookieName, String strPath) {
	    Cookie cookie = new Cookie(strCookieName, "");
	    cookie.setMaxAge(0);
	    cookie.setPath(strPath);
	 
	    return cookie;
	}
	
	public static String get(String name,HttpServletRequest request) {
		Cookie[] cookies=request.getCookies();
		if(cookies!=null) {
			for (Cookie cookie : cookies) {
				if(cookie.getName().equals(name)) {
					return cookie.getValue();
				}
			}
		}
		return "";
	}
}

