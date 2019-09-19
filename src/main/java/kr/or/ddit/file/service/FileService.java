package kr.or.ddit.file.service;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.file.model.File;
import kr.or.ddit.file.repository.IFileDao;

@Service
public class FileService implements IFileService {

	@Resource(name = "fileDao")
	private IFileDao fileDao;
	
	/**
	* Method : insertFile
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param file
	* @return
	* Method 설명 : 파일 추가
	*/
	@Override
	public int insertFile(File file) {
		return fileDao.insertFile(file);
	}

	/**
	* Method : getFileList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : 한 게시글의 전체 파일 리스트 반환
	*/
	@Override
	public List<File> getFileList(int postSeq) {
		return fileDao.getFileList(postSeq);
	}

	/**
	* Method : getFile
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param fileSeq
	* @return
	* Method 설명 : fileSeq에 해당하는 파일객체 반환 
	*/
	@Override
	public File getFile(int fileSeq) {
		return fileDao.getFile(fileSeq);
	}

	/**
	* Method : deleteFile
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param fileSeq
	* @return
	* Method 설명 : fileSeq에 해당하는 데이터 삭제
	*/
	@Override
	public int deleteFile(int fileSeq) {
		return fileDao.deleteFile(fileSeq);
	}

}
