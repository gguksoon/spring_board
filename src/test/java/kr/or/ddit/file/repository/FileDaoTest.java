package kr.or.ddit.file.repository;

import static org.junit.Assert.assertEquals;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;

import kr.or.ddit.config.test.WebTestConfig;
import kr.or.ddit.file.model.File;

public class FileDaoTest extends WebTestConfig {
	
	@Resource(name = "fileDao")
	private IFileDao fileDao; 
	
	/**
	* Method : insertFileTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : 파일 추가 테스트
	*/
	@Test
	public void insertFileTest() {
		/***Given***/
		File file = new File(900, "테스트", "테스트", 1);

		/***When***/
		int insertCnt = fileDao.insertFile(file);

		/***Then***/
		assertEquals(1, insertCnt);
	}
	
	/**
	* Method : getFileListTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : postSeq에 해당하는 파일리스트 반환 테스트
	*/
	@Test
	public void getFileListTest() {
		/***Given***/
		int postSeq = 1;

		/***When***/
		List<File> fileList = fileDao.getFileList(postSeq);

		/***Then***/
		assertEquals(5, fileList.size());
	}
	
	/**
	* Method : getFileTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : fileSeq에 해당하는 파일 반환 테스트
	*/
	@Test
	public void getFileTest() {
		/***Given***/
		int fileSeq = 1;

		/***When***/
		File file = fileDao.getFile(fileSeq);

		/***Then***/
		assertEquals("brown.png", file.getFileName());
	}
	
	/**
	* Method : deleteFileTest
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* Method 설명 : fileSeq에 해당하는 파일 삭제 테스트
	*/
	@Test
	public void deleteFileTest() {
		/***Given***/
		int fileSeq = 1;

		/***When***/
		int deleteCnt = fileDao.deleteFile(fileSeq);

		/***Then***/
		assertEquals(1, deleteCnt);
	}

}
