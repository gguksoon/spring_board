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
	.replyList {
 		margin: 15px;
	}
	
	.replyList .stat0 {
		color: silver;
		background-color: #F2F2F2;
		padding: 20px;
	}
	
	.replyList .stat1 {
		background-color: #F2F2F2;
		padding: 20px;
	}
	
	.btnGroup {
		margin-right: 10px;
	}
</style>

<script src="${cp }/js/jquery-3.4.1.min.js"></script>
<script>
	$(function() {
		$(".btnDeleteReply").on("click", function() {
			var result = confirm("정말로 삭제하시겠습니까?");
			
			if(result) {
				$(this).parent().parent().submit();
			}
		});
		
		$("#btnInsertRePost").on("click", function() {
 			$("#insertRePost").submit();
		});
		
		$("#btnDeletePost").on("click", function() {
			var result = confirm("정말로 삭제하시겠습니까?");
			
			if(result) {
				$("#deletePost").submit();
			}
		});
		
		$("#btnUpdatePost").on("click", function() {
 			$("#updatePost").submit();
		});
		
		// 댓글 글자 제한
		$("textarea").keyup(function() {
			var len = $(this).val().length;
			if(len >= 500) {
				$(this).val($(this).val().slice(0, 500));
				alert("댓글은 500자를 넘길 수 없습니다.")
			}
		})
	});
</script>

</head>

<body id="">
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
					<li class="breadcrumb-item"><a href="${cp }/main">SPRING</a></li>
					<li class="breadcrumb-item"><a href="${cp }/postPagingList?boardSeq=${board.boardSeq }">${board.boardNm }</a></li>
					<li class="breadcrumb-item active">${post.postNm }</li>
				</ol>

				<!-- Page Content -->
				<div class="col-sm-10 blog-main">
					<!-- 제목 -->
					<h1>${post.postNm }</h1>
					작성자: ${post.userId } | 작성일: <fmt:formatDate value="${post.postRegDate }" pattern="yyyy/MM/dd HH:mm"/>
					<hr>
					<!-- 내용 -->
					${post.postContent }
					<hr> 
					<!-- 첨부파일 -->
					첨부파일:<br>
					<c:forEach items="${fileList }" var="file">
				         <a href="${cp }/fileDownloadView?fileSeq=${file.fileSeq }" class="btn btn-dark btn-sm">
      							<i class="fas fa-download"></i> Download
      						</a>
						${file.fileName }<br>
					</c:forEach>
					<hr> 
					<!-- 댓글 내용 -->
					<div class="replyList"> 
						<c:forEach items="${replyList }" var="reply">
							<c:choose>
								<c:when test="${reply.replyStatus == 1 }">
									<form class="deleteReply" action="${cp }/deleteReply" method="post">
										<p class="stat1">
											${reply.replyContent } [ ${reply.userId } | <fmt:formatDate value="${reply.replyRegDate }" pattern="yyyy/MM/dd HH:mm"/> ]
											<c:if test="${reply.userId == S_USERVO.userId }">
												<input type="hidden" name="postSeq" value="${post.postSeq }"/>
												<input type="hidden" name="boardSeq" value="${post.boardSeq }"/>
												<input type="hidden" name="replySeq" value="${reply.replySeq }" />
												<button type="button" class="btnDeleteReply btn btn-xs">
													<i class="fas fa-trash-alt"></i>
												</button>
											</c:if>
										</p>
									</form>
								</c:when>
								<c:otherwise>
									<p class="stat0">삭제된 댓글입니다.</p>
								</c:otherwise>
							</c:choose>
						</c:forEach>
					</div>

					<!-- 댓글 입력창 -->
					<div class="form-group repArea">
						<form class="form-inline" method="post" action="${cp }/insertReply">
							<input type="hidden" name="boardSeq" value="${board.boardSeq }"/>
							<input type="hidden" name="postSeq" value="${post.postSeq }"/>
							<input type="hidden" name="userId" value="${S_USERVO.userId }"/>
							<span class="col-sm-10">
								<textarea class="form-control" rows="4" name="replyContent" id="replyContent" placeholder="댓글을 입력하세요." style="resize: none; width: 100%;"></textarea>
							</span>
							<span class="col-sm-2">
								<button type="submit" class="btn btn-dark btn-block" style="height: 110px;">입력</button>
							</span>
						</form>
					</div>
					<hr>
					<form id="updatePost" method="get" action="${cp }/updatePost">
						<input type="hidden" name="postSeq" value="${post.postSeq }">
						<input type="hidden" name="boardSeq" value="${post.boardSeq }">
					</form>
					
					<form id="deletePost" method="post" action="${cp }/deletePost">
						<input type="hidden" name="postSeq" value="${post.postSeq }">
						<input type="hidden" name="boardSeq" value="${post.boardSeq }">
					</form>
					
					<form id="insertRePost" method="get" action="${cp }/insertPost">
						<input type="hidden" name="postSeq" value="${post.postSeq }">
						<input type="hidden" name="postGn" value="${post.postGn }">
						<input type="hidden" name="boardSeq" value="${post.boardSeq }">
					</form>
					
					<button type="button" id="btnInsertRePost" class="btn btn-outline-dark float-right btnGroup"><span class="glyphicon glyphicon-pencil"></span> 답글</button>
					<c:if test="${post.userId == S_USERVO.userId}">
						<button type="button" id="btnDeletePost" class="btn btn-outline-dark float-right btnGroup"><span class="glyphicon glyphicon-remove"></span> 삭제</button>
						<button type="button" id="btnUpdatePost" class="btn btn-outline-dark float-right btnGroup"><span class="glyphicon glyphicon-wrench"></span> 수정</button>
					</c:if>
					
				</div>
				<!-- ------------------------------------------------------------------------ -->

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
