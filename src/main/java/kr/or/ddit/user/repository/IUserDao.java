package kr.or.ddit.user.repository;

import kr.or.ddit.user.model.User;

public interface IUserDao {

	/**
	* Method : getUser
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : userId에 해당하는 user객체 반환
	*/
	public User getUser(String userId);
}
