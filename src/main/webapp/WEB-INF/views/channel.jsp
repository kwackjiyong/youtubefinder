<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page session="false"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">

<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>채널검색은 YouTubeFinder</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="<c:url value='/resources/template/vendors/mdi/css/materialdesignicons.min.css'/>">
<link rel="stylesheet"
	href="<c:url value='/resources/template/vendors/base/vendor.bundle.base.css'/>">
<!-- endinject -->
<link rel="stylesheet"
	href="<c:url value='/resources/template/vendors/datatables.net-bs4/dataTables.bootstrap4.css'/>">
<!-- inject:css -->
<link rel="stylesheet"
	href="<c:url value='/resources/template/css/style.css'/>">
<!-- endinject -->
<link rel="shortcut icon"
	href="<c:url value='/resources/template/images/YF_logo_small.png'/>" />
<!-- DatetimePicker -->
<link rel="stylesheet" type="text/css"
	href="<c:url value='/resources/datetime/jquery.datetimepicker.css'/>" />


</head>

<body>
	<div class="container-scroller">
		<!-- partial:../../partials/_navbar.html -->

		<nav class="navbar col-lg-12 col-12 p-0 fixed-top d-flex flex-row">
			<div class="navbar-brand-wrapper d-flex justify-content-center">
				<div
					class="navbar-brand-inner-wrapper d-flex justify-content-between align-items-center w-100">
					<a class="navbar-brand brand-logo" href="index.do"><img
						src="<c:url value='/resources/template/images/YF_logo_big.png'/>"
						alt="logo" /></a> <a class="navbar-brand brand-logo-mini"
						href="index.do"><img
						src="<c:url value='/resources/template/images/YF_logo_small.png'/>"
						alt="logo" /></a>
					<button class="navbar-toggler navbar-toggler align-self-center"
						type="button" data-toggle="minimize">
						<span class="mdi mdi-sort-variant"></span>
					</button>
				</div>
			</div>
			<div
				class="navbar-menu-wrapper d-flex align-items-center justify-content-end">
				<ul class="navbar-nav mr-lg-4 w-100">
					<li class="nav-item nav-search d-none d-lg-block w-100">
						<div class="input-group">
							<div class="input-group-prepend">
								<span class="input-group-text" id="search"> <i
									class="mdi mdi-magnify"></i>
								</span>
							</div>
							<input type="text" class="form-control" placeholder="Search now"
								aria-label="search" aria-describedby="search">
						</div>
					</li>
				</ul>
				<ul class="navbar-nav navbar-nav-right">


					<li class="nav-item nav-profile dropdown"><a
						class="nav-link dropdown-toggle" href="#" data-toggle="dropdown"
						id="profileDropdown"> <c:choose>
								<c:when test="${userData.getUserClass() == 0}">
									<span class="nav-profile-name text-primary"><i class="mdi mdi-face"></i>${userData.getUserName()}님 로그인중</span>
								</c:when>
								<c:when test="${userData.getUserClass() == 1}">
									<span class="nav-profile-name text-primary">[ Admin <i class="mdi mdi-sunglasses"></i>] ${userData.getUserName()}님 로그인중</span>
								</c:when>

								<c:otherwise>
									<span class="nav-profile-name">Log Out</span>
								</c:otherwise>
							</c:choose>

					</a>
						<div class="dropdown-menu dropdown-menu-right navbar-dropdown"
							aria-labelledby="profileDropdown">
							<c:choose>
								<c:when
									test="${userData.getUserClass() == 0 ||userData.getUserClass() == 1}">
									<a class="dropdown-item" href="#modifyDialog"
										data-toggle="modal"> <i
										class="mdi mdi-settings text-primary"></i> Modify
									</a>
									<a class="dropdown-item" href="logout.ing"> <i
										class="mdi mdi-logout text-primary"></i> Logout
									</a>
								</c:when>

								<c:otherwise>


									<a class="dropdown-item" href="#signupDialog"
										data-toggle="modal"> <i
										class="mdi mdi-account-plus text-primary"></i> Sign Up
									</a>
									<a class="dropdown-item" data-toggle="modal"
										href="#loginDialog"> <i class="mdi mdi-login text-primary"></i>
										Log In
									</a>
								</c:otherwise>
							</c:choose>

						</div></li>
				</ul>
				<button
					class="navbar-toggler navbar-toggler-right d-lg-none align-self-center"
					type="button" data-toggle="offcanvas">
					<span class="mdi mdi-menu"></span>
				</button>
			</div>
		</nav>

		<!-- partial -->
		<!-- 왼쪽 사이드바 ********************************************-->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:../../partials/_sidebar.html -->
			<nav class="sidebar sidebar-offcanvas" id="sidebar">
				<ul class="nav">
					<li class="nav-item"><a class="nav-link" href="posting.do">
							<i class="mdi mdi-bullhorn menu-icon"></i> <span
							class="menu-title">공지사항</span>
					</a></li>
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#channel" aria-expanded="false"
						aria-controls="channel"> <i
							class="mdi mdi-television-guide menu-icon"></i> <span
							class="menu-title">채널</span> <i class="menu-arrow"></i>
					</a>
						<div class="collapse" id="channel">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link"
									href="channel.do">신규 채널보기</a></li>
								<li class="nav-item"><a class="nav-link"
									href="channel_my.do">선호 채널 보기</a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link"
						data-toggle="collapse" href="#tag" aria-expanded="false"
						aria-controls="tag"> <i class="mdi mdi-tag menu-icon"></i> <span
							class="menu-title">태그</span> <i class="menu-arrow"></i>
					</a>
						<div class="collapse" id="tag">
							<ul class="nav flex-column sub-menu">
								<li class="nav-item"><a class="nav-link" href="tag_my.do">선호
										태그 관리</a></li>
								<li class="nav-item"><a class="nav-link" href="tag_my.do">내가
										단 태그 보기</a></li>
							</ul>
						</div></li>
					<li class="nav-item"><a class="nav-link" href="mypage.do">
							<i class="mdi mdi-account menu-icon"></i> <span
							class="menu-title">사용자 관리</span>
					</a></li>

				</ul>
			</nav>
			<!-- partial -->
			<div class="main-panel">
				<!-- 여기부터 바디 부분 ************************************************-->

				<div class="content-wrapper">
					<!--  -->

					<!-- 2번쨰 줄 시작 -->
					<div class="row">
						<!-- 공지사항 시작 -->
						<div class="col-md-12 stretch-card">
							<div class="card">
								<div class="card-body">
									<p class="card-title">채널 [신규채널순]</p>
									<p class="card-description">채널에 태그를 달아보세요<br>채널의 속성은 당신이 결정합니다</p>
									<div class="table-responsive">
												<div class="row">
												<c:forEach items="${list}" var="list">
												<div class="col-md-4 stretch-card" style="padding-bottom: 20px;">
												<div class="card">
												<div class="card-body">
												<p class="card-title"><a href="#" onclick="javascript:window.open('https://www.youtube.com/results?search_query=${list.channelName}');" >${list.channelName}</a> <i class="mdi mdi-account-circle"></i></p>
												<div class="table-responsive">
														<p>${list.channelExp}</p>
												</div>
												
												<table class="table">
												<tr>
												<td>
												<button class="btn btn-danger btn-rounded btn-sm" onclick="javascript:location.href='channelLike.ing?channelId=${list.channelId}&like_whether=1'">
												<i class="mdi mdi-heart"></i>${list.likeCnt}
														</button>
												</td>
												<td>
												<button class="btn btn-secondary btn-rounded btn-sm" onclick="javascript:location.href='channelLike.ing?channelId=${list.channelId}&like_whether=0'">
												<i class="mdi mdi-heart-broken"></i>${list.dislikeCnt}
												</button>
												</td>
												<td>
												<button class="btn btn-primary btn-rounded btn-sm nav-link dropdown-toggle d-flex align-items-center justify-content-center" id="notificationDropdown" data-toggle="dropdown">
              									<i class="mdi mdi-slack"></i>
            									</button>
            									<div class="dropdown-menu dropdown-menu-right navbar-dropdown" aria-labelledby="tagDropdown">
              									<div class="dropdown-item bg-primary">
                									<div class="item-thumbnail">
                  										<div class="item-icon bg-primary"style="padding: 10px;">
                    										${list.channelName} <i class="mdi mdi-slack mdi-light"></i>
                  										</div>
                									</div>
                									<div class="item-content" style="padding-bottom:20px;">
                										<form action="channelTag.ing">
                										<input type="hidden" name="channelId"value="${list.channelId}">
                  										<input type="text" name="tagName" class="form-control" placeholder="태그 달고 엔터해 빨리!">
                  										</form>
                									</div>
              									</div>
												</div>
												</td>
												</tr>
												</table>
												</div>
												<div class="card-footer">
													<p class="card-description">등록일 : ${list.registerDate}</p>
												</div>
												
												
												
												</div>
												</div>
												</c:forEach>
												</div>										
										<br>
										<br>
										<br>
										<table class="table"><tr><td></td><td>
										<div class="btn-group" role="group"
															aria-label="Basic example">
															<c:if test="${1 != listPageNum}">
															<button type="button" class="btn btn-primary btn-sm" onclick="javascript:location.href='channel.do?pageNum=1';"><i class="mdi mdi-page-first"></i></button>
															<button type="button" class="btn btn-primary btn-sm" onclick="javascript:location.href='channel.do?pageNum=${listPageNum-1}';"><i class="mdi mdi-chevron-left"></i></button>
															</c:if>
															<c:forEach items="${listPageNumList}" var="page">
																<button type="button" class="btn btn-outline-primary btn-sm" onclick="javascript:location.href='channel.do?pageNum=${page}';">
																	<c:choose>
																		<c:when test="${page == listPageNum}">
																		<i class="mdi mdi-tooltip-text"></i>	
																		</c:when>
																		<c:otherwise>
																		${page}
																		</c:otherwise>
																	</c:choose>
																</button>
																
															</c:forEach>
															<c:if test="${listPageNumList_lastNum != listPageNum}">
															<button type="button" class="btn btn-primary btn-sm" onclick="javascript:location.href='channel.do?pageNum=${listPageNum+1}';"><i class="mdi mdi-chevron-right"></i></button>
															<button type="button" class="btn btn-primary btn-sm" onclick="javascript:location.href='channel.do?pageNum=${listPageNumList_lastNum}';"><i class="mdi mdi-page-last"></i></button>
															</c:if>
										</div>
										</td>
										
										</tr>
										</table>
									</div>
								</div>
							</div>
						</div>
												<!-- 공지사항 끝 -->



					</div>


					<!-- 여기까지가 바디의 끝  -->
				</div>
				<!-- content-wrapper ends -->
				<!-- partial:../../partials/_footer.html -->



				<footer class="footer">
					<div
						class="d-sm-flex justify-content-center justify-content-sm-between">
						<span
							class="text-muted text-center text-sm-left d-block d-sm-inline-block">Copyright
							© 2019 <a href="https://www.urbanui.com/" target="_blank">JiYong.K</a>.
							All rights reserved.
						</span> <span
							class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted
							& made with <i class="mdi mdi-heart text-danger"></i>
						</span>
					</div>
				</footer>



				<!-- partial -->




			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->




	</div>
	<!-- container-scroller -->




	<!-- 로그인 다이얼로그 창 -->
	<div class="modal fade" id="loginDialog" tabindex="-1" role="dialog"
		aria-labelledby="loginDialogLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="loginDialogLabel">Log In</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="login.ing" method="post">

						<table class="table">
							<tr>
								<td>사용자 ID</td>
								<td><input type="text" name="userId" class="form-control"
									placeholder="ID를 입력" /></td>
							</tr>
							<tr>
								<td>PassWord</td>
								<td><input type="password" name="userPw"
									class="form-control" placeholder="PassWord를 입력" /></td>
							</tr>
						</table>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">닫기</button>
							<button class="btn btn-primary" type="submit">로그인</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 다이얼로그 -->
	<!-- 회원가입 다이얼로그 창 -->
	<div class="modal fade" id="signupDialog" tabindex="-1" role="dialog"
		aria-labelledby="signupDialogLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="signupDialogLabel">SignUp</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="signup.ing" method="post">

						<table class="table">
							<tr>
								<td>사용자 ID</td>
								<td><input type="text" name="userId" class="form-control"
									placeholder="사용할 ID를 입력" /></td>
							</tr>
							<tr>
								<td>사용자 이름</td>
								<td><input type="text" name="userName" class="form-control"
									placeholder="사용할 이름을 입력" /></td>
							</tr>
							<tr>
								<td>PassWord</td>
								<td><input type="password" name="userPw"
									class="form-control" placeholder="사용할 PassWord를 입력" /></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><input type="email" name="userEmail"
									class="form-control" placeholder="사용할 Email을 입력" /></td>
							</tr>

							<tr>
								<td colspan="2">성별</td>
							<tr>
								<td>
									<div class="form-check">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="userGender"
											id="optionsRadios2" value="1" checked> 여성
										</label>
									</div>
								</td>

								<td>
									<div class="form-check">
										<label class="form-check-label"> <input type="radio"
											class="form-check-input" name="userGender"
											id="optionsRadios2" value="2"> 남성
										</label>
									</div>
									</div>
								</td>
							</tr>
							<tr>
								<td>생년월일</td>
								<td><input type="text" name="userBirth"
									class="form-control" id="userBirth" placeholder="이곳을 클릭해서 입력" /></td>
							</tr>

						</table>



						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">닫기</button>
							<button class="btn btn-primary" type="submit">회원가입</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 다이얼로그 -->
	<!-- 다이얼로그 -->
	<!-- 회원수정 다이얼로그 창 -->
	<div class="modal fade" id="modifyDialog" tabindex="-1" role="dialog"
		aria-labelledby="modifyDialogLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<h5 class="modal-title" id="modifyDialogLabel">Modify</h5>
					<button class="close" type="button" data-dismiss="modal"
						aria-label="Close">
						<span aria-hidden="true">X</span>
					</button>
				</div>
				<div class="modal-body">
					<form action="userModify.ing" method="post">

						<table class="table">
							<tr>
								<td>사용자 ID</td>
								<td><input type="text" name="userId" class="form-control"
									value="${userData.getUserId()}" readonly /></td>
							</tr>
							<tr>
								<td>사용자 이름</td>
								<td><input type="text" name="userName" class="form-control"
									placeholder="사용할 이름을 입력" value="${userData.getUserName()}" /></td>
							</tr>
							<tr>
								<td>기존 PassWord</td>
								<td><input type="password" name="userPw"
									class="form-control" placeholder="사용중인 PassWord를 입력" /></td>
							</tr>
							<tr>
								<td>변경 PassWord</td>
								<td><input type="password" name="userPw2"
									class="form-control" placeholder="사용변경할  PassWord를 입력" /></td>
							</tr>
							<tr>
								<td>Email</td>
								<td><input type="email" name="userEmail"
									class="form-control" placeholder="사용할 Email을 입력"
									value="${userData.getUserEmail()}" /></td>
							</tr>

							<tr>
								<td colspan="2">성별</td>
							<tr>
								<c:choose>
									<c:when test="${userData.getUserGender() == 1}">
										<td>
											<div class="form-check">
												<label class="form-check-label"> <input type="radio"
													class="form-check-input" name="userGender"
													id="optionsRadios2" value="1" checked> 여성
												</label>
											</div>
										</td>

										<td>
											<div class="form-check">
												<label class="form-check-label"> <input type="radio"
													class="form-check-input" name="userGender"
													id="optionsRadios2" value="2"> 남성
												</label>
											</div>
										</td>
									</c:when>

									<c:when test="${userData.getUserGender() == 2}">
										<td>
											<div class="form-check">
												<label class="form-check-label"> <input type="radio"
													class="form-check-input" name="userGender"
													id="optionsRadios2" value="1"> 여성
												</label>
											</div>
										</td>

										<td>
											<div class="form-check">
												<label class="form-check-label"> <input type="radio"
													class="form-check-input" name="userGender"
													id="optionsRadios2" value="2" checked> 남성
												</label>
											</div>
										</td>
									</c:when>

								</c:choose>

							</tr>
							<tr>
								<td>생년월일</td>
								<td><input type="text" name="userBirth"
									class="form-control" id="userBirth" placeholder="이곳을 클릭해서 입력"
									value="${userData.getUserBirth()}" /></td>
							</tr>
						</table>
						<div class="modal-footer">
							<button class="btn btn-secondary" type="button"
								data-dismiss="modal">닫기</button>
							<button class="btn btn-primary" type="submit">정보수정</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
	<!-- 다이얼로그 -->
	
	


	<!-- plugins:js -->
	<script
		src="<c:url value='/resources/template/vendors/base/vendor.bundle.base.js'/>"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<script
		src="<c:url value='/resources/template/vendors/chart.js/Chart.min.js'/>"></script>
	<script
		src="<c:url value='/resources/template/vendors/datatables.net/jquery.dataTables.js'/>"></script>
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script src="<c:url value='/resources/template/js/off-canvas.js'/>"></script>
	<script
		src="<c:url value='/resources/template/js/hoverable-collapse.js'/>"></script>
	<script src="<c:url value='/resources/template/js/template.js'/>"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script src="<c:url value='/resources/template/js/chart.js'/>"></script>
	<!-- DateTimePicker -->
	<script src="<c:url value='/resources/datetime/jquery.js'/>"></script>
	<script
		src="<c:url value='/resources/datetime/build/jquery.datetimepicker.full.js'/>"></script>

	<script>
		jQuery(document).ready(
				function() {
					jQuery('#userBirth,#userBirth2').datetimepicker(
							{
								i18n : {
									de : {
										months : [ 'Januar', 'Februar', 'März',
												'April', 'Mai', 'Juni', 'Juli',
												'August', 'September',
												'Oktober', 'November',
												'Dezember', ],
										dayOfWeek : [ "So.", "Mo", "Di", "Mi",
												"Do", "Fr", "Sa.", ]
									}
								},
								timepicker : false,
								format : 'Y-m-d'
							});
				});
	</script>
	<!-- End custom js for this page-->
</body>

</html>
