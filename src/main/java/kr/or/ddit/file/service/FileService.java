package kr.or.ddit.file.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.file.model.File;
import kr.or.ddit.file.repository.FileDao;
import kr.or.ddit.file.repository.IFileDao;
import kr.or.ddit.util.MybatisUtil;

public class FileService implements IFileService {

	IFileDao fileDao;
	
	public FileService() {
		fileDao = new FileDao();
	}
	
	/**
	* Method : insertFile
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param file
	* @return
	* Method 설명 : 파일 추가
	*/
	@Override
	public int insertFile(File file) {
		SqlSession ss = MybatisUtil.getSession();
		int insertCnt = fileDao.insertFile(ss, file);
		
		ss.commit();
		ss.close();
		
		return insertCnt;
	}

	/**
	* Method : getFileList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : 한 게시글의 전체 파일 리스트 반환
	*/
	@Override
	public List<File> getFileList(int postSeq) {
		SqlSession ss = MybatisUtil.getSession();
		List<File> fileList = fileDao.getFileList(ss, postSeq);
		
		ss.close();
		
		return fileList;

	}

	/**
	* Method : getFile
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param fileSeq
	* @return
	* Method 설명 : fileSeq에 해당하는 파일객체 반환 
	*/
	@Override
	public File getFile(int fileSeq) {
		SqlSession ss = MybatisUtil.getSession();
		File file = fileDao.getFile(ss, fileSeq);
		
		ss.close();
		
		return file;
	}

	/**
	* Method : deleteFile
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param fileSeq
	* @return
	* Method 설명 : fileSeq에 해당하는 데이터 삭제
	*/
	@Override
	public int deleteFile(int fileSeq) {
		SqlSession ss = MybatisUtil.getSession();
		int deleteCnt = fileDao.deleteFile(ss, fileSeq);
		
		ss.commit();
		ss.close();
		
		return deleteCnt;
	}

}
