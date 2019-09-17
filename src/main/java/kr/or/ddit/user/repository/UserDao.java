package kr.or.ddit.user.repository;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.user.model.User;

@Repository
public class UserDao implements IUserDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
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
		return sqlSession.selectOne("user.getUser", userId);
	}

}
