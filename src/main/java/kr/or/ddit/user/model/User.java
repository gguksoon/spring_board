package kr.or.ddit.user.model;

import java.text.SimpleDateFormat;
import java.util.Date;

import kr.or.ddit.encrypt.kisa.sha256.KISA_SHA256;

public class User {
	private String userId;			// 사용자 아이디
	private String userNm;          // 사용자 이름
	private String pass;            // 비밀번호
	private Date reg_dt;            // 사용자 등록일
	private String alias;           // 별명
	private String addr1;           // 주소
	private String addr2;           // 상세주소
	private String zipCode;         // 우편번호
	private String fileName;        // 업로드파일명
	private String realFileName;    // 실제파일경로
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserNm() {
		return userNm;
	}
	public void setUserNm(String userNm) {
		this.userNm = userNm;
	}
	public String getPass() {
		return pass;
	}
	public void setPass(String pass) {
		this.pass = pass;
	}
	public Date getReg_dt() {
		return reg_dt;
	}
	public void setReg_dt(Date reg_dt) {
		this.reg_dt = reg_dt;
	}
	public String getAlias() {
		return alias;
	}
	public void setAlias(String alias) {
		this.alias = alias;
	}
	public String getAddr1() {
		return addr1;
	}
	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}
	public String getAddr2() {
		return addr2;
	}
	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getRealFileName() {
		return realFileName;
	}
	public void setRealFileName(String realFileName) {
		this.realFileName = realFileName;
	}
	
	@Override
	public String toString() {
		return "User [userId=" + userId + ", userNm=" + userNm + ", pass=" + pass + ", reg_dt=" + reg_dt + ", alias="
				+ alias + ", addr1=" + addr1 + ", addr2=" + addr2 + ", zipCode=" + zipCode + ", fileName=" + fileName
				+ ", realFileName=" + realFileName + "]";
	}
	
	// 현재 객체의 시간을 SimpleDateFormat으로 "yyyy-MM-dd"로 변경
	public String getReg_dt_fmt() {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(reg_dt);
	}
	
	// 암호화 문장끼리 비교
	public boolean checkLoginValidate(String userId, String pass) {
		if(userId.equals(this.userId) && KISA_SHA256.encrypt(pass).equals(this.pass)) {
			return true;
		}
		return false;
	}
	
	
}
