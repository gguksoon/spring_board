package kr.or.ddit.post.service;

import java.util.Map;

import kr.or.ddit.post.model.Post;

public interface IPostService {

	/**
	* Method : getPostPagingList
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징리스트 반환
	*/
	public Map<String, Object> getPostPagingList(Map<String, Object> map);

	/**
	* Method : getPost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 게시글 반환
	*/
	public Post getPost(int postSeq);

	/**
	* Method : insertPost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param post
	* @return
	* Method 설명 : 게시글 추가 
	*/
	public int insertPost(Post post);

	/**
	* Method : deletePost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : 게시글 삭제
	*/
	public int deletePost(int postSeq);

	/**
	* Method : updatePost
	* 작성자 : Jo Min-Soo
	* 변경이력 :
	* @param post
	* @return
	* Method 설명 : 게시글 수정 
	*/
	public int updatePost(Post post);
}
