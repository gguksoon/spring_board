package kr.or.ddit.user.service;

import static org.junit.Assert.assertEquals;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.user.model.User;

public class UserServiceTest extends WebTestConfig {

	@Resource(name = "userService")
	private IUserService userService;
	
	/**
	* Method : getUserTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : userId에 해당하는 user객체 반환 테스트
	*/
	@Test
	public void getUserTest() {
		/***Given***/
		String userId = "brown";

		/***When***/
		User user = userService.getUser(userId);

		/***Then***/
		assertEquals("브라운", user.getUserNm());
		assertEquals("곰", user.getAlias());
	}

}
