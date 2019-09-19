package kr.or.ddit.file.repository;

import java.util.List;

import javax.annotation.Resource;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.file.model.File;

@Repository
public class FileDao implements IFileDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
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
		return sqlSession.insert("file.insertFile", file);
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
		return sqlSession.selectList("file.getFileList", postSeq);
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
		return sqlSession.selectOne("file.getFile", fileSeq);
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
		return sqlSession.delete("file.deleteFile", fileSeq);
	}

}
