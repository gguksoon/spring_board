<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
	<title>Login V14</title>
	<meta charset="UTF-8">
	<meta name="viewport" content="width=device-width, initial-scale=1">
<!--===============================================================================================-->	
	<link rel="icon" type="image/png" href="${cp }/login/images/icons/favicon.ico"/>
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${cp }/login/vendor/bootstrap/css/bootstrap.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${cp }/login/fonts/font-awesome-4.7.0/css/font-awesome.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${cp }/login/fonts/Linearicons-Free-v1.0.0/icon-font.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${cp }/login/vendor/animate/animate.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${cp }/login/vendor/css-hamburgers/hamburgers.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${cp }/login/vendor/animsition/css/animsition.min.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${cp }/login/vendor/select2/select2.min.css">
<!--===============================================================================================-->	
	<link rel="stylesheet" type="text/css" href="${cp }/login/vendor/daterangepicker/daterangepicker.css">
<!--===============================================================================================-->
	<link rel="stylesheet" type="text/css" href="${cp }/login/css/util.css">
	<link rel="stylesheet" type="text/css" href="${cp }/login/css/main.css">
<!--===============================================================================================-->

<script src="${cp }/js/js.cookie.js"></script> <!-- cookie -->
<script src="${cp }/js/jquery-3.4.1.min.js"></script> <!-- jquery -->

<script>
	$(function() {
		
		// 쿠키에 있는 userId를 불러온다.
		var userId = Cookies.get('userId');
		if(userId != null) {
			$("#userId").val(userId);
			$("#ckb1").prop('checked', true);
			$('#pass').focus();
		}
		
		// signin btn 클릭 이벤트 핸들러
		$('#signinBtn').on('click', function() {
			// remember me check box가 체크가 되었는가?

			// 체크 되었으면
			if($('#ckb1').prop('checked')) {
				// userId 쿠키를 생성하고 값은 userId input의 값을 쿠기 값으로 설정
				Cookies.set("userId", $("#userId").val(), {expires : 30});
			// 체크 안되었으면
			} else {
				// 기존에 사용자가 아이디를 쿠키에 저장하는 기능을 사용하다가 더 이상 사용하지 않는 경우
				// 처음부터 아이디 쿠키 저장 기능을 사용하지 않는 경우
				// ==> userId 쿠키를 삭제
				Cookies.remove("userId");
			}
			
			// 로그인 요청(현재 button으로 되있으므로 submit해준다.)
			$("#frm").submit();
		})
		
		$("#userId, #pass").keydown(function(key) {
			if (key.keyCode == 13) {
				$("#frm").submit();
			}
		});
		
	});
</script>

</head>
<body>
	
	<div class="limiter">
		<div class="container-login100">
			<div class="wrap-login100 p-l-85 p-r-85 p-t-55 p-b-55">
				<form class="login100-form validate-form flex-sb flex-w" id="frm" action="${cp }/login" method="post">
					<span class="login100-form-title p-b-32">
						Account Login
					</span>

					<span class="txt1 p-b-11">
						UserId
					</span>
					<div class="wrap-input100 validate-input m-b-36" data-validate = "UserId is required">
						<input class="input100" type="text" name="userId" id="userId">
						<span class="focus-input100"></span>
					</div>
					
					<span class="txt1 p-b-11">
						Password
					</span>
					<div class="wrap-input100 validate-input m-b-12" data-validate = "Password is required">
						<span class="btn-show-pass">
							<i class="fa fa-eye"></i>
						</span>
						<input class="input100" type="password" name="pass" id="pass">
						<span class="focus-input100"></span>
					</div>
					
					<div class="flex-sb-m w-full p-b-48">
						<div class="contact100-form-checkbox">
							<input class="input-checkbox100" id="ckb1" type="checkbox" name="rememberMe">
							<label class="label-checkbox100" for="ckb1">
								Remember me
							</label>
						</div>

						<div>
							<a href="#" class="txt3">
								Forgot Password?
							</a>
						</div>
					</div>

					<div class="container-login100-form-btn">
						<button type="button" class="login100-form-btn" id="signinBtn">
							Login
						</button>
					</div>

				</form>
			</div>
		</div>
	</div>
	

	<div id="dropDownSelect1"></div>
	
<!--===============================================================================================-->
	<script src="${cp }/login/vendor/jquery/jquery-3.2.1.min.js"></script>
<!--===============================================================================================-->
	<script src="${cp }/login/vendor/animsition/js/animsition.min.js"></script>
<!--===============================================================================================-->
	<script src="${cp }/login/vendor/bootstrap/js/popper.js"></script>
	<script src="${cp }/login/vendor/bootstrap/js/bootstrap.min.js"></script>
<!--===============================================================================================-->
	<script src="${cp }/login/vendor/select2/select2.min.js"></script>
<!--===============================================================================================-->
	<script src="${cp }/login/vendor/daterangepicker/moment.min.js"></script>
	<script src="${cp }/login/vendor/daterangepicker/daterangepicker.js"></script>
<!--===============================================================================================-->
	<script src="${cp }/login/vendor/countdowntime/countdowntime.js"></script>
<!--===============================================================================================-->
	<script src="${cp }/login/js/main.js"></script>

</body>
</html>