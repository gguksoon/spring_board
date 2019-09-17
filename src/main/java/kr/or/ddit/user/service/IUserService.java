package kr.or.ddit.user.service;

import kr.or.ddit.user.model.User;

public interface IUserService {

	/**
	* Method : getUser
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : userId에 해당하는 user객체 반환
	*/
	public User getUser(String userId);
}
