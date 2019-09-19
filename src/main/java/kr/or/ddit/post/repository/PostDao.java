package kr.or.ddit.post.repository;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import kr.or.ddit.post.model.Post;

@Repository
public class PostDao implements IPostDao {

	@Resource(name="sqlSessionTemplate")
	private SqlSessionTemplate sqlSession;
	
	/**
	* Method : getPostPagingList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징리스트 반환
	*/
	@Override
	public List<Map> getPostPagingList(Map<String, Object> map) {
		return sqlSession.selectList("post.getPostPagingList", map);
	}

	/**
	* Method : getPostTotalCnt
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param boardSeq
	* @return
	* Method 설명 : 전체 게시글 갯수 반환
	*/
	@Override
	public int getPostTotalCnt(String boardSeq) {
		return sqlSession.selectOne("post.getPostTotalCnt", boardSeq);
	}

	/**
	* Method : getPost
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 post객체 반환
	*/
	@Override
	public Post getPost(int postSeq) {
		return sqlSession.selectOne("post.getPost", postSeq);
	}

	/**
	* Method : insertPost
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param post
	* @return
	* Method 설명 : 게시글 추가
	*/
	@Override
	public int insertPost(Post post) {
		return sqlSession.insert("post.insertPost", post);
	}

	/**
	* Method : getPostNextSeq
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 Seq 생성
	*/
	@Override
	public int getPostNextSeq() {
		return sqlSession.selectOne("post.getPostNextSeq");
	}

	/**
	* Method : getPostGnNextSeq
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @return
	* Method 설명 : 게시글 Gn Seq 생성
	*/
	@Override
	public int getPostGnNextSeq() {
		return sqlSession.selectOne("post.getPostGnNextSeq");
	}

	/**
	* Method : deletePost
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : 게시글 삭제
	*/
	@Override
	public int deletePost(int postSeq) {
		return sqlSession.update("post.deletePost", postSeq);
	}

	/**
	* Method : updatePost
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param post
	* @return
	* Method 설명 : 게시글 수정
	*/
	@Override
	public int updatePost(Post post) {
		return sqlSession.update("post.updatePost", post);
	}

}
