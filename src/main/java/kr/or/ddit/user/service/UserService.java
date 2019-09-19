package kr.or.ddit.user.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.user.model.User;
import kr.or.ddit.user.repository.IUserDao;

@Service
public class UserService implements IUserService {

	@Resource(name = "userDao")
	private IUserDao userDao;
	
	/**
	* Method : getUser
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param userId
	* @return
	* Method 설명 : userId에 해당하는 user객체 반환
	*/
	@Override
	public User getUser(String userId) {
		return userDao.getUser(userId);
	}

}
