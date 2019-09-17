package kr.or.ddit.file.repository;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import kr.or.ddit.file.model.File;

public class FileDao implements IFileDao {

	/**
	* Method : insertFile
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param file
	* @return
	* Method 설명 : 파일 추가
	*/
	@Override
	public int insertFile(SqlSession ss, File file) {
		return ss.insert("file.insertFile", file);
	}

	/**
	* Method : getFileList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param postSeq
	* @return
	* Method 설명 : 한 게시글의 전체 파일 리스트 반환
	*/
	@Override
	public List<File> getFileList(SqlSession ss, int postSeq) {
		return ss.selectList("file.getFileList", postSeq);
	}

	/**
	* Method : getFile
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param fileSeq
	* @return
	* Method 설명 : fileSeq에 해당하는 파일객체 반환
	*/
	@Override
	public File getFile(SqlSession ss, int fileSeq) {
		return ss.selectOne("file.getFile", fileSeq);
	}

	/**
	* Method : deleteFile
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param ss
	* @param fileSeq
	* @return
	* Method 설명 : fileSeq에 해당하는 데이터 삭제
	*/
	@Override
	public int deleteFile(SqlSession ss, int fileSeq) {
		return ss.delete("file.deleteFile", fileSeq);
	}

}
