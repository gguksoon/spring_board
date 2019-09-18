<%@page import="kr.or.ddit.user.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

<script src="${cp }/js/jquery-3.4.1.min.js"></script>
<script>
	$(function() {
		$(".btnUp").first().attr("disabled", "disabled");
		$(".btnDown").last().attr("disabled", "disabled");
		
		$(".btnUp").on("click", function() {
			var pForm = $(this).parent($("form"));
			var location = $(pForm).children(".boardLocation").val();
			$(pForm).children(".change").val("up");
			
			if(location != 1) {
				$(pForm).attr("action", "${cp }/locationChangeBoard");
				$(pForm).submit();
			}
		});
		
		$(".btnDown").on("click", function() {
			var pForm = $(this).parent($("form"));
			var location = $(pForm).children(".boardLocation").val();
			var lastLocation = pForm.parent().children(".boardForm").last().children(".boardLocation").val();
			$(pForm).children(".change").val("down");
			//
			console.log(location); 
			console.log(lastLocation); 
			//
			if(location != lastLocation) {
				$(pForm).attr("action", "${cp }/locationChangeBoard");
				$(pForm).submit();
			}
		});
		
		$(".updateBtn").on("click", function() {
			var pForm = $(this).parent($("form"));
			$(pForm).attr("action", "${cp }/updateBoard");
			$(pForm).submit();
		});
		
	});
</script>

</head>

<body id="page-top">

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
					<li class="breadcrumb-item active">게시판 관리</li>
				</ol>

				<!-- Page Content -->
				<h2 class="sub-header"><i class="fas fa-wrench"></i> 게시판 관리</h2>
				<c:forEach items="${boardList}" var="board">
					<form class="form-inline boardForm" method="post">
						<input type="hidden" class="boardSeq" name="boardSeq" value="${board.boardSeq }"/>
						<input type="hidden" class="userId" name="userId" value="${board.userId }"/>
						<input type="hidden" class="boardLocation" name="boardLocation" value="${board.boardLocation }"/>
						<input type="hidden" class="change" name="change"/>

						<button type="button" class="btn btn-outline-dark btnUp">
       						<i class="fas fa-angle-up"></i>
     					</button>
						<button type="button" class="btn btn-outline-dark btnDown">
       						<i class="fas fa-angle-down"></i>
     					</button>
						
						<input type="text" class="form-control" name="boardNm" value="${board.boardNm }"/>
						
						<c:choose>
								<c:when test="${board.boardStatus == 0}"> <%-- 미사용 --%>
									<select name="boardStatus" class="form-control">
								        <option>사용</option>
								        <option selected>미사용</option>
								    </select>
								</c:when>
								<c:otherwise>	<%-- 사용 --%>
									<select name="boardStatus" class="form-control">
								        <option selected>사용</option>
								        <option>미사용</option>
								    </select>
								</c:otherwise>
						</c:choose>
						
						<button type="button" class="btn btn-dark updateBtn">수정</button>
     					<br><br>
					</form>
				</c:forEach>
				
				<br><br><br>
				
				<h2 class="sub-header"><i class="fas fa-wrench"></i> 게시판 추가</h2>
				<form class="form-inline" method="post" action="${cp }/insertBoard">
					<input type="text" class="form-control" name="boardNm" placeholder="게시판 이름"/>
					<input type="hidden" name="userId" value="${S_USERVO.userId }"/>
					<select name="boardStatus" class="form-control">
						<option selected>사용</option>
						<option>미사용</option>
					</select>
					<button type="submit" class="btn btn-dark insertBtn">추가</button>
				</form>
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
