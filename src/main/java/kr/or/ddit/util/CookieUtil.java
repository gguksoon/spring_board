package kr.or.ddit.util;

public class CookieUtil {

	/**
	* Method : getCookie
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param cookieString
	* @param string
	* @return
	* Method 설명 : 쿠키 문자열로부터 특정 쿠키의 값을 반환한다.
	*/
	public static String getCookie(String cookieString, String cookieId) {

		String[] strArr = cookieString.split("; ");
		
		for(int i = 0; i < strArr.length; i++) {
			if(strArr[i].contains(cookieId)) {
				return strArr[i].substring(strArr[i].indexOf("=") + 1, strArr[i].length()); 
			}
		}
		
		return null;
	}

}
