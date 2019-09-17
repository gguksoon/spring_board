<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<ul class="sidebar navbar-nav">
	<li class="nav-item active">
		<a class="nav-link" href="${cp }/manageBoard">
			<i class="fas fa-wrench"></i> 게시판 관리
		</a>
	</li>
	
	<c:forEach items="${boardList}" var="board">
		<c:if test="${board.boardStatus == 1 }">
			<li class="nav-item active">
				<a class="nav-link" href="${cp }/postPagingList?boardSeq=${board.boardSeq }">
					<i class="fas fa-genderless"></i> <span>${board.boardNm }</span>
				</a>
			</li>
		</c:if>
	</c:forEach>
</ul>