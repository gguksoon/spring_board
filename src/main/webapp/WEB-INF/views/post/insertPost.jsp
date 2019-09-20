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
<script src="${cp }/SE2/js/HuskyEZCreator.js"></script>
<script type="text/javascript">
var oEditors = []; // 개발되어 있는 소스에 맞추느라, 전역변수로 사용하였지만, 지역변수로 사용해도 전혀 무관 함.
$(document).ready(function() {
	// Editor Setting
	nhn.husky.EZCreator.createInIFrame({
		oAppRef : oEditors, // 전역변수 명과 동일해야 함.
		elPlaceHolder : "smarteditor", // 에디터가 그려질 textarea ID 값과 동일 해야 함.
		sSkinURI : "${cp }/SE2/SmartEditor2Skin.html", // Editor HTML
		fCreator : "createSEditor2", // SE2BasicCreator.js 메소드명이니 변경 금지 X
		htParams : {
			// 툴바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseToolbar : true,
			// 입력창 크기 조절바 사용 여부 (true:사용/ false:사용하지 않음)
			bUseVerticalResizer : true,
			// 모드 탭(Editor | HTML | TEXT) 사용 여부 (true:사용/ false:사용하지 않음)
			bUseModeChanger : true, 
		}
	});

	// 전송버튼 클릭이벤트
	$("#insertBtn").click(function(){
		if(confirm("작성하시겠습니까?")) {
			// id가 smarteditor인 textarea에 에디터에서 대입
			oEditors.getById["smarteditor"].exec("UPDATE_CONTENTS_FIELD", []);

			// 이부분에 에디터 validation 검증
			if(!validation()) return;
			
			$("#frm").submit();
		}
	});
	
	$("#files").on("change", function() {
		console.log("length: " + this.files.length);
		if(this.files.length > 5) { // 파일의 갯수가 5개 초과일 때
			alert("파일의 갯수는 5개를 초과할 수 없습니다.");
			$(this).val("");
		}
	});
		
});

//필수값 Check
function validation(){
	var contents = $.trim(oEditors[0].getContents());
	if(contents === '<p>&nbsp;</p>' || contents === ''){ // 기본적으로 아무것도 입력하지 않아도 <p>&nbsp;</p> 값이 입력되어 있음. 
		alert("내용을 입력하세요.");
		oEditors.getById['smarteditor'].exec('FOCUS');
		return false;
	}

	return true;
}
</script>

</head>

<body id="page-top" class="se2_inputarea">
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
					<li class="breadcrumb-item active">새글쓰기</li>
				</ol>

				<!-- Page Content -->
				<form class="form-inline" action="${cp }/insertPost" method="post" id="frm" enctype="multipart/form-data">
					<input type="hidden" name="boardSeq" value="${board.boardSeq }"/>
					<input type="hidden" name="postSeq" value="${postSeq }"/>
					<input type="hidden" name="postGn" value="${postGn }"/>
					<input type="hidden" name="userId" value="${S_USERVO.userId }"/>
					<br>	
					<div class="form-group">
						<label for="postNm" style="width: 48px;">제목: </label>
						<input name="postNm" class="form-control" required style="width: 750px;"/>
					</div>
					
					<br><br><br>
					<textarea name="postContent" id="smarteditor" rows="10" cols="100" class="col-sm-10" style="width:800px; height:500px;"></textarea>
					
					<div class="form-group">
						<br><br><br>
						<label for="files" class="col-sm-3 control-label">첨부파일: </label>
						<div class="col-sm-9">
							<input type="file" multiple="multiple" class="form-control-file border" id="files" name="files" placeholder="첨부파일">
						</div>
					</div>
					
					<div style="width: 800px; margin-top: 10px;">
						<a href="${cp }/postPagingList?boardSeq=${board.boardSeq }" class="btn btn-dark float-right" style="margin-left: 5px;">취소</a> 
						<button type="button" id="insertBtn" class="btn btn-dark float-right">작성</button>
					</div>
				</form>
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