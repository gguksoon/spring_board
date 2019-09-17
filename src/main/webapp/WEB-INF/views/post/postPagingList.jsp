<%@page import="kr.or.ddit.user.model.User"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
<meta name="description" content="">
<meta name="author" content="">
	
<title>gguksoon_Spring-Board</title>

<!-- CSS -->
<%@ include file="/WEB-INF/views/commonJsp/cssLib.jsp" %>

<style>
	tr:nth-child(odd) {
		background: #FAFAFA;
	}
	
	.userTr:hover {
		background: #FFCA6C;
		cursor: pointer;
	}
</style>

<script src="${cp }/js/jquery-3.4.1.min.js"></script>
<script>
	$(document).ready(function() {
		
		// 사용자 정보 클릭 시 이벤트 핸들러
		$(".userTr").on("click", function() {
			// 클릭된 tr태그의 자식태그(td)중 첫번째 자식의 텍스트 문자열
			var tdText = $(this).children().eq(1).text();
			console.log("tdText: " + tdText);
			
			// input 태그에 저장된 값 확인
			var inputValue = $(this).find("input").val();
			console.log("inputValue: " + inputValue);
			
			// data 속성으로 값(tr 태그에 저장된 값) 확인
			var dataValue = $(this).data("userid"); // 모두 소문자로 치환됨
			console.log("dataValue: " + dataValue);
			
			// input 태그에 값 설정
			$("#userId").val(dataValue);
			
			// form 태그 이용하여 전송
			console.log("serialize: " + $("#frm").serialize());
			
			$("#frm").submit();
		});
		
	});
</script>

</head>

<body id="page-top">
	<!-- 개발자 입장에서 데이터를 전송하기 위하여 사용하는 form -->
	<form id="frm" action="${cp }/postPagingList" method="get">
		<input type="hidden" id="boardSeq" name="boardSeq"/>
	</form>
	
	<!-- nav -->
	<%@ include file="/WEB-INF/views/form/nav.jsp" %>

	<div id="wrapper">

		<!-- Sidebar -->
		<%@ include file="/WEB-INF/views/form/left.jsp"%>

		<div id="content-wrapper">

			<div class="container-fluid">

				<!-- Breadcrumbs-->
				<ol class="breadcrumb">
					<li class="breadcrumb-item"><a href="${cp }/main">JSP</a></li>
					<li class="breadcrumb-item active">${board.boardNm }</li>
				</ol>

				<!-- Page Content -->
				<div class="table-responsive">
					<table class="table">
						<tr>
							<th class="text-center">게시글 번호</th>
							<th >제목</th>
							<th class="text-center">작성자 아이디</th>
							<th class="text-center">작성일시</th>
						</tr>
						
						<c:forEach items="${postList}" var="post">
							<tr>
								<td class="text-center">${post.POSTSEQ}</td>
								<td>
									<!-- 공백주기 -->
									<c:forEach begin="2" end="${post.LEVEL }" var="i" step="1">
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<c:if test="${post.LEVEL == i}">
											<c:choose>
												<c:when test="${post.POSTSTATUS == 1}">
													<i class="fas fa-long-arrow-alt-right"></i>
												</c:when>
												<c:otherwise>
													<i class="fas fa-long-arrow-alt-right" style="color:silver;"></i>
												</c:otherwise>
											</c:choose>
											
										</c:if>
									</c:forEach>
									<!-- 게시글 제목 -->
									<c:choose>
										<c:when test="${post.POSTSTATUS == 1}">
											<a href="${cp }/post?boardSeq=${board.boardSeq }&postSeq=${post.POSTSEQ }" style="color:black;">
												${post.POSTNM}
											</a>
										</c:when>
										<c:when test="${post.POSTSTATUS == 0}">
											<span style="color:silver;">삭제된 게시글입니다.</span>
										</c:when>
									</c:choose>
								</td>
								<td class="text-center">${post.USERID}</td>
								<td class="text-center"><fmt:formatDate value="${post.POSTREGDATE }" pattern="yyyy/MM/dd HH:mm"/></td>
								
							</tr>
						</c:forEach>
						
					</table>
				</div>
												
				<a href="${cp }/insertPost?boardSeq=${boardSeq }" class="btn btn-outline-dark float-right">
					<i class="fas fa-pen"></i> 새글쓰기
				</a>
				
				<br>
				<div class="text-center">
					<ul class="pagination justify-content-center" style="margin:20px 0">
					
						
						<!--   <<   -->
						<c:choose>
							<c:when test="${pageVo.page == 1 || (paginationSize == 0) }">
								<li class="page-item disabled">
									<i class="fas fa-angle-double-left page-link"></i>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" href="${cp }/postPagingList?boardSeq=${boardSeq }&page=1" aria-label="Previous">
										<i class="fas fa-angle-double-left"></i>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
						
						<!--   <   -->
						<c:choose>
							<c:when test="${pageVo.page == 1 }">
								<li class="page-item disabled">
									<i class="fas fa-angle-left page-link"></i>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" href="${cp }/postPagingList?boardSeq=${boardSeq }&page=${pageVo.page - 1}" aria-label="Previous">
										<i class="fas fa-angle-left"></i>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
						
						<!--   page   출력 -->
						<c:forEach begin="1" end="${paginationSize }" var="idx">
							<c:choose>
								<c:when test="${idx == pageVo.page }">
									<li class="page-item active">
										<a class="page-link" href="#">${idx }</a>
									</li>
								</c:when>
								<c:otherwise>
									<li class="page-item">
										<a class="page-link" href="${cp }/postPagingList?boardSeq=${boardSeq }&page=${idx }">${idx }</a>
									</li>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<!--   >   -->
						<c:choose>
							<c:when test="${(pageVo.page == paginationSize) || (paginationSize == 0) }">
								<li class="page-item disabled">
									<i class="fas fa-angle-right page-link"></i>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" href="${cp }/postPagingList?boardSeq=${boardSeq }&page=${pageVo.page + 1}" aria-label="Previous">
										<i class="fas fa-angle-right"></i>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
						
						<!--   >>   -->
						<c:choose>
							<c:when test="${pageVo.page == paginationSize || (paginationSize == 0) }">
								<li class="page-item disabled">
									<i class="fas fa-angle-double-right page-link"></i>
								</li>
							</c:when>
							<c:otherwise>
								<li class="page-item">
									<a class="page-link" href="${cp }/postPagingList?boardSeq=${boardSeq }&page=${paginationSize }" aria-label="Previous">
										<i class="fas fa-angle-double-right"></i>
									</a>
								</li>
							</c:otherwise>
						</c:choose>
					</ul>
				</div>
			</div>
			<!-- /.container-fluid -->

			<!-- Sticky Footer -->
			<%@ include file="/WEB-INF/views/form/footer.jsp" %>

		</div>
		<!-- /.content-wrapper -->

	</div>
	<!-- /#wrapper -->

	<!-- Scroll to Top Button-->
	<a class="scroll-to-top rounded" href="#page-top">
		<i class="fas fa-angle-up"></i>
	</a>

	<!-- Logout Modal-->
	<%@ include file="/WEB-INF/views/form/modal.jsp" %>
	
	<!-- Script -->
	<%@ include file="/WEB-INF/views/commonJsp/scriptLib.jsp" %>
</body>
</html>
