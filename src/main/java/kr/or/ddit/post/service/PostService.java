package kr.or.ddit.post.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import kr.or.ddit.post.model.Post;
import kr.or.ddit.post.repository.IPostDao;

@Service
public class PostService implements IPostService {

	@Resource(name = "postDao")
	private IPostDao postDao;
	
	/**
	* Method : getPostPagingList
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param map
	* @return
	* Method 설명 : 게시글 페이징리스트 반환
	*/
	@Override
	public Map<String, Object> getPostPagingList(Map<String, Object> map) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		
		String boardSeq = "" + map.get("boardSeq");
		List<Map> postList = postDao.getPostPagingList(map);
		int totalCnt = postDao.getPostTotalCnt(boardSeq);
		
		int pagesize = (int) map.get("pagesize");
		
		resultMap.put("postList", postList);
		resultMap.put("paginationSize", (int)Math.ceil((double)totalCnt / pagesize));
		
		return resultMap;
	}

	/**
	* Method : getPost
	* 작성자 : JO MIN SOO
	* 변경이력 :
	* @param postSeq
	* @return
	* Method 설명 : postSeq에 해당하는 게시글 반환
	*/
	@Override
	public Post getPost(int postSeq) {
		return postDao.getPost(postSeq);
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
		
		int nextPostSeq = postDao.getPostNextSeq();
		post.setPostSeq(nextPostSeq);
		
		if(post.getPostGn() == 0)
			post.setPostGn(postDao.getPostGnNextSeq());
			
		postDao.insertPost(post);
		
		return nextPostSeq;
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
		return postDao.deletePost(postSeq);
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
		return postDao.updatePost(post);
	}

}
